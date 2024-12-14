import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;

public class List {
    public ArrayList<Book>list;
    public Database db;

    public List(){
        try {
            list = new ArrayList<>(getFileLineNum("index.txt"));
            db = new Database(list);
            for(int i=0;i<getFileLineNum("index.txt")-1;i++){
                Book book = new Book();
                list.add(book);
            }
            db.run();
            db.read();
            db.clean();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getFileLineNum(String filePath) {
        try (LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(filePath))){
            lineNumberReader.skip(Long.MAX_VALUE);
            int lineNumber = lineNumberReader.getLineNumber();
            return lineNumber + 1;//实际上是读取换行符数量 , 所以需要+1
        } catch (IOException e) {
            return -1;
        }
    }
}

//Robinson Crusoe;1;Adventure;20
//Three body1;2;Science;25
//Jane Eyre;3;Plot;30
//Three body2;4;Science;30
//Little Prince;5;Adventure;17
