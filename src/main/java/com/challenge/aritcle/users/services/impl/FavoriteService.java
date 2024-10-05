package com.challenge.aritcle.users.services.impl;

import com.challenge.aritcle.aricles.controllers.dto.ArticleGetResponse;
import com.challenge.aritcle.aricles.mappers.IArticleMapper;
import com.challenge.aritcle.aricles.repositories.ArticleRepository;
import com.challenge.aritcle.common.api.ApiResponse;
import com.challenge.aritcle.exceptionHanller.CustomRunTimeException;
import com.challenge.aritcle.exceptionHanller.ErrorCode;
import com.challenge.aritcle.users.models.FavoriteEntity;
import com.challenge.aritcle.users.models.UserEntity;
import com.challenge.aritcle.users.repository.FavoriteRepository;
import com.challenge.aritcle.users.repository.UserRepository;
import com.challenge.aritcle.users.services.IFavoriteService;
import com.challenge.aritcle.utils.AuthUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class FavoriteService implements IFavoriteService {
    UserRepository userRepository;
    IArticleMapper articleMapper;
    FavoriteRepository favoriteRepository;
    ArticleRepository articleRepository;

    @Override
    public ApiResponse<Void> favoriteArticle(String articleId) {
        var article = articleRepository.findById(articleId)
                .orElseThrow(() -> new CustomRunTimeException(ErrorCode.ARTICLE_NOT_FOUND));
        if (favoriteRepository.existsByUserAndArticle(getUserEntity(), article)) {
            throw new CustomRunTimeException(ErrorCode.FAVORITE_ARTICLE_EXISTED);
        }
        var favorite = FavoriteEntity.builder()
                .article(article)
                .user(getUserEntity())
                .build();
        favoriteRepository.save(favorite);
        return ApiResponse.<Void>builder()
                .code(HttpStatus.CREATED.value())
                .message("Favorite article added")
                .build();
    }

    @Override
    public ApiResponse<Void> unFavoriteArticle(String articleId) {
        var article = articleRepository.findById(articleId)
                .orElseThrow(() -> new CustomRunTimeException(ErrorCode.ARTICLE_NOT_FOUND));
        var favorite = favoriteRepository.findByUserAndArticle(getUserEntity(), article)
                .orElseThrow(() -> new CustomRunTimeException(ErrorCode.FAVORITE_ARTICLE_NOT_EXIST));
        favoriteRepository.delete(favorite);
        return ApiResponse.<Void>builder()
                .code(HttpStatus.OK.value())
                .message("Favorite article deleted")
                .build();
    }

    @Override
    public ApiResponse<List<ArticleGetResponse>> getFavoriteArticles(Pageable pageable) {
        var favorites = favoriteRepository.findAllByUser(getUserEntity(), pageable);
        var listFavorites = favorites.get().map(favorite -> {
            var article = favorite.getArticle();
            var articleDto = articleMapper.articleEntityToDto(article);
            articleDto.setNumberOfFavorite(article.getFavorites().size());
            return articleDto;
        }).toList();
        return ApiResponse.<List<ArticleGetResponse>>builder()
                .code(HttpStatus.OK.value())
                .result(listFavorites)
                .page(favorites.getNumber() + 1)
                .pageSize(favorites.getSize())
                .totalPages(favorites.getTotalPages())
                .totalResults(favorites.getTotalElements())
                .build();
    }

    private UserEntity getUserEntity() {
        return userRepository.findByUsername(AuthUtils.getUserCurrent())
                .orElseThrow(() -> new CustomRunTimeException(ErrorCode.USER_NOT_FOUND));
    }
}
