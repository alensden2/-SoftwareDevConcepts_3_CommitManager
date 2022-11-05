import java.util.*;

public class VertexRelationBuilder {
    List<String> findConnections(Set<String> files){
        List<String> commitFilesRelations = new ArrayList<>();
        Set<String> newConnections = new HashSet<>();
        List<String> fileList = new ArrayList<>();
        fileList.addAll(files);
        for(int i = 0; i<fileList.size(); i++){
            for (int j = 0; j<fileList.size(); j++){
                if(i==j){
                    j += 1;
                } else {
                    String newConnection = "";
                    newConnection = fileList.get(i)+fileList.get(j);
                    char sortedString[] = newConnection.toCharArray();
                    Arrays.sort(sortedString);

                    newConnections.add(String.valueOf(sortedString));
                }
            }
        }
        commitFilesRelations.addAll(newConnections);
        return commitFilesRelations;
    }
}
