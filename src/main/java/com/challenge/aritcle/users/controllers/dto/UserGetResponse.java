package com.challenge.aritcle.users.controllers.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserGetResponse {

    String username;

    String password;

    String gmail;

    LocalDateTime createdAt;

    Integer numOfFollowers;

    Integer numOfFollowing;
}
