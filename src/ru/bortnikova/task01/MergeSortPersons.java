package ru.bortnikova.task01;
/**
 * этот класс реализует сортировку объектов Персона методом слияния
 *
 * @author Bortnikova Olga
 */
public class MergeSortPersons implements SortPersons {
    Person[] a;
    static String name = "Сортировка слиянием";

    MergeSortPersons(Person[] persons) {
        this.a = persons;
    }

    MergeSortPersons(){}

    @Override
    public void sort() {
        mergesort(0, a.length - 1);
    }

    @Override
    public Person[] getArr() {
        return a;
    }

    @Override
    public void setArr(Person[] p) {
        a=p;
    }

    /**
     * реализация метода сортировки слянием, рекурсивный
     * @param left левая граница массива
     * @param rigt правая граница массива
     */
    private void mergesort(int left, int rigt) {
        if (left == rigt) // выход из рекурсии - массив из 1 элемента отсортирован по определению
            return;
        int mid = (left + rigt) / 2; //
        mergesort(left, mid);    // сортировка 1-й половины массива
        mergesort(mid + 1, rigt); // сортировка 2-й половины массива
        merge(left, rigt); // метод для слияния отсортированных половин
    }

    private void merge(int begin, int end) {
        int mid = (begin + end) / 2;

        Person[] t = new Person[end - begin + 1];

        int index_1 = begin;

        int index_2 = mid + 1;

        int index_t = 0;

        while ((index_1 <= mid) || (index_2 <= end)) {
            if (index_1 > mid) {
                t[index_t++] = a[index_2++];
                continue;
            }
            if (index_2 > end) {
                t[index_t++] = a[index_1++];
                continue;
            }
            if (a[index_2].compareTo(a[index_1]) > 0) {
                t[index_t++] = a[index_1++];
                continue;
            } else {
                t[index_t++] = a[index_2++];
                continue;
            }
        }
        for (int i = 0; i < end - begin + 1; i++) {
            a[i + begin] = t[i];
        }
    }
}
