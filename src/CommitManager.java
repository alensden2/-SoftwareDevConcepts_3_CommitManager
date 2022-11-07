import java.util.*;

public class CommitManager {

    List<CommitObject> totalCommitsMade = new ArrayList<>();
    List<String> commitFilesRelation = new ArrayList<>();
    GraphWeightCalculator graphWeightCalculator = new GraphWeightCalculator();
    VertexRelationBuilder vertexRelationBuilder = new VertexRelationBuilder();
    BugTasks bugTasks = new BugTasks();
    PopulateMatrix populateMatrix = new PopulateMatrix();
    MatrixRelations matrixRelations = new MatrixRelations();
    Map<String, Integer> frequencyPairMap = new HashMap<>();
    BroadFeatures broadFeatures = new BroadFeatures();
    int[][] adjMatrix;

    Set<String> totalFiles = new HashSet<>();
    Set<Set<String>> components = new HashSet<>();
    Expert expert = new Expert();


    boolean addCommit(String developer, int commitTime, String task, Set<String> commitFiles) {
        Set<String> filesCommitted;
        bugTasks.generateMapForTasks(task, commitFiles);
        expert.generateMapForExpert(developer, commitFiles);
        broadFeatures.generateMapForFeatures(task, commitFiles);
        CommitObject commitObj = new CommitObject(developer, commitTime, task, commitFiles);
        // add obj
        totalCommitsMade.add(commitObj);

        // accessing the set of files
        filesCommitted = commitObj.commitFiles;

        // to get the relation between files we get all the possible connections of
        // graph
        commitFilesRelation.addAll(vertexRelationBuilder.findConnections(commitFiles));

        // this is to calculate the size of the 2d matrix
        String[] filesCommittedArray = filesCommitted.toArray(new String[filesCommitted.size()]);
        Collections.addAll(totalFiles, filesCommittedArray);

        // to get values to fill in the 8x8 mat

        return true;
    }

    boolean componentMinimum(int threshold) {
        frequencyPairMap = graphWeightCalculator.frequencyPairs(commitFilesRelation);

        populateMatrix.createMatrix(totalFiles);

        adjMatrix = populateMatrix.populateMatrixFrequencies(frequencyPairMap);
        matrixRelations.createGeneralComponentSet(adjMatrix, totalFiles.size(), threshold, totalFiles);
        matrixRelations.relatedComponents(adjMatrix);
        matrixRelations.commonComponentGenerator();
        return true;
    }

    Set<Set<String>> softwareComponents() {
        Set<Set<String>> components = new HashSet<>();
        components = matrixRelations.getAllComponents();
        this.components = components;
        return components;
    }

    Set<String> repetitionInBugs(int threshold) {
        bugTasks.generateMaxFrequencyCommits();
        Set<String> repeatedBugs = new HashSet<>();
        repeatedBugs = bugTasks.repeatedBugs(threshold);
        return repeatedBugs;
    }

    Set<String> broadFeatures(int threshold) {
        Set<String> broadFeaturesSet = new HashSet<>();
        broadFeatures.unionOfAllFiles();
        broadFeatures.getBroadFeatures(components);
        broadFeaturesSet = broadFeatures.getFeaturesThreshold(threshold);
        return broadFeaturesSet;
    }

    Set<String> expert(int threshold) {
        Set<String> expertsSet = new HashSet<>();
        expert.unionOfAllFiles();
        expert.getBroadFeatures(components);
        expertsSet = expert.getFeaturesThreshold(threshold);
        return expertsSet;
    }

}
