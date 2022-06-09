package org.example.demo.demoproject.mapper;


import org.example.demo.demoproject.model.MyArticle;
import org.example.demo.demoproject.model.newsApiDomain.Article;
import org.example.demo.demoproject.model.newsApiDomain.Source;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
//TODO: model mapper
@Mapper
public interface ArticleMapper {

    ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);


    @Mapping(source = "articleId", target = "articleId")
    @Mapping(source = "articleInsertionDate", target = "articleInsertionDate")
    @Mapping(source = "newsApiArticle.author", target = "articleAuthor")
    @Mapping(source = "newsApiArticle.title", target = "articleTitle")
    @Mapping(source = "newsApiArticle.description", target = "articleDescription")
    @Mapping(source = "newsApiArticle.url", target = "articleUrl")
    @Mapping(source = "newsApiArticle.urlToImage", target = "articleUrlToImage")
    @Mapping(source = "newsApiArticle.publishedAt", target = "articlePublishedAt")
    @Mapping(source = "newsApiSource.name", target = "sourceName")
    @Mapping(source = "newsApiSource.description", target = "sourceDescription")
    @Mapping(source = "newsApiSource.url", target = "sourceUrl")
    @Mapping(source = "newsApiSource.category", target = "sourceCategory")
    @Mapping(source = "newsApiSource.language", target = "sourceLanguage")
    @Mapping(source = "newsApiSource.country", target = "sourceCountry")
    MyArticle mapNewsApiArticleToMyArticle(Article newsApiArticle,
                                           Source newsApiSource,
                                           String articleId,
                                           LocalDateTime articleInsertionDate);

}
