import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

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

  @Test
  public void save_savesStylistIntoDatabase_true() {
    Stylist testStylist = new Stylist("Ben Smith", "Best hair stylist in town. Great with colors!");
    testStylist.save();
    assertTrue(Stylist.all().get(0).equals(testStylist));
  }

  @Test
  public void all_returnsAllStylistsInDB_true() {
    Stylist testStylist1 = new Stylist("Ben Smith", "Best hair stylist in town. Great with colors!");
    testStylist1.save();
    Stylist testStylist2 = new Stylist("Jo Brown", "Great with guys hair cuts.");
    testStylist2.save();
    assertTrue(Stylist.all().get(0).equals(testStylist1));
    assertTrue(Stylist.all().get(1).equals(testStylist2));
  }

  @Test
  public void equals_ifStylistNameAndDescriptionIsTheSame_true() {
    Stylist testStylist1 = new Stylist("Ben Smith", "Best hair stylist in town. Great with colors!");
    Stylist testStylist2 = new Stylist("Ben Smith", "Best hair stylist in town. Great with colors!");
    assertTrue(testStylist1.equals(testStylist2));
  }

  @Test
  public void save_assignsIdToStylist_true() {
    Stylist testStylist = new Stylist("Ben Smith", "Best hair stylist in town. Great with colors!");
    testStylist.save();
    assertEquals(testStylist.getId(), Stylist.all().get(0).getId());
  }

  @Test
  public void getId_returnsAnId_true() {
    Stylist testStylist = new Stylist("Ben Smith", "Best hair stylist in town. Great with colors!");
    testStylist.save();
    assertTrue(testStylist.getId() > 0);
  }

  @Test
  public void find_returnsStylistWithSameId_Stylist() {
    Stylist testStylist = new Stylist("Ben Smith", "Best hair stylist in town. Great with colors!");
    testStylist.save();
    assertEquals(testStylist, Stylist.find(testStylist.getId()));
  }

  @Test
  public void getClients_returnsAllClientsWithSameStylistId_List() {
    Stylist testStylist = new Stylist("Ben Smith", "Best hair stylist in town. Great with colors!");
    testStylist.save();
    Client testClient1 = new Client("Joe Bob Johnson", testStylist.getId(), "Old timer with little hair", 76, "Male");
    testClient1.save();
    Client testClient2 = new Client("Dallas Win", testStylist.getId(), "Total hipster", 27, "Male");
    testClient2.save();
    Client[] clients = new Client[] {testClient1, testClient2};
    assertTrue(testStylist.getClients().containsAll(Arrays.asList(clients)));
  }

  @Test
  public void updateStylist_updatesStylistProperties_true() {
    Stylist testStylist = new Stylist("Ben Smith", "Best hair stylist in town. Great with colors!");
    testStylist.save();
    testStylist.updateStylist("Ben H. Smith", "Second best now!");
    assertEquals("Ben H. Smith", Stylist.find(testStylist.getId()).getName());
    assertEquals("Second best now!", Stylist.find(testStylist.getId()).getDescription());
    assertEquals("Ben H. Smith", testStylist.getName());
    assertEquals("Second best now!", testStylist.getDescription());
  }

  @Test
  public void deleteStylist_deletesStylistFromDB_true() {
    Stylist testStylist = new Stylist("Ben Smith", "Best hair stylist in town. Great with colors!");
    testStylist.save();
    Client testClient1 = new Client("Joe Bob Johnson", testStylist.getId(), "Old timer with little hair", 76, "Male");
    testClient1.save();
    Client testClient2 = new Client("Dallas Win", testStylist.getId(), "Total hipster", 27, "Male");
    testClient2.save();
    int testStylistId = testStylist.getId();
    int testClient1Id = testClient1.getId();
    int testClient2Id = testClient2.getId();
    testStylist.deleteStylist();
    assertEquals(null, Stylist.find(testStylistId));
    assertEquals(null, Client.find(testClient1Id));
    assertEquals(null, Client.find(testClient2Id));
  }

  @Test
  public void searchStylist_returnsAllStylistsWithMatchingString_List() {
    Stylist testStylist1 = new Stylist("Ben Smith", "Best hair stylist in town. Great with colors!");
    testStylist1.save();
    Stylist testStylist2 = new Stylist("Jo Brown", "Great with guys hair cuts.");
    testStylist2.save();
    Stylist testStylist3 = new Stylist("Ben Long", "just starting out.");
    testStylist3.save();
    Stylist testStylist4 = new Stylist("Joanna Place", "Top notch. Always in a good mood.");
    testStylist4.save();
    Stylist[] stylists = new Stylist[] {testStylist1, testStylist3};
    assertTrue(Stylist.searchStylist("ben").containsAll(Arrays.asList(stylists)));
    assertFalse(Stylist.searchStylist("ben").contains(testStylist2));
    assertFalse(Stylist.searchStylist("ben").contains(testStylist4));
  }

}
