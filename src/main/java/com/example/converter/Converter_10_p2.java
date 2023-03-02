package com.example.converter;

import java.util.ArrayList;
import java.util.List;

public class Converter_10_p2 implements Сonverter {
    @Override
    public String conv(String line, int system) {
        return decimalToOtherBase(Double.valueOf(line),system);
    }

    private String decimalToOtherBase(double decimalNumber, int targetBase) {
        char[] hexDigits = {'A', 'B', 'C', 'D', 'E', 'F'};

        StringBuilder convertedNumberBuilder = new StringBuilder();
        int integerPart = (int) decimalNumber;
        double decimalPart = decimalNumber - integerPart;

        while (integerPart != 0) {
            int remainder = integerPart % targetBase;
            if (remainder < 10) {
                convertedNumberBuilder.insert(0, remainder);
            } else {
                convertedNumberBuilder.insert(0, hexDigits[remainder - 10]);
            }
            integerPart /= targetBase;
        }

        if (decimalPart != 0) {
            convertedNumberBuilder.append(".");
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

        return convertedNumberBuilder.toString();
    }

}
