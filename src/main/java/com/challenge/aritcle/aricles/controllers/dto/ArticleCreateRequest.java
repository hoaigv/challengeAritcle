package com.challenge.aritcle.aricles.controllers.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArticleCreateRequest {
    @NotBlank(message = " title must be not null or only space !")
    @Pattern(regexp = "^(?!\\s)(.*\\S)?$", message = "Title must not contain leading or trailing whitespace and must not be blank if provided")
    String title;
    @NotBlank(message = " body must be not null or only space !")
    String body;
}
