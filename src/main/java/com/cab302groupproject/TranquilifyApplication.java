package com.cab302groupproject;

import com.cab302groupproject.model.SendEmail;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.SecureDirectoryStream;

public class TranquilifyApplication extends Application {
    // Constants defining the window title and size
    public static final String TITLE = "Tranquilify";
    public static final int WIDTH = 640;
    public static final int HEIGHT = 360;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TranquilifyApplication.class.getResource("sign-up-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        //SendEmail send = new SendEmail("emersonsmall8@gmail.com", "testsubject", "testbody");
        launch();
    }
}