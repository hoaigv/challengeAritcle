package com.challenge.aritcle.users.controllers;

import com.challenge.aritcle.aricles.controllers.dto.ArticleGetResponse;
import com.challenge.aritcle.aricles.services.IArticleService;
import com.challenge.aritcle.commnets.services.ICommentService;
import com.challenge.aritcle.common.api.ApiResponse;
import com.challenge.aritcle.users.controllers.dto.UserFollowerRequest;
import com.challenge.aritcle.users.controllers.dto.UserGetResponse;
import com.challenge.aritcle.users.controllers.dto.UserUpdateRequest;
import com.challenge.aritcle.users.services.IFavoriteService;
import com.challenge.aritcle.users.services.IFollowService;
import com.challenge.aritcle.users.services.IUserService;
import com.challenge.aritcle.utils.validators.PositiveNumber;
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


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserControllers {
    IUserService userService;
    IFollowService followService;
    IFavoriteService favoriteService;
    IArticleService articleService;
    ICommentService commentService;
    private final static String DEFAULT_FILTER_SIZE = "10";
    private final static String DEFAULT_FILTER_PAGE = "0";
    private final static Sort DEFAULT_FILTER_SORT = Sort.by(Sort.Direction.DESC, "createdAt");


    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserGetResponse>> getMyInformation() {
        var resp = userService.getMyInformation();
        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }

    @PutMapping("/me")
    public ResponseEntity<ApiResponse<Void>> updateMyInformation(@RequestBody @Valid UserUpdateRequest userUpdateRequest) {
        var resp = userService.updateUser(userUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }

    @PostMapping("/followers")
    public ResponseEntity<ApiResponse<Void>> followers(@RequestBody @Valid UserFollowerRequest request) {
        var resp = followService.followUser(request);
        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }

    @DeleteMapping("/followers")
    public ResponseEntity<ApiResponse<Void>> unfollow(@RequestBody @Valid UserFollowerRequest request) {
        var resp = followService.unfollowUser(request);
        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }

    @GetMapping("/followers")
    public ResponseEntity<ApiResponse<List<UserGetResponse>>> getFollowers(
            @RequestParam(required = false, defaultValue = DEFAULT_FILTER_PAGE) @PositiveNumber String page,
            @RequestParam(required = false, defaultValue = DEFAULT_FILTER_SIZE) @PositiveNumber String size) {

        Pageable pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size), DEFAULT_FILTER_SORT);
        var resp = followService.getAllFollowers(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }

    @GetMapping("/followings")
    public ResponseEntity<ApiResponse<List<UserGetResponse>>> getFollowings(
            @RequestParam(required = false, defaultValue = DEFAULT_FILTER_PAGE) @PositiveNumber  String page,
            @RequestParam(required = false, defaultValue = DEFAULT_FILTER_SIZE) @PositiveNumber String size) {

        Pageable pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size), DEFAULT_FILTER_SORT);
        var resp = followService.getAllFollowings(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }

    @PostMapping("/favorite/{articleId}")
    public ResponseEntity<ApiResponse<Void>> favoriteArticle(@PathVariable String articleId) {
        var resp = favoriteService.favoriteArticle(articleId);
        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }

    @DeleteMapping("/favorite/{articleId}")
    public ResponseEntity<ApiResponse<Void>> unFavoriteArticle(@PathVariable String articleId) {
        var resp = favoriteService.unFavoriteArticle(articleId);
        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }

    @GetMapping("/favorite")
    public ResponseEntity<ApiResponse<List<ArticleGetResponse>>> getFavorites(
            @RequestParam(required = false, defaultValue = DEFAULT_FILTER_PAGE) @PositiveNumber String page,
            @RequestParam(required = false, defaultValue = DEFAULT_FILTER_SIZE) @PositiveNumber  String size
    ) {

        Pageable pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size), DEFAULT_FILTER_SORT);
        var resp = favoriteService.getFavoriteArticles(pageable);
        return ResponseEntity.status(HttpStatus.OK.value()).body(resp);
    }

    @GetMapping("/articles")
    public ResponseEntity<ApiResponse<List<ArticleGetResponse>>> getAllArticles(
            @RequestParam(required = false, defaultValue = DEFAULT_FILTER_PAGE)@PositiveNumber String page,
            @RequestParam(required = false, defaultValue = DEFAULT_FILTER_SIZE) @PositiveNumber String size
    ) {

        Pageable pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size), DEFAULT_FILTER_SORT);
        var resp = articleService.getAllArticlesByUser(pageable);
        return ResponseEntity.status(HttpStatus.OK.value()).body(resp);
    }
    @DeleteMapping("articles/{articleId}/comments/{commentId}")
    public ResponseEntity<ApiResponse<Void>> deleteComment(@PathVariable("articleId") String articleId, @PathVariable("commentId") String commentId) {
        var resp = commentService.deleteComment(commentId, articleId);
        return ResponseEntity.status(HttpStatus.OK.value()).body(resp);
    }


}
