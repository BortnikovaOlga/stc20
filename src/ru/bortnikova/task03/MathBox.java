package ru.bortnikova.task03;

import java.util.*;

/**
 * этот класс хранит и работает с коллекцией объектов числовых типов Number
 *
 * @param <T> числовой тип <T extends Number>
 * @author Bortnikova Olga
 */
public class MathBox<T extends Number> extends ObjectBox {


    MathBox(T[] numArr) {
        super(numArr);
    }

    /**
     * @return метод возвращает сумму всех элементов коллекции
     */
    double summator() {
        double numTot = 0;

        for (T num : (Collection<T>) getObjCollection()) {
            numTot += num.doubleValue();
        }
        return numTot;
    }

    /**
     * метод выполняет поочередное деление всех хранящихся элементов на делитель,
     * являющийся аргументом метода. Хранящиеся в объекте данные полностью заменяются результатами деления.
     *
     * @param den делитель
     */
    void splitter(T den) {
        Collection newSet = new HashSet();

        for (T num : (Collection<T>) getObjCollection()) {
            newSet.add(num.doubleValue() / den.doubleValue());
        }
        super.replaceObject(newSet);
    }

    /**
     * метод добавляет элемент Number в коллекцию
     *
     * @param n число, которого нет в коллекции
     */
    void addObject(T n) {
        super.addObject(n);
    }

    /**
     * при попытке добавить элемент другого типа создается исключение
     */
    void addObject(Object o) {
        throw new RuntimeException();
    }

    /**
     * метод выводит содержимое коллекции в стандартный поток вывода.
     */
    void dump() {
        System.out.println(toString());
    }

    public String toString() {
        return "MathBox" + super.toString();
    }
}
