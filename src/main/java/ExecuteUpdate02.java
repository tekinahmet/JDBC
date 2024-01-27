import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ExecuteUpdate02 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // executeUpdate() is being used for operations: INSERT, UPDATE, DELETE.

        // Step 1: Specified my Driver for DB
        Class.forName("org.postgresql.Driver");

        // Step 2: Get the Connection
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "nur851611nil");

        // Step 3: Create the Statement
        Statement statement = connection.createStatement();

        // Task 1: Find the companies that has less number_of_employees than the average number_of_employees in the table, and set it to 18000
        String query1 = "UPDATE companies SET number_of_employees = 18000 WHERE number_of_employees < (SELECT AVG(number_of_employees) FROM companies)";
        int updatedRowCount = statement.executeUpdate(query1);
        System.out.println("Updated Row Count: "+ updatedRowCount);

        // Task 2: Add another company to the companies table.
        System.out.println("******** TASK 2 ********");
        String query2 = "INSERT INTO companies(company_id, company, number_of_employees) VALUES(104, 'Oracle', 8000)";
        int updatedRowCount2 = statement.executeUpdate(query2);
        System.out.println("Updated Row Count2: "+updatedRowCount2);

        // Task 3: Remove the company with ID: 100
        System.out.println("******** TASK 3 ********");
        String query3 = "DELETE FROM companies WHERE company_id = '100'";
        int updatedRowCount3 = statement.executeUpdate(query3);
        System.out.println("Updated Row Count3: "+updatedRowCount3);

        // Close the connection
        connection.close();

        // Close the Statement
        statement.close();


    }
}