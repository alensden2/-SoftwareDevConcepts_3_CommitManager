import java.util.*;

public class CommitManager {

    List<CommitObject> totalCommitsMade = new ArrayList<>();
    List<String> commitFilesRelation = new ArrayList<>();
    GraphWeightCalculator graphWeightCalculator = new GraphWeightCalculator();
    VertexRelationBuilder vertexRelationBuilder = new VertexRelationBuilder();
    PopulateMatrix populateMatrix = new PopulateMatrix();
    Map<String, Integer> frequencyPairMap = new HashMap<>();

    Set<String> totalFiles = new HashSet<>();
    Set<Set<String>> components = new HashSet<>();

    boolean addCommit(String developer, int commitTime, String task, Set<String> commitFiles) {
        Set<String> filesCommitted;
        CommitObject commitObj = new CommitObject(developer, commitTime, task, commitFiles);
        // add obj
        totalCommitsMade.add(commitObj);

        // accessing the set of files
        filesCommitted = commitObj.commitFiles;

        // to get the relation between files we get all the possible connections of graph
        commitFilesRelation.addAll(vertexRelationBuilder.findConnections(commitFiles));

        // this is to calculate the size of the 2d matrix
        String[] filesCommittedArray = filesCommitted.toArray(new String[filesCommitted.size()]);
        Collections.addAll(totalFiles, filesCommittedArray);

        // to get values to fill in the 8x8 mat


        return true;
    }

    boolean componentMinimum(int threshold){
        frequencyPairMap = graphWeightCalculator.frequencyPairs(commitFilesRelation);

        populateMatrix.createMatrix(totalFiles);

        System.out.println(populateMatrix.populateMatrixFrequencies(frequencyPairMap));

        return true;
    }
}
