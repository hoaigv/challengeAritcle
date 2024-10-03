package com.challenge.aritcle.authentication.controllers.dto;

import jakarta.validation.constraints.NotBlank;
 import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationRequest {
    @NotBlank(message = "username must be not null")
    String username;
    @NotBlank(message = "password must be not null")
    String password;

}