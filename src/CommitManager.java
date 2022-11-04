import java.util.*;

public class CommitManager {

    List<CommitObject> totalCommitsMade = new ArrayList<>();
    Set<String> totalFiles = new HashSet<>();

    boolean addCommit(String developer, int commitTime, String task, Set<String> commitFiles) {
        Set<String> filesCommitted;
        CommitObject commitObj = new CommitObject(developer, commitTime, task, commitFiles);
        // add obj
        totalCommitsMade.add(commitObj);
        filesCommitted = commitObj.commitFiles;
        String[] filesCommittedArray = filesCommitted.toArray(new String[filesCommitted.size()]);
        Collections.addAll(totalFiles, filesCommittedArray);

        return true;
    }
}
