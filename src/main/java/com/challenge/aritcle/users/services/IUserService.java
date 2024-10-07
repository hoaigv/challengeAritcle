package com.challenge.aritcle.users.services;

import com.challenge.aritcle.aricles.controllers.dto.ArticleGetResponse;
import com.challenge.aritcle.common.api.ApiResponse;
import com.challenge.aritcle.users.controllers.dto.UserCreateRequest;
import com.challenge.aritcle.users.controllers.dto.UserFollowerRequest;
import com.challenge.aritcle.users.controllers.dto.UserGetResponse;
import com.challenge.aritcle.users.controllers.dto.UserUpdateRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {
    ApiResponse<Void> registerUser(UserCreateRequest userCreateRequest);
    ApiResponse<UserGetResponse> getMyInformation();
    ApiResponse<Void> updateUser(UserUpdateRequest userUpdateRequest);

}
