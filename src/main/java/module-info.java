module org.example.cab302groupproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.cab302groupproject to javafx.fxml;
    exports org.example.cab302groupproject;
}