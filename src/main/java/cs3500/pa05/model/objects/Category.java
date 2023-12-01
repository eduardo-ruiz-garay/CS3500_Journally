package cs3500.pa05.model.objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.enums.CategoryColor;
import java.util.Objects;

/**
 * Represents a Category, which has a specific name and a color.
 */
public class Category {
  private final String name;
  private CategoryColor color;

  /**
   * Initializes a new instance of Category with the specified name and color.
   *
   * @param name  the name of the category.
   * @param color the color associated with the category.
   */
  @JsonCreator
  public Category(@JsonProperty("name") String name,
                  @JsonProperty("color") CategoryColor color) {
    this.name = name;
    this.color = color;

  }

  /**
   * Retrieves the name of this category.
   *
   * @return the name of the category.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Checks if the given object is equal to this category.
   * categories are considered equal if they have the same name.
   *
   * @param other the object to compare with this category.
   * @return true if the given object is a category with the same name, false otherwise.
   */
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Category otherCategory)) {
      return false;
    }
    return name.equals(otherCategory.name);
  }

  /**
   * Generates a hash code for this category.
   * The hash code is based on the name of the category.
   *
   * @return the hash code of this category.
   */
  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  /**
   * Retrieves the color of this category.
   *
   * @return the color of the category.
   */
  public CategoryColor getColor() {
    return this.color;
  }
}
