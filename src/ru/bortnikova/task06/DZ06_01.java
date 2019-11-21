package ru.bortnikova.task06;


import java.io.*;
import java.util.Set;
import java.util.TreeSet;

public class DZ06_01 {


    public static void main(String[] args) {
        String str;
        Set<String> slovar = new TreeSet<>();

        try (
                FileReader fr = new FileReader("./src/ru/bortnikova/task06/text.txt");
                BufferedReader br = new BufferedReader(fr)) {
            do {
                str = br.readLine();
                String[] arr = str.split("[^a-zA-Zа-яА-Я]+");
                for (String s : arr) {
                    if (!s.equals("")) slovar.add(s.toLowerCase());
                }
                System.out.println(str);
            } while (br.ready());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileWriter fr = new FileWriter("./src/ru/bortnikova/task06/slovar.txt");
             BufferedWriter bw = new BufferedWriter(fr)) {
            for (String s : slovar
            ) {
                bw.write(s+"\n");
                System.out.println(s);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
