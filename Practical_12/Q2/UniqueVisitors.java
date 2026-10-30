import java.util.*;

public class UniqueVisitors {
    public static void main(String[] args) {
        List<String> logins = List.of(
            "alice", "bob", "charlie", "alice", "david", "bob", "emma", "frank", "charlie"
        );

        Set<String> uniqueSet = new HashSet<>(logins);
        System.out.println("Unique user count: " + uniqueSet.size());

        Set<String> orderedUniqueSet = new LinkedHashSet<>(logins);

        System.out.println("First three distinct visitors:");
        int count = 0;
        for (String name : orderedUniqueSet) {
            if (count == 3) {
                break;
            }
            System.out.println((count + 1) + ". " + name);
            count++;
        }
    }
}
