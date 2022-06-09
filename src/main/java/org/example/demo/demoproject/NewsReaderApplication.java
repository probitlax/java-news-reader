package org.example.demo.demoproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@EnableSwagger2
@SpringBootApplication
@EnableScheduling
public class NewsReaderApplication {
    public static void main(String[] args) {
        SpringApplication.run(NewsReaderApplication.class, args);

    }
}
