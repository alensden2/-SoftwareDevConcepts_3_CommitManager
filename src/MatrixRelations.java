import java.util.*;

public class MatrixRelations {
    Set<String> generalComponents = new HashSet<>();
    Set<String> independentComponents = new HashSet<>();
    Set<Set<String>> connectedComponents = new HashSet<>();
    List<String> arrayIndex = new ArrayList<>();
    List<Integer> rowsToBeAvoided = new ArrayList<>();
    Set<Set<String>> commonComponentSet = new HashSet<>();
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
        for (int i = 0; i < independentComponentList.size(); i++) {
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
        for (int i = 0; i < generalComponentsList.size(); i++) {
            connectedComponents.add(linkedNodes);
            linkedNodes = new HashSet<>();
            for (int j = 0; j < generalComponentsList.size(); j++) {

                String x1 = generalComponentsList.get(i);
                String y1 = generalComponentsList.get(j);
                int xCoordinate = arrayIndex.indexOf(x1);
                int yCoordinate = arrayIndex.indexOf(y1);
                if (adjMatrix[xCoordinate][yCoordinate] >= threshold) {
                    linkedNodes.add(x1);
                    linkedNodes.add(y1);
                }

            }
        }
    }

    void commonComponentGenerator() {
        Set<Set<String>> connectedComponent = null;
        Set<Set<String>> finalComponents = new HashSet<>();
        connectedComponent = this.connectedComponents;
        connectedComponent.removeIf(x -> (x.size() == 0));
        for (Set<String> s : connectedComponent) {
            if (finalComponents.size() == 0) {
                finalComponents.add(s);
            }
            Iterator<Set<String>> finalComponentIterator = finalComponents.iterator();
            while (finalComponentIterator.hasNext()) {
                Set<String> x = finalComponentIterator.next();
                Set<String> tempSet1 = new HashSet<>(s);
                Set<String> tempSet2 = new HashSet<>(s);
                tempSet1.retainAll(x);
                if (tempSet1.size() > 0) {
                    finalComponents.remove(x);
                    tempSet2.addAll(x);
                    finalComponents.add(tempSet2);
                    tempSet1 = new HashSet<>();
                    tempSet2 = new HashSet<>();
                    break;
                } else {
                    if (!finalComponentIterator.hasNext())
                        finalComponents.add(s);
                }
                //System.out.println(finalComponentIterator.next());
            }
//            for(Set<String> x: finalComponents){
//                    Set<String> tempSet1 = new HashSet<>(s);
//                    Set<String> tempSet2 = new HashSet<>(s);
//                    tempSet1.retainAll(x);
//                    if(tempSet1.size()>0){
//                        finalComponents.remove(x);
//                        tempSet2.addAll(x);
//                        finalComponents.add(tempSet2);
//                        tempSet1 = new HashSet<>();
//                        tempSet2 = new HashSet<>();
//                        break;
//                    }else {
//                        if(finalComponents.)
//                        finalComponents.add(s);
//                        continue;
//                    }
//                }

        }
        commonComponentSet.addAll(finalComponents);
    }

    Set<Set<String>> getAllComponents(){
        Set<Set<String>> allComponents = new HashSet<>();
        Set<String> unrelatedComponents = new HashSet<>();
        allComponents.addAll(commonComponentSet);
        for(String s : independentComponents){
            unrelatedComponents.add(s);
            allComponents.add(unrelatedComponents);
            unrelatedComponents = new HashSet<>();
        }
        return allComponents;
    }

}
