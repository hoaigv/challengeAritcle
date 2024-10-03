package com.challenge.aritcle.users.controllers.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserFollowerRequest {
    @NotBlank(message = "Please select the user you want to follow!")
    String username;
}
