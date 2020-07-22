package UI;

import DAO.DBArticles;
import DAO.DBDataCollection;
import Resources.DataCollection;
import Utilities.VergeSraper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GUIController implements Initializable {
    @FXML
    TableView<DataCollection> dataView;
    @FXML
    TableColumn<DataCollection, String> dataSource, articlesScraped, articlesServed;
    @FXML
    Button scrapeStart, scrapeStop;
    private ObservableList<DataCollection> data = FXCollections.observableArrayList();
    private boolean timerStarted = false;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataSource.setCellValueFactory(new PropertyValueFactory<>("source"));
        articlesScraped.setCellValueFactory(new PropertyValueFactory<>("scraped"));
        articlesServed.setCellValueFactory(new PropertyValueFactory<>("served"));
        try {
            data = DBDataCollection.getData();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.print(data.size());
        dataView.setItems(data);
        ActionListener listener = e -> {
            try {
                DBArticles.writeArticles(VergeSraper.getVergeArticles());

                dataView.getItems().clear();
                dataView.setItems(DBDataCollection.getData());
            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
            }
        };

        Timer timer = new Timer(300000, listener);

        scrapeStart.setOnAction(event -> {
            if(timerStarted){
                timer.restart();
            }else {
                timer.start();
                timerStarted = true;
            }
        });
        scrapeStop.setOnAction(event -> {
            timer.stop();
        });
    }

}
