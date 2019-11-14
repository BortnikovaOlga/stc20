package ru.bortnikova.task05;


public class Person implements Comparable<Person>{
    String name;
    Sex sex;
    int age;

    Person(String name, Sex sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    @Override
    public String toString() {
        return "{" + name + ", " + sex.getStr() + ", " + age + " лет}";
    }
    @Override
    public int compareTo(Person person) {
        if (sex != person.sex) return sex == Sex.MAN ? -1 : 1;
        if (age != person.age) return age > person.age ? -1 : 1;
        return name.compareTo(person.name);
    }
}
