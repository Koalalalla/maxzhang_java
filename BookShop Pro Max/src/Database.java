import java.io.*;
import java.util.ArrayList;

public class Database {
    BufferedReader br;
    PrintWriter pw;
    ArrayList<Book> list;

    public Database(ArrayList<Book> list) {
        this.list = list;
    }

    public void run() throws IOException {
        pw = new PrintWriter(new FileWriter("index.txt",true), true);
        br = new BufferedReader(new FileReader("index.txt"));
    }

    public void read() {
        try {
            for (Book book : list) {
                String line = br.readLine();
                String[] arr = line.split(";");
                String name = arr[0];
                String type = arr[1];
                String label = arr[2];
                int price = Integer.parseInt(arr[3]);
                book.setBook(name);
                book.setLabel(label);
                book.setType(type);

                book.setPrice(price);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void write() {
        try {
            pw = new PrintWriter(new FileWriter("index.txt",true), true);
            for (Book Book : list) {
                pw.println(Book.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void clean(){
        try {
            pw = new PrintWriter(new FileWriter("index.txt"),true);
            pw.write("");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

