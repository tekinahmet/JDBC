import javax.xml.stream.events.DTD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExecuteQuery01 {
    public static void main(String[] args) throws SQLException {

        //Step 1: Create connection
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "nur851611nil");
        // Step 2: Create Statement
        Statement statement = connection.createStatement();
        //TASK-1. GET "country_name" from countries table with ID between 5 and 10
        String query = "SELECT country_name FROM countries WHERE id BETWEEN 5 AND 10";
        boolean sql1 = statement.execute(query);
        System.out.println("sql1: "+sql1); // true because execute() with DQL queries returns TRUE if the data exists

        // To see the data, we have another method executeQuery()
        ResultSet resultSet = statement.executeQuery(query);
//         System.out.println(resultSet);
//        System.out.println(resultSet.next());
//        resultSet.next();
//        resultSet.next();
//        System.out.println(resultSet.getString("country_name"));


        List<String> countriesList = new ArrayList<>();
        while (resultSet.next()){
            // System.out.println(resultSet.getString("country_name"));
            countriesList.add(resultSet.getString("country_name"));
        }
        System.out.println("countriesList = " + countriesList);

        System.out.println(" ========================================= ");
        //TASK - 2: Get "phone_code" and "country_name" from countries table, whose phone code is greater than 200
        String query1 = "SELECT phone_code, country_name FROM countries WHERE phone_code > 500";
        ResultSet resultSet1 = statement.executeQuery(query1);

        //  System.out.println("resultSet1.next() = " + resultSet1.next());  // true

        while (resultSet1.next()){
            System.out.println(resultSet1.getObject("phone_code")+ "--> "+ resultSet1.getObject("country_name"));
        }

        System.out.println(" ========================================= ");

        //TASK-3. Get all information about the companies whose number_of_employee is lowest
        String query2 = "SELECT company_id, company, number_of_employees FROM companies WHERE number_of_employees = (SELECT MIN(number_of_employees) FROM companies)";
        ResultSet resultSet2 =  statement.executeQuery(query2);

        while (resultSet2.next()){
            System.out.println(resultSet2.getInt("company_id")+"-- "+ resultSet2.getString("company") + "-- "+ resultSet2.getInt("number_of_employees"));
        }
        System.out.println(" ========================================= ");

        //Task 4: Get the company information, which has the most employees.
        String query3 = "SELECT company, number_of_employees from companies WHERE number_of_employees=(SELECT MAX(number_of_employees) FROM companies)";
        ResultSet resultSet3 = statement.executeQuery(query3);
        while (resultSet3.next()){
            System.out.println(resultSet3.getString("company") + "-->" + resultSet3.getInt("number_of_employees"));
        }




        // Step 5: Close the statement and connection
        if (connection != null){
            statement.close();
            connection.close();
            System.out.println("Connection closed successfully");
        }else {
            System.out.println("Connection is not closed");
        }
    }
}