package algorithm;

import java.util.List;


public class QuickSort {
    public static void quickSort(List<String[]> data) {
        quickSort(data, 0, data.size() - 1);
    }

    private static void quickSort(List<String[]> data, int left, int right) {
        int leftIndex = left;
        int rightIndex = right;
        String[] pivot = data.get((left + right) / 2);
        String[] temp;

        while (leftIndex <= rightIndex) {
            while (data.get(leftIndex)[1].compareTo(pivot[1]) < 0) {
                leftIndex++;
            }
            while (data.get(rightIndex)[1].compareTo(pivot[1]) > 0) {
                rightIndex--;
            }
            if (leftIndex <= rightIndex) {
                temp = data.get(leftIndex);
                data.set(leftIndex, data.get(rightIndex));
                data.set(rightIndex, temp);
                leftIndex++;
                rightIndex--;
            }
        }

        if (rightIndex > left) {
            quickSort(data, left, rightIndex);
        }
        if (leftIndex < right) {
            quickSort(data, leftIndex, right);
        }
    }
}    