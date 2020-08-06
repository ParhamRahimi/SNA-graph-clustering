package process;

import utility.AdjacencyListCalculator;
import utility.OutputFileWriter;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Parham on 17-Jan-18.
 */
public class ListEdgePointCalculator {
    public enum SortType {BUBBLE, QUICK, INSERTION, MERGE, OPTIMUM_INSERTION, OPTIMUM_BUBBLE}
    private ArrayList<Float[]> edges = new ArrayList<>(0);
    private ArrayList<ArrayList<Integer>> adjacencyList = null;
    private Stack stack;
    private boolean[] isVisited;
    private int deletedEdgeLeft = 0, deletedEdgeRight = 1;
    private long start, algorithmStart;

    public ListEdgePointCalculator(AdjacencyListCalculator adjacencyListCalculator, SortType sortType, int N){
        start = System.currentTimeMillis();
        int deletedEdgeCounter = 0;
        this.adjacencyList = adjacencyListCalculator.getAdjacencyList();
        stack = new Stack();
        isVisited = new boolean[adjacencyListCalculator.getSizeOfList()];
        for (int i = 0; i < adjacencyListCalculator.getSizeOfList(); i++) {
            for (int j = 0; j < adjacencyListCalculator.getAdjacencyList().get(i).size(); j++){
                if (adjacencyListCalculator.getAdjacencyList().get(i).get(j) > i) {
                    Float[] as = {Float.valueOf(i + 1), Float.valueOf(adjacencyListCalculator.getAdjacencyList().get(i).get(j)), Float.valueOf(0)};
                    edges.add(as);
                }
            }
        }
        TimeHandler.readAndSave += (System.currentTimeMillis() - start);
        algorithmStart = System.currentTimeMillis();
        while (isConnected()) {
            pointCalculator();
            Sorts sorts = new Sorts(edges);
            switch (sortType) {
                case MERGE:
                    edges = sorts.mergeSort(0, edges.size() - 1);
                    break;
                case QUICK:
                    edges = sorts.quickSort(0, edges.size() - 1);
                    break;
                case BUBBLE:
                    edges = sorts.bubbleSort(0, edges.size() - 1);
                    break;
                case INSERTION:
                    edges = sorts.insertionSort(0, edges.size() - 1);
                    break;
                case OPTIMUM_BUBBLE:
                    edges = sorts.optimumBubbleQuickSort(0, edges.size() - 1, N);
                    break;
                case OPTIMUM_INSERTION:
                    edges = sorts.optimumInsertionQuickSort(0, edges.size() - 1, N);
                    break;
            }
            deletedEdgeLeft = edges.get(0)[0].intValue();
            deletedEdgeRight = edges.get(0)[1].intValue();

/*

            for (int i = 0; i < edges.size(); i++){
                System.out.println(edges.get(i)[0] + " " + edges.get(i)[1] + " " + edges.get(i)[2]);
            }
            System.out.println("*****" + isConnected());
*/
            for (int i = 0; i < adjacencyList.get((edges.get(0)[0]).intValue() - 1).size(); i++){
                if (adjacencyList.get((edges.get(0)[0]).intValue() - 1).get(i).intValue() == (edges.get(0)[1]).intValue()){
                    adjacencyList.get((edges.get(0)[0]).intValue() - 1).remove(i);
                    break;
                }
            }
            for (int i = 0; i < adjacencyList.get((edges.get(0)[1]).intValue() - 1).size(); i++){
                if (adjacencyList.get((edges.get(0)[1]).intValue() - 1).get(i).intValue() == (edges.get(0)[0]).intValue()){
                    adjacencyList.get((edges.get(0)[1]).intValue() - 1).remove(i);
                    break;
                }
            }
            deletedEdgeCounter++;
            System.out.println(deletedEdgeCounter + ": Edge " + edges.get(0)[0] + "-" + edges.get(0)[1] + " has been removed!");
            edges.remove(0);
        }
        TimeHandler.algorithm = System.currentTimeMillis() - algorithmStart;
        try {
            OutputFileWriter outputFileWriter = new OutputFileWriter(isVisited);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void pointCalculator(){
        int thirdVertex = 0;
        int point = 0;
        for (int i = 0; i < edges.size(); i++){//iterates on edges || i: edge
            for (int j = 0; j < adjacencyList.get((int) (edges.get(i)[0] - 1)).size(); j++){//iterates on vertices connected to left vertex
                thirdVertex = adjacencyList.get((int) (edges.get(i)[0] - 1)).get(j);
                for (int k = 0; k < adjacencyList.get((int) (edges.get(i)[1] - 1)).size(); k++) {//iterates on vertices connected to right vertex
                    if (thirdVertex == adjacencyList.get((int) (edges.get(i)[1] - 1)).get(k)) {
                        point++;
                        break;
                    }
                }
            }
            try {
                edges.get(i)[2] = (point + 1) / Float.valueOf(Math.min(adjacencyList.get((int) (edges.get(i)[0] - 1)).size() - 1,
                        adjacencyList.get((int) (edges.get(i)[1] - 1)).size() - 1));
            } catch (ArithmeticException e){
                edges.get(i)[2] = Float.valueOf(Float.MAX_VALUE);
            }
            point = 0;
        }
    }


    public boolean isConnected(){
        for (int i = 0; i < adjacencyList.size(); i++)
            isVisited[i] = false;
        stack.push(deletedEdgeLeft);
        while (!stack.isEmpty()){
            int temp = stack.pop();
            if (!isVisited[temp]) {
                isVisited[temp] = true;
                if (temp == deletedEdgeRight)
                    return true;
                for (int i = 0; i < adjacencyList.get(temp).size(); i++) {
                    if (!isVisited[adjacencyList.get(temp).get(i) - 1]) {
                        stack.push(adjacencyList.get(temp).get(i) - 1);
                    }
                }
            }
        }
        for (int i = 0; i < isVisited.length; i++){
            if (!isVisited[i])
                return false;
        }
        return true;
    }


    public ArrayList<Float[]> getEdges() {
        return edges;
    }
}
