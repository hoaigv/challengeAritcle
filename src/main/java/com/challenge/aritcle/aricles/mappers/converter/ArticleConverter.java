package com.challenge.aritcle.aricles.mappers.converter;

import com.challenge.aritcle.aricles.models.ArticleEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Named("ArticleConverter")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
@Component
public class ArticleConverter {
    @Named("articleToArticleId")
    public String articleToArticleId(ArticleEntity article) {
        return article.getId();
    }
}
