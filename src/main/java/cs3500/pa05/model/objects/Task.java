package cs3500.pa05.model.objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.enums.Completion;
import cs3500.pa05.model.enums.DaysWeek;

/**
 * Represents a Task, which is a type of item that can be scheduled.
 */
public class Task implements Item {
  private final String name;
  private final String description;
  private final DaysWeek day;
  private final Completion completion;
  private Category category;

  /**
   * Constructs a Task object with given parameters and allows for JSON mapping of the object.
   *
   * @param name        the name of the task
   * @param day         the day of the week when the task should be performed
   * @param completion  the state of the task completion
   * @param description the description of the task
   * @param category    the category of the task
   */
  @JsonCreator
  public Task(@JsonProperty("name") String name,
              @JsonProperty("day") DaysWeek day,
              @JsonProperty("completion") Completion completion,
              @JsonProperty("description") String description,
              @JsonProperty("category") Category category) {
    this.name = name;
    this.day = day;
    this.completion = completion;
    this.description = description;
    this.category = category;
  }

  /**
   * @return the name of the task
   */
  public String getName() {
    return this.name;
  }

  /**
   * @return the day of the task
   */
  public DaysWeek getDay() {
    return this.day;
  }

  /**
   * @return the completion state of the task
   */
  public Completion getCompletion() {
    return this.completion;
  }

  /**
   * @return the description of the task
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * @return the category of the task
   */
  public Category getCategory() {
    return this.category;
  }

  /**
   * Sets the category of the task.
   *
   * @param category The category to set.
   */
  public void setCategory(Category category) {
    this.category = category;
  }
}
