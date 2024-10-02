package com.challenge.aritcle.aricles.controllers.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArticleGetResponse {

    String id;

    String user_id;

    String title;

    String body;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;

    Integer numberOfFavorite;
}
