import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PopulateMatrix {
    int[][] adjMatrix;
    int adjMatrixSize;
    Map<String, Integer> frequencyPairMap;
    List<String> arrayIndex = new ArrayList<>();

    void createMatrix(Set<String> totalFiles) {
        adjMatrixSize = totalFiles.size();
        arrayIndex.addAll(totalFiles);
        adjMatrix = new int[adjMatrixSize][adjMatrixSize];
    }

    int[][] populateMatrixFrequencies(Map<String, Integer> frequencyPairMap) {
        arrayIndex.sort(String::compareTo);

        frequencyPairMap.forEach((k, v) -> {
            int x1;
            int y1;

            String c1;
            String c2;

            c1 = String.valueOf(k.charAt(0));
            c2 = String.valueOf(k.charAt(1));

            x1 = arrayIndex.indexOf(c1);
            y1 = arrayIndex.indexOf(c2);

            adjMatrix[x1][y1] = v;
            adjMatrix[y1][x1] = v;

        }

        );
        return adjMatrix;
    }

}
