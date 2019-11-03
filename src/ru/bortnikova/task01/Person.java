package ru.bortnikova.task01;

import java.util.Random;
/**
 * этот класс описывает обьект Персона, конструктор без параметров генерирует случаные данные для заполнения полей
 * @author Bortnikova Olga
 *
 */
public class Person implements Comparable<Person> {
    private Sex sex;
    private int age;
    private String name;
    static String[] menNames = {"Александр", "Антон", "Алексей", "Борис", "Бронислав", "Богдан", "Василий", "Владимир",
            "Валерий", "Вячеслав", "Владислав", "Георгий", "Григорий", "Глеб", "Герман", "Дмитрий", "Денис", "Данил",
            "Егор", "Евгений", "Ефим", "Евсей", "Захар", "Иван", "Игорь", "Игнат", "Илья", "Иннокентий", "Леонид", "Лаврентий",
            "Кирилл", "Констанитин", "Клим", "Кузьма", "Лука", "Лев", "Максим", "Михаил", "Матвей", "Михей", "Марк", "Мирослав",
            "Николай", "Никита", "Олег", "Осип", "Остап", "Петр", "Прохор", "Потап", "Павел", "Роман", "Руслан", "Родион", "Ростислав",
            "Сергей", "Станислав", "Степан", "Сидор", "Трофим", "Тимофей", "Тихон", "Тарас", "Тимур", "Устин", "Федор", "Федот", "Харитон",
            "Эдуард", "Эмиль", "Юлиан", "Юрий", "Яков", "Ярослав"};
    static String[] womanNames = {"Александра", "Алена", "Агафья", "Антонина", "Анна", "Алла", "Алиса", "Агата", "Авдотья", "Аксинья",
            "Бела", "Берта", "Валентина", "Василиса", "Виктория", "Вера", "Валерия", "Вероника", "Глафира", "Галина", "Дарья", "Диана",
            "Евгения", "Елизавета", "Ева", "Екатерина", "Евдокия", "Жанна", "Зинаида", "Зоя", "Илона", "Инесса", "Инна", "Ирина",
            "Клавдия", "Карина", "Ксения", "Кира", "Лада", "Лидия", "Людмила", "Лукерья", "Любовь", "Лариса", "Марина", "Мария", "Мила",
            "Марфа", "Маргарита", "Надежда", "Наталья", "Нина", "Ольга", "Оксана", "Олеся", "Полина", "Пелагея", "Прасковья", "Раиса",
            "Регина", "Рита", "Роза", "Светлана", "София", "Серафима", "Таисья", "Татьяна", "Тамара", "Ульяна", "Устинья", "Фаина", "Фекла",
            "Федора", "Эвелина", "Элеонора", "Эмма", "Юлия", "Яна", "Ядвига"};
    static Random rand = new Random();

    Person(Sex sex, int age, String name) {
        this.sex = sex;
        this.age = age;
        this.name = name;
    }

    Person() {
        sex = rand.nextBoolean() ? Sex.MAN : Sex.WOMAN;
        age = rand.nextInt(120) + 1;
        name = (sex != Sex.MAN) ? womanNames[rand.nextInt(womanNames.length - 1)] :
                menNames[rand.nextInt(menNames.length - 1)];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                sex.equals(person.sex) &&
                name.equals(person.name);
    }

    @Override
    public int compareTo(Person person) {
        if (sex != person.sex) return sex == Sex.MAN ? -1 : 1;
        if (age != person.age) return age > person.age ? -1 : 1;
        return name.compareTo(person.name);
    }

    @Override
    public String toString() {
        return sex.getStr() + " " + String.valueOf(age) + " " + name + "!!";
    }
}
