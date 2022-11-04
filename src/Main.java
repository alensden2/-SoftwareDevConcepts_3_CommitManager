import java.util.Arrays;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        CommitManager commitManager = new CommitManager();
        commitManager.addCommit("alen",2, "b-232", new HashSet<>(Arrays.asList("A","B","F","D")));
        commitManager.addCommit("alen",2, "b-232", new HashSet<>(Arrays.asList("A","B","C","F","D")));
        commitManager.addCommit("alen",2, "b-232", new HashSet<>(Arrays.asList("A","B","C","F","D")));
        commitManager.addCommit("alen",2, "b-232", new HashSet<>(Arrays.asList("A","B","F","D")));
        commitManager.addCommit("alen",2, "b-232", new HashSet<>(Arrays.asList("A","B","F","D")));
        commitManager.addCommit("alen",2, "b-232", new HashSet<>(Arrays.asList("A","B","F","D")));
        commitManager.addCommit("alen",2, "b-232", new HashSet<>(Arrays.asList("A","F","D")));
        commitManager.addCommit("alen",2, "b-232", new HashSet<>(Arrays.asList("A","F")));
        commitManager.addCommit("alen",2, "b-232", new HashSet<>(Arrays.asList("A","F")));
        commitManager.addCommit("alen",2, "b-232", new HashSet<>(Arrays.asList("A","F")));
        commitManager.addCommit("alen",2, "b-232", new HashSet<>(Arrays.asList("A","F")));
        commitManager.addCommit("alen",2, "b-232", new HashSet<>(Arrays.asList("A","F")));
        commitManager.addCommit("alen",2, "b-232", new HashSet<>(Arrays.asList("B","D")));
        commitManager.addCommit("alen",2, "b-232", new HashSet<>(Arrays.asList("B","F")));
        commitManager.addCommit("alen",2, "b-232", new HashSet<>(Arrays.asList("B","F")));
        commitManager.addCommit("alen",2, "b-232", new HashSet<>(Arrays.asList("B","F")));
        commitManager.addCommit("alen",2, "b-232", new HashSet<>(Arrays.asList("C","E")));
        commitManager.addCommit("alen",2, "b-232", new HashSet<>(Arrays.asList("E","H","G")));
        commitManager.addCommit("alen",2, "b-232", new HashSet<>(Arrays.asList("E","H","G")));
        commitManager.addCommit("alen",2, "b-232", new HashSet<>(Arrays.asList("E","H","G")));
        commitManager.addCommit("alen",2, "b-232", new HashSet<>(Arrays.asList("E","H","G")));
        commitManager.addCommit("alen",2, "b-232", new HashSet<>(Arrays.asList("E","H")));
        commitManager.addCommit("alen",2, "b-232", new HashSet<>(Arrays.asList("E","H")));
        commitManager.addCommit("alen",2, "b-232", new HashSet<>(Arrays.asList("E","H")));
        commitManager.addCommit("alen",2, "b-232", new HashSet<>(Arrays.asList("E","H")));
        commitManager.addCommit("alen",2, "b-232", new HashSet<>(Arrays.asList("E","G")));
        commitManager.addCommit("alen",2, "b-232", new HashSet<>(Arrays.asList("E","G")));


    }
}