package cs3500.pa05;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa05.model.Week;
import cs3500.pa05.model.enums.CategoryColor;
import cs3500.pa05.model.enums.Completion;
import cs3500.pa05.model.enums.DaysWeek;
import cs3500.pa05.model.file.managing.JsonUtils;
import cs3500.pa05.model.objects.Category;
import cs3500.pa05.model.objects.Day;
import cs3500.pa05.model.objects.Event;
import cs3500.pa05.model.objects.Task;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test Class for JsonUtils
 */
public class JsonUtilsTest {
  private Week week;
  private Day[] days;
  private ArrayList<Task> queue;
  private ArrayList<Category> categories;
  private ArrayList<Event> events;
  private String title;
  private int maxTasks;
  private int maxEvents;

  /**
   * The setup
   */
  @BeforeEach
  public void setup() {
    this.days = new Day[] {
        new Day(DaysWeek.MONDAY),
        new Day(DaysWeek.TUESDAY),
        new Day(DaysWeek.WEDNESDAY),
        new Day(DaysWeek.THURSDAY),
        new Day(DaysWeek.FRIDAY),
        new Day(DaysWeek.SATURDAY),
        new Day(DaysWeek.SUNDAY)};
    this.queue = new ArrayList<>();
    maxEvents = 1;
    maxTasks = 1;
    this.days[0].addTask(new Task("hi", DaysWeek.MONDAY, Completion.COMPLETE,
        "", new Category("B", CategoryColor.GREEN)));
    this.days[0].addEvent(new Event("H", DaysWeek.MONDAY, "i",
        "01:30", "11:11", new Category("B", CategoryColor.GREEN)));
    title = "HI";
    week = new Week(title, this.days, this.queue,
        this.categories, this.events, maxTasks, maxEvents);
  }

  @Test
  public void testJsonUtils() {
    String s = new JsonUtils().seralizeWeek(week);
    String expected = "{\"title\":\"HI\",\"days\":[{\"tasks\":[{\"name\":\"hi\",\"day\":\"MONDAY\""
        + ",\"completion\":\"COMPLETE\",\"description\":\"\",\"category\":{\"name\":\"B\",\"color\""
        + ":\"GREEN\"}}],\"event\":[{\"name\":\"H\",\"day\":\"MONDAY\",\"description\":\"i\","
        + "\"startTime\":\"01:30\",\"duration\":\"11:11\",\"category\":{\"name\":\"B\",\"color\":"
        + "\"GREEN\"}}]},{\"tasks\":[],\"event\":[]},{\"tasks\":[],\"event\":[]},{\"tas"
        + "ks\":[],\"event\":[]},{\"tasks\":[],\"event\":[]},{\"tasks\":[],\"event\":[]},"
        + "{\"tasks\":[],\"event\":[]}],\"categories\":nu"
        + "ll,\"maxTasks\":1,\"maxEvents\":1}";
    assertEquals(expected, s);
  }

  @Test
  public void testJsonUtils2() {
    Week week1 = new JsonUtils().deserializeWeek(
        "{\"title\":\"HI\",\"days\":[{\"tasks\":[{\"name\":\"hi\",\"day\":\"MONDAY\",\"com"
            + "pletion\":\"COMPLETE\",\"description\":\"\",\"category\":{\"name\":\"B\",\""
            + "color\":\"GREEN\"}}],\"event\":[{\"name\":\"H\",\"day\":\"MONDAY\",\"description\""
            + ":\"i\",\"startTime\":\"01:30\",\"duration\":\"11:11\",\"category\":{\"name\":\"B\","
            + "\"color\":\"GREEN\"}}]},{\"tasks\":[],\"event\":[]},{\"tasks\":[],\"event\":[]},"
            + "{\"tasks\":[],\"event\":[]},{\"tasks\":[],\"event\":[]},{\"tasks\":[],\"event\""
            + ":[]},{\"tasks\":[],\"event\":[]}],\"categories\":null,\"maxTasks\":1,\"maxEvents\""
            + ":1}");
    assertEquals(week1.getTitle(), week.getTitle());
    assertEquals(week1.getTasksInDay(DaysWeek.MONDAY).get(0).getDay(),
        week.getTasksInDay(DaysWeek.MONDAY).get(0).getDay());
    assertEquals(week1.getTasksInDay(DaysWeek.MONDAY).get(0).getName(),
        week.getTasksInDay(DaysWeek.MONDAY).get(0).getName());
    assertEquals(week1.getTasksInDay(DaysWeek.MONDAY).get(0).getCategory(),
        week.getTasksInDay(DaysWeek.MONDAY).get(0).getCategory());
    assertEquals(week1.getTasksInDay(DaysWeek.MONDAY).get(0).getCompletion(),
        week.getTasksInDay(DaysWeek.MONDAY).get(0).getCompletion());
    assertEquals(week1.getTasksInDay(DaysWeek.MONDAY).get(0).getDescription(),
        week.getTasksInDay(DaysWeek.MONDAY).get(0).getDescription());
    assertEquals(week1.getTasksInDay(DaysWeek.TUESDAY), week.getTasksInDay(DaysWeek.TUESDAY));
    assertEquals(week1.getTasksInDay(DaysWeek.WEDNESDAY), week.getTasksInDay(DaysWeek.WEDNESDAY));
    assertEquals(week1.getTasksInDay(DaysWeek.THURSDAY), week.getTasksInDay(DaysWeek.THURSDAY));
    assertEquals(week1.getTasksInDay(DaysWeek.FRIDAY), week.getTasksInDay(DaysWeek.FRIDAY));
    assertEquals(week1.getTasksInDay(DaysWeek.SATURDAY), week.getTasksInDay(DaysWeek.SATURDAY));
    assertEquals(week1.getTasksInDay(DaysWeek.SUNDAY), week.getTasksInDay(DaysWeek.SUNDAY));

    assertEquals(week1.getEventInDay(DaysWeek.TUESDAY), week.getTasksInDay(DaysWeek.TUESDAY));
    assertEquals(week1.getEventInDay(DaysWeek.WEDNESDAY), week.getTasksInDay(DaysWeek.WEDNESDAY));
    assertEquals(week1.getEventInDay(DaysWeek.THURSDAY), week.getTasksInDay(DaysWeek.THURSDAY));
    assertEquals(week1.getEventInDay(DaysWeek.FRIDAY), week.getTasksInDay(DaysWeek.FRIDAY));
    assertEquals(week1.getEventInDay(DaysWeek.SATURDAY), week.getTasksInDay(DaysWeek.SATURDAY));
    assertEquals(week1.getEventInDay(DaysWeek.SUNDAY), week.getTasksInDay(DaysWeek.SUNDAY));

    assertEquals(week1.getMaxEvents(), week.getMaxEvents());
    assertEquals(week1.getMaxTasks(), week.getMaxTasks());
  }
}
