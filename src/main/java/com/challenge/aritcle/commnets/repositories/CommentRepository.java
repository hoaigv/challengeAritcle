package com.challenge.aritcle.commnets.repositories;

import com.challenge.aritcle.aricles.models.ArticleEntity;
import com.challenge.aritcle.commnets.models.CommentEntity;
import com.challenge.aritcle.users.models.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, String> {
    Page<CommentEntity> findAllByArticle(ArticleEntity article, Pageable pageable);
    boolean existsByArticleAndUser(ArticleEntity article, UserEntity user);
}
