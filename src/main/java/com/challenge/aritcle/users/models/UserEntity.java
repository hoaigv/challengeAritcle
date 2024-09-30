package com.challenge.aritcle.users.models;

import com.challenge.aritcle.aricle.models.ArticleEntity;
import com.challenge.aritcle.commnets.models.CommentEntity;
import com.challenge.aritcle.utils.BaseEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Table
@Entity(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity extends BaseEntity {

    @Column(unique = true, columnDefinition = "VARCHAR(255) COLLATE utf8mb4_unicode_ci")
    @NotNull(message = "userName must not be null")
    String username;

    @NotNull(message = "password  must not be null")
    String password;

    @Column(unique = true)
    @NotNull(message = "email must not be null")
    String email;

    @OneToMany(mappedBy = "follower" )
    @JsonManagedReference
    Set<FollowEntity> followers=  new HashSet<>();

    @OneToMany(mappedBy = "following")
    @JsonManagedReference
    Set<FollowEntity> followings =  new HashSet<>();

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    Set<FavoriteEntity> favorites =  new HashSet<>();

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    Set<CommentEntity> comments =  new HashSet<>();

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    Set<ArticleEntity> articles =  new HashSet<>();

}
