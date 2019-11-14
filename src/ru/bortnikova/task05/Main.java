package ru.bortnikova.task05;

import java.util.Comparator;
import java.util.UUID;

/**
 * демонстрация работы класса PetsCatalog картотека домашних животных
 *
 * @author Bortnikova Olga
 */
public class Main {
    public static void main(String[] args) {
        Person pn1 = new Person("Иван", Sex.MAN, 20);
        Person pn2 = new Person("Илья", Sex.MAN, 33);
        Person pn3 = new Person("Мария", Sex.WOMAN, 28);

        Pet pt1 = new Pet("Пушок", "кот", pn1, 3F);
        Pet pt2 = new Pet("Пушок", "пес", pn3, 10F);
        Pet pt3 = new Pet("Барсик", "кот", pn2, 5F);
        Pet pt4 = new Pet("Гоша", "попугай", pn2, 0.5F);
        Pet pt5 = new Pet("Шарик", "пес", pn2, 8F);
        Pet pt6 = new Pet("Тузик", "пес", pn1, 7F);

        PetsCatalog petsCatalog = new PetsCatalog();

        // добавление животных в картотеку
        petsCatalog.addPet(pt1);
        petsCatalog.addPet(pt2);
        petsCatalog.addPet(pt3);
        petsCatalog.addPet(pt4);
        petsCatalog.addPet(pt5);
        petsCatalog.addPet(pt6);

        System.out.println("Найти Пушка >>>\n");
        // поиск животного по кличке, возвращается массив УИН-ов
        UUID[] petsArr = petsCatalog.findPet("Пушок");

        if (petsArr != null) {
            for (UUID uin : petsArr) {
                Pet p = petsCatalog.getPet(uin); // получить животное по его УИН
                System.out.println(" " + p);
                // предположим, пес Пушок прибавил в весе на 1 кг !!! отредактировать данные в картотеке
                if (p.getType().equals("пес")) {
                    petsCatalog.editPet(uin, "", "", null, p.getWeight() + 1);
                }
            }
        }
        // составим компоратор для сортировки
        Comparator<Pet> pcomp = new PetOwnerComporator().thenComparing(new PetNameComporator());

        System.out.println("\nОтсортировать каталог [владелец + кличка питомца] >>>\n");

        for (Pet p : petsCatalog.sortPets(pcomp)) {
            System.out.println("" + p);

        }
    }
}
