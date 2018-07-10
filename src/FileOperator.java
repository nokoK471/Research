import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

// ファイル操作を行うために作ってみた
public class FileOperator {
    public boolean write(String filename, boolean isAppend, String str) {
        try {
            File file = new File(filename);
            file.createNewFile();	// ファイルがない時作るメソッド。あれば作らないメソッド
            if(!file.canWrite()) {
                System.out.println("Can't write file.");
                return false;
            }

            BufferedWriter bw = new BufferedWriter(new FileWriter(file, isAppend));
            bw.write(str);
            bw.close();
            return true;

        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
    }
    public boolean writeLine(String filename, boolean isAppend, String str) {
        return write(filename, isAppend, str+"\n");
    }
}
