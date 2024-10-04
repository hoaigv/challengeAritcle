package com.challenge.aritcle.users.services.impl;

import com.challenge.aritcle.common.api.ApiResponse;
import com.challenge.aritcle.exceptionHanller.CustomRunTimeException;
import com.challenge.aritcle.exceptionHanller.ErrorCode;
import com.challenge.aritcle.users.controllers.dto.UserFollowerRequest;
import com.challenge.aritcle.users.controllers.dto.UserGetResponse;
import com.challenge.aritcle.users.mappers.IUserMapper;
import com.challenge.aritcle.users.models.FollowEntity;
import com.challenge.aritcle.users.models.UserEntity;
import com.challenge.aritcle.users.repository.FollowRepository;
import com.challenge.aritcle.users.repository.UserRepository;
import com.challenge.aritcle.users.services.IFollowService;
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
public class FollowService implements IFollowService {
    UserRepository userRepository;
    FollowRepository followRepository;
    IUserMapper userMapper;

    @Override
    public ApiResponse<Void> followUser(UserFollowerRequest request) {
        var user = getUserEntity();
        var userFollow = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new CustomRunTimeException(ErrorCode.USER_NOT_FOUND));
        if(followRepository.existsByFollowerAndFollowing(user,userFollow)) {
            throw new CustomRunTimeException(ErrorCode.FOLLOWER_EXISTED);
        }else if (user.equals(userFollow)) {
            throw  new CustomRunTimeException(ErrorCode.SELF_FOLLOW_NOT_ALLOWED);
        }
        FollowEntity followEntity = FollowEntity.builder()
                .follower(user)
                .following(userFollow)
                .build();
        followRepository.save(followEntity);
        return ApiResponse.<Void>builder()
                .code(HttpStatus.CREATED.value())
                .message("Successfully followed user")
                .build();
    }

    @Override
    public ApiResponse<List<UserGetResponse>> getAllFollowers(Pageable pageable) {
        var user = getUserEntity();
        var followers = followRepository.findByFollowing(user,pageable);
        var listFollowers = followers.get().map(
                followEntity -> {
                    var follower = followEntity.getFollower();
                    var followerDto = userMapper.userEntityToUserGetResponse(follower);
                    followerDto.setNumOfFollowing(follower.getFollowings().size());
                    followerDto.setNumOfFollowers(follower.getFollowers().size());
                    return followerDto;
                }
        ).toList();
        return ApiResponse.<List<UserGetResponse>>builder()
                .code(HttpStatus.OK.value())
                .result(listFollowers)
                .page(followers.getNumber() + 1)
                .pageSize(followers.getSize())
                .totalPages(followers.getTotalPages())
                .totalResults(followers.getTotalElements())
                .build();
    }

    @Override
    public ApiResponse<List<UserGetResponse>> getAllFollowings(Pageable pageable) {
        var user = getUserEntity();
        var followings = followRepository.findByFollower(user, pageable);
        var listFollowings = followings.get().map(
                followEntity -> {
                    var following = followEntity.getFollowing();
                    var followerDto = userMapper.userEntityToUserGetResponse(following);
                    followerDto.setNumOfFollowing(following.getFollowings().size());
                    followerDto.setNumOfFollowers(following.getFollowers().size());
                    return followerDto;
                }
        ).toList();
        return ApiResponse.<List<UserGetResponse>>builder()
                .code(HttpStatus.OK.value())
                .result(listFollowings)
                .page(followings.getNumber() + 1)
                .pageSize(followings.getSize())
                .totalPages(followings.getTotalPages())
                .totalResults(followings.getTotalElements())
                .build();
    }

    @Override
    public ApiResponse<Void> unfollowUser(UserFollowerRequest request) {
        var user = getUserEntity();
        var userFollowing = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new CustomRunTimeException(ErrorCode.USER_NOT_FOUND));
        var follow = followRepository.findByFollowerAndFollowing(user, userFollowing)
                .orElseThrow(() -> new CustomRunTimeException(ErrorCode.FOLLOWER_NOT_EXIST));
        followRepository.delete(follow);
        return ApiResponse.<Void>builder()
                .code(HttpStatus.OK.value())
                .message("Successfully unfollowed user")
                .build();
    }

    private UserEntity getUserEntity() {
        return userRepository.findByUsername(AuthUtils.getUserCurrent())
                .orElseThrow(() -> new CustomRunTimeException(ErrorCode.USER_NOT_FOUND));
    }
}
