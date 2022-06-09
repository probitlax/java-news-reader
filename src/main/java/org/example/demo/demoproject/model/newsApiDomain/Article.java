package org.example.demo.demoproject.model.newsApiDomain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Kwabena Berko on 5/7/2018.
 */

@Getter
@Setter
@NoArgsConstructor
public class Article {
    private Source source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;

}
