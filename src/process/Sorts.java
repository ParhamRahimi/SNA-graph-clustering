package process;

import java.util.ArrayList;

/**
 * Created by Parham on 17-Jan-18.
 */
public class Sorts {

    private ArrayList<Float[]> edges = new ArrayList<>(0);

    public Sorts(ArrayList<Float[]> edges){
        this.edges = edges;
    }

    public ArrayList<Float[]> insertionSort(int L, int R)
    {
        Float[] key = {Float.valueOf(0), Float.valueOf(0), Float.valueOf(0)};
        for (int i = L; i < R; ++i)
        {
            key[0] = edges.get(i)[0];
            key[1] = edges.get(i)[1];
            key[2] = edges.get(i)[2];
            int j = i - 1;
            while ((j >= 0) && (edges.get(j)[2] > key[2]))
            {
                edges.get(j + 1)[0] = edges.get(j)[0];
                edges.get(j + 1)[1] = edges.get(j)[1];
                edges.get(j + 1)[2] = edges.get(j)[2];
                j = j - 1;
            }
            edges.get(j + 1)[0] = key[0];
            edges.get(j + 1)[1] = key[1];
            edges.get(j + 1)[2] = key[2];
        }
        return edges;
    }


    public ArrayList<Float[]> bubbleSort(int L, int R)
    {
        Float[] temp = {Float.valueOf(0), Float.valueOf(0), Float.valueOf(0)};
        int n = edges.size();
        for (int i = L; i < R; i++)
            for (int j = 0; j < R-i-1; j++)
                if (edges.get(j)[2] > edges.get(j+1)[2])
                {
                    // swap temp and arr[i]
                    temp[0] = edges.get(j)[0];
                    temp[1] = edges.get(j)[1];
                    temp[2] = edges.get(j)[2];
                    edges.get(j)[0] = edges.get(j + 1)[0];
                    edges.get(j)[1] = edges.get(j + 1)[1];
                    edges.get(j)[2] = edges.get(j + 1)[2];
                    edges.get(j + 1)[0] = temp[0];
                    edges.get(j + 1)[1] = temp[1];
                    edges.get(j + 1)[2] = temp[2];
                }
        return edges;
    }

    // Merges two subarrays of arr[].
    // First subarray is arr[l..m]
    // Second subarray is arr[m+1..r]
    private void merge(int l, int m, int r)
    {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        ArrayList<Float[]> L = new ArrayList<>(n1);
        ArrayList<Float[]> R = new ArrayList<>(n2);
        Float[] temp = {Float.valueOf(0), Float.valueOf(0), Float.valueOf(0)};

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i) {
            L.add(i, temp);
            L.get(i)[0] = edges.get(l + i)[0];
            L.get(i)[1] = edges.get(l + i)[1];
            L.get(i)[2] = edges.get(l + i)[2];
        }

