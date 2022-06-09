package org.example.demo.demoproject.service;

import lombok.extern.slf4j.Slf4j;
import org.example.demo.demoproject.model.newsApiDomain.response.ArticleResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class NewsAPIServiceClient {
    private RestTemplate restTemplate;

    @Value("${news.api.uri}")
    private String newsApiUri;

    @Value("${news.api.key}")
    private String newsApiKey;

    public NewsAPIServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ArticleResponse getLatestNews() {
        String newsApiUrl = newsApiUri + newsApiKey;
        log.info("NewsApi will be called : GET " + newsApiUrl);
        ArticleResponse articleResponse = restTemplate.getForObject(newsApiUrl, ArticleResponse.class);
        log.info("NewsApi was called and " + articleResponse.getArticles().size() + " articles was fetched.");
        return articleResponse;
    }


}
