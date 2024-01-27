import java.sql.*;

public class ExecuteUpdate01 {
    public static void main(String[] args) throws SQLException {

        // Create Connection
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "nur851611nil");
        // Create Statement
        Statement statement = con.createStatement();

        // Execute SQL queries
        String query1 = "UPDATE developers SET salary = (SELECT AVG(salary) FROM developers) WHERE salary < (SELECT AVG(salary)FROM developers)";

        int numOfRowsUpdated = statement.executeUpdate(query1);
        System.out.println("numOfRowsUpdated = " + numOfRowsUpdated);

        // To see the updated values
        String query2 = "SELECT name, salary FROM developers";
        ResultSet rs1 = statement.executeQuery(query2);
        while(rs1.next()){
            System.out.println(rs1.getString("name") + "-- "+ rs1.getInt("salary"));
        }

        System.out.println("==========================================");
        // Task 2: Add a developer in the developers table (id, name, salary, prog_lang)
        String query3 = "INSERT INTO developers(id, name, salary, prog_lang) VALUES (25, 'Ali Can', 6500, 'Java')";
        int numOfRowsUpdated1 =  statement.executeUpdate(query3);

        // To see the updated values
        String query4 = "SELECT * FROM developers";
        ResultSet rs2 = statement.executeQuery(query4);
        while(rs2.next()){
            System.out.println(rs2.getInt("id") + "-- "+rs2.getString("name")+"-- "+
                    rs2.getInt("salary")+ "-- "+rs2.getString("prog_lang"));
        }

        System.out.println("==========================================");

        // Task 3:  DELETE rows from developers table if  prog_lang is “Ruby”

        String query5 = "DELETE FROM developers WHERE prog_lang ILIKE 'ruby' ";
        int numOfDeletedRows = statement.executeUpdate(query5);
        System.out.println("numOfDeletedRows = " + numOfDeletedRows);

        // Close statement and connection
        statement.close();
        con.close();

    }
}