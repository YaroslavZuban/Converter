package com.example.converter;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class MainWindowController {
    public static StringBuffer lineInput = new StringBuffer();
    @FXML
    private Pane buttonBackSpace;

    @FXML
    private Pane buttonCleanEntry;

    @FXML
    private ImageView buttonClose;

    @FXML
    private ImageView buttonCollapse;

    @FXML
    private Pane buttonExecut;

    @FXML
    private ImageView buttonHistory;

    @FXML
    private Pane button_Point;

    @FXML
    private Pane button_0;

    @FXML
    private Pane button_1;

    @FXML
    private Pane button_2;

    @FXML
    private Pane button_3;

    @FXML
    private Pane button_4;

    @FXML
    private Pane button_5;

    @FXML
    private Pane button_6;

    @FXML
    private Pane button_7;

    @FXML
    private Pane button_8;

    @FXML
    private Pane button_9;

    @FXML
    private Pane button_A;

    @FXML
    private Pane button_B;

    @FXML
    private Pane button_C;
    @FXML
    private Pane button_D;

    @FXML
    private Pane button_E;

    @FXML
    private Pane button_F;

    @FXML
    private TextField textInput;

    @FXML
    private Label labelResult;

    @FXML
    private Label labelInputSystem;

    @FXML
    private Label labelResultSystem;

    @FXML
    private Slider sliderInput;

    @FXML
    private Slider sliderResult;
    @FXML
    private ImageView buttonInfo;
    @FXML
    private Spinner<Integer> spinnerInput;

    @FXML
    private Spinner<Integer> spinnerResult;

    @FXML
    private Pane titlePane;

    @FXML
    private AnchorPane window;

    public static int systemInput = 10;
    public static int systemResult = 16;
    private SpinnerValueFactory<Integer> spinnerValueFactoryInput;
    private SpinnerValueFactory<Integer> spinnerValueFactoryResult;

    private double x, y;

    /**
     * в данном методе реализуется перетаскиваения приложения, закрытие, сворачивание
     * так же просмотр истории и информации
     * @param stage
     */
    public void init(Stage stage) {
        titlePane.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });

        titlePane.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - x);
            stage.setY(mouseEvent.getScreenY() - y);
        });

        buttonCollapse.setOnMouseClicked(mouseEvent -> stage.setIconified(true));
        buttonClose.setOnMouseClicked(mouseEvent -> stage.close());

        buttonInfo.setOnMouseClicked(mouseEvent-> Info.information(Reference.info,window));


        buttonHistory.setOnMouseClicked(mouseEvent->{
            Stage ss = (Stage) window.getScene().getWindow();//береться параметры стратого она и закрывается
            ss.close();//закрытия окна

            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("HistoryInterface.fxml"));//считывание дизайн самого интерфейса

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
                ((HistoryController)fxmlLoader.getController()).init(s);
                s.show();//Попытки показать это окно, установив для видимости значение true
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        sliderInput.setSnapToPixel(false);
        sliderResult.setSnapToPixel(true);

        spinnerValueFactoryInput =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 16);
        spinnerValueFactoryInput.setValue(10);
        spinnerInput.setValueFactory(spinnerValueFactoryInput);

        spinnerValueFactoryResult = new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 16);
        spinnerValueFactoryResult.setValue(16);
        spinnerResult.setValueFactory(spinnerValueFactoryResult);
    }

    @FXML
    void onNumberClicked(MouseEvent event) {
        int value = Integer.parseInt(((Pane) event.getSource()).getId().replace("button_", ""));

        if (textInput.getText().equals("0")) {
            textInput.setText(String.valueOf(value));
        } else {
            StringBuilder line = new StringBuilder(textInput.getText());
            line.append(value);

            textInput.setText(String.valueOf(line).toUpperCase());
        }
    }

    @FXML
    void onSymbolClicked(MouseEvent event) {
        String value = ((Pane) event.getSource()).getId().replace("button_", "");
        String point = ".";

        if (textInput.getText().equals("0")) {
            if (value.equals("Point")) {
                textInput.setText("0" + point);
            } else {
                textInput.setText(value);
            }
        } else {
            StringBuilder line = new StringBuilder(textInput.getText());

            if (value.equals("Point") && line.indexOf(".") == -1) {
                line.append(point);
                textInput.setText(line.toString());
            } else if (!value.equals("Point")) {
                line.append(value);
                textInput.setText(line.toString());
            }
        }
    }

    @FXML
    void onNumberSystemSlider(MouseEvent event) {
        systemInput = (int) sliderInput.getValue();
        systemResult = (int) sliderResult.getValue();

        numberSystemChanges(sliderInput, labelInputSystem, spinnerValueFactoryInput, spinnerInput, systemInput);
        numberSystemChanges(sliderResult, labelResultSystem, spinnerValueFactoryResult, spinnerResult, systemResult);
    }

    @FXML
    void onNumberSystemSpinner(MouseEvent event) {
        systemInput = spinnerInput.getValue();
        systemResult = spinnerResult.getValue();

        numberSystemChanges(sliderInput, labelInputSystem, spinnerValueFactoryInput, spinnerInput, systemInput);
        numberSystemChanges(sliderResult, labelResultSystem, spinnerValueFactoryResult, spinnerResult, systemResult);
    }

    private void numberSystemChanges(Slider slider, Label text,
                                     SpinnerValueFactory<Integer> factory,
                                     Spinner<Integer> spinner, int value) {
        text.setText(String.valueOf(value));
        factory.setValue(value);
        spinner.setValueFactory(factory);
        slider.setValue(value);
    }

    @FXML
    public void onBaskSpace(MouseEvent event) {
        StringBuilder line = new StringBuilder(textInput.getText());

        if (line.length() > 0) {
            line.deleteCharAt(line.length() - 1);

            if (line.length() == 0) {
                textInput.setText("0");
            } else {
                textInput.setText(line.toString());
            }
        }
    }

    @FXML
    public void onCleanEntry(MouseEvent event) {
        textInput.setText("0");
        spinnerValueFactoryInput.setValue(10);
        spinnerInput.setValueFactory(spinnerValueFactoryInput);
        sliderInput.setValue(10);

        labelResult.setText("0");
        spinnerValueFactoryResult.setValue(16);
        spinnerResult.setValueFactory(spinnerValueFactoryResult);
        sliderResult.setValue(16);

    }

    /**
     * в данном методе делается следующее
     * проверяется что число находиться в СС (2-16)
     * в дальнейшем, происходят преобразования если число в 10 СС то оно перевод осуществляется сразу в N
     * если число в N системе счисления то оно переводиться в 10, а из 10 в M
     * @param event
     */
    @FXML
    public void onExpect(MouseEvent event) {
        Boolean isNonexistent = false;

        String line = textInput.getText().toUpperCase();
        textInput.setText(line);

        String digits = "0123456789ABCDEF";
        String tempLine = digits.substring(0, (int) sliderInput.getValue());

        //данный метод позволяет найти обнаружить в строке лишние элементы
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) != '.' && tempLine.indexOf(line.charAt(i)) == -1) {
                isNonexistent = true;
            }
        }

        int numberSystemInput = (int) sliderInput.getValue();
        int numberSystemResult = (int) sliderResult.getValue();

        String result;

        if (isNonexistent) {
            Info.information("Лишний символ, проверти поле ввода.",window);
        } else {
            if (numberSystemInput == numberSystemResult) {//если СС перевода равны то просто вставляем строку
                labelResult.setText(line);
            } else if (numberSystemInput == 10) {//если СС находится в 10 -> N
                result = new Converter_10_p2().conv(line, numberSystemResult);
                labelResult.setText(result);
            } else if (numberSystemResult == 10) {//если СС находится N -> 10
                result = new Converter_p1_10().conv(line, numberSystemInput);
                labelResult.setText(result);
            } else {//если число находится в N СС счисления, а результат в M СС, то переводим число в 10, а потом в M
                String temp = new Converter_p1_10().conv(line, numberSystemInput);
                result = new Converter_10_p2().conv(temp, numberSystemResult);
                labelResult.setText(result);
            }

            Computing computing=new Computing(textInput.getText(),numberSystemInput,
                    labelResult.getText(),numberSystemResult);

            Story.input.add(computing);
        }
    }
}