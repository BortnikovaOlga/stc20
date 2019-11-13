package ru.bortnikova.task03;

import java.math.BigDecimal;

/**
 * Демонстрация применения объекта MathBox
 *
 * @author Bortnikova Olga
 */

public class Main {
    public static void main(String[] args) {

        // создаем объект, с произвольным набором Number элеметов
        MathBox mb = new MathBox(new Number[]{1, 2L, 4F, 8D});
        // проверим метод дополнения коллекции, выведем на экран
        mb.addObject(new BigDecimal(1.9));
        mb.dump();

        // проверим методы summator, splitter
        System.out.println("сумма элементов коллекции " + mb.summator());
        mb.splitter(2);
        mb.dump();
        // определим хеш по объекту MathBox
        System.out.println("hash " + mb.hashCode());

        mb.addObject(2); // добавили
        mb.dump();
        System.out.println("новый hash " + mb.hashCode());

        mb.deleteObject(2); // удалили
        mb.dump();
        // снова посчитаем хеш, должен принять прежнее значение
        System.out.println("проверка hash " + mb.hashCode());

        // создадим nmb новый обьект MathBox, на основе коллекции объекта mb;
        MathBox nmb = new MathBox((Number[]) mb.getObjCollection().toArray(new Number[]{}));
        System.out.println("проверка equals : " + mb.equals(nmb));
        System.out.println("проверка hash " + nmb.hashCode());
    }
}
