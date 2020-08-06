import process.ListEdgePointCalculator;
import process.TimeHandler;
import utility.AdjacencyListCalculator;
import utility.InputFileReader;

import java.util.Scanner;

/**
 * Created by Parham on 29-Dec-17.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        TimeHandler.start = System.currentTimeMillis();
        InputFileReader inputFileReader = new InputFileReader("mtest0.txt");
        AdjacencyListCalculator adjacencyListCalculator =
                new AdjacencyListCalculator(inputFileReader.getFirstVertices(), inputFileReader.getSecondVertices());
        TimeHandler.readAndSave = System.currentTimeMillis() - TimeHandler.start;
        ListEdgePointCalculator listEdgePointCalculator = null;
/*        AdjacencySparseMatrixCalculator adjacencyMatrixCalculator =
                new AdjacencySparseMatrixCalculator(inputFileReader.getFirstVertices(), inputFileReader.getSecondVertices());
        for (int i = 0; i < adjacencyMatrixCalculator.getAdjacencySparseMatrix().size(); i++)
            System.out.println(adjacencyMatrixCalculator.getAdjacencySparseMatrix().get(i)[0] + " " +
                    adjacencyMatrixCalculator.getAdjacencySparseMatrix().get(i)[1]);
*/
        Scanner scanner = new Scanner(System.in);
        String inputString =  null, splitString [] = null;
        inputString = scanner.nextLine();
        TimeHandler.orderStart = System.currentTimeMillis();
        try {
            splitString = inputString.split(" ");
            switch (splitString[0]) {
                case "RUN":
                    switch (splitString[1]) {
                        case "LinkedList":
                            switch (splitString[2]) {
                                case "Quick":
                                    listEdgePointCalculator =
                                            new ListEdgePointCalculator(adjacencyListCalculator, ListEdgePointCalculator.SortType.QUICK, 0);
                                    break;
                                case "Insertion":
                                    listEdgePointCalculator =
                                            new ListEdgePointCalculator(adjacencyListCalculator, ListEdgePointCalculator.SortType.INSERTION, 0);
                                    break;
                                case "Merge":
                                    listEdgePointCalculator =
                                            new ListEdgePointCalculator(adjacencyListCalculator, ListEdgePointCalculator.SortType.MERGE, 0);
                                    break;
                                case "Bubble":
                                    listEdgePointCalculator =
                                            new ListEdgePointCalculator(adjacencyListCalculator, ListEdgePointCalculator.SortType.BUBBLE, 0);
                                    break;
                                case "Optimum":
                                    switch (splitString[3]) {
                                        case "Insertion":
                                            listEdgePointCalculator =
                                                    new ListEdgePointCalculator(adjacencyListCalculator, ListEdgePointCalculator.SortType.OPTIMUM_INSERTION, Integer.parseInt(splitString[4]));
                                            break;
                                        case "Bubble":
                                            listEdgePointCalculator =
                                                    new ListEdgePointCalculator(adjacencyListCalculator, ListEdgePointCalculator.SortType.OPTIMUM_BUBBLE, Integer.parseInt(splitString[4]));
                                            break;
                                        default:
                                            System.out.println("Wrong input!");
                                    }
                                    break;
                                default:
                                    System.out.println("Wrong input!");
                            }
                            break;
                        case "Matrix":
                            switch (splitString[2]) {
                                case "Quick":
                                    listEdgePointCalculator =
                                            new ListEdgePointCalculator(adjacencyListCalculator, ListEdgePointCalculator.SortType.QUICK, 0);
                                    break;
                                case "Insertion":
                                    listEdgePointCalculator =
                                            new ListEdgePointCalculator(adjacencyListCalculator, ListEdgePointCalculator.SortType.INSERTION, 0);
                                    break;
                                case "Merge":
                                    listEdgePointCalculator =
                                            new ListEdgePointCalculator(adjacencyListCalculator, ListEdgePointCalculator.SortType.MERGE, 0);
                                    break;
                                case "Bubble":
                                    listEdgePointCalculator =
                                            new ListEdgePointCalculator(adjacencyListCalculator, ListEdgePointCalculator.SortType.BUBBLE, 0);
                                    break;
                                case "Optimum":
                                    switch (splitString[3]) {
                                        case "Insertion":
                                            listEdgePointCalculator =
                                                    new ListEdgePointCalculator(adjacencyListCalculator, ListEdgePointCalculator.SortType.OPTIMUM_INSERTION, Integer.parseInt(splitString[4]));
                                            break;
                                        case "Bubble":
                                            listEdgePointCalculator =
                                                    new ListEdgePointCalculator(adjacencyListCalculator, ListEdgePointCalculator.SortType.OPTIMUM_BUBBLE, Integer.parseInt(splitString[4]));
                                            break;
                                        default:
                                            System.out.println("Wrong input!");
                                    }
                                    break;
                                default:
                                    System.out.println("Wrong input!");
                            }
                            break;
                        default:
                            System.out.println("Wrong input!");
                    }
                default:
                    System.out.println("Wrong input!");
            }
        } catch (NullPointerException e) {
            System.out.println("Wrong input!");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Wrong input!");
        }


/*
        for (int i = 0; i < listEdgePointCalculator.getEdges().size(); i++){
            System.out.println(listEdgePointCalculator.getEdges().get(i)[0] + " " + listEdgePointCalculator.getEdges().get(i)[1] + " " + listEdgePointCalculator.getEdges().get(i)[2]);
        }
*/

        System.out.println("is connected: " + listEdgePointCalculator.isConnected());
        int size = adjacencyListCalculator.getSizeOfList();
        TimeHandler.end = TimeHandler.readAndSave + System.currentTimeMillis() - TimeHandler.orderStart;
        System.out.println("Read And Save: " + TimeHandler.readAndSave);
        System.out.println("Algorithm: " + TimeHandler.algorithm);
        System.out.println("Total Time: " + TimeHandler.end);

/*
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < adjacencyListCalculator.getAdjacencyList().get(i).size(); j++){
                System.out.print(adjacencyListCalculator.getAdjacencyList().get(i).get(j) + " ");
            }
            System.out.println("");
        }
*/
    }
}
