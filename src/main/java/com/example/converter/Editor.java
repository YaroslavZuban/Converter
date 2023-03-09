package com.example.converter;

import javafx.scene.input.KeyEvent;

/**
 * Класс редактор, но он нигде не выполняется
 */
public class Editor {
    public static String processingClick(String line, KeyEvent keyEvent){
        StringBuilder tempLine=new StringBuilder(line);

        String ch=keyEvent.getCharacter();

        if (keyEvent.getText().contains("BackSpace")){
            tempLine.deleteCharAt(tempLine.length()-1);
        }else{
            tempLine.append(ch);
        }

        System.out.println(ch);

        return String.valueOf(tempLine);
    }
}
