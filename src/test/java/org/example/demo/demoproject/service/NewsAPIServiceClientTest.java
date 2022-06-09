package org.example.demo.demoproject.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.demo.demoproject.data.DataFixture;
import org.example.demo.demoproject.model.newsApiDomain.Article;
import org.example.demo.demoproject.model.newsApiDomain.Source;
import org.example.demo.demoproject.model.newsApiDomain.response.ArticleResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(NewsAPIServiceClient.class)
@AutoConfigureWebClient(registerRestTemplate = true)
class NewsAPIServiceClientTest {

    @Autowired
    private NewsAPIServiceClient newsAPIServiceClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockRestServiceServer mockRestServiceServer;

    @Value("${news.api.uri}")
    private String newsApiUri;

    @Value("${news.api.key}")
    private String newsApiKey;

    @Test
    void getLatestNews() throws JsonProcessingException {
        String newsApiUrl = newsApiUri + newsApiKey;
        String json = getJsonOfResponseObject();
        this.mockRestServiceServer
                .expect(requestTo(newsApiUrl))
                .andRespond(withSuccess(json, MediaType.APPLICATION_JSON));

        ArticleResponse articleResponseResult = newsAPIServiceClient.getLatestNews();
        assertNotNull(articleResponseResult);
        assertEquals(1 , articleResponseResult.getTotalResults());
        assertEquals("author1" , articleResponseResult.getArticles().get(0).getAuthor());
        assertEquals("source1" , articleResponseResult.getArticles().get(0).getSource().getName());
    }

    private String getJsonOfResponseObject() throws JsonProcessingException {
        ArticleResponse articleResponse = new ArticleResponse();
        articleResponse.setStatus("status");
        articleResponse.setTotalResults(1);
        articleResponse.setArticles(DataFixture.anyValidListOfArticles());

        String json =this.objectMapper.writeValueAsString(articleResponse);
        return json;
    }
}