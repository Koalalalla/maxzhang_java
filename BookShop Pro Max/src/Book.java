public class Book {
    String book;
    String label;
    String type;
    int price;

    public Book() {

    }
    public Book(String book, String label, String type, int price) {
        this.book = book;
        this.label = label;
        this.type = type;
        this.price=price;
    }
    public String getBook() {
        String space=getSpace(book);
        return book+space;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getLabel() {
        String space=getSpace(label);
        return label+space;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    public String getType() {
        String space=getSpace(type);
        return type+space;
    }

    public void setType(String type) {
        this.type = type;
    }
    public void setPrice(int price){
        this.price=price;
    }
    public int getPrice(){
        return price;
    }
    public String getSpace(String item){
        int n=20-item.length();
        StringBuilder space = new StringBuilder(" ");
        while(n>0){
            space.append(" ");
            n--;
        }
        return space.toString();
    }
    public String toString(){
        return book+";"+label+";"+type+";"+price;
    }
}