package Example.Datagram;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Клиент (@user2) многопользовательского чата
 */
public class User2 {
    public static void main(String[] args) throws InterruptedException {

        try (DatagramSocket ds = new DatagramSocket()){ // получить сокет
            AtomicBoolean ready = new AtomicBoolean(true); // флаг чтения сообщений
            // запуск чтения входящих сообщений в отдельном потоке
            ClientListner clientListner = new ClientListner(ds,ready);
            Thread ct = new Thread(clientListner);
            ct.start();

            Scanner scanner = new Scanner(System.in);

            InetAddress addr = InetAddress.getByName("127.0.0.1");
            // отправить на сервер первый пакет с именем пользователя
            byte[] data = "@user2".getBytes();
            DatagramPacket pack = new DatagramPacket(data, data.length, addr, 4999);
            ds.send(pack);
            // отправка сообщений, приватное сообщение начинать @<user>, quit - выход из чата
            while (true) {
                String message = scanner.nextLine();
                data = message.getBytes();
                pack = new DatagramPacket(data, data.length, addr, 4999);
                ds.send(pack);
                if (message.toLowerCase().equals("quit")) break;
            }
            ready.set(false); // изменить флаг для потока входящих сообщений
            ct.join();        // ждать завершения работы потока входящих сообщений
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
