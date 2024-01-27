import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

public class CountriesTestJUnit {
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

    @Test
    public void countriesTest(){
        // User connects to the database
        JDBCUtils.connectToDatabase("localHost", "postgres","postgres", "nur851611nil");
        JDBCUtils.createStatement();

        //User sends the query to get the region ids from "countries" table
        List<Object> codeList = JDBCUtils.getColumnList("phone_code", "countries");
        System.out.println("codeList = " + codeList);

        //Verify/Assert that the number of region ids greater than 300 is 13.-->TESTING STEP
        int number_of_codes = codeList.stream().filter(t -> (int) t > 300).collect(Collectors.toList()).size();
        System.out.println("number_of_codes = " + number_of_codes);

        //to verify
        Assert.assertEquals(13,number_of_codes);
        Assert.assertTrue(2==2);
        //Assert.assertFalse(2==1);

        //User closes the connection
        JDBCUtils.closeStatementAndConnection();


    }
}
