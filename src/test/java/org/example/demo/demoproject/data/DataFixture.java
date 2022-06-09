package org.example.demo.demoproject.data;

import org.example.demo.demoproject.model.MyArticle;
import org.example.demo.demoproject.model.newsApiDomain.Article;
import org.example.demo.demoproject.model.newsApiDomain.Source;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class DataFixture {

    public static LocalDateTime anyValidFixedDateAndTime(){
        return LocalDateTime.of(2022, Month.JANUARY, 01, 10, 10, 10);
    }

    public static MyArticle anyValidMyArticle(int extNum){
        MyArticle myArticle = MyArticle.builder().
                                        articleId(extNum).
                                        articleInsertionDate(anyValidFixedDateAndTime()).
                                        articleAuthor("articleAuthor"+extNum).
                                        articleTitle("articleTitle"+extNum).
                                        articleDescription("articleDescription"+extNum).
                                        articleUrl("articleUrl"+extNum).
                                        articleUrlToImage("articleUrlToImage"+extNum).
                                        articlePublishedAt("articlePublishedAt"+extNum).
                                        sourceName("sourceName"+extNum).
                                        sourceDescription("sourceDescription"+extNum).
                                        sourceUrl("sourceUrl"+extNum).
                                        sourceCategory("sourceCategory"+extNum).
                                        sourceLanguage("sourceLanguage"+extNum).
                                        sourceCountry("sourceCountry"+extNum).build();
        return myArticle;
    }

    public static Set<MyArticle> anyValidSetOfMyArticles() {
        Set<MyArticle> myArticles = new LinkedHashSet<>();
        for(int i=1; i<=3; i++){
            myArticles.add(anyValidMyArticle(i));
        }
        return myArticles;
    }

    public static Article anyValidArticle(int extNum){
        Article article = new Article();
        article.setAuthor("author"+extNum);
        article.setTitle("title"+extNum);
        Source source = new Source();
        source.setName("source"+extNum);
        source.setCategory("category"+extNum);
        source.setCountry("country"+extNum);
        source.setDescription("description"+extNum);
        article.setSource(source);

        return article;
    }

    public static List<Article> anyValidListOfArticles() {
        List<Article> articles = new LinkedList<>();
        for(int i=1; i<=3; i++){
            articles.add(anyValidArticle(i));
        }
        return articles;
    }

}
