package com.challenge.aritcle.aricles.mappers;

import com.challenge.aritcle.aricles.controllers.dto.ArticleCreateRequest;
import com.challenge.aritcle.aricles.controllers.dto.ArticleGetResponse;
import com.challenge.aritcle.aricles.controllers.dto.ArticleUpdateRequest;
import com.challenge.aritcle.aricles.models.ArticleEntity;
import com.challenge.aritcle.users.mappers.converter.UserConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring" , nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,uses = {UserConverter.class})
public interface IArticleMapper {
    ArticleEntity articleDtoToEntity(ArticleCreateRequest articleCreateRequest);
    @Mapping(target = "username",source = "user" ,qualifiedByName = {"UserConverter","usersToUserName"})
    ArticleGetResponse articleEntityToDto(ArticleEntity articleEntity);

    void updateArticleFromDto(ArticleUpdateRequest articleUpdateRequest, @MappingTarget ArticleEntity oldArticle);
}
