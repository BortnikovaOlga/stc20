package ru.bortnikova.task03;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;

/**
 * @author Bortnikova Olga
 */

public class Main {
    public static void main(String[] args) {

        MathBox mb = new MathBox(new Number[]{1, 2L, 4F, 8D});

        mb.addObject(new BigDecimal(1.9));
        mb.dump();


        System.out.println("сумма элементов коллекции " + mb.summator());

        mb.splitter(2); // поделили все элементы на 2
        mb.dump();
        System.out.println("hash " + mb.hashCode());

        mb.addObject(2); // добавили целочисленный
        mb.dump();
        mb.deleteObject(2); // удалили
        mb.dump();
        System.out.println("проверка hash " + mb.hashCode());

        MathBox nmb = new MathBox<>(new Number[]{4.0, 2.0, 0.5, 1.0, 0.95});
        System.out.println("проверка equals : " + mb.equals(nmb));
    }
}
