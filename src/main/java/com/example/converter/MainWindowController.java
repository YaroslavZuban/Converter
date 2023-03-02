package com.example.converter;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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

    @FXML
    public void onExpect(MouseEvent event) {
        Boolean isNonexistent = false;

        String line = textInput.getText().toUpperCase();
        textInput.setText(line);

        String digits = "0123456789ABCDEF";
        String tempLine = digits.substring(0, (int) sliderInput.getValue());

        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) != '.' && tempLine.indexOf(line.charAt(i)) == -1) {
                isNonexistent = true;
                System.out.println("Ошибка");
            }
        }

        int numberSystemInput = (int) sliderInput.getValue();
        int numberSystemResult = (int) sliderResult.getValue();

        String result;

        if (isNonexistent) {
            System.out.println("Вывод окна об ошибки");
        } else {
            if (numberSystemInput == numberSystemResult) {
                labelResult.setText(line);
                System.out.println("ВЫчисления 1");
                System.out.println("результат: " + line);
            } else if (numberSystemInput == 10) {
                result = new Converter_10_p2().conv(line, numberSystemResult);
                labelResult.setText(result);
            } else if (numberSystemResult == 10) {
                result = new Converter_p1_10().conv(line, numberSystemInput);
                labelResult.setText(result);
                System.out.println("ВЫчисления 2");
                System.out.println("результат: " + result);
            } else {
                String temp = new Converter_p1_10().conv(line, numberSystemInput);
                System.out.println("Перевод числа в 10 СС: " + temp);
                result = new Converter_10_p2().conv(temp, numberSystemResult);
                labelResult.setText(result);
                System.out.println("ВЫчисления 3");
                System.out.println("результат: " + result);
            }
        }
    }
}