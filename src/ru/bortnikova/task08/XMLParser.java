package ru.bortnikova.task08;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

/**
 * класс для десериализации из XML формата
 *
 * @author Bortnikova Olga
 */
public class XMLParser {

    private String readLine; // стока для парсинга
    private int start = 0; // начальный индекс в строке
    private int end = 0;   // конечный индекс
    private Class objClass;
    private Object obj;
    private BufferedReader bufferedReader;

    private final String strXMLException ="Неверный формат или файл поврежден";

    /**
     * @param br принимает поток чтения BufferedReader
     */
    public XMLParser(BufferedReader br) {
        bufferedReader = br;
    }

    /**
     * метод читает часть строки между заданными подстроками
     *
     * @param startStr подстрока передшествующая искомой подстроке
     * @param endStr   подстрока следующая за искомой
     * @return искомая подстрока
     * @throws XMLException
     */
    private String parseNext(String startStr, String endStr) throws XMLException {
        if (readLine.equals("") || readLine == null) throw new XMLException("Отсутствует строка для парсера");

        start = readLine.indexOf(startStr, end) + startStr.length();
        end = readLine.indexOf(endStr, start);
        if (start == 0 || end < 0) throw new XMLException(strXMLException);
        return readLine.substring(start, end).trim();
    }

    /**
     * метод читает заголовок из XML файла
     *
     * @param objClassPackage пакет сериализуемого класса
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws IOException
     * @throws XMLException
     */
    public void readHead(String objClassPackage) throws ClassNotFoundException,
            IllegalAccessException, InstantiationException, IOException, XMLException {

        readLine = bufferedReader.readLine();
        String nameClass = parseNext("class", ">");
        objClass = Class.forName(objClassPackage + nameClass);
        obj = objClass.newInstance();

    }

    /**
     * метод сохраняет значение поля в объект (для строк, примитивов и их оберток)
     *
     * @param strValue строковое представление значения поля
     * @param f        поле объекта
     * @throws IllegalAccessException
     */
    private void initValue(String strValue, Field f) throws IllegalAccessException {

        if (byte.class.equals(f.getType()) || Byte.class.equals(f.getType())) {
            f.set(obj, Integer.valueOf(strValue));
            return;
        }
        if (char.class.equals(f.getType()) || Character.class.equals(f.getType())) {
            f.set(obj, Integer.valueOf(strValue));
            return;
        }
        if (short.class.equals(f.getType()) || Short.class.equals(f.getType())) {
            f.set(obj, Integer.valueOf(strValue));
            return;
        }
        if (int.class.equals(f.getType()) || Integer.class.equals(f.getType())) {
            f.set(obj, Integer.valueOf(strValue));
            return;
        }
        if (long.class.equals(f.getType()) || Long.class.equals(f.getType())) {
            f.set(obj, Integer.valueOf(strValue));
            return;
        }
        if (float.class.equals(f.getType()) || Float.class.equals(f.getType())) {
            f.set(obj, Integer.valueOf(strValue));
            return;
        }
        if (double.class.equals(f.getType()) || Double.class.equals(f.getType())) {
            f.set(obj, Double.valueOf(strValue));
            return;
        }
        f.set(obj, strValue);
    }

    /**
     * метод читает объект из XML файла
     *
     * @return десериализованный объект
     * @throws IOException
     * @throws XMLException
     * @throws IllegalAccessException
     */
    public Object readObj() throws IOException, XMLException, IllegalAccessException {
        if (objClass == null) throw new XMLException("Не определен объект десериализации");

        Field[] objFields = objClass.getDeclaredFields(); // получить все поля класса
        AccessibleObject.setAccessible(objFields, true);
        start = 0;
        end = 0;
        readLine = bufferedReader.readLine();

        for (Field f : objFields) {

            if (readLine.length() == end + 1) {
                if (bufferedReader.ready()) readLine = bufferedReader.readLine();
                else throw new XMLException(strXMLException);

            } else if (end > 0) readLine = readLine.substring(end + 1);
            // это условие для случая , если все элементы в XML файле идут в одну строку
            start = 0;
            end = 0;
            String nameFieldTeg = parseNext("<", "type="); // читать открывающий тег - имя поля, проверить с информацией по полю класса
            if (!f.getName().equals(nameFieldTeg)) throw new XMLException(strXMLException);

            String typeName = parseNext("\"", "\""); // читать атрибут - тип поля, проверить с информацией по полю класса
            String fieldType = f.getType().toString();
            if (!fieldType.substring(fieldType.lastIndexOf('.') + 1).equals(typeName))
                throw new XMLException(strXMLException);

            String strValue = parseNext(">", "<"); // читать значение поля, занести в поле объекта
            initValue(strValue, f);

            String endTeg = parseNext("/", ">"); // читать закрывающий тег, проверить на соответствие открывающему
            if (!nameFieldTeg.equals(endTeg)) throw new XMLException(strXMLException);

        }
        return obj;
    }

}
