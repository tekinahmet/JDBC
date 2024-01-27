import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Objects;

public class GmiBankTest {
    /*
        Given
          User connects to the database
          (Host name: gmibank.com, Database name: gmibank_db, Usename: techprodb_user, Password: Techpro_@126))
        When
          User sends the query to get the user ids from "tp_customer" table
        Then
          Assert that number of all null user ids is 1422
        And
          User closes the connection
     */

    @Test
    public void GmiBankTest() {
        //User connects to the database
        JDBCUtils.connectToDatabase("gmibank.com", "gmibank_db", "techprodb_user", "Techpro_@126");
        JDBCUtils.createStatement();

        //User sends the query to get the user ids from "tp_customer" table
        List<Object> country_id = JDBCUtils.getColumnList("country_id", "tp_customer");
        System.out.println("ids = " + country_id);

        //Assert that number of all null user ids is 1422
        long numOfCountry_id = country_id.stream().filter(t-> t==null).count();
        System.out.println("numOfIds = " + numOfCountry_id);


        //User closes the connection








    }
}
