package com.example.converter;

public class Converter_10_p2 implements Сonverter {
    @Override
    public String conv(String line, int system) {
        return String.valueOf(Integer.parseInt(line,system));
    }
}
