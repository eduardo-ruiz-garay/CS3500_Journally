package cs3500.pa05.model.enums;

/**
 * Represents the days of the week. Each constant corresponds to a specific day.
 */
public enum DaysWeek {
  MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

  /**
   * Converts the name of the day to a string format,
   * where the first letter is uppercase and the rest are lowercase.
   *
   * @return the formatted name of the day.
   */
  @Override
  public String toString() {
    String name = name();
    return name.charAt(0) + name.substring(1).toLowerCase();
  }

}
