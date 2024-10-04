package com.challenge.aritcle.users.services;

import com.challenge.aritcle.common.api.ApiResponse;
import com.challenge.aritcle.users.controllers.dto.UserFollowerRequest;
import com.challenge.aritcle.users.controllers.dto.UserGetResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IFollowService {
    ApiResponse<Void> followUser(UserFollowerRequest request);
    ApiResponse<List<UserGetResponse>> getAllFollowers(Pageable pageable);
    ApiResponse<List<UserGetResponse>> getAllFollowings(Pageable pageable);
    ApiResponse<Void> unfollowUser(UserFollowerRequest request);


}
