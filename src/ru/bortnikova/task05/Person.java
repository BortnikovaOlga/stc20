package ru.bortnikova.task05;

/**
 * класс Person , поля -имя, пол (Sex.MAN, Sex.WOMAN), возраст
 *
 * @author Bortnikova Olga
 */

public class Person implements Comparable<Person> {
    private String name;
    private Sex sex;
    private int age;

    Person(String name, Sex sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    @Override
    public String toString() {
        return "{" + name + ", " + sex.getStr() + ", " + age + " лет}";
    }

    /**
     * сравнивает два объекта Person по полу, возрасту и имени
     * @param person объект Person для сравнения
     * @return значение больше нуля , если обект-параметр "меньше", ноль - если объекты равны, и значение меньше нуля, если объет-параметр "больше"
     */
    @Override
    public int compareTo(Person person) {
        if (sex != person.sex) return sex == Sex.MAN ? -1 : 1;
        if (age != person.age) return age > person.age ? -1 : 1;
        return name.compareTo(person.name);
    }
}
