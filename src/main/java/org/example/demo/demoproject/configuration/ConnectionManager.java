package org.example.demo.demoproject.configuration;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
@Slf4j
public class ConnectionManager {

    @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    @Value("${spring.datasource.driverClassName}")
    private String driverName;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    private Connection con;

    @Bean
    @Scope("singleton")
    public Connection getConnection() {
        try {
            Class.forName(driverName);
            try {
                con = DriverManager.getConnection(dataSourceUrl, username, password);
            } catch (SQLException ex) {
                log.info("Failed to create the database connection.");
            }
        } catch (ClassNotFoundException ex) {
            log.info("Driver not found.");
        }
        return con;
    }
}
