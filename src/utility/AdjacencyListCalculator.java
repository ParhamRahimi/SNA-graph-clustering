package utility;

import java.util.ArrayList;

/**
 * Created by Parham on 30-Dec-17.
 */
public class AdjacencyListCalculator {
    private ArrayList<ArrayList<Integer>> adjacencyList = null;
    private int sizeOfList = 0;

    public AdjacencyListCalculator(ArrayList<Integer> firstVertices, ArrayList<Integer> secondVertices){
        for (int i = 0; i < firstVertices.size(); i++){
            if (firstVertices.get(i) > sizeOfList)
                sizeOfList = firstVertices.get(i);
        }
        adjacencyList = new ArrayList<ArrayList<Integer>>(sizeOfList);
        for (int i = 0; i < sizeOfList; i++)
            adjacencyList.add(i, new ArrayList<Integer>(0));
        for (int i = 0; i < firstVertices.size(); i++){
            adjacencyList.get(firstVertices.get(i) - 1).add(secondVertices.get(i));
        }
    }

    public ArrayList<ArrayList<Integer>> getAdjacencyList() {
        return adjacencyList;
    }

    public int getSizeOfList() {
        return sizeOfList;
    }
}
