package com.example.converter;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Данный класс отвечает за управление интерфейсом история
 */
public class HistoryController implements Initializable {
    @FXML
    private ImageView buttonClose;

    @FXML
    private ImageView buttonCollapse;

    @FXML
    private GridPane grid;
    @FXML
    private ImageView buttonConv;
    @FXML
    private Pane titlePane;

    @FXML
    private AnchorPane window;
    private double x, y;
    public void init(Stage stage){

        //с помощью этих методов выполняется перетаскивание окна на рабочем столе
        titlePane.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });

        titlePane.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - x);
            stage.setY(mouseEvent.getScreenY() - y);
        });

        buttonCollapse.setOnMouseClicked(mouseEvent -> stage.setIconified(true));//сворачивание окна
        buttonClose.setOnMouseClicked(mouseEvent -> stage.close());//закрытие приложения

        buttonConv.setOnMouseClicked(mouseEvent->{
            Stage ss = (Stage) window.getScene().getWindow();//береться параметры стратого она и закрывается
            ss.close();//закрытия окна

            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("MainWindowInterface.fxml"));//считывание дизайн самого интерфейса

            Stage s = new Stage();
            Scene scene = null;//запуск дизайн

            try {
                scene = new Scene(fxmlLoader.load());
                //   stage.getIcons().add(new Image(HelloApplication.class.getResourceAsStream("bit.png")));//установление иконки
                scene.setFill(Color.TRANSPARENT);
                s.setTitle("Converter");
                s.initStyle(StageStyle.TRANSPARENT);
                s.setResizable(false);
                s.setScene(scene);
                s.setScene(scene);//установка Scene для Stage
                ((MainWindowController)fxmlLoader.getController()).init(s);
                s.show();//Попытки показать это окно, установив для видимости значение true
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int column = 0;
        int row = 1;

        try {
            for (int i = 0; i < Story.input.size(); i++) {

                FXMLLoader fxmlLoader = new FXMLLoader();

                fxmlLoader.setLocation(getClass().getResource("Doing.fxml"));//подгружаем окно doing.fxml
                AnchorPane anchorPane = fxmlLoader.load();//позволяет вам определять точки привязки fxml формат

                DoingController doingController = fxmlLoader.getController();//загружаем doing контроллер
                Computing computing = Story.input.get(i);//устанавливаем значение итерации

                doingController.setData(computing.input, String.valueOf(computing.inputSystem),
                        computing.result, String.valueOf(computing.resultSystem));//вносим данные в fxml формат


                if (column == 1) {//данное условие позволяет в таблице создавать только одну колонку
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row);//вставляем в таблицу значения

                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_COMPUTED_SIZE);

                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_COMPUTED_SIZE);

                GridPane.setMargin(anchorPane, new Insets(5));//устанавливает отступ между ячейками
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
