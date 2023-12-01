package cs3500.pa05;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa05.model.enums.CategoryColor;
import cs3500.pa05.model.objects.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class CategoryTest {
  private Category category1;
  private Category category2;
  private Category category3;

  @BeforeEach
  void setUp() {
    category1 = new Category("Test", CategoryColor.RED);
    category2 = new Category("Test", CategoryColor.BLUE);
    category3 = new Category("AnotherTest", CategoryColor.RED);
  }

  @Test
  void getName() {
    assertEquals("Test", category1.getName());
    assertEquals("Test", category2.getName());
    assertNotEquals("Dummy", category1.getName());
  }

  @Test
  void getColor() {
    assertEquals(CategoryColor.RED, category1.getColor());
    assertEquals(CategoryColor.BLUE, category2.getColor());
    assertNotEquals(CategoryColor.GREEN, category1.getColor());
  }

  @Test
  void testEquals() {
    assertTrue(category1.equals(category2));
    assertFalse(category1.equals(category3));
    assertFalse(category1.equals(new Object()));
    assertFalse(category1.equals(null));
  }

  @Test
  void testHashCode() {
    assertEquals(category1.hashCode(), category2.hashCode());
    assertNotEquals(category1.hashCode(), category3.hashCode());
  }
}
