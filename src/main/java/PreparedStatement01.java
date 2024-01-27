import java.sql.*;

public class PreparedStatement01 {
    public static void main(String[] args) throws SQLException {
        // Create Connection
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "nur851611nil");
        // Create Statement
        Statement statement = con.createStatement();

        // Execute SQL queries
        //TASK-1. Update pass_grade to 475 of Mathematics department (use PreparedStatement)
        // Normal query
        String query = "UPDATE departments SET pass_grade = 475 Where department ILIKE 'mathematics'";

        // Parameterised query
        String query1 = "UPDATE departments SET pass_grade = ? Where department ILIKE ? ";

        // Create prepared statement
        PreparedStatement ps1 = con.prepareStatement(query1);

        // Set the values
        ps1.setInt(1, 475);
        ps1.setString(2, "mathematics");

        // Execute the prepared statement
        int numOfUpdatedRows = ps1.executeUpdate();
        System.out.println("numOfUpdatedRows = " + numOfUpdatedRows);

        System.out.println("============================================");
        // To see the table

        String q1 = "Select * FROM departments";
        ResultSet rs1 = statement.executeQuery(q1);

        while (rs1.next()){
            System.out.println(rs1.getObject(1)+"-- "+rs1.getObject(2)+
                    "-- " +rs1.getObject(3)+"-- " +rs1.getObject(4));
        }

        System.out.println("============================================");
        // TASK-2. Update pass_grade to 455 of Literature department (use PreparedStatement)
        ps1.setString(2, "Literature");
        ps1.setInt(1, 455);
        int numOfUpdatedRows1 = ps1.executeUpdate();
        System.out.println("numOfUpdatedRows1 = " + numOfUpdatedRows1);

        String q2 = "Select * FROM departments";
        ResultSet rs2 = statement.executeQuery(q2);

        while (rs2.next()){
            System.out.println(rs2.getObject(1)+"-- "+rs2.getObject(2)+
                    "-- " +rs2.getObject(3)+"-- " +rs2.getObject(4));
        }

        // Close statement and connection
        statement.close();
        ps1.close();
        con.close();

    }
}