import java.sql.*;

public class PreparedStatement03 {
    public static void main(String[] args) throws SQLException {
        // Create Connection
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "nur851611nil");

        // Create Statement
        Statement statement = con.createStatement();
        // Execute SQL queries
        // Task 1: Insert software engineering department using prepared statement into departments table.
        //  (dept_id, department, pass_grade, campus)
        // String query = "INSERT INTO departments VALUES(dept_id, department, pass_grade, campus)";  // Normal query
        String query = "INSERT INTO departments VALUES(?, ?, ? , ?)";  // Parameterised Query
        PreparedStatement ps1 = con.prepareStatement(query);
        ps1.setInt(1, 5007);
        ps1.setObject(3, 530);
        ps1.setString(4, "South");
        ps1.setString(2, "software engineering");
        int numOfRowsUpdated = ps1.executeUpdate();
        System.out.println("numOfRowsUpdated = " + numOfRowsUpdated);
        // Task 2: Using preparedstatement, delete students who are from Mathematics department, from students table.
        // String query1 = "DELETE FROM students WHERE department ILIKE 'mathematics' "; // Normal Query
        String query1 = "DELETE FROM students WHERE department ILIKE ? "; // Normal Query
        PreparedStatement ps2 = con.prepareStatement(query1);
        ps2.setString(1, "Literature");
        int numOfRowsUpdated1 = ps2.executeUpdate();
        System.out.println("numOfRowsUpdated1 = " + numOfRowsUpdated1);
        // Close connection and statement
        ps1.close();
        ps2.close();
        statement.close();
        con.close();
        // Note: Parameterised Query should be used for values instead of table names and column names because that's not
        // safe for the databases
    }
}
