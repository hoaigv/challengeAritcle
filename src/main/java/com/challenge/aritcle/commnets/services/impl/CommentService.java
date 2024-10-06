package com.challenge.aritcle.commnets.services.impl;

import com.challenge.aritcle.aricles.repositories.ArticleRepository;
import com.challenge.aritcle.commnets.controllers.dto.CommentCreateRequest;
import com.challenge.aritcle.commnets.controllers.dto.CommentGetResponse;
import com.challenge.aritcle.commnets.mappers.ICommentMapper;
import com.challenge.aritcle.commnets.repositories.CommentRepository;
import com.challenge.aritcle.commnets.services.ICommentService;
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
public class CommentService implements ICommentService {
    UserRepository userRepository;
    CommentRepository commentRepository;
    ArticleRepository articleRepository;
    ICommentMapper commentMapper;


    @Override
    @Transactional
    public ApiResponse<Void> addComment(CommentCreateRequest request, String articleId) {
        var article = articleRepository.findById(articleId)
                .orElseThrow(() -> new CustomRunTimeException(ErrorCode.ARTICLE_NOT_FOUND));
        var comment = commentMapper.commentDtoToEntity(request);
        comment.setArticle(article);
        comment.setUser(getUserEntity());
        commentRepository.save(comment);
        return ApiResponse.<Void>builder()
                .code(HttpStatus.CREATED.value())
                .message("Comment created successfully")
                .build();
    }

    @Override
    public ApiResponse<List<CommentGetResponse>> readAllComment(Pageable pageable, String articleId) {
        var article = articleRepository.findById(articleId)
                .orElseThrow(() -> new CustomRunTimeException(ErrorCode.ARTICLE_NOT_FOUND));
        var comments = commentRepository.findAllByArticle(article, pageable);
        var listComments = comments.stream().map(
                commentMapper::commentEntityToDto
        ).toList();

        return ApiResponse.<List<CommentGetResponse>>builder()
                .code(HttpStatus.OK.value())
                .result(listComments)
                .page(comments.getNumber() + 1)
                .pageSize(comments.getSize())
                .totalPages(comments.getTotalPages())
                .totalResults(comments.getTotalElements())
                .build();
    }

    @Override
    @Transactional
    public ApiResponse<Void> deleteComment(String commentId, String articleId) {
        var article = articleRepository.findById(articleId)
                .orElseThrow(() -> new CustomRunTimeException(ErrorCode.ARTICLE_NOT_FOUND));
        if (!commentRepository.existsByArticleAndUser(article, getUserEntity())) {
            throw new CustomRunTimeException(ErrorCode.COMMENT_NOT_OWNED_BY_USER);
        }
        commentRepository.deleteById(commentId);
        return ApiResponse.<Void>builder()
                .code(HttpStatus.OK.value())
                .message("Comment deleted successfully")
                .build();
    }

    private UserEntity getUserEntity() {
        return userRepository.findByUsername(AuthUtils.getUserCurrent())
                .orElseThrow(() -> new CustomRunTimeException(ErrorCode.USER_NOT_FOUND));
    }

}
