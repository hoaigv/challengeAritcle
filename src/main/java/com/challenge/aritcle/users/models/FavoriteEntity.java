package com.challenge.aritcle.users.models;

import com.challenge.aritcle.aricles.models.ArticleEntity;
import com.challenge.aritcle.common.models.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Table
@Entity(name = "favorites")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FavoriteEntity extends BaseEntity {
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
