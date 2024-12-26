package com.example.project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable
{
    DBAdapter dbAdapter = new DBAdapter();

    @FXML
    protected void OpenPostRedactor() throws IOException
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

//        PostController goodbyeController = loader.getController();
//        goodbyeController.addData(checks, randomValue, checksCount);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        dbAdapter.create_or_connection();
    }

}