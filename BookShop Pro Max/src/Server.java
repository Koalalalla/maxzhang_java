import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Server {
    public static void main(String[] args){
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            ExecutorService es= Executors.newFixedThreadPool(4);
            System.out.println("Server is running...");
            List ls =new List();
            Thread listenThread = new Thread(() -> {
                while (true) {
                    try {
                        Socket socket = serverSocket.accept();
                        es.submit(new myThread(socket,ls.list));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            listenThread.start();
            String command = new Scanner(System.in).nextLine();
            if (command.equals("exit")) {
                es.shutdown();
                ls.db.write();
                System.exit(0);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


class myThread extends Thread {
    Socket socket;
    ArrayList<Book> list;

    public myThread(Socket socket,ArrayList<Book> list) {
        this.socket = socket;
        this.list = list;
    }

    public void run() {
        try {
            System.out.println("["+socket.getRemoteSocketAddress()+"]"+"is  connected!");
            BookShop bookshop =new BookShop(socket,list);
            bookshop.run();
        } catch (Exception e) {
            try {
                this.socket.close();
            } catch (IOException ex) {
                System.out.println("["+socket.getRemoteSocketAddress()+"]"+" is disconnected");
                throw new RuntimeException(ex);
            }
        }
    }
}

