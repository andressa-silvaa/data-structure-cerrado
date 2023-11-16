package algorithm;

import java.util.List;

public class BubbleSort {

    public void bubbleSort(List<String[]> data) {
        int n = data.size();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                // Compare as colunas 'fid' para ordenar pela coluna 'fid'
                if (data.get(j)[1].compareTo(data.get(j + 1)[1]) > 0) {
                    // Troca os elementos se estiverem fora de ordem
                    replace(data, j, j + 1);
                }
            }
        }
    }

    private void replace(List<String[]> data, int i, int j) {
        String[] temp = data.get(i);
        data.set(i, data.get(j));
        data.set(j, temp);
    }
}