package org.example.demo.demoproject.rest;

import org.example.demo.demoproject.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.example.demo.demoproject.Constant.Constant.ARTICLE_RESOURCE_URL;
import static org.example.demo.demoproject.Constant.Constant.NO_ROWS_WAS_DELETED;
import static org.example.demo.demoproject.Constant.Constant.ROW_ROWS_WAS_WERE_SUCCESSFULLY_DELETED;
import static org.example.demo.demoproject.data.DataFixture.anyValidSetOfMyArticles;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(ArticleRestController.class)
class ArticleRestControllerTest {


    @Autowired
    MockMvc mockMvc;

    @MockBean
    ArticleService articleService;

    @Test
    void given_getLatestArticles_when_ThreeArticlesFound_then_showThreeArticlesInResponse() throws Exception {
        when(articleService.getLatestArticles()).thenReturn(anyValidSetOfMyArticles());

        MvcResult mvcResult =
                mockMvc.perform(get(ARTICLE_RESOURCE_URL).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")).
                        andDo(print()).
                        andExpect(status().is2xxSuccessful()).
                        andExpect(jsonPath("$[0].articleAuthor", equalTo("articleAuthor1"))).
                        andExpect(jsonPath("$[0].sourceName", equalTo("sourceName1"))).
                        andExpect(jsonPath("$[1].articleAuthor", equalTo("articleAuthor2"))).
                        andExpect(jsonPath("$[1].sourceName", equalTo("sourceName2"))).
                        andExpect(jsonPath("$[2].articleAuthor", equalTo("articleAuthor3"))).
                        andExpect(jsonPath("$[2].sourceName", equalTo("sourceName3"))).
                        andReturn();
    }


    @Test
    void given_deleteArticleByAuthorName_when_AuthorNameIsFoundInDB_then_showDeletedSuccessfullyResponse() throws Exception {
        Mockito.when(articleService.deleteArticleByName(anyString())).thenReturn(1);
        String authorName = "Sample Author Name";
        boolean result =  mockMvc.perform(delete(String.format(ARTICLE_RESOURCE_URL+"/%s",authorName)).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")).
                andDo(print()).
                andExpect(status().is2xxSuccessful()).
                andReturn().
                getResponse().
                getContentAsString().
                contains(ROW_ROWS_WAS_WERE_SUCCESSFULLY_DELETED);

        assertTrue(result);
    }

    @Test
    void given_deleteArticleByAuthorName_when_AuthorNameIsNotFoundInDB_then_showNoNewsFoundResponse() throws Exception {
        Mockito.when(articleService.deleteArticleByName(anyString())).thenReturn(0);
        String authorName = "Sample Author Name";
        boolean result =  mockMvc.perform(delete(String.format(ARTICLE_RESOURCE_URL+"/%s",authorName)).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")).
                andDo(print()).
                andExpect(status().is2xxSuccessful()).
                andReturn().
                getResponse().
                getContentAsString().
                contains(NO_ROWS_WAS_DELETED);

        assertTrue(result);
    }


}



