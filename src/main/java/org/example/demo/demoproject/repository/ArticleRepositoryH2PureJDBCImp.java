package org.example.demo.demoproject.repository;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.demo.demoproject.configuration.ConnectionManager;
import org.example.demo.demoproject.model.MyArticle;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Component
@Slf4j
@NoArgsConstructor
public class ArticleRepositoryH2PureJDBCImp implements ArticleRepository {
    private static final String SQL_CREATE_TABLE = "create table if not exists tbl_article(artifact_id bigint auto_increment primary key,artifact_insertion_date timestamp,artifact_author varchar(2000),artifact_title varchar(2000),artifact_desc varchar(100000),artifact_url varchar(2000),artifact_url_img varchar(2000),artifact_published_at varchar(2000),source_name varchar(2000),source_desc varchar(100000),source_url varchar(2000),source_category varchar(2000),source_lang varchar(2000),source_country varchar(2000))";
    private static final String SQL_INSERT_ARTICLE = "insert into tbl_article (artifact_id,artifact_insertion_date,artifact_author,artifact_title,artifact_desc,artifact_url,artifact_url_img,artifact_published_at,source_name,source_desc,source_url,source_category,source_lang,source_country) values(null,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_SELECT_NEWEST_ARTICLE = "select artifact_id,artifact_insertion_date,artifact_author,artifact_title,artifact_desc,artifact_url,artifact_url_img,artifact_published_at,source_name,source_desc,source_url,source_category,source_lang,source_country  from tbl_article  order by artifact_insertion_date ,artifact_id  limit 10 offset 0";
    private static final String SQL_DELETE_ARTICLE_BY_AUTHOR = "delete  from tbl_article  where artifact_author = ?";


    private ConnectionManager connectionManager;

    public ArticleRepositoryH2PureJDBCImp(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }




    @Override
    public void createArticleTable() {
        try (Connection con = connectionManager.getConnection();
             Statement stmt = con.createStatement();) {
            stmt.executeUpdate(SQL_CREATE_TABLE);
            log.info("article table is created.");
        } catch (SQLException ex) {
            log.error(ex.getMessage());
        }
    }


    @Override
    public void saveArticle(MyArticle myArticle) {
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(SQL_INSERT_ARTICLE)) {
            mapMyArticleModelToArticleTable(myArticle, preparedStatement);

            preparedStatement.executeUpdate();
            log.info("an article was inserted into the database: AUTHOR=("+myArticle.getArticleAuthor()+"); SOURCE=(" + myArticle.getSourceName() + ") was inserted into the table.");
        } catch (SQLException ex) {
            log.error(ex.getMessage());
        }
    }


    private void mapMyArticleModelToArticleTable(MyArticle myArticle, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setTimestamp(1, Timestamp.valueOf(myArticle.getArticleInsertionDate()));
        preparedStatement.setString(2, myArticle.getArticleAuthor());
        preparedStatement.setString(3, myArticle.getArticleTitle());
        preparedStatement.setString(4, myArticle.getArticleDescription());
        preparedStatement.setString(5, myArticle.getArticleUrl());
        preparedStatement.setString(6, myArticle.getArticleUrlToImage());
        preparedStatement.setString(7, myArticle.getArticlePublishedAt());
        preparedStatement.setString(8, myArticle.getSourceName());
        preparedStatement.setString(9, myArticle.getSourceDescription());
        preparedStatement.setString(10, myArticle.getSourceUrl());
        preparedStatement.setString(11, myArticle.getSourceCategory());
        preparedStatement.setString(12, myArticle.getSourceLanguage());
        preparedStatement.setString(13, myArticle.getSourceCountry());
    }


    @Override
    public Set<MyArticle> findTenLatestArticles() {
        Set<MyArticle> myArticles = new LinkedHashSet<>();
        ;
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(SQL_SELECT_NEWEST_ARTICLE)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                MyArticle myArticle = mapMyArticleTableToArticleModel(resultSet);
                myArticles.add(myArticle);
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage());
        }
        return myArticles;
    }


    private MyArticle mapMyArticleTableToArticleModel(ResultSet resultSet) throws SQLException {
        MyArticle myArticle = new MyArticle();
        myArticle.setArticleId(resultSet.getInt("artifact_id"));
        myArticle.setArticleInsertionDate(resultSet.getTimestamp("artifact_insertion_date").toLocalDateTime());
        myArticle.setArticleAuthor(resultSet.getString("artifact_author"));
        myArticle.setArticleTitle(resultSet.getString("artifact_title"));
        myArticle.setArticleDescription(resultSet.getString("artifact_desc"));
        myArticle.setArticleUrl(resultSet.getString("artifact_url"));
        myArticle.setArticleUrlToImage(resultSet.getString("artifact_url_img"));
        myArticle.setArticlePublishedAt(resultSet.getString("artifact_published_at"));
        myArticle.setSourceName(resultSet.getString("source_name"));
        myArticle.setSourceDescription(resultSet.getString("source_desc"));
        myArticle.setSourceUrl(resultSet.getString("source_url"));
        myArticle.setSourceCategory(resultSet.getString("source_category"));
        myArticle.setSourceLanguage(resultSet.getString("source_lang"));
        myArticle.setSourceCountry(resultSet.getString("source_country"));
        return myArticle;
    }


    @Override
    public int deleteArticleByAuthorName(String authorName) {
        int deletedRowsNumber = 0;
        try (Connection con = connectionManager.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(SQL_DELETE_ARTICLE_BY_AUTHOR)) {
            preparedStatement.setString(1, authorName);
            deletedRowsNumber = preparedStatement.executeUpdate();
            log.info(deletedRowsNumber + " record was deleted from the table.");
        } catch (SQLException ex) {
            log.error(ex.getMessage());
        }
        return deletedRowsNumber;
    }
}
