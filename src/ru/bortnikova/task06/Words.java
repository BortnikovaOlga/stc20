package ru.bortnikova.task06;

import java.io.*;
import java.util.Random;

public class Words {
    static private Random rand = new Random();
    static final int MAX_CHARS = 15; // максимальное количество букв в слове
    static final int MAX_WORDS = 15; // максимальное количество слов в предложении
    static final int MAX_SENTENS = 20; // максимальное количество предложений в абзаце.

    static final double COMMA_P = 0.25; // вероятность запятой в предложении
    static final double POINT_P = 0.75; // вероятность точки в конце предложения
    static final double QMARK_P = 0.15; // вероятность вопроса в конце предложения
    static int SIZE_TEXT=3000;

    static boolean randProbability(double p) {
        return Math.random() < p;
    }

    static StringBuilder randWord(int nChars, boolean upperCase) {

        char c = upperCase ? (char) (rand.nextInt(25) + 65) : (char) (rand.nextInt(25) + 97);
        StringBuilder word = new StringBuilder().append(c);

        for (int i = rand.nextInt(nChars); i > 0; i--) {
            c = (char) (rand.nextInt(25) + 97);
            word = word.append(c);

        }
        return word;
    }

    static StringBuilder sentence(int countWords, String wordBook) {
        if (countWords == 1) return new StringBuilder().append('$').append(wordBook);
        int k = rand.nextInt(countWords);
        StringBuilder s = (k == 0) ? new StringBuilder().append('$').append(wordBook) : randWord(MAX_CHARS, true);
        for (int i = 1; i < countWords; i++) {

            s = s.append(randProbability(COMMA_P) ? ", " : " ");
            if (i == k) s = s.append('$').append(wordBook);
            else s = s.append(randWord(MAX_CHARS, false));

        }
        return s;
    }

    static StringBuilder sentence(int countWords) {

        StringBuilder s = randWord(MAX_CHARS, true);
        if (countWords == 1) return s;
        for (int i = 1; i < countWords; i++) {

            s = s.append(randProbability(COMMA_P) ? ", " : " ");
            s = s.append(randWord(MAX_CHARS, false));
        }
        return s;
    }

    static StringBuilder randSentence(int maxCountWords, String[] wordBook, double probability) {

        int countWords = rand.nextInt(maxCountWords) + 1;
        StringBuilder s = randProbability(probability) ? sentence(countWords, wordBook[rand.nextInt(wordBook.length)]) : sentence(countWords);
        s.append(randProbability(POINT_P) ? ". " : randProbability(QMARK_P) ? "? " : "! ");
        return s;
    }

    static StringBuilder randParagraph(int maxSentens, String[] WordBook) {
        StringBuilder paragrath = new StringBuilder();
        for (int i = rand.nextInt(maxSentens) + 1; i > 0; i--) {

            paragrath = paragrath.append(randSentence(MAX_WORDS, WordBook, 0.6));
        }
        return paragrath.append("\n");
    }

    static StringBuilder randText(int textSize, String[] WordBook) {
        StringBuilder text = new StringBuilder();
      
        while (true) {
            text = text.append("      ").append(randParagraph(MAX_SENTENS, WordBook));
            if (text.length() > textSize) {
                break;
            }
        }
        return text;
    }

    public static void main(String[] args) {
        StringBuilder text = randText(SIZE_TEXT, new String[]{"OOO", "DDD", "FFF", "XX"});
        System.out.println(text.length());
        try (FileWriter fw = new FileWriter("./src/ru/bortnikova/task06/word.txt");
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(text.toString().substring(0,SIZE_TEXT));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
