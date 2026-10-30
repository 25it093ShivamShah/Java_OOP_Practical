import java.util.*;

public class Leaderboard {
    public static void printTopN(Map<String, Integer> scores, int n) {
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(scores.entrySet());

        Comparator<Map.Entry<String, Integer>> leaderboardComparator = (e1, e2) -> {
            int scoreComparison = e2.getValue().compareTo(e1.getValue());
            if (scoreComparison != 0) {
                return scoreComparison;
            }
            return e1.getKey().compareToIgnoreCase(e2.getKey());
        };

        entries.sort(leaderboardComparator);

        System.out.println("Top " + n + " Players:");
        int limit = Math.min(n, entries.size());
        for (int i = 0; i < limit; i++) {
            Map.Entry<String, Integer> entry = entries.get(i);
            System.out.printf("%d. %-10s : %d%n", (i + 1), entry.getKey(), entry.getValue());
        }
    }

    public static void main(String[] args) {
        Map<String, Integer> playerScores = new HashMap<>();
        playerScores.put("Zara", 850);
        playerScores.put("Alex", 920);
        playerScores.put("Bob", 740);
        playerScores.put("Charlie", 920);
        playerScores.put("David", 850);
        playerScores.put("Elena", 990);
        playerScores.put("Frank", 610);

        printTopN(playerScores, 5);
    }
}
