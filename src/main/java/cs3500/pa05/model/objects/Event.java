package cs3500.pa05.model.objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.enums.DaysWeek;

/**
 * Represents an Event, which is a type of item that can be scheduled.
 */
public class Event implements Item {
  private final String name;
  private String description;
  private final DaysWeek day;
  private final String startTime;
  private final String duration;
  private Category category;

  /**
   * Constructs an Event object with given parameters and allows for JSON mapping.
   *
   * @param name        the name of the event
   * @param day         the day of the week when the event should take place
   * @param description the description of the event
   * @param startTime   the start time of the event
   * @param duration    the duration of the event
   * @param category    the category of the event
   */
  @JsonCreator
  public Event(@JsonProperty("name") String name,
               @JsonProperty("day") DaysWeek day,
               @JsonProperty("description") String description,
               @JsonProperty("startTime") String startTime,
               @JsonProperty("duration") String duration,
               @JsonProperty("category") Category category) {
    this.name = name;
    this.day = day;
    this.description = description;
    this.startTime = startTime;
    this.duration = duration;
    this.category = category;
  }

  /**
   * @return the name of the event
   */
  public String getName() {
    return this.name;
  }

  /**
   * @return the description of the event
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * @return the day of the event
   */
  public DaysWeek getDay() {
    return this.day;
  }

  /**
   * @return the start time of the event
   */
  public String getStartTime() {
    return this.startTime;
  }

  /**
   * @return the duration of the event
   */
  public String getDuration() {
    return this.duration;
  }

  /**
   * Sets the description of the event.
   *
   * @param description The description to set.
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Sets the category of the event.
   *
   * @param category The category to set.
   */
  public void setCategory(Category category) {
    this.category = category;
  }

  /**
   * @return the category of the event
   */
  public Category getCategory() {
    return this.category;
  }
}
