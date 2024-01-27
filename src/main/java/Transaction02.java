import java.sql.*;

public class Transaction02 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // Step 1: Specified my Driver for DB
        Class.forName("org.postgresql.Driver");

        // Step 2: Get the Connection
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "nur851611nil");

        // Step: Get the control of transaction
        connection.setAutoCommit(false);


        String query1 = "UPDATE companies SET number_of_employees = ? WHERE company = ?";

        // Create the Prepared Statement
        PreparedStatement prepStatement = connection.prepareStatement(query1);

        // Task: Update Microsoft's number_of_employees to 8000.
        prepStatement.setInt(1,8000);
        prepStatement.setString(2, "MICROSOFT");
        prepStatement.executeUpdate();

        if (true){
            connection.rollback();
        }

        // If there is no problem, just commit it. (Ship the product)

        connection.commit();

        prepStatement.close();
        connection.close();
    }
}