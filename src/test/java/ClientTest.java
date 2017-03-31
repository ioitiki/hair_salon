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

  @Test
  public void getAge_returnsCorrectAgeOfClient_76() {
    Client testClient = new Client("Joe Bob Johnson", 1, "Old timer with little hair", 76, "Male");
    assertEquals(76, testClient.getAge());
  }

  @Test
  public void getGender_returnsCorrectGenderOfClient_76() {
    Client testClient = new Client("Joe Bob Johnson", 1, "Old timer with little hair", 76, "Male");
    assertEquals("Male", testClient.getGender());
  }

  @Test
  public void save_savesClientIntoDatabase_true() {
    Client testClient = new Client("Joe Bob Johnson", 1, "Old timer with little hair", 76, "Male");
    testClient.save();
    assertTrue(Client.all().get(0).equals(testClient));
  }

  @Test
  public void all_returnsAllClientsInDB_true() {
    Client testClient1 = new Client("Joe Bob Johnson", 1, "Old timer with little hair", 76, "Male");
    testClient1.save();
    Client testClient2 = new Client("Dallas Win", 2, "Total hipster", 27, "Male");
    testClient2.save();
    assertTrue(Client.all().get(0).equals(testClient1));
    assertTrue(Client.all().get(1).equals(testClient2));
  }

  @Test
  public void equals_ifClientNameStylistIdDescriptionAgeAndGenderAreTheSame_true() {
    Client testClient1 = new Client("Joe Bob Johnson", 1, "Old timer with little hair", 76, "Male");
    Client testClient2 = new Client("Joe Bob Johnson", 1, "Old timer with little hair", 76, "Male");
    assertTrue(testClient1.equals(testClient2));
  }

  @Test
  public void save_assignsIdToClient_true() {
    Client testClient = new Client("Joe Bob Johnson", 1, "Old timer with little hair", 76, "Male");
    testClient.save();
    assertEquals(testClient.getId(), Client.all().get(0).getId());
  }

  @Test
  public void getId_returnsAnId_true() {
    Client testClient = new Client("Joe Bob Johnson", 1, "Old timer with little hair", 76, "Male");
    testClient.save();
    assertTrue(testClient.getId() > 0);
  }



}
