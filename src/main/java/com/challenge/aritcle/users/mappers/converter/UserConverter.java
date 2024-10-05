package com.challenge.aritcle.users.mappers.converter;

import com.challenge.aritcle.users.models.UserEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Named("UserConverter")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
@Component
public class UserConverter {

    @Named("usersToUserName")
    public String usersToUserName(UserEntity user) {
        return user.getUsername();
    }
}
