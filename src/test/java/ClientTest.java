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

}
