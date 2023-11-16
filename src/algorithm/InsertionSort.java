package algorithm;

import java.util.List;

public class InsertionSort {

    // Método público para iniciar a ordenação por Insertion Sort.
    public void insertionSort(List<String[]> data) {
        for (int j = 1; j < data.size(); j++) {
            String[] chave = data.get(j);
            int i = j - 1;

            while (i >= 0 && Integer.parseInt(data.get(i)[1]) > Integer.parseInt(chave[1])) {
                data.set(i + 1, data.get(i));
                i--;
            }
            
            data.set(i + 1, chave);
        }
    }
}