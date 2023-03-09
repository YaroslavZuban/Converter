package com.example.converter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * класс который запускает все
 */
public class MainWindow extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("MainWindowInterface.fxml"));//загрузка fxml файла
        Scene scene=new Scene(loader.load());
        scene.setFill(Color.TRANSPARENT);
        stage.setTitle("Converter");
        stage.initStyle(StageStyle.TRANSPARENT);//убираем вверхнее меню, закрыть, свернуть приложение
        stage.setResizable(false);//нельяз расширять окно
        stage.setScene(scene);//установление сцены
        //stage.getIcons().add(new Image(getClass().getResourceAsStream()));
        ((MainWindowController)loader.getController()).init(stage);//устанавливаем сцену
        stage.show();//отображаем сцену
    }

    public static void main(String[] args) {
        launch();
    }
}