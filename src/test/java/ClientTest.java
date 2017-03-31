import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class ClientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void client_instantiatesCorrectly_true() {
    Client testClient = new Client("Joe Bob Johnson", 1, "Old timer with little hair", 76, "Male");
    assertTrue(testClient instanceof Client);
  }

  @Test
  public void getName_returnsCorrectNameOfClient_JoeBobJohnson() {
    Client testClient = new Client("Joe Bob Johnson", 1, "Old timer with little hair", 76, "Male");
    assertEquals("Joe Bob Johnson", testClient.getName());
  }

  @Test
  public void getStylistId_returnsCorrectStylistIdOfClient_1() {
    Client testClient = new Client("Joe Bob Johnson", 1, "Old timer with little hair", 76, "Male");
    assertEquals(1, testClient.getStylistId());
  }

  @Test
  public void getDescription_returnsCorrectDescriptionOfClient_clientDescription() {
    Client testClient = new Client("Joe Bob Johnson", 1, "Old timer with little hair", 76, "Male");
    assertEquals("Old timer with little hair", testClient.getDescription());
  }

}
