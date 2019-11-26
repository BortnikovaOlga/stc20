package ru.bortnikova.task06;

import java.io.*;
import java.util.Random;

/**
 * класс для генерации случайных слов, предложений, абзацев
 *
 * @author Bortnikova Olga
 */
public class RandWords {
    static private Random rand = new Random();
    private int MAX_CHARS;// максимальное количество букв в слове
    private int MAX_WORDS;// максимальное количество слов в предложении
    private int MAX_SENTENS;// максимальное количество предложений в абзаце.

    static final double COMMA_P = 0.25; // эмперическая вероятность запятой в предложении
    static final double POINT_P = 0.75; // эмперическая вероятность точки в конце предложения
    static final double QMARK_P = 0.15; // эмперическая вероятность вопроса в конце предложения
    private int SIZE_TEXT = 3000;

    RandWords(int maxchars, int maxwords, int maxsentens) {
        MAX_CHARS = maxchars;
        MAX_WORDS = maxwords;
        MAX_SENTENS = maxsentens;

    }

    /**
     * метод для определения возникновения случайного события по заданной вероятности
     *
     * @param p вероятность события
     * @return true - если событие "произошло"
     */
    boolean randProbability(double p) {
        return Math.random() < p;
    }

    /**
     * метод генерирует случайное слово
     *
     * @param maxChars  максимальное количество символов в слове
     * @param upperCase true - если слово должно быть с заглавной буквы
     * @return случайное слово
     */
    StringBuilder randWord(int maxChars, boolean upperCase) {
        char c = upperCase ?
                (char) (rand.nextInt(25) + 65) :
                (char) (rand.nextInt(25) + 97);
        StringBuilder word = new StringBuilder().append(c);
        for (int i = rand.nextInt(maxChars); i > 0; i--) {
            c = (char) (rand.nextInt(25) + 97);
            word = word.append(c);
        }
        return word;
    }

    /**
     * метод генерирует предложение из заданного количества слов , с вхождением в него переданного слова
     *
     * @param countWords количество слов в предложении
     * @param wordBook   слово, которое войдет в результат
     * @return составленное предложение
     */
    StringBuilder sentence(int countWords, String wordBook) {
        if (countWords == 1) return new StringBuilder().append('$').append(wordBook);
        int k = rand.nextInt(countWords); // место вхождения слова-параметра
        StringBuilder s = (k == 0) ?
                new StringBuilder().append('$').append(wordBook) :
                randWord(MAX_CHARS, true);
        for (int i = 1; i < countWords; i++) {
            s = s.append(randProbability(COMMA_P) ? ", " : " ");
            if (i == k) s = s.append('$').append(wordBook);
            else s = s.append(randWord(MAX_CHARS, false));
        }
        return s;
    }

    /**
     * метод генерирует предложение из заданного количества слов
     *
     * @param countWords количество слов
     * @return составленное предложение
     */
    StringBuilder sentence(int countWords) {
        StringBuilder s = randWord(MAX_CHARS, true);
        if (countWords == 1) return s;
        for (int i = 1; i < countWords; i++) {
            s = s.append(randProbability(COMMA_P) ? ", " : " ");
            s = s.append(randWord(MAX_CHARS, false));
        }
        return s;
    }

    /**
     * метод генерирует предложение из случайного количества слов
     *
     * @param maxCountWords максимальное возможное количество слов
     * @param wordBook      массив слов(словарь) для вставки случайного слова в предложение с заданной вероятностью
     * @param probability   вероятность вхождения слова из словаря в следующее предложение (не больше еденицы !)
     * @return составленное предложение
     */
    StringBuilder randSentence(int maxCountWords, String[] wordBook, double probability) {
        int countWords = rand.nextInt(maxCountWords) + 1;
        StringBuilder s = randProbability(probability) ?
                sentence(countWords, wordBook[rand.nextInt(wordBook.length)]) :
                sentence(countWords);
        s.append(randProbability(POINT_P) ? ". " : randProbability(QMARK_P) ? "? " : "! ");
        SIZE_TEXT = SIZE_TEXT - s.length();
        return s;
    }

    /**
     * метод генерирует абзац из случайного количества предложениий
     *
     * @param maxSentens  максимальное возможное количество предложений
     * @param WordBook    массив слов(словарь) для вставки случайного слова в предложение с заданной вероятностью
     * @param probability вероятность вхождения слова из словаря в следующее предложение (не больше еденицы !)
     * @return составленный текст
     */
    StringBuilder randParagraph(int maxSentens, String[] WordBook, double probability) {
        StringBuilder paragrath = new StringBuilder();
        for (int i = rand.nextInt(maxSentens) + 1; i > 0; i--) {
            paragrath = paragrath.append(randSentence(MAX_WORDS, WordBook, probability));
            if (SIZE_TEXT < 0 || SIZE_TEXT < (MAX_CHARS + 1) * MAX_WORDS) break;
        }
        return paragrath.append("\n");
    }

    /**
     * метод генерирует случаный текст и записывает его в файл
     *
     * @param filename    имя файла приемника
     * @param WordBook    массив слов(словарь) для вставки случайного слова в предложение с заданной вероятностью
     * @param probability вероятность вхождения слова из словаря в следующее предложение (не больше еденицы !)
     * @throws IOException
     */
    void randFile(String filename, String[] WordBook, double probability) throws IOException {
        try (FileWriter fw = new FileWriter(filename);
             BufferedWriter bw = new BufferedWriter(fw)) {
            while (true) {
                StringBuilder text = new StringBuilder();
                text = text.append("      ").append(randParagraph(MAX_SENTENS, WordBook, probability));
                bw.write(text.toString());
                if (SIZE_TEXT < 0 || SIZE_TEXT < (MAX_CHARS + 1) * MAX_WORDS) break;
            }
        }
    }

    /**
     * метод записывает в файлы тексты случайной генерации
     *
     * @param path        путь для файлов приемников
     * @param n           количество файлов
     * @param size        верхняя граница размера файла
     * @param words       массив слов(словарь) для вставки случайного слова в предложение с заданной вероятностью
     * @param probability вероятность вхождения слова из словаря в следующее предложение (не больше еденицы !)
     * @throws IOException
     */
    void getFiles(String path, int n, int size, String[] words, double probability) throws IOException {
        for (int i = 0; i < n; i++) {
            SIZE_TEXT = size;
            randFile(path + "/random_" + i + ".txt", words, probability);
        }
    }

}
