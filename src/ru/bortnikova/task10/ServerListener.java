package Example.Datagram;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

/**
 * класс реализует чтение, обработку сообщений многопользовательского чата ( UDP сервер)
 *
 * @author Bortnikova Olga
 */
public class ServerListener extends Thread {
    private int serverPort;             // порт сервера
    private Map<Integer, String> users; // пользователи, ключ порт
    private Map<String, Integer> ports; // порты пользователей, ключ имя пользователя
    private int port;

    /**
     * @param port порт сервера
     */
    public ServerListener(int port) {
        this.serverPort = port;
        users = new HashMap<>();
        ports = new HashMap<>();
    }

    /**
     * метод анализирует строку и возвращает true, если она вероятно начинается именем пользователя чата
     *
     * @param message стока сообщения
     * @return true, если строка вероятно начинается именем пользователя чата
     */
    private boolean isUserNameInMessage(String message) {
        // (имя начинается символом @ и следует без пробела)
        return (message.length() > 1 && message.startsWith("@") && message.charAt(1) != ' ');
    }

    /**
     * метод отделяет от строки сообщения вероятное имя пользователя
     *
     * @param message строка сообщения
     * @return вероятное имя пользователя
     */
    private String parseUserName(String message) {
        {
            int i = message.indexOf(' ');
            return message.substring(1, i > 0 ? i : message.length());
        }
    }

    /**
     * метод добавляет пользователя в чат
     *
     * @param user имя пользователя
     * @param port порт пользователя
     */
    private void addUser(String user, int port) {
        ports.put(user, port);
        users.put(port, user);
    }

    /**
     * удаляет пользователя из чата
     *
     * @param port порт пользователя
     */
    private void delUser(int port) {
        ports.remove(users.get(port));
        users.remove(port);
    }

    /**
     * метод получает пакет (сообщение)
     *
     * @param ds сокет сервера
     * @return строка сообщения
     * @throws IOException
     */
    private String receivePacket(DatagramSocket ds) throws IOException {
        byte[] dataIn = new byte[100];
        DatagramPacket pack = new DatagramPacket(dataIn, 100);
        ds.receive(pack);
        port = pack.getPort(); // сохранить порт с которого пришло сообщение
        return new String(dataIn, 0, pack.getLength());
    }

    /**
     * метод отправляет сообщение (пакет) по имени пользователя чата
     *
     * @param ds      сокет сервера
     * @param message сообщение
     * @param name    имя получателя
     * @throws IOException
     */
    private void sendPacket(DatagramSocket ds, String message, String name) throws IOException {
        byte[] dataout = message.getBytes();
        int sendPort = ports.get(name);
        DatagramPacket sendpack = new DatagramPacket(dataout, dataout.length, InetAddress.getByName("127.0.0.1"), sendPort);
        ds.send(sendpack);
    }

    /**
     * метод отправляет сообщение по заданному номеру порта
     *
     * @param ds       сокет сервера
     * @param message  сообщение
     * @param sendPort порт получателя
     * @throws IOException
     */
    private void sendPacket(DatagramSocket ds, String message, int sendPort) throws IOException {
        byte[] dataout = message.getBytes();
        DatagramPacket sendpack = new DatagramPacket(dataout, dataout.length, InetAddress.getByName("127.0.0.1"), sendPort);
        ds.send(sendpack);
    }

    /**
     * логика обработки сообщений
     */
    @Override
    public void run() {
        // открываем сокет
        try (DatagramSocket ds = new DatagramSocket(serverPort)) {
            //читаем сообщения
            while (true) {
                String message = receivePacket(ds); // получить сообщение
                // если сообщение содержит имя, отделить имя
                String name = isUserNameInMessage(message) ? parseUserName(message) : "";

                // если сообщение публичное или это сообщение о выходе из чата
                if (name.equals("")) {
                    if (message.toLowerCase().equals("quit")) {
                        sendPacket(ds, "выход из чата", port);
                        System.out.println(users.get(port) + ": вышел из чата");
                        delUser(port);
                    } else
                        System.out.println(users.get(port) + ": " + message);
                    continue;
                }
                // если сообщение приватное
                if (ports.containsKey(name)) {
                    message = "от " + users.get(port) + ": " + message.substring(name.length() + 1).trim();
                    sendPacket(ds, message, name);
                } else {
                    // если сообщение регистрация в чате
                    if (!users.containsKey(port)) {
                        addUser(name, port);
                        System.out.println(name + ": зашел в чат");
                        sendPacket(ds, name + " зашел в чат", port);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
