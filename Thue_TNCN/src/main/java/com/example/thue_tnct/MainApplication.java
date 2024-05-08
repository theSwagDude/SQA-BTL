package com.example.thue_tnct;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/views/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        URL cssFileUrl = getClass().getResource("/views/css/styles.css");
        if (cssFileUrl != null) {
            scene.getStylesheets().add(cssFileUrl.toExternalForm());
        } else {
            System.out.println("Không thể tìm thấy tệp CSS.");
        }
        stage.setTitle("Thuế");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}