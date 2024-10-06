package com.challenge.aritcle.commnets.mappers;

import com.challenge.aritcle.aricles.models.ArticleEntity;
import com.challenge.aritcle.commnets.controllers.dto.CommentCreateRequest;
import com.challenge.aritcle.commnets.controllers.dto.CommentGetResponse;
import com.challenge.aritcle.commnets.models.CommentEntity;
import com.challenge.aritcle.users.mappers.converter.UserConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring" , nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,uses = {UserConverter.class})
public interface ICommentMapper {
    CommentEntity commentDtoToEntity(CommentCreateRequest commentCreateRequest);
    @Mapping(target = "username",source = "user" ,qualifiedByName = {"UserConverter","usersToUserName"})
    CommentGetResponse commentEntityToDto(CommentEntity comment);
}
    