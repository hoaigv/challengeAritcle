package com.challenge.aritcle.commnets.mappers;

import com.challenge.aritcle.aricles.mappers.converter.ArticleConverter;
import com.challenge.aritcle.commnets.controllers.dto.CommentCreateRequest;
import com.challenge.aritcle.commnets.controllers.dto.CommentGetResponse;
import com.challenge.aritcle.commnets.models.CommentEntity;
import com.challenge.aritcle.users.mappers.converter.UserConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring" , nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,uses = {UserConverter.class , ArticleConverter.class})
public interface ICommentMapper {
    CommentEntity commentDtoToEntity(CommentCreateRequest commentCreateRequest);
    @Mapping(target = "username",source = "user" ,qualifiedByName = {"UserConverter","usersToUserName"})
    @Mapping(target = "articleId" , source = "article" , qualifiedByName = {"ArticleConverter","articleToArticleId"})
    CommentGetResponse commentEntityToDto(CommentEntity comment);
}
    