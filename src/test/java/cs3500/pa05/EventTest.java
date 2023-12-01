package cs3500.pa05;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.enums.CategoryColor;
import cs3500.pa05.model.enums.DaysWeek;
import cs3500.pa05.model.objects.Category;
import cs3500.pa05.model.objects.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EventTest {
  private String name;
  private DaysWeek day;
  private String description;
  private String startTime;
  private String duration;
  private Category category;

  @BeforeEach
  void setUp() {
    name = "Event 1";
    day = DaysWeek.MONDAY;
    description = "Description 1";
    startTime = "10:00 AM";
    duration = "1 hour";
    category = new Category("Category 1", CategoryColor.BLACK);
  }

  @Test
  void testConstructorAndGetters() {
    Event event = new Event(name, day, description, startTime, duration, category);

    assertEquals(name, event.getName());
    assertEquals(day, event.getDay());
    assertEquals(description, event.getDescription());
    assertEquals(startTime, event.getStartTime());
    assertEquals(duration, event.getDuration());
    assertEquals(category, event.getCategory());
  }

  @Test
  void testSetDescription() {
    String description1 = "Description 1";
    String description2 = "Description 2";

    Event event = new Event(name, day, description1, startTime, duration, category);
    assertEquals(description1, event.getDescription());

    event.setDescription(description2);
    assertEquals(description2, event.getDescription());
  }

  @Test
  void testSetCategory() {
    Category category1 = new Category("Category 1", CategoryColor.GREEN);
    Category category2 = new Category("Category 2", CategoryColor.BLACK);

    Event event = new Event(name, day, description, startTime, duration, category1);
    assertEquals(category1, event.getCategory());

    event.setCategory(category2);
    assertEquals(category2, event.getCategory());
  }
}

