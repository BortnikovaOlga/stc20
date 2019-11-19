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
        Pet[] pt = new Pet[]{
                new Pet("Пушок", "кот", pn1, 3F),
                new Pet("Пушок", "пес", pn3, 10F),
                new Pet("Барсик", "кот", pn2, 5F),
                new Pet("Гоша", "попугай", pn2, 0.5F),
                new Pet("Шарик", "пес", pn2, 8F),
                new Pet("Тузик", "пес", pn1, 7F),
                new Pet("Гоша", "попугай", pn2, 0.3F)};

        PetsCatalog petsCatalog = new PetsCatalog();

        // добавление животных в картотеку
        for (int i = 0; i < pt.length; i++) {
            petsCatalog.addPet(pt[i]);
        }

        System.out.println("Найти Пушка >>>");
        // поиск животного по кличке, возвращается массив УИН-ов
        UUID[] petsUinArr = petsCatalog.findPet("Пушок");

        if (petsUinArr.length != 0) {
            for (UUID uin : petsUinArr) {
                Pet p = petsCatalog.getPet(uin); // получить животное по его УИН
                System.out.println(" " + p);
                // предположим, пес Пушок прибавил в весе на 1 кг !!! отредактировать данные в картотеке
                if (p.getType().equals("пес")) {
                    petsCatalog.editPet(uin, "Пуш", "", null, p.getWeight() + 1);
                    System.out.println("\nПереименовать Пушка-> новая кличка Пуш, новый вес");
                }
            }
        }

        System.out.println("Найти Пуша >>>\n" + petsCatalog.getPet(petsCatalog.findPet("Пуш")[0]));

        // составим компоратор для сортировки
        Comparator<Pet> pcomp = new PetOwnerComporator().thenComparing(new PetNameComporator()).thenComparing(new PetWeightComporator());

        System.out.println("\nОтсортировать каталог [владелец + кличка питомца + вес] >>>\n");

        for (Pet p : petsCatalog.sortPets(pcomp)) {
            System.out.println("" + p);
        }
    }
}
