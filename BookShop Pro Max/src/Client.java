import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
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

class readThread extends Thread {
    Socket socket;
    public readThread(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
            while (socket.isConnected()&&!socket.isClosed()) {
                String s = in.readLine();
                System.out.println(s);
                System.out.print("\033[34;1m[PBS]\033[0m");
            }
        } catch (Exception e) {
            try {
                socket.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}

class writeThread extends Thread {
    Socket socket;

    public writeThread(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        try (PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            while (socket.isConnected()&&!socket.isClosed()) {
                Scanner sc = new Scanner(System.in);
                out.write(sc.nextLine() + "\n");
                out.flush();
            }
        } catch (Exception e) {
            try {
                socket.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
