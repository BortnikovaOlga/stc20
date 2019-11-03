package ru.bortnikova.task01;
/**
 * Домашняя работа 2, задание 1
 *
 * @author Bortnikova Olga
 */
public class DZ_2_1 {

    public static void main(String[] args) {
        String[] myStr = {null, "Привет, мир !!!"};

        // моделируем ошибку «NullPointerException»
        try {
            System.out.printf(myStr[0]);
        } catch (NullPointerException e1) {
            System.out.println("catch1 " + e1.toString());
        }
        // моделируем ошибку «ArrayIndexOutOfBoundsException»
        try {
            System.out.printf(myStr[2]);
        } catch (ArrayIndexOutOfBoundsException e2) {
            System.out.println("catch2 " + e2.toString());
        }
        //вызваем свой вариант ошибки
        try {
            throw new myException(myStr[1]);
        } catch (myException e) {
            System.out.println(e.toString());
        }

    }

}