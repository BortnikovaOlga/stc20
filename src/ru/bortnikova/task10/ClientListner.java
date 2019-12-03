package Example.Datagram;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * класс реализует обработку входящих сообщений пользователя чата
 *
 * @author Bortnikova Olga
 */
public class ClientListner extends Thread {
    private DatagramSocket ds;   // сокет пользователя
    private AtomicBoolean ready; // флаг указывающий на небходимость чтения

    /**
     * @param ds сокет пользователя, флаг для чтения сообщений
     * @param ready
     */
    public ClientListner(DatagramSocket ds, AtomicBoolean ready) {
        this.ds = ds;
        this.ready = ready;
    }

    /**
     * обработка входящих сообщений пользователя чата
     */
    @Override
    public void run() {
        String message;
        byte[] datain = new byte[100];
        // читаем сообщения, пока флаг не изменится
        while (ready.get()) {
            DatagramPacket pack = new DatagramPacket(datain, datain.length);
            try {
                ds.receive(pack); // читаем
                message = new String(datain,0, pack.getLength()); // вывод на экран
                System.out.println(message);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
