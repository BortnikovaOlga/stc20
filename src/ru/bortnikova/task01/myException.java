package ru.bortnikova.task01;
/**
 * этот класс описывает пользовательское исключение
 *
 * @author Bortnikova Olga
 */
public class myException extends Exception {
    myException(String massage) {
        super(massage);
    }
}