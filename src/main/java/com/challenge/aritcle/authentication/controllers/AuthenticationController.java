package com.challenge.aritcle.authentication.controllers;

import com.challenge.aritcle.authentication.controllers.dto.AuthenticationRequest;
import com.challenge.aritcle.authentication.controllers.dto.AuthenticationResponse;
import com.challenge.aritcle.authentication.controllers.dto.RefreshRequest;
import com.challenge.aritcle.authentication.service.IAuthenticationService;
import com.challenge.aritcle.common.api.ApiResponse;
import com.challenge.aritcle.users.controllers.dto.UserCreateRequest;
import com.challenge.aritcle.users.services.IUserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level =  AccessLevel.PRIVATE , makeFinal = true)
public class AuthenticationController {
    IAuthenticationService authenticationService;
    IUserService userService;

   @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid AuthenticationRequest authenticationRequest) {
       var resp  = authenticationService.authenticate(authenticationRequest);
       return ResponseEntity.status(HttpStatus.CREATED).body(resp);
   }

   @PostMapping("/register")
    public ResponseEntity<ApiResponse<Void>> resister(@RequestBody @Valid UserCreateRequest request) {
       var resp = userService.registerUser(request);
       return ResponseEntity.status(HttpStatus.CREATED).body(resp);
   }

   @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout() {
       var resp = authenticationService.logout();
       return ResponseEntity.ok(resp);
   }
   @PostMapping("/refresh")
    public ResponseEntity<AuthenticationResponse> refresh(@RequestBody @Valid RefreshRequest request) {
       var resp = authenticationService.refreshToken(request);
       return ResponseEntity.ok(resp);
   }
}
