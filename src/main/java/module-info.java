module eajteam.cab302groupproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javax.mail.api;


    opens com.cab302groupproject to javafx.fxml;
    exports com.cab302groupproject;
    exports com.cab302groupproject.controller;
    opens com.cab302groupproject.controller to javafx.fxml;
}