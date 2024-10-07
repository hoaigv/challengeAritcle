package com.challenge.aritcle.users.services.impl;

import com.challenge.aritcle.aricles.controllers.dto.ArticleGetResponse;
import com.challenge.aritcle.aricles.mappers.IArticleMapper;
import com.challenge.aritcle.aricles.repositories.ArticleRepository;
import com.challenge.aritcle.common.api.ApiResponse;
import com.challenge.aritcle.exceptionHanller.CustomRunTimeException;
import com.challenge.aritcle.exceptionHanller.ErrorCode;
import com.challenge.aritcle.users.controllers.dto.UserCreateRequest;
import com.challenge.aritcle.users.controllers.dto.UserFollowerRequest;
import com.challenge.aritcle.users.controllers.dto.UserGetResponse;
import com.challenge.aritcle.users.controllers.dto.UserUpdateRequest;
import com.challenge.aritcle.users.mappers.IUserMapper;
import com.challenge.aritcle.users.models.UserEntity;
import com.challenge.aritcle.users.repository.UserRepository;
import com.challenge.aritcle.users.services.IUserService;
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
public class UserService implements IUserService {
    UserRepository userRepository;
    IUserMapper userMapper;



    @Override
    public ApiResponse<Void> registerUser(UserCreateRequest userCreateRequest) {
        if (userRepository.existsByUsername(userCreateRequest.getUsername())) {
            throw new CustomRunTimeException(ErrorCode.USER_EXISTED);
        }
        var user = userMapper.userDtoToUserEntity(userCreateRequest);
        userRepository.save(user);

        return ApiResponse.<Void>builder()
                .code(HttpStatus.CREATED.value())
                .message("User registered successfully")
                .build();
    }

    @Override
    public ApiResponse<UserGetResponse> getMyInformation() {
        var user = getUserEntity();
        var userDTO = userMapper.userEntityToUserGetResponse(user);
        userDTO.setNumOfFollowers(user.getFollowings().size());
        userDTO.setNumOfFollowing(user.getFollowers().size());
        return ApiResponse.<UserGetResponse>builder()
                .code(HttpStatus.OK.value())
                .result(userDTO)
                .build();
    }

    @Override
    @Transactional
    public ApiResponse<Void> updateUser(UserUpdateRequest userUpdateRequest) {
        var oldUser = getUserEntity();
         userMapper.updateUserEntityFromDto(userUpdateRequest, oldUser);
        userRepository.save(oldUser);
        return ApiResponse.<Void>builder()
                .code(HttpStatus.OK.value())
                .message("User updated successfully")
                .build();
    }



    private UserEntity getUserEntity() {
        return userRepository.findByUsername(AuthUtils.getUserCurrent())
                .orElseThrow(() -> new CustomRunTimeException(ErrorCode.USER_NOT_FOUND));
    }
}
