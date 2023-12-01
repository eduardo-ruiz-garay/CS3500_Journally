package cs3500.pa05;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa05.model.enums.CategoryColor;
import cs3500.pa05.model.enums.Completion;
import cs3500.pa05.model.enums.DaysWeek;
import cs3500.pa05.model.objects.Category;
import cs3500.pa05.model.objects.Day;
import cs3500.pa05.model.objects.Event;
import cs3500.pa05.model.objects.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



/**
 * Test class for Day
 */
class DayTest {
  private Day day;
  private DaysWeek testDay;
  private Task task1;
  private Task task2;
  private Event event1;
  private Event event2;

  /**
   * The SetUp
   */
  @BeforeEach
  void setUp() {
    testDay = DaysWeek.MONDAY;
    day = new Day(testDay);
    task1 = new Task("Task 1", testDay, Completion.INCOMPLETE,
        "Description 1", new Category("Category 1", CategoryColor.BLACK));
    task2 = new Task("Task 2", testDay, Completion.INCOMPLETE,
        "Description 2", new Category("Category 2", CategoryColor.GREEN));
    event1 = new Event("Event 1", testDay, "Description 1",
        "10:00 AM", "1 hour", new Category("Category 1", CategoryColor.BLACK));
    event2 = new Event("Event 2", testDay, "Description 2",
        "02:00 PM", "2 hours", new Category("Category 2", CategoryColor.GREEN));
  }

  @Test
  void testGetters() {
    assertEquals(0, day.getEvent().size());
    assertEquals(0, day.getTasks().size());
  }

  @Test
  void testAddTask() {
    day.addTask(task1);
    assertEquals(1, day.getTasks().size());
    assertTrue(day.getTasks().contains(task1));

    day.addTask(task2);
    assertEquals(2, day.getTasks().size());
    assertTrue(day.getTasks().contains(task2));
  }

  @Test
  void testRemoveTask() {
    day.addTask(task1);
    day.addTask(task2);

    day.removeTask(task1);
    assertEquals(1, day.getTasks().size());
    assertFalse(day.getTasks().contains(task1));
    assertTrue(day.getTasks().contains(task2));
  }

  @Test
  void testAddEvent() {
    day.addEvent(event1);
    assertEquals(1, day.getEvent().size());
    assertTrue(day.getEvent().contains(event1));

    day.addEvent(event2);
    assertEquals(2, day.getEvent().size());
    assertTrue(day.getEvent().contains(event2));
  }

  @Test
  void testRemoveEvent() {
    day.addEvent(event1);
    day.addEvent(event2);

    day.removeEvent(event1);
    assertEquals(1, day.getEvent().size());
    assertFalse(day.getEvent().contains(event1));
    assertTrue(day.getEvent().contains(event2));
  }
}
