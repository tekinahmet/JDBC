import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountriesTest {
    //gherkin language
    /*
        Given --> pre-condition/pre-requisite
          User connects to the database
        When --> task/action
          User sends the query to get the region ids from "countries" table
        Then --> get the result and verify it
          Verify that the number of region ids greater than 10 is 49.
        And  --> to avoid repetition of Given, When or Then, we use
        And
          User closes the connection
     */
    public static void main(String[] args) throws SQLException {
        JDBCUtils.connectToDatabase("localHost", "postgres", "postgres", "nur851611nil");
        JDBCUtils.createStatement();

        //  User sends the query to get the region ids from "countries" table
        String query = "SELECT id FROM countries";
        ResultSet resultSet = JDBCUtils.statement.executeQuery(query);
        List<Integer> idList = new ArrayList<>();
        while(resultSet.next()){
            idList.add((resultSet.getInt("id")));
        }
        System.out.println("idList = " + idList);

        // Verify that the number of region ids greater than 10 is 49
        int counter=0;
        for (int w:idList) {
            if(w>10){
                counter++;
            }
        }System.out.println("counter = " + counter);

    }

}
