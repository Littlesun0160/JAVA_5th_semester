module com.example.project {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.xerial.sqlitejdbc;


    opens com.example.project to javafx.fxml;
    exports com.example.project;
}