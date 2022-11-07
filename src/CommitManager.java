import java.util.*;

public class CommitManager {

    List<CommitObject> totalCommitsMade = new ArrayList<>();
    List<CommitObject> allCommits = new ArrayList<>();

    List<String> commitFilesRelation = new ArrayList<>();
    GraphWeightCalculator graphWeightCalculator = new GraphWeightCalculator();
    VertexRelationBuilder vertexRelationBuilder = new VertexRelationBuilder();
    BugTasks bugTasks = new BugTasks();
    PopulateMatrix populateMatrix = new PopulateMatrix();
    MatrixRelations matrixRelations = new MatrixRelations();
    Map<String, Integer> frequencyPairMap = new HashMap<>();
    BroadFeatures broadFeatures = new BroadFeatures();
    BusyClasses busyClasses = new BusyClasses();
    int start;
    int end;

    int[][] adjMatrix;

    Set<String> totalFiles = new HashSet<>();
    Set<Set<String>> components = new HashSet<>();
    Expert expert = new Expert();


    boolean addCommit(String developer, int commitTime, String task, Set<String> commitFiles) {
        if(start != -1 && end !=-1){
            CommitObject commitObj = new CommitObject(developer, commitTime, task, commitFiles);
            if(commitTime>=start && commitTime <= end){

                Set<String> filesCommitted;
                bugTasks.generateMapForTasks(task, commitFiles);
                expert.generateMapForExpert(developer, commitFiles);
                broadFeatures.generateMapForFeatures(task, commitFiles);
                busyClasses.storeFiles(commitFiles);

                // add obj
                totalCommitsMade.add(commitObj);
                allCommits.add(commitObj);

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
            } else {
                allCommits.add(commitObj);
            }

        } else {
            Set<String> filesCommitted;
            bugTasks.generateMapForTasks(task, commitFiles);
            expert.generateMapForExpert(developer, commitFiles);
            broadFeatures.generateMapForFeatures(task, commitFiles);
            busyClasses.storeFiles(commitFiles);
            CommitObject commitObj = new CommitObject(developer, commitTime, task, commitFiles);

            // add obj
            totalCommitsMade.add(commitObj);
            allCommits.add(commitObj);

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
        return false;
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

    Set<String> experts(int threshold) {
        Set<String> expertsSet = new HashSet<>();
        expert.unionOfAllFiles();
        expert.getBroadFeatures(components);
        expertsSet = expert.getFeaturesThreshold(threshold);
        return expertsSet;
    }

    List<String> busyClasses ( int limit ) {
        List<String> busyClass = new ArrayList<>();
        busyClasses.generateFrequencyTable();
        busyClass = busyClasses.getBusyClasses(limit);
        return busyClass;
    }

    boolean setTimeWindow( int startTime, int endTime ){
        if(allCommits.size() == 0){
            start = startTime;
            end = endTime;
        } else {
            start = startTime;
            end = endTime;
            for (CommitObject commitObject : allCommits){
                addCommit(commitObject.developer, commitObject.commitTime,commitObject.task,commitObject.commitFiles);
            }
        }

        return true;
    }

    void clearTimeWindow(){
        start = -1;
        end = -1;
    }
}
