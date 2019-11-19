package ru.bortnikova.task05;

import java.util.Comparator;
import java.util.UUID;

/**
 * класс для хранения данных о домашнем животном
 * UIN - уникальный идентификационный номер
 * name - кличка
 * type - вид животного
 * owner - владелец
 * weight - вес животного
 * @author Bortnikova Olga
 */
public class Pet {
    UUID UIN;
    String name;
    String type;
    Person owner;
    Float weight;

    public Pet(UUID UIN, String name, String type, Person owner, Float weight) {
        this.UIN = UIN;
        this.name = name;
        this.type = type;
        this.owner = owner;
        this.weight = weight;
    }

    public Pet(String name, String type, Person owner, Float weight) {
        this(UUID.randomUUID(), name, type, owner, weight);
    }

    public Pet(Pet p) {
        this(p.UIN, p.name, p.type, p.owner, p.weight);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public UUID getUIN() {
        return UIN;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Person getOwner() {
        return owner;
    }

    public Float getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "{" + UIN + " " + name + ", " + type + ", вес " + weight + ", владелец " + owner + "}";
    }
}

/**
 * компоратор для сравнения Pet по владельцу
 */
class PetOwnerComporator implements Comparator<Pet> {
    @Override
    public int compare(Pet p1, Pet p2) {
        return p1.getOwner().compareTo(p2.getOwner());
    }
}
/**
 * компоратор для сравнения Pet по кличке
 */
class PetNameComporator implements Comparator<Pet> {
    @Override
    public int compare(Pet p1, Pet p2) {
        return p1.getName().compareTo(p2.getName());
    }
}
/**
 * компоратор для сравнения Pet по весу
 */
class PetWeightComporator implements Comparator<Pet> {
    @Override
    public int compare(Pet p1, Pet p2) {
        return (p1.getWeight()-p2.getWeight())>0 ? 1:-1;
    }
}