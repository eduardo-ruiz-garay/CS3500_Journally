package cs3500.pa05;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cs3500.pa05.model.file.managing.FileManager;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for File Manager
 */
public class FileManagerTest {
  private FileManager fileManager;
  private Path tempFile;
  private Path tempDir;

  /**
   * The setup
   */
  @BeforeEach
  public void setup() {
    fileManager = new FileManager();
    try {
      tempDir = Files.createTempDirectory("myTempDir");
      tempFile = Files.createFile(tempDir.resolve("WrongExtension.txt"));
    } catch (IOException e) {
      System.out.println(e);
    }
  }

  @Test
  public void testFileManager() {
    String s = fileManager.readFile(Path.of("src/main/resources/FileManagerTest1.bujo"));
    String expected = "Lets get groovy, Gradle! 1212";
    assertEquals(expected, s);
  }

  @Test
  public void testFileManager2() {
    fileManager.writeFile("{\"title\":\"hi\",\"days\":[{\"tasks\":[],\"event\":"
        + "[]},{\"tasks\":[],\"event\":[]},{\"tasks\":[],\"event\":[]},{\"tasks\":[],\"event\""
        + ":[]},{\"tasks\":[],\"event\":[]},{\"tasks\":[],\"event\":[]},{\"tasks\":[],\"event\":"
        + "[]}],\"categories\":[],\"maxTasks\":0,\"maxEvents\":0}\n",
        Path.of("src/main/resources/FileManagerWrite.bujo"));
    String s = FileManager.readFile(Path.of("src/main/resources/FileManagerWrite.bujo"));
    String expected = "{\"title\":\"hi\",\"days\":[{\"tasks\":[],\"event\":[]},{\"tasks\":[],"
        + "\"event\":[]},{\"tasks\":[],\"event\":[]},{\"tasks\":[],\"event\":[]},{\"tasks\":"
        + "[],\"event\":[]},{\"tasks\":[],\"event\":[]},{\"tasks\":[],\"event\":[]}],"
        + "\"categories\":[],\"maxTasks\":0,\"maxEvents\":0}" + System.lineSeparator();
    assertEquals(expected, s);
  }


  @Test
  public void testWriteWrongExtension() {
    assertThrows(RuntimeException.class, () -> {
      fileManager.writeFile("hello test", tempFile);
    });
  }

  @Test
  public void testReadWrongExtension() {
    assertThrows(RuntimeException.class, () -> {
      fileManager.readFile(tempFile);
    });
  }


  @Test
  public void testFileManager3() {
    fileManager.writeFile("{\"title\":\"hi\",\"days\":[{\"tasks\":[],\"event\":"
        + "[]},{\"tasks\":[],\"event\":[]},{\"tasks\":[],\"event\":[]},{\"tasks\":[],"
        + "\"event\":[]},{\"tasks\":[],\"event\":[]},{\"tasks\":[],\"event\":[]},{\"tasks\":[],"
        + "\"event\":[]}],\"categories\":[],\"maxTasks\":0,\"maxEvents"
        + "\":0}\n", Path.of("src/main/resources/FileManagerWrite.bujo"));
    String s = FileManager.readFile(Path.of("src/main/resources/FileManagerWrite.bujo"));
    String expected = "{\"title\":\"hi\",\"days\":[{\"tasks\":[],\"event\":[]},{\"tasks\":[],"
        + "\"event\":[]},{\"tasks\":[],\"event\":[]},{\"tasks\":[],\"event\":[]},{\"tasks\":"
        + "[],\"event\":[]},{\"tasks\":[],\"event\":[]},{\"tasks\":[],\"event\":[]}],"
        + "\"categories\":[],\"maxTasks\":0,\"maxEvents\":0}" + System.lineSeparator();
    assertEquals(expected, s);
  }

  @Test
  public void testFileManager4() {
    fileManager.writeFile("{\"title\":\"hi\",\"days\":[{\"tasks\":[],\"event\":"
        + "[]},{\"tasks\":[],\"event\":[]},{\"tasks\":[],\"event\":[]},{\"tasks\":[],\"event\":[]},"
        + "{\"tasks\":[],\"event\":[]},{\"tasks\":[],\"event\":[]},{\"tasks\":[],\"event\":[]}]"
        + ",\"categories\":[],\"maxTasks\":0,\"maxEvents"
        + "\":0}\n", Path.of("src/main/resources/FileManagerWrite.bujo"));
    String s = FileManager.readFile(Path.of("src/main/resources/FileManagerWrite.bujo"));
    String expected = "{\"title\":\"hi\",\"days\":[{\"tasks\":[],\"event\":[]},{\"tasks\":[],\""
        + "event\":[]},{\"tasks\":[],\"event\":[]},{\"tasks\":[],\"event\":[]},{\"tasks\":"
        + "[],\"event\":[]},{\"tasks\":[],\"event\":[]},{\"tasks\":[],\"event\":[]}],\"categories"
        + "\":[],\"maxTasks\":0,\"maxEvents\":0}" + System.lineSeparator();
    assertEquals(expected, s);
  }
}
