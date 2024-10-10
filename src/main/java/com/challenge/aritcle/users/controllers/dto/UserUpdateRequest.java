package com.challenge.aritcle.users.controllers.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    @Size(min = 6 , message = "username length must be 6")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username must not contain special characters or accented characters")
    String username;

    @Size(min = 6, message = "Password length must be at least 6 characters")
    @Pattern(regexp = "^[a-zA-Z0-9@#$%^&*()!]+$", message = "Password must not contain spaces or accented characters")
    String password;
}
