package com.example.project;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    private Integer ID_Post = null;
    HelloController hello;

    DBAdapter dbAdapter = new DBAdapter();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        dbAdapter.create_or_connection();

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
//                formatter = formatter.withLocale( putAppropriateLocaleHere );  // Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH
                LocalDate date = LocalDate.parse(Posts.get(i).getDate(), formatter);
                data.setValue(date);
                text.setText(Posts.get(i).getPost());
                slider.setValue(Posts.get(i).getMood());
                ID_Post = ID;
                hello = helloController;
                break;
            }
        }
    }

    public void SaveChange() throws SQLException {
        if (ID_Post == null)
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            String formattedString = data.getValue().format(formatter);
            dbAdapter.insert_data(formattedString, text.getText(),(int)slider.getValue());
            Stage stage = (Stage) (text.getScene().getWindow());
            stage.hide();
        }
        else
        {
        }
    }
}
