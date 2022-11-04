import java.util.Set;

public class CommitObject {
    String developer;
    int commitTime;
    String task;
    Set<String> commitFiles;

    CommitObject(String dev, int time, String taskString, Set<String> commit){
        developer = dev;
        commitTime = time;
        task = taskString;
        commitFiles = commit;
    }
}
