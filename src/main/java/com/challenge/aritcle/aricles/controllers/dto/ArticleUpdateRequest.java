package com.challenge.aritcle.aricles.controllers.dto;

import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArticleUpdateRequest {

    @Pattern(regexp = "^(?!\\s)(.*\\S)?$", message = "Title must not contain leading or trailing whitespace and must not be blank if provided")
    String title;

    @Pattern(regexp = "^\\s*\\S.*$", message = "Body must not be blank if provided")
    String body;

}
