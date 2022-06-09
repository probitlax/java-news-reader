package org.example.demo.demoproject.service;

import lombok.extern.slf4j.Slf4j;
import org.example.demo.demoproject.model.MyArticle;
import org.example.demo.demoproject.model.newsApiDomain.response.ArticleResponse;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;

import static org.example.demo.demoproject.Constant.Constant.*;

@Component
@Slf4j
public class NewsReaderJob {
    NewsAPIServiceClient newsAPIServiceClient;
    ArticleService articleService;

    public NewsReaderJob(ArticleService articleService, NewsAPIServiceClient newsAPIServiceClient) {
        this.articleService = articleService;
        this.newsAPIServiceClient = newsAPIServiceClient;
    }

    @Scheduled(fixedDelayString = SCHEDULER_FIXED_DELAY)
    public void readAndSaveNewNews() {
        log.info("Scheduled Job is Started...");
        ArticleResponse latestNews = newsAPIServiceClient.getLatestNews();
        Set<MyArticle> myArticles = articleService.formatArticles(latestNews);
        articleService.persistArticles(myArticles);
    }
}
