package org.example.demo.demoproject.service;

import org.example.demo.demoproject.data.DataFixture;
import org.example.demo.demoproject.exception.NoNewsFoundException;
import org.example.demo.demoproject.repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;

import static org.example.demo.demoproject.data.DataFixture.anyValidSetOfMyArticles;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class ArticleServiceTest {

    @Spy
    @InjectMocks
    ArticleService articleService;

    @Mock
    ArticleRepository articleRepository;

    @Test
    void given_getLatestArticles_when_noArticleWasFound_then_ThrowException() {
        Mockito.when(articleRepository.findTenLatestArticles()).thenReturn(Collections.emptySet());
        assertThrows(NoNewsFoundException.class, ()-> articleService.getLatestArticles());
    }

    @Test
    void given_getLatestArticles_when_someArticleWasFound_then_returnThem() {
        Mockito.when(articleRepository.findTenLatestArticles()).thenReturn(anyValidSetOfMyArticles());
        articleService.getLatestArticles();
        verify(articleService, times(1)).getLatestArticles();
    }
}