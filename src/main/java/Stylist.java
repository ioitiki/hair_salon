import org.sql2o.*;
import java.util.List;

public class Stylist {
  private String stylistName;
  private int id;
  private String stylistDescription;


  public Stylist(String stylistName, String stylistDescription) {
    this.stylistName = stylistName;
    this.stylistDescription = stylistDescription;
  }

  public String getName() {
    return stylistName;
  }

  public String getDescription() {
    return stylistDescription;
  }

  public int getId() {
    return id;
  }

  public void save() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stylists (stylistName, stylistDescription) VALUES (:stylistName, :stylistDescription);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("stylistName", this.stylistName)
        .addParameter("stylistDescription", this.stylistDescription)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Stylist> all() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stylists;";
      return con.createQuery(sql)
        .executeAndFetch(Stylist.class);
    }
  }

  @Override
  public boolean equals(Object otherStylist) {
    if (!(otherStylist instanceof Stylist)) {
      return false;
    } else {
      Stylist newStylist = (Stylist) otherStylist;
      return this.getName().equals(newStylist.getName()) &&
             this.getDescription().equals(newStylist.getDescription()) &&
             this.getId() == newStylist.getId();
    }
  }





}
