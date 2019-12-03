package Example.Datagram;

/**
 * запуск потока сервера многопользовательского чата
 *
 * @author Bortnikova Olga
 */
public class Server {
    public static void main(String[] args) {
        // создать сервер для обработки сообщений
        ServerListener serverListener=new ServerListener(4999);
        Thread tl=new Thread(serverListener);
        // старт сервера
        tl.start();
    }
}
