package utility;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Parham on 30-Dec-17.
 */
public class AdjacencySparseMatrixCalculator {
    private ArrayList<Integer[]> adjacencySparseMatrix = new ArrayList<>(0);
    public AdjacencySparseMatrixCalculator(ArrayList<Integer> firstVertices, ArrayList<Integer> secondVertices){
        for (int i = 0; i < firstVertices.size(); i++){
            Integer[] as = {firstVertices.get(i) - 1, secondVertices.get(i) - 1};
            adjacencySparseMatrix.add(as);
        }
    }

    public ArrayList<Integer[]> getAdjacencySparseMatrix() {
        return adjacencySparseMatrix;
    }
}
