package ru.bortnikova.task01;
/** этот интерфейс описывает метод для сортировки объетов Персона,
 *  методы для получения и установки ссылки на массив объектов
 *
 * @author Bortnikova Olga
 */
public interface SortPersons {

    void sort ();
    Person[] getArr();
    void setArr(Person[] p);

}
