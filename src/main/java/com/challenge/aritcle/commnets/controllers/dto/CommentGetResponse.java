package com.challenge.aritcle.commnets.controllers.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentGetResponse {
    String id;

    String username;

    String articleId;

    String content;

    LocalDateTime createdAt;
}
