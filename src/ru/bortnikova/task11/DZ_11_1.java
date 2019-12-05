package ru.bortnikova.task11;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Домашняя работа 11, пример использования StreamAPI и лямбда-выражений
 *
 * Задача (из ДЗ 2):
 * Составить программу, генерирующую N случайных чисел (положительных и отрицательных).
 * Для каждого положительного числа k вычислить квадратный корень q.
 * Если квадрат целой части q числа равен k, то вывести это число на экран
 *
 * @author Bortnikova Olga
 */

public class DZ_11_1 {


    public static void main(String[] args) {

        final int N = 1000000;
        int[] dArr = new int[N];
        Random rand = new Random();
        // генерируем случайные числа в массив, положительные и отрицательные
        for (int i = 0; i < N; i++) dArr[i] = rand.nextInt() * (rand.nextBoolean() ? 1 : -1);

        // получить поток из массива
        IntStream ds = Arrays.stream(dArr);


        ds.filter(k -> k > 0 ? true : false)        // убираем отрицательные
                .map(k -> {
                    Double q = Math.sqrt(k);        // вычисления из условия, неподходящие зануляем
                    return Math.pow(q.intValue(), 2) == k ? k : 0;
                })
                .filter(k -> k > 0 ? true : false) // убираем неподходящие числа
                .forEach(System.out::println);     // выводим на экран
    }
}
