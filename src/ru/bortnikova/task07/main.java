package ru.bortnikova.task07;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * задача по вычислению факториалов, используется пул потоков
 *
 * @author Bortnikova Olga
 */

public class main {
    public static void main(String[] args) {

        final int countNum = 30; // определить количество чисел для вычислений

        // составить случайный набор чисел со значением от 1 до 51
        Set<Integer> intSet = new HashSet<>();
        Random rand = new Random();
        for (int i = 0; i < countNum; ) {
            if (intSet.add(rand.nextInt(50) + 1)) i++;
        }
        // переложить числа в массив и отсортировать
        Integer[] intArr = intSet.toArray(new Integer[]{});
        Arrays.sort(intArr);
        System.out.println(Arrays.toString(intArr));

        // массив для записи вычислений факториалов
        BigDecimal[] factorialArr = new BigDecimal[countNum];
        // пул для 4 потоков
        ExecutorService es = Executors.newFixedThreadPool(4);
        //счетчик посчитаных факториалов
        CountDownLatch cdl = new CountDownLatch(30);
        // индекс для определения числа-аргумента (в массиве) для вычисления его факториала
        AtomicInteger curIdx = new AtomicInteger(0);
        // индекс, указывающий на результат последнего вычисления
        AtomicInteger lastIdx = new AtomicInteger(0);
        // отправить в пул countNum задач подсчета факториалов
        for (int i = 0; i < countNum; i++) {
            es.execute(new FactorialThread(intArr, factorialArr, cdl, curIdx, lastIdx));
        }
        //ждать вычислений
        try {
            cdl.await();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        es.shutdown();
        //печать результата вычисления факториалов
        System.out.println(Arrays.toString(factorialArr));
    }

}
