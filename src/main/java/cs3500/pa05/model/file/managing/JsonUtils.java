package cs3500.pa05.model.file.managing;

import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.model.Week;
import java.io.IOException;

/**
 * A utility class for serializing and deserializing Week objects to and from JSON format.
 */
public class JsonUtils {

  /**
   * Default constructor for JsonUtils.
   */
  public JsonUtils() {
  }

  /**
   * Serializes a Week object into a JSON string.
   *
   * @param week the Week object to serialize.
   * @return the JSON string representation of the Week object.
   */
  public String seralizeWeek(Week week) {
    ObjectMapper mapper = new ObjectMapper();
    String s = null;
    try {
      s = mapper.writeValueAsString(week);
    } catch (IOException e) {
      System.err.println(e.getStackTrace());
    }
    return s;
  }

  /**
   * Deserializes a JSON string into a Week object.
   *
   * @param json the JSON string to deserialize.
   * @return the Week object represented by the JSON string.
   */
  public Week deserializeWeek(String json) {
    ObjectMapper mapper = new ObjectMapper();
    Week week = null;
    try {
      week = mapper.readValue(json, Week.class);
    }  catch (IOException e) {
      System.err.println(e);
    }
    return week;
  }
}
