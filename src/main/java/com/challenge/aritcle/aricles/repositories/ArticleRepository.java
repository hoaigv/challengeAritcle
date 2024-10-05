package com.challenge.aritcle.aricles.repositories;

import com.challenge.aritcle.aricles.models.ArticleEntity;
import com.challenge.aritcle.users.models.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity,String> {
    Optional<ArticleEntity>  findByIdAndUser(String id, UserEntity user);
    Page<ArticleEntity> findAllByUser(UserEntity user, Pageable pageable);

}
