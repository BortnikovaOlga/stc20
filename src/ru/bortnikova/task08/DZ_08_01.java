package ru.bortnikova.task08;


import java.io.*;

/**
 * Демонстрация классов сериализации XMLWriter и десериализации XMLParser (для плоских объектов)
 *
 * @author Bortnikova Olga
 */
public class DZ_08_01 {

    public static void main(String[] args) throws Throwable {

        // тестовые объекты для сериализации
        TestClass obj1 = new TestClass("Казань", "Татарстан", 16, 199.1, 1251969);
        TestClass obj2 = new TestClass("Барнаул", "Алтайский край", 22, 123.9, 632723);

        String file = "./src/ru/bortnikova/task08/test.xml"; // файл приемник

        // выполнить сериализацию
        try (FileWriter fw = new FileWriter(file);
             BufferedWriter bw = new BufferedWriter(fw)) {

            XMLWriter xw = new XMLWriter(bw); // создать объект который выполнит сериализацию

            xw.writeHead(obj1);// записать заголовок

            xw.writeObj(obj1); // записать объекты
            xw.writeObj(obj2);
        } catch (IOException | IllegalAccessException e) {
            e.printStackTrace();
        }

        // выполнить десериализацию
        try (FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader(fr)) {

            XMLParser xp = new XMLParser(br); // создать объект который выполнит десериализацию

            xp.readHead("ru.bortnikova.task08."); // читать заголовок
            while (br.ready()) {
                TestClass obj = (TestClass) xp.readObj(); // читать объект
                System.out.println(obj.toString());
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
