package org.example.demo.demoproject.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MyArticle {

    private Integer articleId;
    private LocalDateTime articleInsertionDate;

    private String articleAuthor;
    private String articleTitle;
    private String articleDescription;
    private String articleUrl;
    private String articleUrlToImage;
    private String articlePublishedAt;

    private String sourceName;
    private String sourceDescription;
    private String sourceUrl;
    private String sourceCategory;
    private String sourceLanguage;
    private String sourceCountry;
}
