package com.example.converter;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс который позволяет перевести число из 10-ой СС в любую другую (2-16) СС
 */
public class Converter_10_p2 implements Сonverter {
    public String conv(String line, int system) {
        return decimalToOtherBase(Double.valueOf(line), system);
    }

    private String decimalToOtherBase(double decimalNumber, int targetBase) {
        char[] hexDigits = {'A', 'B', 'C', 'D', 'E', 'F'};

        StringBuilder convertedNumberBuilder = new StringBuilder();

        // Разбиение переданного десятичного числа на целую и дробную части
        int integerPart = (int) decimalNumber;
        double decimalPart = decimalNumber - integerPart;

        // Цикл, в котором целая часть последовательно делится на целевую систему счисления,
        // и остатки от этих делений добавляются в строку с преобразованным числом
        while (integerPart != 0) {
            int remainder = integerPart % targetBase;
            if (remainder < 10) {
                convertedNumberBuilder.insert(0, remainder);
            } else {
                convertedNumberBuilder.insert(0, hexDigits[remainder - 10]);
            }
            integerPart /= targetBase;
        }

        // Если дробная часть исходного числа не равна нулю, то к строке с преобразованным
        // числом добавляется точка, и начинается второй цикл
        if (decimalPart != 0) {
            convertedNumberBuilder.append(".");

            // Второй цикл, в котором дробная часть последовательно умножается на целевую систему счисления,
            // и целая часть от каждого произведения добавляется в строку с преобразованным числом
            for (int i = 0; i < 10; i++) {
                double temp = decimalPart * targetBase;
                int integerPartTemp = (int) temp;

                if (integerPartTemp < 10) {
                    convertedNumberBuilder.append(integerPartTemp);
                } else {
                    convertedNumberBuilder.append(hexDigits[integerPartTemp - 10]);
                }

                decimalPart = temp - integerPartTemp;
            }
        }

        // Возвращение строки с преобразованным числом
        return convertedNumberBuilder.toString();
    }

}
