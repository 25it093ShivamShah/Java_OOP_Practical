public class chatFilter {
    public static String filter(String[] logs, String keyword) {
        StringBuilder report = new StringBuilder();
        int count = 0;

        for (String line : logs) {
            if (line.toLowerCase().contains(keyword.toLowerCase())) {
                count++;
                String censored = line.replaceAll("(?i)" + java.util.regex.Pattern.quote(keyword), "***");
                report.append(censored).append("\n");
            }
        }
        report.append("Total flagged messages: ").append(count);
        return report.toString();
    }
}
