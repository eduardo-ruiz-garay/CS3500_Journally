package cs3500.pa05.model.objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.enums.DaysWeek;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a day, which has a specific day of the week and day of the month,
 * and holds a list of tasks and events scheduled for that day.
 */
public class Day {
  private final DaysWeek day;
  private final List<Task> tasks;
  private final List<Event> event;

  /**
   * Initializes a new instance of Day with the specified day of the week,
   * day of the month, tasks and events, and allows for JSON mapping.
   *
   * @param day        the day of the week.
   * @param tasks      the list of tasks scheduled for this day.
   * @param event      the list of events scheduled for this day.
   */
  @JsonCreator
  public Day(@JsonProperty("day") DaysWeek day,
             @JsonProperty("tasks") List<Task> tasks,
             @JsonProperty("event") List<Event> event) {
    this.day = day;
    this.tasks = tasks;
    this.event = event;
  }

  /**
   * Initializes a new instance of Day with the specified day of the week.
   * Initializes empty lists of tasks and events.
   *
   * @param day the day of the week.
   */
  public Day(DaysWeek day) {
    this.day = day;
    this.event = new ArrayList<>();
    this.tasks = new ArrayList<>();
  }

  /**
   * Adds a new task to the list of tasks scheduled for this day.
   *
   * @param task the task to add.
   */
  public void addTask(Task task) {
    this.tasks.add(task);
  }

  /**
   * Removes a task from the list of tasks scheduled for this day.
   *
   * @param task the task to remove.
   */
  public void removeTask(Task task) {
    this.tasks.remove(task);
  }

  /**
   * Adds a new event to the list of events scheduled for this day.
   *
   * @param event the event to add.
   */
  public void addEvent(Event event) {
    this.event.add(event);
  }

  /**
   * Removes an event from the list of events scheduled for this day.
   *
   * @param event the event to remove.
   */
  public void removeEvent(Event event) {
    this.event.remove(event);
  }

  /**
   * Retrieves the list of tasks scheduled for this day.
   *
   * @return the list of tasks.
   */
  public List<Task> getTasks() {
    return this.tasks;
  }

  /**
   * Retrieves the list of events scheduled for this day.
   *
   * @return the list of events.
   */
  public List<Event> getEvent() {
    return this.event;
  }
}
