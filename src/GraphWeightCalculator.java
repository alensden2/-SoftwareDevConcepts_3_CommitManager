import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphWeightCalculator {
    Map<String, Integer> frequencyPairs(List<String> commitRelationFiles) {
        Map<String, Integer> frequencyPairMap = new HashMap<>();

        for (String pair : commitRelationFiles) {
            if (frequencyPairMap.containsKey(pair)) {
                int newValue = frequencyPairMap.get(pair) + 1;
                frequencyPairMap.put(pair, newValue);
            } else {
                frequencyPairMap.put(pair, 1);
            }
        }

        return frequencyPairMap;
    }
}
