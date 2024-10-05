package com.challenge.aritcle.users.repository;

import com.challenge.aritcle.aricles.models.ArticleEntity;
import com.challenge.aritcle.users.models.FavoriteEntity;
import com.challenge.aritcle.users.models.UserEntity;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteEntity,String> {
    boolean  existsByUserAndArticle(@NotNull UserEntity user, @NotNull ArticleEntity article);
    Optional<FavoriteEntity> findByUserAndArticle(@NotNull UserEntity user, @NotNull ArticleEntity article);
    Page<FavoriteEntity> findAllByUser(@NotNull UserEntity user, @NotNull Pageable pageable);
}
