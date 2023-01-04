/**
 * Software Development Concepts
 * 
 * @author Alen Santosh John
 * @author B00930528
 * 
 *         This class is used to get all the busy classes
 * 
 */
import java.util.*;

public class BusyClasses {
    List<String> committedFiles = new ArrayList<>();
    Map<String, Integer> frequencyTable = new LinkedHashMap<>();
    Map<String, Integer> sortedFrequencyTable = new LinkedHashMap<>();
    int count;

    /**
     * 
     * @param commitFiles
     */
    void storeFiles(Set<String> commitFiles) {
        committedFiles.addAll(commitFiles);
    }

    void generateFrequencyTable() {
        for (String file : committedFiles) {
            if (frequencyTable.containsKey(file)) {
                int newValue = frequencyTable.get(file) + 1;
                frequencyTable.put(file, newValue);
            } else {
                frequencyTable.put(file, 1);
            }
        }
        // reference - https://howtodoinjava.com/java/sort/java-sort-map-by-values/
        frequencyTable.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> sortedFrequencyTable.put(x.getKey(), x.getValue()));
    }

    /**
     * 
     * @param limit
     * @return
     */
    List<String> getBusyClasses(int limit) {
        Iterator<Map.Entry<String, Integer>> itr = sortedFrequencyTable.entrySet().iterator();
        Map<String, Integer> busyClasses = new LinkedHashMap<>();
        List<String> busyClass = new ArrayList<>();
        this.count = limit;
        while (itr.hasNext()) {
            Map.Entry<String, Integer> entry = itr.next();
            if (count != 0) {
                busyClasses.put(entry.getKey(), entry.getValue());
                busyClass.add(entry.getKey());
                count--;
            } else {
                break;
            }
        }

        String valueToBeFound = busyClass.get(limit - 1);
        int valueOfToBeFound = sortedFrequencyTable.get(valueToBeFound);

        Iterator<Map.Entry<String, Integer>> iter = sortedFrequencyTable.entrySet().iterator();

        while (iter.hasNext()) {
            Map.Entry<String, Integer> entry = iter.next();
            if ((entry.getValue() == valueOfToBeFound)) {
                if (!busyClass.contains(entry.getKey()))
                    busyClass.add(entry.getKey());
            }
        }
        return busyClass;
    }
}
