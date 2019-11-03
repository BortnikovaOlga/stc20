package ru.bortnikova.task01;
/**
 * Этот класс содержит массив объектов Персона, может сортировать их с помощью sorter
 *
 * @author Bortnikova Olga
 */
public class Persons implements SortPersons {
    private Person[] arrP;
    private SortPersons sorter;

    /**
     * @param len задает нужное количество персон
     */
    Persons(int len) {
        arrP = new Person[len];
        for (int i = 0; i < len; i++) {
            arrP[i] = new Person();
        }
    }

    /**
     * @param sorter устанавливает необходимый Сортировщик интерфейса SortPersons
     */
    public void setSorter(SortPersons sorter) {
        this.sorter = sorter;
    }

    public Person[] getArr() {
        return arrP;
    }

    /**
     * вызывает сортировщик, если он установлен
     */
    @Override
    public void sort() {
        if (sorter == null) return;
        if (sorter.getArr() == null) sorter.setArr(arrP);
        sorter.sort();
    }

    @Override
    public void setArr(Person[] p) {
        arrP = p;
    }

    /**
     * печать массива объектов Персона
     */
    public void print() {
        for (int i = 0; i < arrP.length; i++) {
            System.out.println(arrP[i].toString());
        }
    }

}
