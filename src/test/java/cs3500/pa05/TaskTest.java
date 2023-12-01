package cs3500.pa05;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.enums.CategoryColor;
import cs3500.pa05.model.enums.Completion;
import cs3500.pa05.model.enums.DaysWeek;
import cs3500.pa05.model.objects.Category;
import cs3500.pa05.model.objects.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskTest {
  private String name;
  private DaysWeek day;
  private Completion completion;
  private String description;
  private Category category;

  @BeforeEach
  void setUp() {
    name = "Task 1";
    day = DaysWeek.MONDAY;
    completion = Completion.INCOMPLETE;
    description = "Description 1";
    category = new Category("Category 1", CategoryColor.GREEN);
  }

  @Test
  void testConstructorAndGetters() {
    Task task = new Task(name, day, completion, description, category);

    assertEquals(name, task.getName());
    assertEquals(day, task.getDay());
    assertEquals(completion, task.getCompletion());
    assertEquals(description, task.getDescription());
    assertEquals(category, task.getCategory());
  }

  @Test
  void testSetCategory() {
    Category category1 = new Category("Category 1", CategoryColor.GREEN);
    Category category2 = new Category("Category 2", CategoryColor.BLACK);

    Task task = new Task(name, day, completion, description, category1);
    assertEquals(category1, task.getCategory());

    task.setCategory(category2);
    assertEquals(category2, task.getCategory());
  }
}

