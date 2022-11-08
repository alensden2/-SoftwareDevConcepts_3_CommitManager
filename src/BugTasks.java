
/**
 * Software Development Concepts
 * 
 * @author Alen Santosh John
 * @author B00930528
 * 
 *         This class is used to get the repetion in bugs
 * 
 */
import com.sun.jdi.Value;

import java.security.Key;
import java.util.*;

public class BugTasks {
    Map<String, ArrayList<String>> taskFiles = new HashMap<>();
    Map<String, Integer> bugFiles = new HashMap<>();
    Set<String> repeatedBugs = new HashSet<>();

    /**
     * 
     * @param taskName
     * @param committedFiles
     */
    // stores the bugs
    void generateMapForTasks(String taskName, Set<String> committedFiles) {
        ArrayList<String> files = new ArrayList<>();
        if (taskName.charAt(0) == 'B') {
            if (taskFiles.containsKey(taskName)) {
                ArrayList<String> currentFiles = new ArrayList<>();
                currentFiles = taskFiles.get(taskName);
                files.addAll(committedFiles);
                files.addAll(currentFiles);
                taskFiles.put(taskName, files);
                files = new ArrayList<>();

            } else {
                files.addAll(committedFiles);
                taskFiles.put(taskName, files);
                // clear
                files = new ArrayList<>();
            }
        }

    }

    // ref - https://www.geeksforgeeks.org/iterate-map-java/
    void generateMaxFrequencyCommits() {
        Iterator<Map.Entry<String, ArrayList<String>>> itr = taskFiles.entrySet().iterator();
        ArrayList<String> currentFilesPerCommit = new ArrayList<>();
        while (itr.hasNext()) {
            Map<String, Integer> commitFrequencies = new HashMap<>();
            Map.Entry<String, ArrayList<String>> entry = itr.next();
            currentFilesPerCommit = entry.getValue();
            for (String file : currentFilesPerCommit) {
                if (commitFrequencies.containsKey(file)) {
                    int newValue = commitFrequencies.get(file) + 1;
                    commitFrequencies.put(file, newValue);
                } else {
                    commitFrequencies.put(file, 1);
                }
            }
            int currentCommitMax = Collections.max(commitFrequencies.values());
            String currentBug = entry.getKey();
            bugFiles.put(currentBug, currentCommitMax);
            // clear
            commitFrequencies = new HashMap<>();
        }

    }

    /**
     * 
     * @param threshold
     * @return
     */
    // returns the set of all the repeated bugs
    Set<String> repeatedBugs(int threshold) {
        Iterator<Map.Entry<String, Integer>> itr = bugFiles.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry<String, Integer> entry = itr.next();
            if (entry.getValue() >= threshold) {
                repeatedBugs.add(entry.getKey());
            }
        }
        return repeatedBugs;
    }
}
