package com.challenge.aritcle.users.services;

import com.challenge.aritcle.aricles.controllers.dto.ArticleGetResponse;
import com.challenge.aritcle.common.api.ApiResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IFavoriteService {
    ApiResponse<Void> favoriteArticle(String articleId);
    ApiResponse<Void> unFavoriteArticle(String articleId);
    ApiResponse<List<ArticleGetResponse>> getFavoriteArticles(Pageable pageable);
}
