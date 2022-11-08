/**
 * Software Development Concepts
 * 
 * @author Alen Santosh John
 * @author B00930528
 * 
 *         Commit Object
 * 
 */
import java.util.Set;

public class CommitObject {
    String developer;
    int commitTime;
    String task;
    Set<String> commitFiles;

    /**
     * 
     * @param dev
     * @param time
     * @param taskString
     * @param commit
     */
    CommitObject(String dev, int time, String taskString, Set<String> commit) {
        developer = dev;
        commitTime = time;
        task = taskString;
        commitFiles = commit;
    }
}
