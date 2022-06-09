package org.example.demo.demoproject.rest;

import org.example.demo.demoproject.model.MyArticle;
import org.example.demo.demoproject.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNullApi;
import org.springframework.web.bind.annotation.*;
import static org.example.demo.demoproject.Constant.Constant.NO_ROWS_WAS_DELETED;
import static org.example.demo.demoproject.Constant.Constant.ROW_ROWS_WAS_WERE_SUCCESSFULLY_DELETED;
import java.util.Set;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

import static org.example.demo.demoproject.Constant.Constant.ARTICLE_RESOURCE_URL;

@RestController
@RequestMapping(  ARTICLE_RESOURCE_URL)
@Api(value = ARTICLE_RESOURCE_URL)
public class ArticleRestController {

    ArticleService articleService;

    public ArticleRestController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("")
    private ResponseEntity<Set<MyArticle>> getLatestArticles() {
        return ResponseEntity.ok().body(articleService.getLatestArticles());
    }

    @DeleteMapping("{authorName}")
    private ResponseEntity<String> deleteArticleByAuthorName(@ApiParam(required = true) @PathVariable String authorName) {
        int deletedRowsNumber = articleService.deleteArticleByName(authorName);
        return (deletedRowsNumber > 0) ? ResponseEntity.ok().body(deletedRowsNumber + ROW_ROWS_WAS_WERE_SUCCESSFULLY_DELETED) : ResponseEntity.ok().body(NO_ROWS_WAS_DELETED);
    }
}
