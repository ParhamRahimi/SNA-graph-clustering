package utility;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Parham on 21-Jan-18.
 */
public class OutputFileWriter {
    public OutputFileWriter(boolean[] isVisited) throws IOException {
        FileWriter fileWriter = new FileWriter("output.txt");
        String newLine = System.getProperty("line.separator");


        if (isVisited[0]) {
            for (int i = 0; i < isVisited.length; i++) {
                if (isVisited[i])
                    fileWriter.write("#" + (i + 1) + " : A" + newLine);
                else
                    fileWriter.write("#" + (i + 1) + " : B" + newLine);
            }
        } else {
            for (int i = 0; i < isVisited.length; i++) {
                if (!isVisited[i])
                    fileWriter.write("#" + (i + 1) + " : A" + newLine);
                else
                    fileWriter.write("#" + (i + 1) + " : B" + newLine);
            }
        }
        fileWriter.close();
    }
}
