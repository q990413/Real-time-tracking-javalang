module org.example.cab302groupproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.cab302groupproject to javafx.fxml;
    exports org.example.cab302groupproject;
    exports org.example.cab302groupproject.controller;
    opens org.example.cab302groupproject.controller to javafx.fxml;
}