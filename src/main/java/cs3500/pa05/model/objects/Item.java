package cs3500.pa05.model.objects;

import cs3500.pa05.model.enums.DaysWeek;

/**
 * The Item interface represents entities that have a name,
 * associated day, description, and category.
 */
public interface Item {

  /**
   * Gets the name of this item.
   *
   * @return The name of this item as a String.
   */
  String getName();

  /**
   * Gets the day of the week this item is associated with.
   *
   * @return The day of the week this item is associated with as a DaysWeek enum.
   */
  DaysWeek getDay();

  /**
   * Gets the description of this item.
   *
   * @return The description of this item as a String.
   */
  String getDescription();

  /**
   * Gets the category of this item.
   *
   * @return The category of this item as a Category object.
   */
  Category getCategory();

  /**
   * Sets the category of this item.
   *
   * @param category The category to be set for this item.
   */
  void setCategory(Category category);
}

