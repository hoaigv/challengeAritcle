package com.challenge.aritcle.commnets.models;

import com.challenge.aritcle.aricle.models.ArticleEntity;
import com.challenge.aritcle.users.models.UserEntity;
import com.challenge.aritcle.common.models.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Table
@Entity(name = "comments")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentEntity extends BaseEntity {

    @NotBlank(message = "comment must be not null")
    @Column(columnDefinition = "TEXT")
    String content;

    @NotNull(message = "status must be not null")
    Boolean status;

    @ManyToOne
    @JoinColumn
    @NotNull
    @JsonBackReference
    UserEntity user;

    @ManyToOne
    @JoinColumn
    @NotNull
    @JsonBackReference
    ArticleEntity article;

}
