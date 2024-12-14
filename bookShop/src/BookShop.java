import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class BookShop {
    static Socket socket;
    static ArrayList<Book> list;
    public BookShop(Socket socket,ArrayList<Book> list) {
        BookShop.socket = socket;
        BookShop.list = list;
    }
    public void run(){
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            out.println(" ");
            out.println(" ");
            logo(out);
            out.println(" ");
            out.println("\33[33;1m -----<-----<-----<-----<-----WELCOME----->----->----->----->-----\033[0m");
            out.println("\33[33;1m ! Welcome to the Peter Bookshop !\033[0m");
            out.println(" ");
            out.println("\033[33;1m Before you use it,please let us know you are not a robot.\033[0m\033[032m[-_-]\033[0m");
            out.println("\033[33;1m So,please enter the verification code\033[0m");
            out.println("\33[33;1m -----<-----<-----<-----<-------<->------->----->----->----->-----\033[0m");
            out.println("\033[31m Warm:make sure it right,otherwise you can not use the system!!!\033[0m");

            out.println(" ");
            out.println("===SOME BOOK===");

            show(list, out);
            //The verification code
            out.println("The verification code:");
            boolean flag = true;
            do {
                String NewCode = code();
                out.println(NewCode);
                System.out.println("[" + socket.getRemoteSocketAddress() + "]" + " start verification...");
                String sure = in.readLine();
                if (sure.equalsIgnoreCase(NewCode)) {
                    flag = true;
                    System.out.println("[" + socket.getRemoteSocketAddress() + "]" + " successful!");
                } else {
                    out.println("\033[31mWrong\033[0m");
                    out.println("\033[31mPlease input again\033[0m");
                    flag = false;
                }
            } while (!flag);
            out.println("===SUCCESS===");
            out.println("\033[034m-------------------------------------------------------------------------------------------------------------------------\033[0m");
            out.println("\033[34mSUCCESSFUL\033[0m");
            way:
            while (true) {
                out.println("\033[034m-------------------------------------------------------------------------------------------------------------------------\033[0m");
                out.println("Please choose the way");
                out.println("1:Add the book information");
                out.println("2:Delete the book information");
                out.println("3:Check the book information");
                out.println("4:Change the book information");
                out.println("5:Filter the book according to the type");
                out.println("6:Filter the book according to the price");
                out.println("7:Instruction of each function");
                out.println("8:Exit");
                out.println("\033[034m-------------------------------------------------------------------------------------------------------------------------\033[0m");

                String chose = in.readLine();

                switch (chose) {
                    case "1": {
                        out.println("===Add===");
                        add(list, out, in);
                        break;
                    }
                    case "2": {
                        out.println("===Delete===");
                        delete(list, out, in);
                        break;
                    }
                    case "3": {
                        out.println("===Check===");
                        broose(list, out);
                        break;
                    }
                    case "4": {
                        out.println("===Change===");
                        change(list, out, in);
                        break;
                    }
                    case "5": {
                        out.println("===GetType===");
                        hobby(list, out, in);
                        break;
                    }
                    case "6": {
                        out.println("===GetPrice===");
                        price(list, out, in);
                        break;
                    }
                    case "7": {
                        out.println("===Instruction===");
                        out.println("Please choose the way you want to know");
                        out.println("1,2,3,4,5,6,7,8");
                        instruction(in, out);
                        break;
                    }
                    case "8": {
                        out.println("===Exit===");
                        out.println("Thank you for using our system,BYE~");
                        break way;
                    }
                    default: {
                        out.println("\033[31mWrong\033[0m");
                        out.println("\033[31mPlease rechose\033[0m");
                        break;
                    }
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
            try {
                socket.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    //CHECK
    public static void broose(ArrayList<Book> list, PrintWriter out) {
        if (list.isEmpty()) {
            out.println("Not data available");
        } else {
            show(list,out);
        }
    }

    //SHOW
    public static void show(ArrayList<Book> list, PrintWriter out) {
        out.println("\033[035m+----------------------------------+-----------------------------------+-----------------------------------+-----------+\33[0m");
        out.println("\33[35m|\33[0m\tName \t\t\t\t\t\t\t\33[35m|\33[0m\tType \t\t\t\t\t\t\t\33[35m|\33[0m\tNumber\t\t\t\t\t\t\t\33[35m|\33[0m\tPrice\t\33[35m|\33[0m\t");
        out.println("\033[035m+----------------------------------+-----------------------------------+-----------------------------------+-----------+\033[0m");
        for (Book book : list) {
            out.println("\33[35m|\33[0m\t" + book.getBook() + "\t\t\t\33[35m|\33[0m\t" + book.getType() + "\t\t\t\33[35m|\33[0m\t" + book.getLabel() + "\t\t\t\33[35m|\33[0m\t" + book.getPrice() + "￥" + "\t\33[35m|\33[0m\t");
        }
        out.println("\033[035m+----------------------------------+-----------------------------------+-----------------------------------+-----------+\033[0m");
    }

    //ADD
    public static void add(ArrayList<Book> list, PrintWriter out, BufferedReader in) {
        try {
            Book book = new Book();
            out.println("Add book name");
            String name = in.readLine();
            book.setBook(name);
            out.println("Add book type");
            String type = in.readLine();
            book.setType(type);
            boolean flag = true;
            String input;
            do {
                out.println("Add book number ");
                input = in.readLine();
                for (Book example : list) {
                    if (input.equals(example.getLabel())) {
                        out.println("\033[34mThe number exits\033[0m");
                        out.println("\033[34mPlease input again\033[0m");
                        flag = false;
                        break;
                    } else {
                        flag = true;
                    }
                }
            } while (!flag);
            book.setLabel(input);

            out.println("Add book price");
            int price = Integer.parseInt(in.readLine());
            book.setPrice(price);
            list.add(book);

        } catch (IOException e) {
            e.printStackTrace();
            try {
                socket.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    //DELETE+NUMBER
    public static void delete(ArrayList<Book> list, PrintWriter out, BufferedReader in) {
        try {
            out.println("Add the number");
            String label = in.readLine();
            int num = labelGet(list, label);
            if (check(list, label)) {
                list.remove(num);
                out.println("Delete the book with the number" + label);
            } else {
                out.println("The number does not exist");
            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                socket.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public static boolean check(ArrayList<Book> list, String label) {
        int b = labelGet(list, label);
        return b != -1;
    }

    public static int labelGet(ArrayList<Book> list, String label) {
        for (int i = 0; i < list.size(); i++) {
            Book book = list.get(i);
            String realLabel = book.getLabel().trim();
            if (realLabel.equals(label)) {
                return i;
            }
        }
        return -1;
    }

    //CHANGE
    public static void change(ArrayList<Book> list, PrintWriter out, BufferedReader in) {
        try {
            out.println("Please choose the book number you want to change");
            String label = in.readLine();
            int num = labelGet(list, label);
            if (check(list, label)) {
                Book book = list.get(num);
                out.println("Please input the name");
                String name = in.readLine();
                book.setBook(name);
                out.println("Please input the type");
                String type = in.readLine();
                book.setType(type);
                out.println("Please input the price");
                int price = Integer.parseInt(in.readLine());
                book.setPrice(price);

            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                socket.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    //TYPE
    public static void hobby(ArrayList<Book> list, PrintWriter out, BufferedReader in) {
        try {
            out.println("Please input the type");
            String hobby = in.readLine();
            out.println("\033[035m+----------------------------------+-----------------------------------+-----------------------------------+-----------+\33[0m");
            out.println("\33[35m|\33[0m\tName \t\t\t\t\t\t\t\33[35m|\33[0m\tType \t\t\t\t\t\t\t\33[35m|\33[0m\tNumber\t\t\t\t\t\t\t\33[35m|\33[0m\tPrice\t\33[35m|\33[0m\t");
            out.println("\033[035m+----------------------------------+-----------------------------------+-----------------------------------+-----------+\33[0m");
            for (Book book : list) {
                String hobbyType = book.getType().trim();
                if (hobby.equals(hobbyType)) {
                    out.println("\33[35m|\33[0m\t" + book.getBook() + "\t\t\t\33[35m|\33[0m\t" + book.getType() + "\t\t\t\33[35m|\33[0m\t" + book.getLabel() + "\t\t\t\33[35m|\33[0m\t" + book.getPrice() + "￥" + "\t\33[35m|\33[0m\t");
                }
            }
            out.println("\033[035m+----------------------------------+-----------------------------------+-----------------------------------+-----------+\33[0m");
        } catch (IOException e) {
            e.printStackTrace();
            try {
                socket.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    //PRICE
    public static void price(ArrayList<Book> list, PrintWriter out, BufferedReader in) {
        try {
            out.println("Please input the max price");
            int maxPrice = Integer.parseInt(in.readLine());
            out.println("Please input the min price");
            int minPrice = Integer.parseInt(in.readLine());
            out.println("\033[035m+----------------------------------+-----------------------------------+-----------------------------------+-----------+\33[0m");
            out.println("\33[35m|\33[0m\tName \t\t\t\t\t\t\t\33[35m|\33[0m\tType \t\t\t\t\t\t\t\33[35m|\33[0m\tNumber\t\t\t\t\t\t\t\33[35m|\33[0m\tPrice\t\33[35m|\33[0m\t");
            out.println("\033[035m+----------------------------------+-----------------------------------+-----------------------------------+-----------+\33[0m");
            for (Book book : list) {
                int priceGet = book.getPrice();
                if (priceGet >= minPrice & priceGet <= maxPrice) {
                    System.out.println("\33[35m|\33[0m\t" + book.getBook() + "\t\t\t\33[35m|\33[0m\t" + book.getType() + "\t\t\t\33[35m|\33[0m\t" + book.getLabel() + "\t\t\t\33[35m|\33[0m\t" + book.getPrice() + "￥" + "\t\33[35m|\33[0m\t");
                }
            }
            out.println("\033[035m+----------------------------------+-----------------------------------+-----------------------------------+-----------+\33[0m");
        } catch (IOException e) {
            e.printStackTrace();
            try {
                socket.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    //VERIFICATION CODE
    public static String code() {
        StringBuilder sb = new StringBuilder(4);
        for (int i = 0; i < sb.capacity() - 1; i++) {
            Random code = new Random();
            sb.append(code.nextInt(10));
        }
        Random r = new Random();
        char l = (char) (r.nextInt(26) + 65);
        sb.append(l);
        return sb.toString();
    }

    //LOGO
    public static void logo(PrintWriter out) {
        out.println("   \033[34;1m ********         *********        ***********         *********          **********  \033[0m      ");
        out.println("   \033[34;1m **     **        **                    *              **                 **       ** \033[0m      ");
        out.println("   \033[34;1m **     **        **                    *              **                 **       ** \033[0m      ");
        out.println("   \033[34;1m *******          *********             *              *********          *********   \033[0m      ");
        out.println("   \033[34;1m **               **                    *              **                 ****        \033[0m      ");
        out.println("   \033[34;1m **               **                    *              **                 **  ****    \033[0m      ");
        out.println("   \033[34;1m **               *********             *              *********          **      ****\033[0m      ");
        out.println(" ");
        out.println(" ");
        out.println("   \033[35;1m ********              *****                 *****              **       **        \033[0m      ");
        out.println("   \033[35;1m **     **          **       **           **       **           **     **          \033[0m      ");
        out.println("   \033[35;1m **     **        **           **       **           **         **   **            \033[0m      ");
        out.println("   \033[35;1m *******         **             **     **             **        ****               \033[0m      ");
        out.println("   \033[35;1m **     **        **           **       **           **         **   **            \033[0m      ");
        out.println("   \033[35;1m **     **          **       **           **       **           **     **          \033[0m      ");
        out.println("   \033[35;1m ********              *****                 *****              **       **        \033[0m      ");
        out.println(" ");
        out.println(" ");
        out.println("   \033[32;1m  ********        **          **             *****              ********  \033[0m      ");
        out.println("   \033[32;1m *                **          **          **       **           **     ** \033[0m      ");
        out.println("   \033[32;1m   **             **          **        **           **         **     ** \033[0m      ");
        out.println("   \033[32;1m     **           **************       **             **        *******   \033[0m      ");
        out.println("   \033[32;1m      **          **          **        **           **         **        \033[0m      ");
        out.println("   \033[32;1m        *         **          **          **       **           **        \033[0m      ");
        out.println("   \033[32;1m ********         **          **             *****              **        \033[0m      ");


    }

    //INSTRUCTION
    public static void instruction(BufferedReader in,PrintWriter out) throws IOException {
        try {
            switch (in.readLine()) {
                case "1" -> {
                    out.println("Add information about a book, name, type, price and number.");
                    out.println("Please make sure the number is not repeated.");

                }
                case "2" -> out.println("Select a book you want to delete and enter the number to delete it.");
                case "3" -> out.println("Display information about all books.");
                case "4" -> {
                    out.println("Select a book you want to change and enter the number,");
                    out.println("You can change its name,type and price.");
                }
                case "5" -> {
                    out.println("Input the type.");
                    out.println("All of these types of books will show up.");
                }
                case "6" -> {
                    out.println("Input the max price and min price.");
                    out.println("All the books in this price range will show up.");
                }
                case "7" -> out.println("Instruction of various methods.");
                case "8" -> out.println("End the whole system.");
                default -> out.println("We do not have this method.");
            }

        } catch (IOException e) {
            e.printStackTrace();
            try {
                socket.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}





