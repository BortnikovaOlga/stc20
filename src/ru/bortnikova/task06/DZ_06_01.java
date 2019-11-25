package ru.bortnikova.task06;


import java.io.*;
import java.util.Set;
import java.util.TreeSet;

/**
 * демонстрация работы с потоками чтения/ записи
 *
 * @author Bortnikova Olga
 */
public class DZ_06_01 {


    public static void main(String[] args) {
        String str;
        // словарь в который будем вносить слова из текста
        Set<String> slovar = new TreeSet<>();

        // текст возьмем из файла, открываем поток чтения из файла,
        // и перенаправляем его в удобный для работы поток с буферизацией
        try (
                FileReader fr = new FileReader("./src/ru/bortnikova/task06/text.txt");
                BufferedReader br = new BufferedReader(fr)) {
            do {
                str = br.readLine();
                System.out.println(str);
                // разобрать прочитанную строку на слова
                String[] arr = str.split("[^a-zA-Zа-яА-Я]+");
                // добавить слова в словарь
                for (String s : arr) {
                    if (!s.equals("")) slovar.add(s.toLowerCase());
                }
                // читать файл пока не закончатся строки
            } while (br.ready());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // выгрузить составленный словарь в файл
        try (FileWriter fw = new FileWriter("./src/ru/bortnikova/task06/slovar.txt");
             BufferedWriter bw = new BufferedWriter(fw)) {
            for (String s : slovar
            ) {
                bw.write(s + "\n");
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
