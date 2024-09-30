package com.challenge.aritcle.aricles.controllers.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArticleCreateRequest {
    @NotBlank
    String title;
    @NotBlank
    String body;
    @NotNull
    Boolean status;
}
