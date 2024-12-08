import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class bookshop {
    public static void main(String[] args) {
        ArrayList<method> list = new ArrayList();
        Scanner sc = new Scanner(System.in);
        System.out.println(" ");
        System.out.println(" ");
        logo();
        System.out.println(" ");
        System.out.println("\33[33;1m -----<-----<-----<-----<-----WELCOME----->----->----->----->-----\033[0m");
        System.out.println("\33[33;1m ! Welcome to the Peter Bookshop !\033[0m");
        System.out.println(" ");
        System.out.println("\033[33;1m Before you use it,please let us know you are not a robot.\033[0m\033[032m[-_-]\033[0m");
        System.out.println("\033[33;1m So,please enter the verification code\033[0m");
        System.out.println("\33[33;1m -----<-----<-----<-----<-------<->------->----->----->----->-----\033[0m");
        System.out.println("\033[31m Warm:make sure it right,otherwise you can not use the system!!!\033[0m");

        System.out.println(" ");
        System.out.println("===SOME BOOK===");

        method book1=new method("Robinson Crusoe","1","Adventure");
        book1.setPrice(20);
        method book2=new method("Three body1","2","Science");
        book2.setPrice(25);
        method book3=new method("Jane Eyre ","3","Plot");
        book3.setPrice(30);
        method book4=new method("Three body2","4","Science");
        book4.setPrice(30);
        method book5=new method("Little Prince","5","Adventure");
        book5.setPrice(17);
        list.add(book1);
        list.add(book2);
        list.add(book3);
        list.add(book4);
        list.add(book5);

        show(list);
    //The verification code
        System.out.println("The verification code:");
        boolean flag=true;
        do{
            String NewCode=code();
            System.out.println(NewCode);
            System.out.print(">>>");
            String sure= sc.next();
        if (sure.equalsIgnoreCase(NewCode)){
            flag=true;
            }else
            {
                System.out.println("\033[31mWrong\033[0m");
                System.out.println("\033[31mPlease input again\033[0m");
                flag=false;}
        }while (!flag);
            System.out.println("\033[034m-------------------------------------------------------------------------------------------------------------------------\033[0m");
            System.out.println("\033[34mSUCCESSFUL\033[0m");
    way:    while (true) {
            System.out.println("\033[034m-------------------------------------------------------------------------------------------------------------------------\033[0m");
            System.out.println("Please choose the way");
            System.out.println("1:Add the book information");
            System.out.println("2:Delete the book information");
            System.out.println("3:Check the book information");
            System.out.println("4:Change the book information");
            System.out.println("5:Filter the book according to the type");
            System.out.println("6:Filter the book according to the price");
            System.out.println("7:Instruction of each function");
            System.out.println("8:Exit");
            System.out.println("\033[034m-------------------------------------------------------------------------------------------------------------------------\033[0m");
            System.out.print(">>>");
            int chose = sc.nextInt();

            switch (chose) {
                case 1: {
                    System.out.println("===Add===");
                    add(list);
                    break;
                }
                case 2: {
                    System.out.println("===Delete===");
                    delete(list);
                    break;
                }
                case 3: {
                    System.out.println("===Check===");
                    broose(list);
                    break;
                }
                case 4: {
                    System.out.println("===Change===");
                    change(list);
                    break;
                }
                case 5: {
                    System.out.println("===GetType===");
                    hobby(list);
                    break;
                }
                case 6: {
                    System.out.println("===GetPrice===");
                    price(list);
                    break;
                    }
                case 7: {
                    System.out.println("===Instruction===");
                    System.out.println("Please choose the way you want to know");
                    System.out.println("1,2,3,4,5,6,7,8");
                    instruction();
                    break;
                    }
                case 8: {
                    System.out.println("===Exit===");
                    System.out.println("Thank you for using our system,BYE~");
                    break way;
                }
                default:{
                    System.out.println("\033[31mWrong\033[0m");
                    System.out.println("\033[31mPlease rechose\033[0m");
                    break;
                    }
                }
            }
    }
    //CHECK
    public static void broose(ArrayList<method> list) {
        if (list.isEmpty()) {
            System.out.println("Not data available");
        } else {
            show(list);
        }
    }
    //SHOW
    public static void show(ArrayList<method> list){
        System.out.println("\033[035m-------------------------------------------------------------------------------------------------------------------------\033[0m");
        System.out.println("\33[35m|\33[0m\tName \t\t\t\t\t\t\t\33[35m|\33[0m\tType \t\t\t\t\t\t\t\33[35m|\33[0m\tNumber\t\t\t\t\t\t\t\33[35m|\33[0m\tPrice\t\33[35m|\33[0m\t");
        System.out.println("\033[035m-------------------------------------------------------------------------------------------------------------------------\033[0m");
        for (method book : list) {
            System.out.println("\33[35m|\33[0m\t"+book.getBook() + "\t\t\t\33[35m|\33[0m\t" + book.getType() + "\t\t\t\33[35m|\33[0m\t" + book.getLabel() + "\t\t\t\33[35m|\33[0m\t" + book.getPrice() + "￥"+"\t\33[35m|\33[0m\t");
        }
        System.out.println("\033[035m-------------------------------------------------------------------------------------------------------------------------\033[0m");
    }
    //ADD
    public static void add(ArrayList<method> list) {
        method book = new method();
        Scanner sc = new Scanner(System.in);
        System.out.println("Add book name");
        System.out.print(">>>");
        String name = sc.next();
        book.setBook(name);
        System.out.println("Add book type");
        System.out.print(">>>");
        String type = sc.next();
        book.setType(type);
        boolean flag = true;
        String input;
        do {
            System.out.println("Add book number ");
            System.out.print(">>>");
            input = sc.next();
            for (method example : list) {
                if (input.equals(example.getLabel())) {
                    System.out.println("\033[34mThe number exits\033[0m");
                    System.out.println("\033[34mPlease input again\033[0m");
                    flag = false;
                    break;
                } else {
                    flag = true;
                }
            }
        } while (!flag);
        book.setLabel(input);

        System.out.println("Add book price");
        System.out.print(">>>");
        int price= sc.nextInt();
        book.setPrice(price);
        list.add(book);
    }
    //DELETE+NUMBER
    public static void delete(ArrayList<method> list) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Add the number");
        System.out.print(">>>");
        String label = sc.next();
        int num = labelGet(list, label);
        if (check(list, label)) {
            list.remove(num);
            System.out.println("Delete the book with the number"+label);
        } else {
            System.out.println("The number does not exist");
        }
    }

    public static boolean check(ArrayList<method> list, String label) {
        int b = labelGet(list, label);
        return b != -1;
    }

    public static int labelGet(ArrayList<method> list, String label) {
        for (int i = 0; i < list.size(); i++) {
            method book = list.get(i);
            String realLabel = book.getLabel().trim();
            if (realLabel.equals(label)) {
                return i;
            }
        }
        return -1;
    }
    //CHANGE
    public static void change(ArrayList<method> list) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please choose the book number you want to change");
        System.out.print(">>>");
        String label = sc.next();
        int num = labelGet(list, label);
        if (check(list, label)) {
            method book = list.get(num);
            System.out.println("Please input the name");
            System.out.print(">>>");
            String name = sc.next();
            book.setBook(name);
            System.out.println("Please input the type");
            System.out.print(">>>");
            String type = sc.next();
            book.setType(type);
            System.out.println("Please input the price");
            System.out.print(">>>");
            int price=sc.nextInt();
            book.setPrice(price);

        }
    }
    //TYPE
    public static void hobby(ArrayList<method>list){
        Scanner sc=new Scanner(System.in);
        System.out.println("Please input the type");
        System.out.print(">>>");
        String hobby=sc.next();
        System.out.println("\033[035m-------------------------------------------------------------------------------------------------------------------------\033[0m");
        System.out.println("\33[35m|\33[0m\tName \t\t\t\t\t\t\t\33[35m|\33[0m\tType \t\t\t\t\t\t\t\33[35m|\33[0m\tNumber\t\t\t\t\t\t\t\33[35m|\33[0m\tPrice\t\33[35m|\33[0m\t");
        System.out.println("\033[035m-------------------------------------------------------------------------------------------------------------------------\033[0m");
        for (method book : list) {
            String hobbyType = book.getType().trim();
            if (hobby.equals(hobbyType)) {
                System.out.println("\33[35m|\33[0m\t"+book.getBook() + "\t\t\t\33[35m|\33[0m\t" + book.getType() + "\t\t\t\33[35m|\33[0m\t" + book.getLabel() + "\t\t\t\33[35m|\33[0m\t" + book.getPrice() + "￥"+"\t\33[35m|\33[0m\t");
            }
        }
        System.out.println("\033[035m-------------------------------------------------------------------------------------------------------------------------\033[0m");
    }
    //PRICE
    public static void price(ArrayList<method>list){
     Scanner sc=new Scanner(System.in);
        System.out.println("Please input the max price");
        System.out.print(">>>");
        int maxPrice= sc.nextInt();
        System.out.println("Please input the min price");
        System.out.print(">>>");
        int minPrice= sc.nextInt();
        System.out.println("\033[035m-------------------------------------------------------------------------------------------------------------------------\033[0m");
        System.out.println("\33[35m|\33[0m\tName \t\t\t\t\t\t\t\33[35m|\33[0m\tType \t\t\t\t\t\t\t\33[35m|\33[0m\tNumber\t\t\t\t\t\t\t\33[35m|\33[0m\tPrice\t\33[35m|\33[0m\t");
        System.out.println("\033[035m-------------------------------------------------------------------------------------------------------------------------\033[0m");
        for (method book : list) {
            int priceGet = book.getPrice();
            if (priceGet >= minPrice & priceGet <= maxPrice) {
                System.out.println("\33[35m|\33[0m\t"+book.getBook() + "\t\t\t\33[35m|\33[0m\t" + book.getType() + "\t\t\t\33[35m|\33[0m\t" + book.getLabel() + "\t\t\t\33[35m|\33[0m\t" + book.getPrice() + "￥"+"\t\33[35m|\33[0m\t");
            }
        }
        System.out.println("\033[035m-------------------------------------------------------------------------------------------------------------------------\033[0m");
    }
    //VERIFICATION CODE
    public static String code(){
        StringBuilder sb=new StringBuilder(4);
        for (int i = 0; i < sb.capacity()-1; i++) {
            Random code=new Random();
           sb.append(code.nextInt(10));
        }
        Random r=new Random();
        char l=(char)(r.nextInt(26)+65);
        sb.append(l);
        return sb.toString();
    }
    //LOGO
    public static void logo(){
        System.out.println("   \033[34;1m ********         *********        ***********         *********          **********  \033[0m      ");
        System.out.println("   \033[34;1m **     **        **                    *              **                 **       ** \033[0m      ");
        System.out.println("   \033[34;1m **     **        **                    *              **                 **       ** \033[0m      ");
        System.out.println("   \033[34;1m *******          *********             *              *********          *********   \033[0m      ");
        System.out.println("   \033[34;1m **               **                    *              **                 ****        \033[0m      ");
        System.out.println("   \033[34;1m **               **                    *              **                 **  ****    \033[0m      ");
        System.out.println("   \033[34;1m **               *********             *              *********          **      ****\033[0m      ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("   \033[35;1m ********              *****                 *****              **       **        \033[0m      ");
        System.out.println("   \033[35;1m **     **          **       **           **       **           **     **          \033[0m      ");
        System.out.println("   \033[35;1m **     **        **           **       **           **         **   **            \033[0m      ");
        System.out.println("   \033[35;1m *******         **             **     **             **        ****               \033[0m      ");
        System.out.println("   \033[35;1m **     **        **           **       **           **         **   **            \033[0m      ");
        System.out.println("   \033[35;1m **     **          **       **           **       **           **     **          \033[0m      ");
        System.out.println("   \033[35;1m ********              *****                 *****              **       **        \033[0m      ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("   \033[32;1m  ********        **          **             *****              ********  \033[0m      ");
        System.out.println("   \033[32;1m *                **          **          **       **           **     ** \033[0m      ");
        System.out.println("   \033[32;1m   **             **          **        **           **         **     ** \033[0m      ");
        System.out.println("   \033[32;1m     **           **************       **             **        *******   \033[0m      ");
        System.out.println("   \033[32;1m      **          **          **        **           **         **        \033[0m      ");
        System.out.println("   \033[32;1m        *         **          **          **       **           **        \033[0m      ");
        System.out.println("   \033[32;1m ********         **          **             *****              **        \033[0m      ");


    }
    //INSTRUCTION
    public static void instruction(){
        Scanner sc=new Scanner(System.in);
        System.out.print(">>>");
        int num= sc.nextInt();
        switch (num){
            case 1->{
                System.out.println("Add information about a book, name, type, price and number.");
                System.out.println( "Please make sure the number is not repeated.");

            }
            case 2-> System.out.println("Select a book you want to delete and enter the number to delete it.");
            case 3-> System.out.println("Display information about all books.");
            case 4->{
                System.out.println("Select a book you want to change and enter the number,");
                System.out.println("You can change its name,type and price.");
            }
            case 5->{
                System.out.println("Input the type.");
                System.out.println("All of these types of books will show up.");
            }
            case 6->{
                System.out.println("Input the max price and min price.");
                System.out.println("All the books in this price range will show up.");
            }
            case 7-> System.out.println("Instruction of various methods.");
            case 8-> System.out.println("End the whole system.");
            default -> System.out.println("We do not have this method.");
        }
    }
}





