import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        CommitManager commitManager = new CommitManager();
        commitManager.addCommit("alen", 2, "b-232", new HashSet<>(Arrays.asList("A", "B", "C", "D")));
        commitManager.addCommit("alen", 2, "b-232", new HashSet<>(Arrays.asList("A", "B", "C")));
        commitManager.addCommit("alen", 2, "b-232", new HashSet<>(Arrays.asList("A", "B")));
        commitManager.addCommit("alen", 2, "b-232", new HashSet<>(Arrays.asList("A", "D")));
        // commitManager.addCommit("alen",2, "b-232", new
        // HashSet<>(Arrays.asList("C","B")));
        commitManager.addCommit("alen", 2, "b-232", new HashSet<>(Arrays.asList("D", "C")));
        commitManager.addCommit("alen", 2, "b-232", new HashSet<>(Arrays.asList("B")));
        commitManager.addCommit("alen", 2, "b-232", new HashSet<>(Arrays.asList("E", "A")));
        commitManager.addCommit("alen", 2, "b-232", new HashSet<>(Arrays.asList("B", "D")));
        commitManager.addCommit("alen", 2, "b-232", new HashSet<>(Arrays.asList("A", "D")));
        commitManager.addCommit("alen", 2, "b-232", new HashSet<>(Arrays.asList("B", "C", "D")));
        commitManager.addCommit("alen", 2, "b-232", new HashSet<>(Arrays.asList("E", "A")));
        commitManager.addCommit("alen", 2, "b-232", new HashSet<>(Arrays.asList("A", "B", "E")));
        commitManager.addCommit("alen", 2, "b-232", new HashSet<>(Arrays.asList("D", "E")));
        commitManager.addCommit("alen", 2, "b-232", new HashSet<>(Arrays.asList("D", "C")));

        commitManager.componentMinimum(1);
        System.out.println("Hello world!");

        // Set<String> s1 = new HashSet<>(Arrays.asList("A","B"));
        // Set<String> s2 = new HashSet<>(Arrays.asList("B"));
        //
        // Set<String> s3 = new HashSet<>(Arrays.asList("E","G", "H"));
        // Set<String> s4 = new HashSet<>(Arrays.asList("G","H"));
        //
        // s1.retainAll(s2);
        // System.out.println(s1.isEmpty());
    }
}