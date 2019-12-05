package ru.bortnikova.task11.dz_11_2;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Домашняя работа 11, пример использования StreamAPI и лямбда-выражений
 *
 * Задача : создать массив объектов Person, отсортировать по правилам:
 * -первые идут мужчины
 * -выше в списке тот, кто более старший
 * -имена сортируются по алфавиту
 * вывести на экран отсортированный список
 *
 * @author Bortnikova Olga
 */
public class DZ_11_2 {

    public static void main(String[] args) {

        final int N = 30; // размер массива

        Person[] persons = new Person[N];

        // Получить поток из массива
        Stream<Person> personStream = Arrays.stream(persons);

        personStream.map(p -> { return new Person(); })      // заполнить рандомными объектами
                .peek(p -> System.out.println("было : " + p))// вывод в первоначальном порядке
                .sorted(Person::compareTo)                   // сортировка, метод сравнения реализован в классе Person
                .forEach(System.out::println);               // вывод на экран

    }

}
