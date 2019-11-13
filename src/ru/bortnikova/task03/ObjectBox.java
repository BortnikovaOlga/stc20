package ru.bortnikova.task03;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.UUID;

/**
 * этот класс хранит коллекцию объектов, может добавлять и удалять элементы
 *
 * @author Bortnikova Olga
 **/

public class ObjectBox {
    private Collection objCollection;
    private static final UUID uuid = UUID.randomUUID();

    ObjectBox() {
        objCollection = new HashSet();
    }

    /**
     * конструктор для создания коллекции по предоставленному массиву
     *
     * @param oArr массив объектов, из которого строится коллекция
     */
    ObjectBox(Object[] oArr) {
        objCollection = new HashSet();
        Collections.addAll(objCollection, oArr);
    }

    /**
     * метод для дополнения коллекции новым элементом
     *
     * @param o элемент, которого нет в коллеции
     */
    void addObject(Object o) {
        objCollection.add(o);
    }

    /**
     * метод для удаления элемента из коллекции
     *
     * @param o элемент, который нужно удалить
     */
    void deleteObject(Object o) {
        objCollection.remove(o);
    }

    /**
     * метод полностью очищает коллекцию и заменяет на содержимое другой коллекции
     *
     * @param oc новая коллекция
     */
    void replaceObject(Collection oc) {
        objCollection.clear();
        if (oc != null) objCollection = oc;
    }

    /**
     * метод выводит содержимое коллекции в стандартный поток вывода.
     */
    void dump() {
        System.out.println("ObjectBox" + toString());
    }

    public int hashCode() {
        return objCollection.hashCode() + uuid.hashCode();
    }

    public boolean equals(ObjectBox o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return objCollection.equals(o.getObjCollection());
    }

    public String toString() {
        return objCollection.toString();
    }

    public Collection getObjCollection() {
        return objCollection;
    }
}

