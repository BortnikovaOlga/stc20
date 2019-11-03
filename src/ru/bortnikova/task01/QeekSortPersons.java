package ru.bortnikova.task01;
/**
 * этот класс реализует сортировку объектов Персона методом быстрой сортировки
 * @author Bortnikova Olga
 */
public class QeekSortPersons implements SortPersons {
    Person[] personsArr;
    static String name = "Быстрая сортировка";

    QeekSortPersons(Person[] persons) {
        this.personsArr = persons;
    }

    QeekSortPersons() {
    }

    @Override
    public void sort() {
        qsort(0, personsArr.length - 1);

    }

    @Override
    public Person[] getArr() {
        return personsArr;
    }

    @Override
    public void setArr(Person[] p) {
        personsArr = p;
    }

    /**
     * реализация метода быстрой сортировки, рекурсивный
     * @param left левая граница массива
     * @param right правая граница массива
     */
    private void qsort(int left, int right) {
        int l = left, r = right;
        Person piv = personsArr[(l + r) / 2]; // Опорным элементом для примера возьмём средний
        while (l <= r) {
            while (piv.compareTo(personsArr[l]) > 0)
                l++;
            while (piv.compareTo(personsArr[r]) < 0)
                r--;
            if (l <= r) {
                Person p = personsArr[l];
                personsArr[l] = personsArr[r];
                personsArr[r] = p;
                r--;
                l++;
            }
        }
        if (left < r)
            qsort(left, r);
        if (right > l)
            qsort(l, right);
    }
}

