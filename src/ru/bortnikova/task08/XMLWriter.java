package ru.bortnikova.task08;

import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

/**
 * класс для сериализации в формате XML
 *
 * @author Bortnikova Olga
 */
public class XMLWriter {
    private BufferedWriter bw;
    private Class objClass;

    /**
     * принимает поток записи BufferedWriter
     */
    public XMLWriter(BufferedWriter bw) {
        this.bw = bw;
    }

    /**
     * метод записывает заголовок XML файла
     *
     * @param obj объект сериализации
     * @throws IOException
     */
    public void writeHead(Object obj) throws IOException {
        objClass = obj.getClass();
        String objClassName = objClass.getName();
        String strWrite = "<head class " + objClassName.substring(objClassName.lastIndexOf('.') + 1) + ">\n";
        bw.write(strWrite);
    }

    /**
     * метод записывает объекты в XML файл
     *
     * @param obj объект для сериализации
     * @throws IOException
     * @throws IllegalAccessException
     */
    public void writeObj(Object obj) throws IOException, IllegalAccessException, XMLException {
        if (objClass == null) throw new XMLException("Не определен объект сериализации");

        Field[] fields = objClass.getDeclaredFields();   // узнать все поля объекта
        AccessibleObject.setAccessible(fields, true);
        for (Field f : fields) {
            String fieldTypeName = f.getType().toString();
            String strWrite = " <" +                     // здесь формируем строку по текущему полю
                    f.getName() + " type=\"" +
                    fieldTypeName.substring(fieldTypeName.lastIndexOf('.') + 1) + "\">" +
                    f.get(obj) + "</" +
                    f.getName() + ">\n";
            bw.write(strWrite);

        }
    }
}
