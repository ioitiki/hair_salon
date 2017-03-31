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


}
