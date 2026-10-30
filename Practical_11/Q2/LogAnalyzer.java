import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Stream;

public class LogAnalyzer {
    public static void main(String[] args) {
        String keyword = "ERROR";
        Path file1 = Paths.get("app.log");
        Path file2 = Paths.get("db.log");

        try {
            Files.write(file1, List.of("INFO: Server started", "ERROR: Connection timeout", "INFO: Retrying..."));
            Files.write(file2, List.of("INFO: DB initialized", "DEBUG: Query ok", "ERROR: Disk full", "ERROR: Crash"));

            List<Path> logFiles = List.of(file1, file2);

            long totalLines = 0;
            long keywordCount = 0;

            for (Path path : logFiles) {
                long size = Files.size(path);
                System.out.println("File: " + path.getFileName() + " | Size: " + size + " bytes");

                try (Stream<String> lines = Files.lines(path)) {
                    List<String> lineList = lines.toList();
                    totalLines += lineList.size();
                    keywordCount += lineList.stream().filter(line -> line.contains(keyword)).count();
                }
            }

            System.out.println("\n--- Summary ---");
            System.out.println("Total lines analyzed: " + totalLines);
            System.out.println("Lines containing '" + keyword + "': " + keywordCount);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                Files.deleteIfExists(file1);
                Files.deleteIfExists(file2);
            } catch (IOException ignored) {}
        }
    }
}
