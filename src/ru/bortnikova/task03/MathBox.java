package ru.bortnikova.task03;

import java.util.*;

/**
 * этот класс хранит и работает с коллекцией объектов числовых типов Number
 * @author Bortnikova Olga
 *
 * @param <T> числовой тип <T extends Number>
 */
public class MathBox<T extends Number> extends ObjectBox {


    MathBox(T[] numArr) {
        super(numArr);
    }

    double summator() {
        double numTot = 0;

        for (T num : (Collection<T>) getObjCollection()) {
            numTot += num.doubleValue();
        }
        return numTot;
    }

    void splitter(T den) {
        Collection newSet = new HashSet();

        for (T num : (Collection<T>) getObjCollection()) {
            newSet.add(num.doubleValue() / den.doubleValue());
        }
        super.replaceObject(newSet);
    }

    void addObject(T n) {
        super.addObject(n);
    }

    void addObject(Object o) {
        throw new RuntimeException(); // так в ДЗ хо-хо-хо
    }

    void dump() {
        System.out.println(toString());
    }

    public String toString() {
        return "MathBox" + super.toString();
    }
}
