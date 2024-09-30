package com.challenge.aritcle.aricles.controllers.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArticleGetResponse {

    Integer id;

    Integer user_id;

    String title;

    String body;

    Boolean status;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;

    Integer numberOfFavorite;
}
