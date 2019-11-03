package ru.bortnikova.task01;

import java.util.Arrays;
/**
 * Домашняя работа 2, задание 3
 * для варианта с небольшим количеством объектов Персона раскомментировать persons.print(); для вывода результатов сортировки на экран
 *
 * @author Bortnikova Olga
 */
public class DZ_2_3 {
    public static void main(String[] args) {
        final int numP = 10000;
        //final int numP = 31;
        Persons persons = new Persons(numP);
        // закоментировать print() для numP =10000
        //persons.print();

        // сделаем копию массива персон
        Person[] p2 = persons.getArr();
        p2 = Arrays.copyOf(p2, p2.length);


        // установим метод быстрой сортировки и выполним сортировку
        persons.setSorter(new QeekSortPersons());
        System.out.println("старт сортировки -----------" );
        long start = System.currentTimeMillis();
        persons.sort();
        long end = System.currentTimeMillis();
        System.out.println("сортировка выполнена за " + (end - start)+" милисекунд");
        // закоментировать print() для numP =10000
        //persons.print();

        // заменим массив персон на копию с первоночального состояния
        persons.setArr(p2);
        System.out.println("-----------" );
        // закоментировать print() для numP =10000
        //persons.print();

        // установим метод сортировки слиянием и выполним сортировку
        persons.setSorter(new MergeSortPersons());
        System.out.println("старт сортировки -----------");
        start = System.currentTimeMillis();
        persons.sort();
        end = System.currentTimeMillis();
        System.out.println("сортировка выполнена за " + (end - start)+" милисекунд");
        // закоментировать print() для numP =10000
        //persons.print();
    }


}
