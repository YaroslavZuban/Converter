package com.example.converter;

/**
 * интрефейс который в дальнейшем будет наследоваться и реализовыввать метод
 * conv перевода числа в другую систему счисления
 */
public interface Сonverter {
    String conv(String line, int system);
}
