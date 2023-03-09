package com.example.converter;

/**
 * класс ответает за обьект который будет в себя записывать
 * какое число ввели и СС в которым число
 * вывод числа в новой СС в котом нам нужно представить число
 */
public class Computing {
    public String input;
    public int inputSystem;
    public String result;
    public int resultSystem;

    public Computing(String input, int inputSystem, String result, int resultSystem) {
        this.input = input;
        this.inputSystem = inputSystem;
        this.result = result;
        this.resultSystem = resultSystem;
    }
}
