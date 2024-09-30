package com.challenge.aritcle.commnets.controllers.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentDeleteRequest {
    @Positive(message = "Article ID must be positive")
    @NotEmpty
    Set<Integer> articleIds;
}
