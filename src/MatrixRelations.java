import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MatrixRelations {
    Set<String> generalComponents = new HashSet<>();
    Set<String> independentComponents = new HashSet<>();
    Set<Set<String>> connectedComponents = new HashSet<>();
    List<String> arrayIndex = new ArrayList<>();
    List<Integer> rowsToBeAvoided = new ArrayList<>();
    int size;
    int threshold;

    Set<String> createGeneralComponentSet(int[][] adjMatrix, int size, int threshold, Set<String> totalFiles) {
        arrayIndex.addAll(totalFiles);
        this.size = size;
        this.threshold = threshold;
        Set<String> rowComponents = new HashSet<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (adjMatrix[i][j] >= threshold) {
                    String x1 = arrayIndex.get(i);
                    String y1 = arrayIndex.get(j);
                    rowComponents.add(x1);
                    rowComponents.add(y1);
                } else if (j == size - 1 && rowComponents.size() != 0) {
                    generalComponents.addAll(rowComponents);
                    rowComponents.clear();
                } else if ((j == size - 1) && rowComponents.size() == 0) {
                    String x = arrayIndex.get(i);
                    independentComponents.add(x);
                }
            }
        }
        List<String> independentComponentList = new ArrayList<>();
        List<Integer> rowsToBeAvoided = new ArrayList<>();
        independentComponentList.addAll(independentComponents);
        for(int i = 0; i<independentComponentList.size(); i++){
            String component = independentComponentList.get(i);
            int indexToBeAvoided = arrayIndex.indexOf(component);
            rowsToBeAvoided.add(indexToBeAvoided);
        }

        this.rowsToBeAvoided = rowsToBeAvoided;
        return generalComponents;
    }

    void relatedComponents(int[][] adjMatrix) {
        Set<String> linkedNodes = new HashSet<>();
        List<String> generalComponentsList = new ArrayList<>();
        generalComponentsList.addAll(generalComponents);
        for(int i = 0; i < generalComponentsList.size(); i++){
            connectedComponents.add(linkedNodes);
            linkedNodes = new HashSet<>();
                for(int j = 0; j < generalComponentsList.size(); j++){

                        String x1 = generalComponentsList.get(i);
                        String y1 = generalComponentsList.get(j);
                        int xCoordinate = arrayIndex.indexOf(x1);
                        int yCoordinate = arrayIndex.indexOf(y1);
                        if(adjMatrix[xCoordinate][yCoordinate] >= threshold){
                            linkedNodes.add(x1);
                            linkedNodes.add(y1);
                        }

                }
        }
    }
}
