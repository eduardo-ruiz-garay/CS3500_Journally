package cs3500.pa05;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa05.model.Week;
import cs3500.pa05.model.enums.Completion;
import cs3500.pa05.model.enums.DaysWeek;
import cs3500.pa05.model.objects.Event;
import cs3500.pa05.model.objects.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WeekTest {

  private Week week;

  @BeforeEach
  void setUp() {
    week = new Week();
  }

  @Test
  void testGetDays() {
    assertEquals(7, week.getDays().length);
  }

  @Test
  void testGetTasksInDay() {
    for (int i = 0; i < week.getDays().length; i++) {
      assertTrue(week.getTasksInDay(DaysWeek.values()[i]).isEmpty());
    }
    for (int i = 0; i < week.getDays().length; i++) {
      this.week.addTask(new Task("task", DaysWeek.MONDAY, Completion.COMPLETE,
          "description", null), i);
    }
    for (int i = 0; i < week.getDays().length; i++) {
      assertFalse(week.getTasksInDay(DaysWeek.values()[i]).isEmpty());
    }
  }

  @Test
  void testGetEventInDay() {
    for (int i = 0; i < week.getDays().length; i++) {
      assertTrue(week.getEventInDay(DaysWeek.values()[i]).isEmpty());
    }
    for (int i = 0; i < week.getDays().length; i++) {
      this.week.addEvent(new Event("name", DaysWeek.MONDAY, "description",
          "00.00", "00.00", null), i);
    }
    for (int i = 0; i < week.getDays().length; i++) {
      assertFalse(week.getEventInDay(DaysWeek.values()[i]).isEmpty());
    }
  }

  @Test
  void testGetAllTasks() {
    assertTrue(week.getAllTasks().isEmpty());
    for (int i = 0; i < week.getDays().length; i++) {
      this.week.addTask(new Task("task", DaysWeek.MONDAY, Completion.COMPLETE,
          "description", null), i);
    }
    assertEquals(7, week.getAllTasks().size());
    for (int i = 0; i < week.getDays().length; i++) {
      this.week.addTask(new Task("task", DaysWeek.MONDAY, Completion.COMPLETE,
          "description", null), i);
    }
    assertEquals(14, week.getAllTasks().size());
  }

  @Test
  void testGetCategories() {
    assertTrue(week.getCategories().isEmpty());
    for (int i = 0; i < week.getDays().length; i++) {
      this.week.addCategory("name");
    }
    assertEquals(7, week.getCategories().size());
  }

  @Test
  void testGetAllEvents() {
    assertTrue(week.getAllEvents().isEmpty());
    for (int i = 0; i < week.getDays().length; i++) {
      this.week.addEvent(new Event("name", DaysWeek.MONDAY, "description",
          "00.00", "00.00", null), i);
    }
    assertEquals(7, week.getAllEvents().size());
    for (int i = 0; i < week.getDays().length; i++) {
      this.week.addEvent(new Event("name", DaysWeek.MONDAY, "description",
          "00.00", "00.00", null), i);
    }
    assertEquals(14, week.getAllEvents().size());
  }

  @Test
  void testAddAndRemoveTask() {
    Task task = new Task("task1", DaysWeek.MONDAY, Completion.COMPLETE, "description", null);
    week.addTask(task, 0);
    assertEquals(task, week.getTasksInDay(DaysWeek.MONDAY).get(0));

    week.removeTask(task, 0);
    assertTrue(week.getTasksInDay(DaysWeek.MONDAY).isEmpty());
  }

  @Test
  void testAddAndRemoveEvent() {
    Event event = new Event("event1", DaysWeek.MONDAY, "description", "12:00", "1h", null);
    week.addEvent(event, 0);
    assertFalse(week.getEventInDay(DaysWeek.MONDAY).isEmpty());

    week.removeEvent(event, 0);
    assertTrue(week.getEventInDay(DaysWeek.MONDAY).isEmpty());
  }

  @Test
  void testAddCategory() {
    week.addCategory("category1");
    assertFalse(week.getCategories().isEmpty());
  }

  @Test
  void testGetAndSetTitle() {
    week.setTitle("Test Week");
    assertEquals("Test Week", week.getTitle());
  }

  @Test
  void testGetAndSetMaxTasks() {
    week.setMaxTasks(10);
    assertEquals(10, week.getMaxTasks());
  }

  @Test
  void testGetAndSetMaxEvents() {
    week.setMaxEvents(10);
    assertEquals(10, week.getMaxEvents());
  }
}

