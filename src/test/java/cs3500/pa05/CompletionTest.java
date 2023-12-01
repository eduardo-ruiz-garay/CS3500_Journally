package cs3500.pa05;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.enums.Completion;
import org.junit.jupiter.api.Test;


class CompletionTest {

  @Test
  void testToString() {
    assertEquals("Incomplete", Completion.INCOMPLETE.toString());
    assertEquals("Complete", Completion.COMPLETE.toString());
  }
}
