package cs3500.pa05;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.enums.DaysWeek;
import org.junit.jupiter.api.Test;



/**
 * Test Class for the DaysWeek
 */
class DaysWeekTest {

  @Test
  void testToString() {
    assertEquals("Monday", DaysWeek.MONDAY.toString());
    assertEquals("Tuesday", DaysWeek.TUESDAY.toString());
    assertEquals("Wednesday", DaysWeek.WEDNESDAY.toString());
    assertEquals("Thursday", DaysWeek.THURSDAY.toString());
    assertEquals("Friday", DaysWeek.FRIDAY.toString());
    assertEquals("Saturday", DaysWeek.SATURDAY.toString());
    assertEquals("Sunday", DaysWeek.SUNDAY.toString());
  }
}

