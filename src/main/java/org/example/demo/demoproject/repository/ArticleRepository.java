package org.example.demo.demoproject.repository;

import org.example.demo.demoproject.model.MyArticle;

import java.util.Set;

public interface ArticleRepository {
    void createArticleTable();

    void saveArticle(MyArticle myArticle);

    Set<MyArticle> findTenLatestArticles();

    int deleteArticleByAuthorName(String authorName);
}
