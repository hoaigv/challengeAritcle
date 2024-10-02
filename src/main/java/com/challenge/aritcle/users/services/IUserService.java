package com.challenge.aritcle.users.services;

import com.challenge.aritcle.common.api.ApiResponse;
import com.challenge.aritcle.users.controllers.dto.UserCreateRequest;

public interface IUserService {
    ApiResponse<Void> registerUser(UserCreateRequest userCreateRequest);
}
