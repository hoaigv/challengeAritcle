package com.challenge.aritcle.commnets.controllers.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentCreateRequest {
    @NotBlank(message = "comment must be not null or only space !")
    String content;
}
