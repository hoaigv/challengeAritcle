package com.challenge.aritcle.authentication.service.impl;

import com.challenge.aritcle.authentication.controllers.dto.*;
import com.challenge.aritcle.authentication.service.IAuthenticationService;
import com.challenge.aritcle.common.api.ApiResponse;
import com.challenge.aritcle.exceptionHanller.CustomRunTimeException;
import com.challenge.aritcle.exceptionHanller.ErrorCode;
import com.challenge.aritcle.users.models.UserEntity;
import com.challenge.aritcle.users.repository.UserRepository;
import com.challenge.aritcle.utils.AuthUtils;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService  implements IAuthenticationService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
    @NonFinal
    @Value("${jwt.signerKeyAccess}")
    protected String SIGNER_KEY_ACCESS_KEY;

    @NonFinal
    @Value("${jwt.signerKeyRefresh}")
    protected String SIGNER_KEY_REFRESH_KEY;


    @NonFinal
    @Value("${jwt.valid-duration}")
    protected String VALID_DURATION;

    @NonFinal
    @Value("${jwt.refreshable-duration}")
    protected String REFRESHABLE_DURATION;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        var user = userRepository.findByUsername(authenticationRequest.getUsername())
                .orElseThrow(() -> new CustomRunTimeException(ErrorCode.USER_NOT_FOUND));
        if (!user.getPassword().equals(authenticationRequest.getPassword())) {
            throw new CustomRunTimeException(ErrorCode.PASSWORD_INCORRECT);
        }
        var accessToken = generateToken(user,"access_token");
        var refreshToken = generateToken(user,"refresh_token");
        user.setRefreshToken(passwordEncoder.encode(refreshToken));
        userRepository.save(user);
        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public ApiResponse<Void> logout() {
     var user = userRepository.findByUsername(AuthUtils.getUserCurrent())
             .orElseThrow(() -> new CustomRunTimeException(ErrorCode.USER_NOT_FOUND));
     user.setRefreshToken(null);
     userRepository.save(user);
        return ApiResponse.<Void>builder()
                .message("Successfully logged out")
                .build();
    }

    @Override
    public IntrospectResponse introspect(IntrospectRequest introspectRequest) {

        var token = introspectRequest.getAccessToken();
        boolean isValid = true;
        try {
            verifyToken(token ,false);
        } catch (CustomRunTimeException e) {
            isValid = false;
        }
        return IntrospectResponse.builder()
                .valid(isValid)
                .build();
    }
    private SignedJWT verifyToken(String token , boolean isRefresh) {
        JWSVerifier verifier ;

        try {
           if(isRefresh){
               verifier = new MACVerifier(SIGNER_KEY_REFRESH_KEY);
           }
           else {
               verifier = new MACVerifier(SIGNER_KEY_ACCESS_KEY);
           }
            SignedJWT signedJWT = SignedJWT.parse(token);
            Date expiration = signedJWT.getJWTClaimsSet().getExpirationTime();
            var verified = signedJWT.verify(verifier);
            if (!(verified && expiration.after(new Date())))
                throw new CustomRunTimeException(ErrorCode.UNAUTHENTICATED);
            return signedJWT;
        } catch (JOSEException | ParseException e) {
            throw new CustomRunTimeException(ErrorCode.UNAUTHENTICATED);
        }
    }

    @Override
    public AuthenticationResponse refreshToken(RefreshRequest request) {
        var signedJWT = verifyToken(request.getRefreshToken(), true);
        try {
            var username = signedJWT.getJWTClaimsSet().getSubject();
            var user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new CustomRunTimeException(ErrorCode.USER_NOT_FOUND));
            boolean authenticated = passwordEncoder.matches(request.getRefreshToken(), user.getRefreshToken());
            if (!authenticated) {
                throw new CustomRunTimeException(ErrorCode.REFRESH_TOKEN_FAILED);
            }
            var accessToken = generateToken(user,"access_token");
            var refreshToken = generateToken(user,"refresh_token");
            user.setRefreshToken(passwordEncoder.encode(refreshToken));
            userRepository.save(user);
            return AuthenticationResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
        } catch (ParseException e) {
            throw new CustomRunTimeException(ErrorCode.UNAUTHENTICATED);
        }
    }

    private String generateToken(UserEntity user,String typeToken) {
        String time = VALID_DURATION;
        String key = SIGNER_KEY_ACCESS_KEY;
        if(!typeToken.equals("access_token")) {
            time = REFRESHABLE_DURATION;
            key = SIGNER_KEY_REFRESH_KEY;
        }
        JWSHeader header =new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(Long.parseLong(time), ChronoUnit.SECONDS).toEpochMilli()))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", buildScope(typeToken))
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);
        try {
            jwsObject.sign(new MACSigner(key.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new CustomRunTimeException(ErrorCode.TOKEN_CREATION_FAILED);
        }
    }

    private String buildScope(String typeToken) {
        StringJoiner scopeJoiner = new StringJoiner(" ");
            scopeJoiner.add(typeToken);
        return scopeJoiner.toString();
    }
}
