import org.sql2o.*;
import java.util.List;

public class Client {
  private String clientName;
  private int id;
  private int stylistId;
  private String clientDescription;
  private int clientAge;
  private String clientGender;




  public Client(String clientName, int stylistId, String clientDescription, int clientAge, String clientGender) {
    this.clientName = clientName;
    this.stylistId = stylistId;
    this.clientDescription = clientDescription;
    this.clientAge = clientAge;
    this.clientGender = clientGender;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return clientName;
  }

  public int getStylistId() {
    return stylistId;
  }

  public String getDescription() {
    return clientDescription;
  }

  public int getAge() {
    return clientAge;
  }

  public String getGender() {
    return clientGender;
  }

  public void save() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients (clientName, stylistId, clientDescription, clientAge, clientGender) VALUES (:clientName, :stylistId, :clientDescription, :clientAge, :clientGender);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("clientName", this.clientName)
        .addParameter("stylistId", this.stylistId)
        .addParameter("clientDescription", this.clientDescription)
        .addParameter("clientAge", this.clientAge)
        .addParameter("clientGender", this.clientGender)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Client> all() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients;";
      return con.createQuery(sql)
        .executeAndFetch(Client.class);
    }
  }

  @Override
  public boolean equals(Object otherClient) {
    if (!(otherClient instanceof Client)) {
      return false;
    } else {
      Client newClient = (Client) otherClient;
      return this.getName().equals(newClient.getName()) &&
             this.getStylistId() == newClient.getStylistId() &&
             this.getDescription().equals(newClient.getDescription()) &&
             this.getAge() == newClient.getAge() &&
             this.getGender().equals(newClient.getGender()) &&
             this.getId() == newClient.getId();
    }
  }

  public static Client find(int id) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients WHERE id = :id;";
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Client.class);
    }
  }

}
