package com.example.converter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainWindow extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("MainWindowInterface.fxml"));
        Scene scene=new Scene(loader.load());
        scene.setFill(Color.TRANSPARENT);
        stage.setTitle("Converter!");
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setResizable(false);
        stage.setScene(scene);
        //stage.getIcons().add(new Image(getClass().getResourceAsStream()));
        ((MainWindowController)loader.getController()).init(stage);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}