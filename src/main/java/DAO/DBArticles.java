package DAO;

import Resources.NewsArticle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBArticles {
    static Connection connection;

    public static void writeArticles(List<NewsArticle> articles) throws SQLException {
        connection = DBConnect.getConnection();
        for (NewsArticle article : articles) {
            if(!exists(article.getTitle())) {
                String query = "INSERT INTO article ("
                        + "title,"
                        + "url,"
                        + "author,"
                        + "image,"
                        + "source)"
                        + "VALUES (?,?,?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, article.getTitle());
                preparedStatement.setString(2, article.getUrl());
                preparedStatement.setString(3, article.getAuthor());
                preparedStatement.setString(4, article.getImg());
                preparedStatement.setString(5, article.getSrc());
                preparedStatement.executeUpdate();
            }
        }
        DBDataCollection.updateScraped();
        connection.close();
    }

    private static boolean exists(String title) throws SQLException {
        int count = 0;
        connection = DBConnect.getConnection();
        String query = "SELECT articleId FROM article WHERE title='"+title+"'";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()){
            count++;
        }
        if(count !=0){
            return true;
        }else {
            return false;
        }
    }

    public List<NewsArticle> getArticles() throws SQLException {
        connection = DBConnect.getConnection();
        List<NewsArticle> articles = new ArrayList<NewsArticle>();
        String query = "SELECT * FROM article";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()){
            NewsArticle article = new NewsArticle();
            article.setArticleId(resultSet.getInt("articleId"));
            article.setTitle(resultSet.getString("title"));
            article.setUrl(resultSet.getString("url"));
            article.setAuthor(resultSet.getString("author"));
            article.setImg(resultSet.getString("image"));
            article.setSrc(resultSet.getString("source"));
            if(!(article ==null)){
                articles.add(article);
            }
        }
        connection.close();
        return articles;
    }
}
