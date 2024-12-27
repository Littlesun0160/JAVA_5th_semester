package com.example.project;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.ResourceBundle;

public class PostController implements Initializable
{
    @FXML
    private TextArea text;
    @FXML
    private ImageView foto;
    @FXML
    private DatePicker data;
    @FXML
    private Label mood1;
    @FXML
    private Slider slider;
    @FXML
    private Pagination indicator;

    private Integer ID_Post = null;
    HelloController hello;

    DBAdapter dbAdapter = new DBAdapter();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        dbAdapter.create_or_connection();

        IntegerProperty currentPageIndex = new SimpleIntegerProperty(-1);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), event ->
        {
            currentPageIndex.set(indicator.getCurrentPageIndex());
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);


        currentPageIndex.addListener(new ChangeListener<Number>()
        {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
            {
                System.out.println(newValue.intValue());
                switch(newValue.intValue())
                {
                    case 0:
                        foto.setImage(new Image((Objects.requireNonNull(getClass().getResource("/images/photo0.jpg")).toExternalForm())));
                        break;
                    case 1:
                        foto.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/photo1.jpg")).toExternalForm()));
                       break;
                    case 2:
                        foto.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/photo2.jpg")).toExternalForm()));
                        break;
                    case 3:
                        foto.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/photo3.jpg")).toExternalForm()));
                        break;
                    case 4:
                        foto.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/photo4.jpg")).toExternalForm()));
                        break;
                }
            }
        });
        timeline.play();

        slider.setMin(0);
        slider.setMax(4);
        slider.setMajorTickUnit(1);
        slider.valueProperty().addListener(new ChangeListener<Number>()
        {
            public void changed(ObservableValue<? extends Number> obsVal,
                                Number oldVal, Number newVal)
            {
                System.out.println(newVal.intValue());
                switch(newVal.intValue())
                {
                    case 0:
                        mood1.setText("Ужасно");
                        mood1.setTextFill(Color.DARKVIOLET); break;
                    case 1:
                        mood1.setText("Плохо");
                        mood1.setTextFill(Color.VIOLET); break;
                    case 2:
                        mood1.setText("Нормально");
                        mood1.setTextFill(Color.BLUE); break;
                    case 3:
                        mood1.setText("Хорошо");
                        mood1.setTextFill(Color.LIGHTGREEN); break;
                    case 4:
                        mood1.setText("Отлично!");
                        mood1.setTextFill(Color.GREEN); break;
                }
            }
        });

    }

    public void EditPost (int ID, HelloController helloController) throws SQLException
    {
        ObservableList<Post> Posts = dbAdapter.select_data();
        for (int i=0; i<Posts.size(); i++)
        {
            if (Posts.get(i).getId() == ID)
            {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                LocalDate date = LocalDate.parse(Posts.get(i).getDate(), formatter);
                data.setValue(date);
                text.setText(Posts.get(i).getPost());
                slider.setValue(Posts.get(i).getMood());
                indicator.setCurrentPageIndex(Integer.parseInt(Posts.get(i).getPhoto()));
                ID_Post = ID;
                hello = helloController;
                break;
            }
        }
    }

    public void SaveChange() throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formattedString = data.getValue().format(formatter);
        if (ID_Post == null)
        {
            dbAdapter.insert_data(formattedString, text.getText(),(int)slider.getValue(), String.valueOf(indicator.getCurrentPageIndex()));
        }
        else
        {
            dbAdapter.update_data(ID_Post, formattedString, text.getText(),(int)slider.getValue(), String.valueOf(indicator.getCurrentPageIndex()));
        }
        Stage stage = (Stage) (text.getScene().getWindow());
        stage.hide();
    }
}
