package com.challenge.aritcle.config.security;

import com.challenge.aritcle.authentication.controllers.dto.IntrospectRequest;
import com.challenge.aritcle.authentication.service.impl.AuthenticationService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.util.Objects;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomJwtDecoder implements JwtDecoder {
    @Value("${jwt.signerKeyAccess}")
    String SIGNER_KEY_ACCESS;
    @Autowired
    AuthenticationService authenticationService;
    NimbusJwtDecoder nimbusJwtDecoder = null;

    @Override
    public Jwt decode(String token){
        var response = authenticationService.introspect(
                IntrospectRequest.builder().accessToken(token).build()
        );

            if (!response.isValid()) throw new BadJwtException("invalid token");
        if (Objects.isNull(nimbusJwtDecoder)) {
            SecretKeySpec secretKeySpec = new SecretKeySpec(SIGNER_KEY_ACCESS.getBytes(), "HS512");
            nimbusJwtDecoder = NimbusJwtDecoder.withSecretKey(secretKeySpec)
                    .macAlgorithm(MacAlgorithm.HS512)
                    .build();
        }

        return nimbusJwtDecoder.decode(token);
    }
}
