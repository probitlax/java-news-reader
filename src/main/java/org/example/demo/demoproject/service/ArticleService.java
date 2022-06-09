package org.example.demo.demoproject.service;

import lombok.extern.slf4j.Slf4j;
import org.example.demo.demoproject.repository.ArticleRepository;
import org.example.demo.demoproject.exception.NoNewsFoundException;
import org.example.demo.demoproject.mapper.ArticleMapper;
import org.example.demo.demoproject.model.MyArticle;
import org.example.demo.demoproject.model.newsApiDomain.response.ArticleResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Service
@Slf4j
public class ArticleService {

    private ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }


    public Set<MyArticle> formatArticles(ArticleResponse articleResponse) {
        Set<MyArticle> myArticles = new LinkedHashSet<>();
        LocalDateTime localDateTime = LocalDateTime.now();

        articleResponse.getArticles().forEach(article -> {
                    MyArticle myArticle = ArticleMapper.INSTANCE.mapNewsApiArticleToMyArticle(article, article.getSource(), null, localDateTime);
                    myArticles.add(myArticle);
                }
        );
        log.info("articles are modified and mapped to their new model.");
        return myArticles;
    }

    public void persistArticles(Set<MyArticle> myArticles) {
        articleRepository.createArticleTable();
        myArticles.forEach(myArticle -> articleRepository.saveArticle(myArticle));
    }

    public Set<MyArticle> getLatestArticles() {
        Set<MyArticle> latestArticles = articleRepository.findTenLatestArticles();
        if(latestArticles.isEmpty())
            throw new NoNewsFoundException("No news was found in database. News is not ready!");
        return latestArticles;
    }

    public int deleteArticleByName(String articleAuthor) {
        return articleRepository.deleteArticleByAuthorName(articleAuthor);
    }

}
