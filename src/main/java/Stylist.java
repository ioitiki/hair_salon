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

  public static Stylist find(int id) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stylists WHERE id = :id;";
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Stylist.class);
    }
  }

  public List<Client> getClients() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients WHERE stylistId = :stylistId;";
      return con.createQuery(sql)
        .addParameter("stylistId", this.id)
        .executeAndFetch(Client.class);
    }
  }

  public void updateStylist(String stylistName, String stylistDescription) {
    this.stylistName = stylistName;
    this.stylistDescription = stylistDescription;
    try (Connection con = DB.sql2o.open()) {
      String sql = "UPDATE stylists SET (stylistName, stylistDescription) = (:stylistName, :stylistDescription) WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("stylistName", stylistName)
        .addParameter("stylistDescription", stylistDescription)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }

  public void deleteStylist() {
    List<Client> clients = this.getClients();
    for (Client client : clients) {
      client.deleteClient();
    }
    try (Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM stylists WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }

  public static List<Stylist> searchStylists(String input) {
    String newInput = "%" + input + "%";
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stylists WHERE lower(stylistName) LIKE lower(:input);";
      return con.createQuery(sql)
        .addParameter("input", newInput)
        .executeAndFetch(Stylist.class);
    }
  }




}
