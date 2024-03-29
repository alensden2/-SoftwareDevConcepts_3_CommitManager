
/**
 * Software Development Concepts
 * 
 * @author Alen Santosh John
 * @author B00930528
 * 
 *         Returns all the developers
 * 
 */
import java.util.*;

public class Expert {
    Map<String, ArrayList<String>> taskFiles = new HashMap<>();
    ArrayList<ArrayList<String>> allComponents = new ArrayList<>();
    Map<String, Integer> thresholdFrequencyComponents = new HashMap<>();

    /**
     * 
     * @param developer
     * @param committedFiles
     */
    void generateMapForExpert(String developer, Set<String> committedFiles) {
        ArrayList<String> files = new ArrayList<>();

        if (taskFiles.containsKey(developer)) {
            ArrayList<String> currentFiles = new ArrayList<>();
            currentFiles = taskFiles.get(developer);
            files.addAll(committedFiles);
            files.addAll(currentFiles);
            taskFiles.put(developer, files);
            files = new ArrayList<>();

        } else {
            files.addAll(committedFiles);
            taskFiles.put(developer, files);
            // clear
            files = new ArrayList<>();
        }
    }

    void unionOfAllFiles() {
        Iterator<Map.Entry<String, ArrayList<String>>> itr = taskFiles.entrySet().iterator();

        Set<String> allFilesPerCommit = new HashSet<>();

        ArrayList<String> allFilesPerCommitList = new ArrayList<>();

        while (itr.hasNext()) {
            Map.Entry<String, ArrayList<String>> entry = itr.next();
            allFilesPerCommit.addAll(entry.getValue());
            allFilesPerCommitList.addAll(allFilesPerCommit);
            taskFiles.put(entry.getKey(), allFilesPerCommitList);
            allFilesPerCommit = new HashSet<>();
            allFilesPerCommitList = new ArrayList<>();
        }

    }

    /**
     * 
     * @param components
     */
    void getBroadFeatures(Set<Set<String>> components) {
        ArrayList<ArrayList<String>> allComponents = new ArrayList<>();
        Map<String, Integer> thresholdFrequencyComponent = new HashMap<>();

        for (Set<String> component : components) {
            ArrayList<String> currentComponent = new ArrayList<>();
            currentComponent.addAll(component);
            allComponents.add(currentComponent);
            currentComponent = new ArrayList<>();
        }
        this.allComponents = allComponents;

        Iterator<Map.Entry<String, ArrayList<String>>> itr = taskFiles.entrySet().iterator();

        while (itr.hasNext()) {
            Map.Entry<String, ArrayList<String>> entry = itr.next();
            for (ArrayList<String> component : allComponents) {
                Set<String> tempSet1 = new HashSet<>();
                Set<String> tempSet2 = new HashSet<>();
                tempSet1.addAll(entry.getValue());
                tempSet2.addAll(component);
                tempSet1.retainAll(tempSet2);
                if (tempSet1.size() > 0) {
                    if (thresholdFrequencyComponent.containsKey(entry.getKey())) {
                        int newValue = thresholdFrequencyComponent.get(entry.getKey());
                        newValue += 1;
                        thresholdFrequencyComponent.put(entry.getKey(), newValue);
                    } else {
                        thresholdFrequencyComponent.put(entry.getKey(), 1);
                    }
                }
                tempSet1 = new HashSet<>();
                tempSet2 = new HashSet<>();
            }
        }
        this.thresholdFrequencyComponents = thresholdFrequencyComponent;
    }

    /**
     * 
     * @param threshold
     * @return
     */
    Set<String> getFeaturesThreshold(int threshold) {
        Set<String> broadFeatureSet = new HashSet<>();
        Iterator<Map.Entry<String, Integer>> itr = thresholdFrequencyComponents.entrySet().iterator();

        while (itr.hasNext()) {
            Map.Entry<String, Integer> entry = itr.next();
            if (entry.getValue() >= threshold) {
                broadFeatureSet.add(entry.getKey());
            }
        }
        return broadFeatureSet;
    }

}
