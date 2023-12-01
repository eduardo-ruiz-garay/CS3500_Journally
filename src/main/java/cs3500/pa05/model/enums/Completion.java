package cs3500.pa05.model.enums;

/**
 * Represents the completion status of a task.
 * INCOMPLETE for tasks that are not yet done, and COMPLETE for tasks that are finished.
 */

public enum Completion {
  INCOMPLETE, COMPLETE;

  /**
   * Converts the status to a string format,
   * where the first letter is uppercase and the rest are lowercase.
   *
   * @return the formatted string.
   */
  @Override
  public String toString() {
    String name = name();
    return name.charAt(0) + name.substring(1).toLowerCase();
  }
}
