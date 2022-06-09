package org.example.demo.demoproject.model.newsApiDomain;

import lombok.*;

/**
 * Created by Kwabena Berko on 5/7/2018.
 */
@Getter
@Setter
@NoArgsConstructor
public class Source {
    private String id;
    private String name;
    private String description;
    private String url;
    private String category;
    private String language;
    private String country;

}
