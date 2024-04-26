module eajteam.cab302groupproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens eajteam.cab302groupproject to javafx.fxml;
    exports eajteam.cab302groupproject;
    exports eajteam.cab302groupproject.controller;
    opens eajteam.cab302groupproject.controller to javafx.fxml;
}