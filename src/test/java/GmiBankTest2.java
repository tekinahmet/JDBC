import jdbc_recap.Utils;
import org.junit.Test;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
public class GmiBankTest2 {
  /*
  User connects to the database
        (Host name: gmibank.com, Database name: gmibank_db, Usename: techprodb_user, Password: Techpro_@126))
      When
        User sends the query to get the user city from "tp_customer" table
      Then
        Assert that user exists where city is 'Izmir'
      And
        User closes the connection
   */
  @Test
  public void test() throws SQLException {
    //  User connects to the database
    Utils.connectToDB("gmibank.com","gmibank_db","techprodb_user", "Techpro_@126");
    Statement statement = Utils.createStatement();
    //  User sends the query to get the user city from "tp_customer" table
    // String query = "SELECT city FROM tp_customer";
    List<Object> list = Utils.getColumnList("city", "tp_customer");
    System.out.println("list = " + list);
    // Assert that user exists where city is 'Izmir'
    String desiredCity = "Izmir";
    boolean nameExists = list.stream().map(String::valueOf).anyMatch(t -> t.equals(desiredCity));
    System.out.println("nameExists = " + nameExists);
    // To see if user exists at this city name
    String query = "SELECT first_name FROM tp_customer WHERE city = 'St Louis'";
    ResultSet rs1 = Utils.statement.executeQuery(query);
    while (rs1.next()){
      System.out.println(rs1.getString(1));
    }
    // Use JUnit's assertTrue for assertion
    assertTrue(desiredCity, nameExists );
    // User closes the connection
    Utils.closeStatementAndConnection();
  }
}
