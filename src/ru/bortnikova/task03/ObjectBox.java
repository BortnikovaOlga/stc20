package ru.bortnikova.task03;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

/**
 * этот класс хранит коллекцию объектов, может добавлять и удалять элементы
 *
 * @author Bortnikova Olga
 **/

public class ObjectBox {
    private Collection objCollection;

    ObjectBox() {
        objCollection = new HashSet();
    }

    /**
     * конструктор для создания коллекции из массива
     *
     * @param oArr массив, из которого строится коллекция
     */
    ObjectBox(Object[] oArr) {
        objCollection = new HashSet();
        Collections.addAll(objCollection, oArr);
    }

    /**
     * метод для дополнения коллекции
     *
     * @param o элемент, который нужно добавить
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

    void replaceObject(Collection oc) {
        objCollection.clear();
        if (oc != null) objCollection = oc;
    }

    void dump() {
        System.out.println("ObjectBox" + toString());
    }

    public int hashCode() {
        return objCollection.hashCode();
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

