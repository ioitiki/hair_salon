import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class StylistTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void stylist_instantiatesCorrectly_true() {
    Stylist testStylist = new Stylist("Ben Smith", "Best hair stylist in town. Great with colors!");
    assertTrue(testStylist instanceof Stylist);
  }

  @Test
  public void getName_returnsNameOfStylist_BenSmith() {
    Stylist testStylist = new Stylist("Ben Smith", "Best hair stylist in town. Great with colors!");
    assertEquals("Ben Smith", testStylist.getName());
  }

  @Test
  public void getDescription_returnsDescriptionOfStylist_BenSmith() {
    Stylist testStylist = new Stylist("Ben Smith", "Best hair stylist in town. Great with colors!");
    assertEquals("Best hair stylist in town. Great with colors!", testStylist.getDescription());
  }


}
