package com.challenge.aritcle.users.controllers;

import com.challenge.aritcle.common.api.ApiResponse;
import com.challenge.aritcle.users.controllers.dto.UserFollowerRequest;
import com.challenge.aritcle.users.controllers.dto.UserGetResponse;
import com.challenge.aritcle.users.controllers.dto.UserUpdateRequest;
import com.challenge.aritcle.users.services.impl.FollowService;
import com.challenge.aritcle.users.services.impl.UserService;
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
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserControllers {
    UserService userService;
    FollowService followService;
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
    public ResponseEntity<ApiResponse<Void>> unfollowers(@RequestBody @Valid UserFollowerRequest request) {
        var resp = followService.unfollowUser(request);
        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }

    @GetMapping("/followers")
    public ResponseEntity<ApiResponse<List<UserGetResponse>>> getFollowers(
            @RequestParam(required = false, defaultValue = DEFAULT_FILTER_PAGE) String page,
            @RequestParam(required = false, defaultValue = DEFAULT_FILTER_SIZE) String size) {
        if (!isNumeric(page) || !isNumeric(size)) {
            var resp = ApiResponse.<List<UserGetResponse>>builder()
                    .result(null)
                    .message("Page and size must be numeric")
                    .code(400)
                    .build();
            return ResponseEntity.badRequest().body(resp);
        }
        Pageable pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size), DEFAULT_FILTER_SORT);
        var resp = followService.getAllFollowers(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }

    @GetMapping("/followings")
    public ResponseEntity<ApiResponse<List<UserGetResponse>>> getFollowings(
            @RequestParam(required = false, defaultValue = DEFAULT_FILTER_PAGE) String page,
            @RequestParam(required = false, defaultValue = DEFAULT_FILTER_SIZE) String size) {
        if (!isNumeric(page) || !isNumeric(size)) {
            var resp = ApiResponse.<List<UserGetResponse>>builder()
                    .result(null)
                    .message("Page and size must be numeric")
                    .code(400)
                    .build();
            return ResponseEntity.badRequest().body(resp);
        }
        Pageable pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size), DEFAULT_FILTER_SORT);
        var resp = followService.getAllFollowings(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }

}
