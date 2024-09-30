package com.challenge.aritcle.aricles.controllers.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.util.Set;

public class ArticleDeleteRequest {
    @Positive(message = "Article ID must be positive")
    @NotEmpty
    Set<Integer> articleIds;
}
