package ru.bortnikova.task05;

import java.util.*;

/**
 * класс PetsCatalog картотека домашних животных - объектов Pet,
 * реализованы функции добавления животного в картотеку, поиск по кличке, редактирование данных, сортировка
 *
 * @author Bortnikova Olga
 */
public class PetsCatalog {
    private Map<UUID, Pet> petsMap; // основная карта, где ключ=уин, значение=животное(Pet)
    private Map<String, HashSet<UUID>> namesMap;// вспомогательная карта, где ключ=кличка, значение=множество уин-ов для этой клички

    PetsCatalog() {
        petsMap = new HashMap<>();
        namesMap = new HashMap<>();
    }

    /**
     * метод добавляет животное в картотеку
     *
     * @param pet объект Pet
     */
    void addPet(Pet pet) {

        if (petsMap.containsKey(pet.getUIN())) return;

        petsMap.put(pet.getUIN(), pet);

        if (!namesMap.containsKey(pet.getName()))
            namesMap.put(pet.getName(), new HashSet<>());
        //добавить уин-value по key-кличке
        namesMap.get(pet.getName()).add(pet.getUIN());
    }

    /**
     * метод вносит изменения в картотеку по УИН животного
     *
     * @param uin    УИН
     * @param name   новое имя, "" если нет изменений
     * @param type   новый тип, "" если нет изменений
     * @param owner  новый владелец Person, null если нет изменений
     * @param weight новый вес, или 0 если нет изменений
     */
    void editPet(UUID uin, String name, String type, Person owner, float weight) {

        if (!petsMap.containsKey(uin)) return;

        Pet newPet = petsMap.get(uin);
        if (name != null && !name.equals("")) {
            namesMap.get(newPet.getName()).remove(uin);
            if (!namesMap.containsKey(name))
                namesMap.put(name, new HashSet<>());
            //добавить уин-value по key-кличке
            namesMap.get(name).add(uin);
            newPet.setName(name);
        }
        if (type != null && !type.equals("")) newPet.setType(type);
        if (owner != null) newPet.setOwner(owner);
        if (weight != 0) newPet.setWeight(weight);

    }

    /**
     * метод ищет животных по переданному параметру Кличка
     *
     * @param name кличка животного для поиска
     * @return возвращает массив УИН-ов или пустой массив, если такого животного нет
     */
    UUID[] findPet(String name) {
        if (!namesMap.containsKey(name)) return null;
        return namesMap.get(name).toArray(new UUID[]{}); // передаем копию сведений из служебной namePet
    }

    /**
     * метод для сортировки по заданному порядку приоритетов в переданном параметре-компораторе
     *
     * @param pcomp компоратор для объектов Pet, по которому будет выполнена сортировка
     * @return массив объектов Pet
     */
    Pet[] sortPets(Comparator<Pet> pcomp) {

        Pet[] pArr = petsMap.values().toArray(new Pet[]{});
        Arrays.sort(pArr, pcomp);
        return pArr;
    }

    /**
     * метод возвращает животное из картотеки по переданному параметру УИН
     *
     * @param uin
     * @return объект Pet - копия из картотеки
     */
    Pet getPet(UUID uin) {
        return new Pet(petsMap.get(uin));
    }
}
