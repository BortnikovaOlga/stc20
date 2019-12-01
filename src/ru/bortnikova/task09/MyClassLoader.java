package ru.bortnikova.task09;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Класс - пользовательская реализация загрузчика классов
 *
 * @author Bortnikova Olga
 */
public class MyClassLoader extends ClassLoader {
    String classname; // имя файла класса для обработки обособленным способом

    public MyClassLoader(String classname) {
        this.classname = classname;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (name.indexOf("SomeClass") > 0) {
            return findClass(name);
        }
        return super.loadClass(name);
    }

    /**
     * метод проверяет имя файла класса , если это пользовательский класс SomeClass, то загружает его сам
     * @param name имя *.class файла
     * @return Class-объект для name-файла
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if (name.indexOf("SomeClass") > 0) {
            byte[] bytes = new byte[0];
            try {
                bytes = Files.readAllBytes(Paths.get(name));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return defineClass(classname, bytes, 0, bytes.length);
        }
        return super.findClass(name);
    }

}
