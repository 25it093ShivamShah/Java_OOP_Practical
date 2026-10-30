import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.stream.Stream;

public class FolderReportGenerator {

    public static void generateReport(Path rootDir, Path outputFile) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(outputFile);
             Stream<Path> paths = Files.walk(rootDir)) {

            writer.write(String.format("%-40s | %-12s | %-30s%n", "File Path", "Size (bytes)", "Last Modified"));
            writer.write("-".repeat(90));
            writer.newLine();

            paths.filter(Files::isRegularFile).forEach(path -> {
                try {
                    BasicFileAttributes attrs = Files.readAttributes(path, BasicFileAttributes.class);
                    Path relativePath = rootDir.relativize(path);
                    String line = String.format("%-40s | %-12d | %-30s%n",
                            relativePath,
                            attrs.size(),
                            attrs.lastModifiedTime());
                    writer.write(line);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    public static void main(String[] args) {
        Path targetDir = Paths.get("sample_project");
        Path reportFile = Paths.get("folder_report.txt");

        try {
            Files.createDirectories(targetDir.resolve("src"));
            Files.createDirectories(targetDir.resolve("docs"));
            Files.writeString(targetDir.resolve("src/App.java"), "public class App {}");
            Files.writeString(targetDir.resolve("docs/readme.txt"), "Documentation contents");

            generateReport(targetDir, reportFile);

            System.out.println("Report generated:\n");
            Files.readAllLines(reportFile).forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                Files.deleteIfExists(targetDir.resolve("src/App.java"));
                Files.deleteIfExists(targetDir.resolve("docs/readme.txt"));
                Files.deleteIfExists(targetDir.resolve("src"));
                Files.deleteIfExists(targetDir.resolve("docs"));
                Files.deleteIfExists(targetDir);
                Files.deleteIfExists(reportFile);
            } catch (IOException ignored) {}
        }
    }
}
