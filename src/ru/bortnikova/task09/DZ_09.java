package ru.bortnikova.task09;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * выполняет чтение строк кода(вводить код не требующий импорта новых классов) и добавляет их в метод doWork()
 * пользовательского класса SomeClass, выполняет компиляцию результата, его загрузку средствами пользовательского
 * загрузчика и вызывает "обновленный" метод doWork().
 *
 * @author Bortnikova Olga
 */
public class DZ_09 {

    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException,
            InvocationTargetException, ClassNotFoundException, NoSuchMethodException {


        String fileClassName = "./src/ru/bortnikova/task09/SomeClass.java";

        List<String> methodStr = new ArrayList<>(); // здесь сохранить введеные строки

        System.out.println(">>> Вводите строки кода для метода doWork() , пустая строка конец ввода >>>");
        Scanner scanner = new Scanner(System.in);
        String readstr;

        while (!(readstr = scanner.nextLine()).isEmpty()) {
            methodStr.add(readstr + "\n");
        }

        List<String> fileClassStr = new ArrayList<>(); // здесь будет результат для записи нового файла

        try (BufferedReader br = new BufferedReader(new FileReader(fileClassName))) {

            while (br.ready()) {
                String str = br.readLine();
                fileClassStr.add(str + "\n");
                if (str.indexOf("doWork()") > 0) {
                    fileClassStr.addAll(methodStr); // добавить новые строки метода
                }
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileClassName))) {
            for (String str : fileClassStr) {
                bw.write(str); // записать новый файл
            }
        }

        // выполнить компиляцию
        JavaCompiler jc = ToolProvider.getSystemJavaCompiler();
        System.out.println("Запуск компиляции....");
        jc.run(System.in, System.out, System.out, new File(fileClassName).getCanonicalPath());

        // создать пользовательский загрузчик классов
        MyClassLoader loader = new MyClassLoader("ru.bortnikova.task09.SomeClass");
        // загрузить новый байткод
        Class cl = loader.findClass("./src/ru/bortnikova/task09/SomeClass.class");
        // получить метод
        Method method = cl.getMethod("doWork", new Class[]{});

        System.out.println("Запуск нового метода doWork()....");
        method.invoke(cl.newInstance(), new Object[]{});

    }
}