        Float[] temp1 = {Float.valueOf(0), Float.valueOf(0), Float.valueOf(0)};
        for (int j = 0; j < n2; ++j){
            R.add(j, temp1);
            R.get(j)[0] = edges.get(m + 1+ j)[0];
            R.get(j)[1] = edges.get(m + 1+ j)[1];
            R.get(j)[2] = edges.get(m + 1+ j)[2];
        }


        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2)
        {
            if (L.get(i)[2] <= R.get(j)[2])
            {
                edges.get(k)[0] = L.get(i)[0];
                edges.get(k)[1] = L.get(i)[1];
                edges.get(k)[2] = L.get(i)[2];
                i++;
            }
            else
            {
                edges.get(k)[0] = R.get(j)[0];
                edges.get(k)[1] = R.get(j)[1];
                edges.get(k)[2] = R.get(j)[2];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1)
        {
            edges.get(k)[0] = L.get(i)[0];
            edges.get(k)[1] = L.get(i)[1];
            edges.get(k)[2] = L.get(i)[2];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2)
        {
            edges.get(k)[0] = R.get(j)[0];
            edges.get(k)[1] = R.get(j)[1];
            edges.get(k)[2] = R.get(j)[2];
            j++;
            k++;
        }
    }
    // Main function that sorts arr[l..r] using
    // merge()
    public ArrayList<Float[]>  mergeSort(int l, int r)
    {
        if (l < r)
        {
            // Find the middle point
            int m = (l+r)/2;

            // Sort first and second halves
            mergeSort(l, m);
            mergeSort(m+1, r);

            // Merge the sorted halves
            merge(l, m, r);
        }
        return edges;
    }

    public ArrayList<Float[]>  quickSort(int low, int high) {
        int i = low, j = high;
        double pivot = edges.get((low + high) / 2)[2];
        while (i <= j) {
            while (edges.get(i)[2] < pivot) i++;
            while (edges.get(j)[2]  > pivot) j--;
            if (i <= j) {
                Float[] temp = {Float.valueOf(0), Float.valueOf(0), Float.valueOf(0)};
                temp[0] = edges.get(i)[0];
                temp[1] = edges.get(i)[1];
                temp[2] = edges.get(i)[2];
                edges.get(i)[0] = edges.get(j)[0];
                edges.get(i)[1] = edges.get(j)[1];
                edges.get(i)[2] = edges.get(j)[2];
                edges.get(j)[0] = temp[0];
                edges.get(j)[1] = temp[1];
                edges.get(j)[2] = temp[2];
                i++;
                j--;
            }
        }
        if (low < j)
            quickSort(low, j);
        if (i < high)
            quickSort(i, high);
        return edges;
    }

    public ArrayList<Float[]>  optimumBubbleQuickSort(int low, int high, int N) {
        int i = low, j = high;
        if (j - i < N)
            return bubbleSort(i , j);
        double pivot = edges.get((low + high) / 2)[2];
        while (i <= j) {
            while (edges.get(i)[2] < pivot) i++;
            while (edges.get(j)[2]  > pivot) j--;
            if (i <= j) {
                Float[] temp = {Float.valueOf(0), Float.valueOf(0), Float.valueOf(0)};
                temp[0] = edges.get(i)[0];
                temp[1] = edges.get(i)[1];
                temp[2] = edges.get(i)[2];
                edges.get(i)[0] = edges.get(j)[0];
                edges.get(i)[1] = edges.get(j)[1];
                edges.get(i)[2] = edges.get(j)[2];
                edges.get(j)[0] = temp[0];
                edges.get(j)[1] = temp[1];
                edges.get(j)[2] = temp[2];
                i++;
                j--;
            }
        }
        if (low < j)
            quickSort(low, j);
        if (i < high)
            quickSort(i, high);
        return edges;
    }

    public ArrayList<Float[]>  optimumInsertionQuickSort(int low, int high, int N) {
        int i = low, j = high;
        if (j - i < N)
            return insertionSort(i , j);
        double pivot = edges.get((low + high) / 2)[2];
        while (i <= j) {
            while (edges.get(i)[2] < pivot) i++;
            while (edges.get(j)[2]  > pivot) j--;
            if (i <= j) {
                Float[] temp = {Float.valueOf(0), Float.valueOf(0), Float.valueOf(0)};
                temp[0] = edges.get(i)[0];
                temp[1] = edges.get(i)[1];
                temp[2] = edges.get(i)[2];
                edges.get(i)[0] = edges.get(j)[0];
                edges.get(i)[1] = edges.get(j)[1];
                edges.get(i)[2] = edges.get(j)[2];
                edges.get(j)[0] = temp[0];
                edges.get(j)[1] = temp[1];
                edges.get(j)[2] = temp[2];
                i++;
                j--;
            }
        }
        if (low < j)
            quickSort(low, j);
        if (i < high)
            quickSort(i, high);
        return edges;
    }
}
