package com.challenge.aritcle.aricles.services.impl;

import com.challenge.aritcle.aricles.controllers.dto.ArticleCreateRequest;
import com.challenge.aritcle.aricles.controllers.dto.ArticleGetResponse;
import com.challenge.aritcle.aricles.controllers.dto.ArticleUpdateRequest;
import com.challenge.aritcle.aricles.mappers.IArticleMapper;
import com.challenge.aritcle.aricles.repositories.ArticleRepository;
import com.challenge.aritcle.aricles.services.IArticleService;
import com.challenge.aritcle.common.api.ApiResponse;
import com.challenge.aritcle.exceptionHanller.CustomRunTimeException;
import com.challenge.aritcle.exceptionHanller.ErrorCode;
import com.challenge.aritcle.users.models.UserEntity;
import com.challenge.aritcle.users.repository.UserRepository;
import com.challenge.aritcle.utils.AuthUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ArticleService implements IArticleService {
    UserRepository userRepository;
    ArticleRepository articleRepository;
    IArticleMapper articleMapper;

    @Override
    @Transactional
    public ApiResponse<Void> createArticle(ArticleCreateRequest request) {
        var user = getUserEntity();
        var article = articleMapper.articleDtoToEntity(request);
        article.setUser(user);
        articleRepository.save(article);
        return ApiResponse.<Void>builder()
                .code(HttpStatus.CREATED.value())
                .message("Created article successfully")
                .build();
    }

    @Override
    public ApiResponse<ArticleGetResponse> getArticle(String articleId) {
        var article = articleRepository.findById(articleId)
                .orElseThrow(() -> new CustomRunTimeException(ErrorCode.ARTICLE_NOT_FOUND));
        var resp = articleMapper.articleEntityToDto(article);
        resp.setNumberOfFavorite(article.getFavorites().size());
        return ApiResponse.<ArticleGetResponse>builder()
                .code(HttpStatus.OK.value())
                .result(resp)
                .build();
    }

    @Override
    public ApiResponse<List<ArticleGetResponse>> getAllArticles(Pageable pageable) {
        var articles = articleRepository.findAll(pageable);
        var listArticle = articles.get().map(article -> {
            var resp = articleMapper.articleEntityToDto(article);
            resp.setNumberOfFavorite(article.getFavorites().size());
            return resp;
        }).toList();

        return ApiResponse.<List<ArticleGetResponse>>builder()
                .code(HttpStatus.OK.value())
                .result(listArticle)
                .page(articles.getNumber() + 1)
                .pageSize(articles.getSize())
                .totalPages(articles.getTotalPages())
                .totalResults(articles.getTotalElements())
                .build();
    }

    @Override
    public ApiResponse<List<ArticleGetResponse>> getAllArticlesByUser(Pageable pageable) {
        var articles = articleRepository.findAllByUser(getUserEntity(), pageable);
        var listArticle = articles.get().map(article -> {
            var resp = articleMapper.articleEntityToDto(article);
            resp.setNumberOfFavorite(article.getFavorites().size());
            return resp;
        }).toList();

        return ApiResponse.<List<ArticleGetResponse>>builder()
                .code(HttpStatus.OK.value())
                .result(listArticle)
                .page(articles.getNumber() + 1)
                .pageSize(articles.getSize())
                .totalPages(articles.getTotalPages())
                .totalResults(articles.getTotalElements())
                .build();
    }

    @Override
    @Transactional
    public ApiResponse<Void> updateArticle(String articleId, ArticleUpdateRequest request) {
        var article = articleRepository.findByIdAndUser(articleId, getUserEntity())
                .orElseThrow(() -> new CustomRunTimeException(ErrorCode.ARTICLE_NOT_FOUND));
        articleMapper.updateArticleFromDto(request, article);
        articleRepository.save(article);
        return ApiResponse.<Void>builder()
                .code(HttpStatus.OK.value())
                .message("Updated article successfully")
                .build();
    }

    @Override
    @Transactional
    public ApiResponse<Void> deleteArticle(String articleId) {
        var article = articleRepository.findByIdAndUser(articleId, getUserEntity())
                .orElseThrow(() -> new CustomRunTimeException(ErrorCode.ARTICLE_NOT_OWNED_BY_USER));
        articleRepository.delete(article);
        return ApiResponse.<Void>builder()
                .code(HttpStatus.OK.value())
                .message("Deleted article successfully")
                .build();
    }

    private UserEntity getUserEntity() {
        return userRepository.findByUsername(AuthUtils.getUserCurrent())
                .orElseThrow(() -> new CustomRunTimeException(ErrorCode.USER_NOT_FOUND));
    }
}
