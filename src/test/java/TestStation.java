import POJOs.Station;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.json.UTF8JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TestStation {

  @Test
  public void shouldSerializeProperly() {
    Station testSubject = new Station("hest", "Abbey road", 51.15, -0.156, "description is here");
    ObjectMapper objectMapper = new ObjectMapper();

    String expectedResult = "{\"name\":\"Abbey road\"" +
        ",\"loc\":{\"type\":\"Point\",\"coordinates\":[-0.156,51.15]},\"description\":\"description is here\",\"id\":\"hest\",\"latitude\":51.15,\"longitude\":-0.156}";

    String result = null;
    try {
      result = objectMapper.writeValueAsString(testSubject);
    } catch (IOException e) {
      e.printStackTrace();
    }

    System.out.println(result);
    assertEquals(expectedResult, result);
  }
}
