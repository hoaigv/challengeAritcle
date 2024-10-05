package com.challenge.aritcle.aricles.controllers;

import com.challenge.aritcle.aricles.controllers.dto.ArticleCreateRequest;
import com.challenge.aritcle.aricles.controllers.dto.ArticleGetResponse;
import com.challenge.aritcle.aricles.controllers.dto.ArticleUpdateRequest;
import com.challenge.aritcle.aricles.services.impl.ArticleService;
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
@RequestMapping("/articles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ArticleControllers {
    ArticleService articleService;
    private final static String DEFAULT_FILTER_SIZE = "10";
    private final static String DEFAULT_FILTER_PAGE = "0";
    private final static Sort DEFAULT_FILTER_SORT = Sort.by(Sort.Direction.DESC, "createdAt");

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createArticle(@RequestBody @Valid ArticleCreateRequest articleCreateRequest) {
        var resp = articleService.createArticle(articleCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @GetMapping("/{articleId}")
    public ResponseEntity<ApiResponse<ArticleGetResponse>> getArticle(@PathVariable String articleId) {
        var resp = articleService.getArticle(articleId);
        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }
    @GetMapping
    public ResponseEntity<ApiResponse<List<ArticleGetResponse>>> getAllArticles(
            @RequestParam(required = false, defaultValue = DEFAULT_FILTER_PAGE) String page,
            @RequestParam(required = false, defaultValue = DEFAULT_FILTER_SIZE) String size
    ) {
        if (!isNumeric(page) || !isNumeric(size)) {
            var resp = ApiResponse.<List<ArticleGetResponse>>builder()
                    .result(null)
                    .message("Page and size must be numeric")
                    .code(400)
                    .build();
            return ResponseEntity.badRequest().body(resp);
        }
        Pageable pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size), DEFAULT_FILTER_SORT);
        var resp = articleService.getAllArticles(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }

    @PutMapping("/{articleId}")
    public ResponseEntity<ApiResponse<Void>> updateArticle(@PathVariable String articleId, @RequestBody @Valid ArticleUpdateRequest articleUpdateRequest) {
        var resp = articleService.updateArticle(articleId, articleUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }


}
