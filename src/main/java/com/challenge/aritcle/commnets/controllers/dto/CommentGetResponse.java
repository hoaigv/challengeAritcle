package com.challenge.aritcle.commnets.controllers.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentGetResponse {

    Integer id;

    String username;

    Integer article_id;

    String content ;

    LocalDateTime createdAt;

    Boolean status;
}
