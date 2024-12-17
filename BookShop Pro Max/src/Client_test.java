import java.io.*;
import java.net.Socket;

public class Client_test {
    static String address="127.0.0.1"; //"127.0.0.1"
    static int port=8888;
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(address, port);
        readThread read = new readThread(socket);
        writeThread write = new writeThread(socket);
        read.setDaemon(true);
        write.setDaemon(true);
        read.start();
        write.start();
    }
}

