package com.challenge.aritcle.aricle.models;

import com.challenge.aritcle.commnets.models.CommentEntity;
import com.challenge.aritcle.users.models.FavoriteEntity;
import com.challenge.aritcle.users.models.UserEntity;
import com.challenge.aritcle.common.models.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Table
@Entity(name = "articles")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArticleEntity extends BaseEntity {

    @NotBlank(message = "title must be not null")
    String title;

    @NotBlank(message = "body must be not null")
    @Column(columnDefinition = "MEDIUMTEXT")
    String body;

    @NotNull(message = "status must be not null")
    Boolean status;

    @Column
    @LastModifiedDate
    LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn
    @NotNull
    @JsonBackReference
    UserEntity user;

    @OneToMany(mappedBy = "article")
    @JsonManagedReference
    Set<FavoriteEntity> favorites =  new HashSet<>();

    @OneToMany(mappedBy = "article" )
    @JsonManagedReference
    Set<CommentEntity>  comments =  new HashSet<>();


}
