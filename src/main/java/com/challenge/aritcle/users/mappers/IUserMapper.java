package com.challenge.aritcle.users.mappers;

import com.challenge.aritcle.users.controllers.dto.UserCreateRequest;
import com.challenge.aritcle.users.models.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring" , nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface IUserMapper {
    UserEntity userDtoToUserEntity(UserCreateRequest userCreateRequest);
}
