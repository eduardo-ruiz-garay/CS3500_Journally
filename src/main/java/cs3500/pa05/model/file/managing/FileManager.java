package cs3500.pa05.model.file.managing;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;


/**
 * A utility class for reading from and writing to files.
 */
public class FileManager {

  /**
   * Default constructor for FileManager.
   */
  public FileManager() {
  }

  /**
   * Writes a JSON-encoded string to a file at the specified path.
   * Only writes to files with a ".bujo" extension.
   *
   * @param jsonEncoding the JSON-encoded string to write.
   * @param path         the path of the file to write to.
   * @throws RuntimeException if the file path doesn't end with ".bujo".
   */
  public static void writeFile(String jsonEncoding, Path path) {
    if (path.toString().endsWith(".bujo")) {
      try {
        Files.write(path, Collections.singletonList(jsonEncoding), StandardCharsets.UTF_8);
      } catch (IOException e) {
        System.out.println("An error occurred.");
      }
    } else {
      throw new RuntimeException("file path doesn't end with '.bujo'");
    }
  }

  /**
   * Reads a file at the specified path and returns its contents as a string.
   * Only reads from files with a ".bujo" extension.
   *
   * @param path the path of the file to read.
   * @return the contents of the file as a string.
   * @throws RuntimeException if the file path doesn't end with ".bujo".
   */
  public static String readFile(Path path) {
    if (path.toString().endsWith(".bujo")) {
      try {
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        return String.join(System.lineSeparator(), lines);
      } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
    } else {
      throw new RuntimeException("File path doesn't end with '.bujo'");
    }
    return null;
  }
}
