package ru.bortnikova.task01;

import java.util.Random;

/**
 * Домашняя работа 2, задание 2
 *
 * @author Bortnikova Olga
 */
public class DZ_2_2 {
    public static void main(String[] args) {
        final int n = 1000000;
        int k;
        Random rand = new Random();
        Double q;

        for (int i = 0; i < n; i++) {
            k = rand.nextInt();
            try {
                if (rand.nextBoolean()) throw new myException("генерация отрицательного");
                // если число "положительное" - вычислить квадратный корень q.
                // eсли квадрат целой части числа q равен числу k, то вывести это число на экран
                q = Math.sqrt(k);
                if (Math.pow(q.intValue(), 2) == k) System.out.println(String.valueOf(k));
            } catch (myException e) {
                //отрицательные числа игнорируем
            }
        }
    }
}
