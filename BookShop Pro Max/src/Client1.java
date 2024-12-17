import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Client1 {
    static String address; //"127.0.0.1"
    static int port;
    public static void main(String[] args) throws IOException {
        System.out.print("address:");
        address=new Scanner(System.in).nextLine();
        System.out.print("port:");
        port=new Scanner(System.in).nextInt();
        Socket socket = new Socket(address, port);
        readThread read = new readThread(socket);
        writeThread write = new writeThread(socket);

        read.start();
        write.start();
    }
}

