package com.challenge.aritcle.authentication.service;

import com.challenge.aritcle.authentication.controllers.dto.*;
import com.challenge.aritcle.common.api.ApiResponse;

public interface IAuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
    ApiResponse<Void> logout();
    IntrospectResponse introspect(IntrospectRequest introspectRequest) ;
    AuthenticationResponse refreshToken(RefreshRequest request) ;
}
