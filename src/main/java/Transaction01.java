import java.sql.*;

public class Transaction01 {
    public static void main(String[] args) throws SQLException {
        // Create Connection
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "nur851611nil");

        // Create Statement
        Statement statement = con.createStatement();

        // Execute SQL queries
        /// Task 2: Using preparedStatement, delete students who are from Mathematics department, from students table.
        // String query1 = "DELETE FROM students WHERE department ILIKE 'mathematics' "; // Normal Query
        String query1 = "DELETE FROM students WHERE department ILIKE ? "; // Normal Query
        PreparedStatement ps1 = con.prepareStatement(query1);

        ps1.setString(1, "Comp.Eng.");
        ps1.executeUpdate();
        int numOfRowsUpdated1 = ps1.executeUpdate();
        System.out.println("numOfRowsUpdated1 = " + numOfRowsUpdated1);

        //get the control of our transaction
        con.setAutoCommit(false);// we are controlling the auto-shipping by declaring it false

        if(true){
            con.rollback();
        }
        con.commit();



        // Close connection and statement
        statement.close();
        con.close();
    }
}
