package com.challenge.aritcle.aricles.services;

import com.challenge.aritcle.aricles.controllers.dto.ArticleCreateRequest;
import com.challenge.aritcle.aricles.controllers.dto.ArticleGetResponse;
import com.challenge.aritcle.aricles.controllers.dto.ArticleUpdateRequest;
import com.challenge.aritcle.common.api.ApiResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IArticleService {
    ApiResponse<Void>  createArticle(ArticleCreateRequest request);
    ApiResponse<ArticleGetResponse> getArticle(String articleId);
    ApiResponse<Void> updateArticle(String articleId, ArticleUpdateRequest request);
    ApiResponse<List<ArticleGetResponse>> getAllArticles(Pageable pageable);
    ApiResponse<List<ArticleGetResponse>> getAllArticlesByUser(Pageable pageable);
    ApiResponse<Void> deleteArticle(String articleId);
}
