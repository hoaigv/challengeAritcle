package com.challenge.aritcle.users.services.impl;

import com.challenge.aritcle.common.api.ApiResponse;
import com.challenge.aritcle.exceptionHanller.CustomRunTimeException;
import com.challenge.aritcle.exceptionHanller.ErrorCode;
import com.challenge.aritcle.users.controllers.dto.UserCreateRequest;
import com.challenge.aritcle.users.mappers.IUserMapper;
import com.challenge.aritcle.users.repository.UserRepository;
import com.challenge.aritcle.users.services.IUserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService implements IUserService {
    UserRepository userRepository;
    IUserMapper userMapper;
    @Override
    public ApiResponse<Void> registerUser(UserCreateRequest userCreateRequest) {
        if(userRepository.existsByUsername(userCreateRequest.getUsername())) {
         throw  new CustomRunTimeException(ErrorCode.USER_EXISTED);
        }
        var user = userMapper.userDtoToUserEntity(userCreateRequest);
        userRepository.save(user);

        return ApiResponse.<Void>builder()
                .code(HttpStatus.CREATED.value())
                .message("User registered successfully")
                .build();
    }
}
