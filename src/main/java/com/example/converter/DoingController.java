package com.example.converter;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class DoingController {
    @FXML
    private Text numberInput;
    @FXML
    private Text numberResult;

    @FXML
    private Text numberSystemInput;

    @FXML
    private Text numberSystemResult;

    public void setData(String numberInput,String numberSystemInput,
                        String numberResult,String numberSystemResult){
        this.numberInput.setText(numberInput);
        this.numberSystemInput.setText(numberSystemInput);
        this.numberResult.setText(numberResult);
        this.numberSystemResult.setText(numberSystemResult);
    }
}
