package utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Parham on 29-Dec-17.
 */
public class InputFileReader {
    private ArrayList<Integer> firstVertices = new ArrayList<Integer>(0);
    private ArrayList<Integer> secondVertices = new ArrayList<Integer>(0);
    public InputFileReader(String fileName) {
        // This will reference one line at a time
        String line = null;
        String[] splitString = null;
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int i = 0;
            while ((line = bufferedReader.readLine()) != null) {
                splitString = line.split(",");
                firstVertices.add(Integer.parseInt(splitString[0]));
                secondVertices.add(Integer.parseInt(splitString[1]));
                //System.out.println(firstVertices.get(i) + "\t" + secondVertices.get(i));
                i++;
            }
            // Always close files.
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
    }

    public ArrayList<Integer> getFirstVertices() {
        return firstVertices;
    }

    public ArrayList<Integer> getSecondVertices() {
        return secondVertices;
    }
}
