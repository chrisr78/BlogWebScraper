package DAO;

import Resources.DataCollection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DBDataCollection {
    private static ObservableList<DataCollection> collections = FXCollections.observableArrayList();

    public static ObservableList<DataCollection> getData() throws SQLException {
        Connection connection = DBConnect.getConnection();
        updateScraped();
        String getData = "select scraped.source, scraped.count as scraped, served.count as served from scraped left join served on scraped.source = served.source";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(getData);
        while (resultSet.next()){
            DataCollection dc = new DataCollection();
            dc.setSource(resultSet.getString("source"));
            dc.setScraped(resultSet.getInt("scraped"));
            dc.setServed(resultSet.getInt("served"));
            collections.add(dc);
        }
        connection.close();
        return collections;
    }

    public static void updateScraped() throws SQLException {
        Connection connection = DBConnect.getConnection();
        String getData = "select source, count(source) as count from article";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(getData);
        while (resultSet.next()){
            String source = resultSet.getString("source");
            int count = resultSet.getInt("count");
            if(newSource(source,connection)){
                String updateData = "UPDATE scraped SET count = ? WHERE source = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(updateData);
                preparedStatement.setInt(1, count);
                preparedStatement.setString(2, source);
                preparedStatement.executeUpdate();
            }else {
               String insertData = "INSERT INTO scraped VALUES(?,?)";
               PreparedStatement prepStatement = connection.prepareStatement(insertData);
               prepStatement.setString(1, source);
               prepStatement.setInt(2,count);
               prepStatement.executeUpdate();
            }
        }
        connection.close();
    }

    private static boolean newSource(String source, Connection connection) throws SQLException {
        boolean exists = false;
        String selectSource = "SELECT * FROM scraped";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(selectSource);
        while (resultSet.next()){

            if (resultSet.getString("source").equals(source)){
                exists = true;
            }
        }
        return exists;
    }
}
