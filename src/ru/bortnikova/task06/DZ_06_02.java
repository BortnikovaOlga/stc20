package ru.bortnikova.task06;

import java.io.IOException;

/**
 * демонстрация класса генерации случайных текстов
 *
 * @author Bortnikova Olga
 */
public class DZ_06_02 {
    public static void main(String[] args) throws IOException {

        // объект для генерации случайных текстов
        RandWords w = new RandWords(15, 15, 20);

        // составим словарь из случайных слов
        String[] randWords = new String[50];
        for (int i = 0; i < randWords.length; i++) {
            randWords[i] = w.randWord(10, false).toString();
        }
        // запишем случайные тексты в 3 файла
        w.getFiles("./src/ru/bortnikova/task06/", 3, 3000, randWords, 0.6);
    }
}
