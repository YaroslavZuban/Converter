package com.example.converter;

import java.util.ArrayList;
import java.util.List;

public class Converter_p1_10 implements Сonverter{
    @Override
    public String conv(String line, int system) {
        return null;
    }

    public  String getInRadix(int number, int radix) {
        List<Character> digits = getDigitTable();

        if (radix < 2 || radix >= digits.size() || number < 0) {
            throw new IllegalArgumentException();
        }

        StringBuilder valueStr = new StringBuilder();

        while (number > 0) {
            valueStr.insert(0, digits.get(number % radix));
            number = number / radix;
        }

        return valueStr.toString();
    }

    private  List<Character> getDigitTable() {
        ArrayList<Character> digits = new ArrayList<>();

        for (char i = '0'; i <= '9'; i++) {
            digits.add(i);
        }

        for (char i = 'A'; i <= 'Z'; i++) {
            digits.add(i);
        }

        return digits;
    }
}
