package com.challenge.aritcle.commnets.controllers;

import com.challenge.aritcle.commnets.controllers.dto.CommentCreateRequest;
import com.challenge.aritcle.commnets.controllers.dto.CommentGetResponse;
import com.challenge.aritcle.commnets.services.ICommentService;
import com.challenge.aritcle.common.api.ApiResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNumeric;

@RestController
@RequestMapping("articles/{articleId}/comments")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CommentController {
    ICommentService commentService;

    private final static String DEFAULT_FILTER_SIZE = "10";
    private final static String DEFAULT_FILTER_PAGE = "0";
    private final static Sort DEFAULT_FILTER_SORT = Sort.by(Sort.Direction.DESC, "createdAt");

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> addComment(@PathVariable("articleId") String articleId,@RequestBody @Valid CommentCreateRequest commentCreateRequest) {
        var resp = commentService.addComment(commentCreateRequest, articleId);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(resp);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CommentGetResponse>>> getComments(
            @PathVariable("articleId") String articleId,
            @RequestParam(required = false, defaultValue = DEFAULT_FILTER_PAGE) String page,
            @RequestParam(required = false, defaultValue = DEFAULT_FILTER_SIZE) String size) {
        if (!isNumeric(page) || !isNumeric(size)) {
            var resp = ApiResponse.<List<CommentGetResponse>>builder()
                    .result(null)
                    .message("Page and size must be numeric")
                    .code(400)
                    .build();
            return ResponseEntity.badRequest().body(resp);
        }
        Pageable pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size), DEFAULT_FILTER_SORT);
        var resp = commentService.readAllComment(pageable, articleId);
        return ResponseEntity.status(HttpStatus.OK.value()).body(resp);
    }

}
