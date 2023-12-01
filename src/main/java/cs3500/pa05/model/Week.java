package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.enums.CategoryColor;
import cs3500.pa05.model.enums.DaysWeek;
import cs3500.pa05.model.objects.Category;
import cs3500.pa05.model.objects.Day;
import cs3500.pa05.model.objects.Event;
import cs3500.pa05.model.objects.Task;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a week with associated tasks, categories, and events, stored in day-by-day fashion.
 */
@JsonIgnoreProperties({"allEvents", "allTasks"})
public class Week {
  private final Day[] days;
  private final ArrayList<Task> queue;
  private final ArrayList<Category> categories;
  private ArrayList<Event> events;
  private String title;
  private int maxTasks;
  private int maxEvents;


  /**
   * Constructs a Week object.
   *
   * @param title      the title of the week
   * @param days       array of days in the week
   * @param queue      list of tasks
   * @param categories list of categories
   * @param events     list of events
   * @param maxTasks   maximum number of tasks in a week
   * @param maxEvents  maximum number of events in a week
   */
  @JsonCreator
  public Week(@JsonProperty("title") String title,
              @JsonProperty("days") Day[] days,
              @JsonProperty("queue") ArrayList<Task> queue,
              @JsonProperty("categories") ArrayList<Category> categories,
              @JsonProperty("events") ArrayList<Event> events,
              @JsonProperty("maxTasks") int maxTasks,
              @JsonProperty("maxEvents") int maxEvents) {
    this.days = days;
    this.queue = queue;
    this.categories = categories;
    this.events = events;
    this.title = title;
    this.maxTasks = maxTasks;
    this.maxEvents = maxEvents;
  }

  /**
   * Constructs a default Week object with an empty queue and category list.
   */
  public Week() {
    this.days = new Day[] {
        new Day(DaysWeek.MONDAY),
        new Day(DaysWeek.TUESDAY),
        new Day(DaysWeek.WEDNESDAY),
        new Day(DaysWeek.THURSDAY),
        new Day(DaysWeek.FRIDAY),
        new Day(DaysWeek.SATURDAY),
        new Day(DaysWeek.SUNDAY)};
    this.queue = new ArrayList<>();
    this.categories = new ArrayList<>();
  }

  /*
   * Gets the days in the week.
   *
   * @return an array of days
   */
  public Day[] getDays() {
    return this.days;
  }

  /**
   * Gets the tasks in a given day.
   *
   * @param day the day
   * @return a list of tasks
   */
  public List<Task> getTasksInDay(DaysWeek day) {
    int dayNum = day.ordinal();
    return days[dayNum].getTasks();
  }

  /**
   * Gets the events in a given day.
   *
   * @param day the day
   * @return a list of events
   */
  public List<Event> getEventInDay(DaysWeek day) {
    int dayNum = day.ordinal();
    return days[dayNum].getEvent();
  }

  /**
   * Gets all tasks in the week.
   *
   * @return a list of tasks
   */
  public List<Task> getAllTasks() {
    List<Task> allTasks = new ArrayList<>();
    for (Day day : days) {
      allTasks.addAll(day.getTasks());
    }
    return allTasks;
  }

  /**
   * Gets all categories in the week.
   *
   * @return a list of categories
   */
  public List<Category> getCategories() {
    return this.categories;
  }

  /**
   * Gets all events in the week.
   *
   * @return a list of events
   */
  public List<Event> getAllEvents() {
    List<Event> allEvents = new ArrayList<>();
    for (Day day : days) {
      allEvents.addAll(day.getEvent());
    }
    return allEvents;
  }

  /**
   * Adds a task to a specific day.
   *
   * @param task   the task to add
   * @param dayInt the day to add the task to
   */
  public void addTask(Task task, int dayInt) {
    this.days[dayInt].addTask(task);
  }

  /**
   * Removes a task from a specific day.
   *
   * @param task   the task to remove
   * @param dayInt the day to remove the task from
   */
  public void removeTask(Task task, int dayInt) {
    this.days[dayInt].removeTask(task);
  }

  /**
   * Adds an event to a specific day.
   *
   * @param event  the event to add
   * @param dayInt the day to add the event to
   */
  public void addEvent(Event event, int dayInt) {
    this.days[dayInt].addEvent(event);
  }

  /**
   * Removes an event from a specific day.
   *
   * @param event  the event to remove
   * @param dayInt the day to remove the event from
   */
  public void removeEvent(Event event, int dayInt) {
    this.days[dayInt].removeEvent(event);
  }

  /**
   * Adds a category.
   *
   * @param categoryName the name of the category to add
   */
  public void addCategory(String categoryName) {
    this.categories.add(new Category(categoryName,
        CategoryColor.values()[this.categories.size()]));
  }

  /**
   * Gets the title of the week.
   *
   * @return the title of the week
   */
  public String getTitle() {
    return this.title;
  }

  /**
   * Sets the title of the week.
   *
   * @param text the title to set
   */
  public void setTitle(String text) {
    this.title = text;
  }

  /**
   * Gets the maximum number of tasks.
   *
   * @return the maximum number of tasks
   */
  public int getMaxTasks() {
    return this.maxTasks;
  }

  /**
   * Sets the maximum number of tasks.
   *
   * @param newValue the maximum number of tasks
   */
  public void setMaxTasks(Integer newValue) {
    this.maxTasks = newValue;
  }

  /**
   * Gets the maximum number of events.
   *
   * @return the maximum number of events
   */
  public int getMaxEvents() {
    return this.maxEvents;
  }

  /**
   * Sets the maximum number of events.
   *
   * @param newValue the maximum number of events
   */
  public void setMaxEvents(Integer newValue) {
    this.maxEvents = newValue;
  }

}


