package com.example.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HelloController implements Initializable
{
    DBAdapter dbAdapter = new DBAdapter();
    @FXML
    private TableView<Post> table;
    @FXML
    private TableColumn<Post, String> dateColumn;
    @FXML
    private TableColumn<Post, String> postColumn;
    @FXML
    private TableColumn<Post, Integer> idColumn;

    @FXML
    protected PostController OpenPostRedactor() throws IOException
    {
//        Stage stage = (Stage) (targetValue.getScene().getWindow());
//        stage.hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("post-view.fxml"));
        Scene scene = new Scene(loader.load(), 666, 499);
        Stage stage = new Stage();
        stage.setTitle("Запись");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        return loader.getController();
//        PostController goodbyeController = loader.getController();
//        goodbyeController.addData(checks, randomValue, checksCount);
    }
    public void EditPost() throws IOException, SQLException
    {
        try
        {
            PostController postController = OpenPostRedactor();
            postController.EditPost(table.getSelectionModel().getSelectedItem().getId(), this);
        } catch (Exception e) {
        }
    }
    public  void UpdateBase()
    {
        Init();
    }
    public void DeletePost()
    {
        try
        {
            int IDelete = table.getSelectionModel().getSelectedItem().getId();
            dbAdapter.delete_data(IDelete);
        } catch (Exception e) {
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        Init();
    }
    protected void Init ()
    {
        dbAdapter.create_or_connection();
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        postColumn.setCellValueFactory(new PropertyValueFactory<>("post"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        ObservableList<Post> posts = null;
        try {
            posts = dbAdapter.select_data();
            table.setItems(posts);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}