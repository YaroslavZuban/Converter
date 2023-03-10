package com.example.converter;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс который позволяет перевести число из одной СС в 10-ную
 */
public class Converter_p1_10 implements Сonverter {
    private final String digits = "0123456789ABCDEF";

    public String conv(String line, int system) {
        return String.valueOf(parseNumber(line, system));
    }

    private double parseNumber(String num, int base) {
        num = num.toUpperCase(); // digits are in UPPER_CASE
        double val = 0;
        int i = 0;

        while (i < num.length()) {// пока не кончилась строка
            char c = num.charAt(i);

            if (c == '.') { // нашли точку '.'
                i++; // Переместить на следующий символ и выйти из цикла.
                break;
            }

            int d = digits.indexOf(c); // Индексы совпадают с числами из [0..15]

            if (d == -1 || d >= base)
                return Double.NaN;

            val = base * val + d;
            i++;
        }

        int power = 1; // вычислить лишний порядок.

        while (i < num.length()) {
            char c = num.charAt(i);
            int d = digits.indexOf(c);

            if (d == -1 || d >= base)
                return Double.NaN;

            power *= base; // увеличиваем степень порядка на единицу
            val = base * val + d;
            i++;
        }

        return val / power;
    }
}
