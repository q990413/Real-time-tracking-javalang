module com.cab302groupproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javax.mail.api;

    exports com.cab302groupproject;
    opens com.cab302groupproject to javafx.fxml;
    exports com.cab302groupproject.model;
    exports com.cab302groupproject.controller;
    opens com.cab302groupproject.controller to javafx.fxml;
}