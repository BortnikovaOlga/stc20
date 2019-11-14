package ru.bortnikova.task05;

import java.util.*;

/**
 * класс PetsCatalog картотека домашних животных - объектов Pet,
 * реализованы функции добавления животного в картотеку, поиск по кличке, редактирование данных, сортировка
 *
 * @author Bortnikova Olga
 */
public class PetsCatalog {
    private Map<UUID, Pet> pets;
    private Map<String, HashSet<UUID>> petsNames;

    PetsCatalog() {
        pets = new HashMap<>();
        petsNames = new HashMap<>();
    }

    /**
     * метод добавляет животное в картотеку
     * @param pet объект Pet
     */
    void addPet(Pet pet) {

        if (pets.containsKey(pet.getUIN())) return;
        pets.put(pet.getUIN(), pet);

        if (!petsNames.containsKey(pet.getName()))
            petsNames.put(pet.getName(), new HashSet<>());

        petsNames.get(pet.getName()).add(pet.getUIN());
    }

    /**
     * метод вносит изменения в картотеку по УИН животного
     * @param uin УИН
     * @param name новое имя, "" если нет изменений
     * @param type новый тип, "" если нет изменений
     * @param owner новый владелец Person, null если нет изменений
     * @param weight новый вес, или 0 если нет изменений
     */
    void editPet(UUID uin, String name, String type, Person owner, float weight) {

        if (!pets.containsKey(uin)) return;

        Pet newPet = new Pet(pets.get(uin));
        if (!name.equals("")) newPet.setName(name);
        if (!type.equals("")) newPet.setType(type);
        if (owner != null) newPet.setOwner(owner);
        if (weight != 0) newPet.setWeight(weight);
        pets.put(uin, newPet);

    }

    /**
     * метод ищет животных по переданному параметру Кличка
     * @param name кличка животного для поиска
     * @return возвращает массив УИН-ов или null, если такого животного нет
     */
    UUID[] findPet(String name) {
        if (!petsNames.containsKey(name)) return null;
        return petsNames.get(name).toArray(new UUID[]{});
    }

    /**
     * метод для сортировки по заданному порядку приоритетов в переданном параметре-компораторе
     * @param pcomp компоратор для объектов Pet, по которому будет выполнена сортировка
     * @return массив объектов Pet
     */
    Pet[] sortPets(Comparator<Pet> pcomp) {

        Pet[] pArr = pets.values().toArray(new Pet[]{});
        Arrays.sort(pArr, pcomp);
        return pArr;
    }

    /**
     * метод возвращает животное из картотеки по переданному параметру УИН
     * @param uin
     * @return объект Pet
     */
    Pet getPet(UUID uin){
        return pets.get(uin);
    }
}
