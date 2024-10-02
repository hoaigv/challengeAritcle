package com.challenge.aritcle.aricles.controllers.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArticleUpdateRequest {
    @NotEmpty
    String title;
    @NotEmpty
    String body;
}
