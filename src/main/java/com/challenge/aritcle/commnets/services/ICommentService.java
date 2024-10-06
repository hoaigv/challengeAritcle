package com.challenge.aritcle.commnets.services;

import com.challenge.aritcle.commnets.controllers.dto.CommentCreateRequest;
import com.challenge.aritcle.commnets.controllers.dto.CommentGetResponse;
import com.challenge.aritcle.common.api.ApiResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICommentService {
    ApiResponse<Void> addComment(CommentCreateRequest comment  , String articleId);
    ApiResponse<List<CommentGetResponse>> readAllComment(Pageable pageable, String articleId);
    ApiResponse<Void> deleteComment(String commentId , String articleId);
}
