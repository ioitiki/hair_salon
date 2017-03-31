import org.sql2o.*;

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

  // public String getName() {
  //   return "";
  // }

}
