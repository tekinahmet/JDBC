import java.sql.*;

public class ExecuteQuery02 {
    public static void main(String[] args) throws SQLException {
        // Create Connection
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "nur851611nil");
        // Create Statement
        Statement statement = con.createStatement();

        // Execute SQL queries
        //Task-1: Print department name and grade of department which has the second highest pass_grade
        String query1 = "SELECT department, pass_grade FROM departments ORDER BY pass_grade DESC OFFSET 1 LIMIT 1";
        ResultSet rs1 = statement.executeQuery(query1);

        while (rs1.next()){
            System.out.println(rs1.getString("department")+ "-- "+rs1.getInt("pass_grade"));
        }

        System.out.println("===============================================");

        // INTERVIEW QUESTION
        //Task-2: Print department name and pass_grade of department which has second_highest pass_grade using sub-query
        String query2 = "SELECT department, pass_grade FROM departments WHERE pass_grade = (SELECT MAX(pass_grade) FROM departments WHERE pass_grade < (SELECT MAX(pass_grade) FROM departments))";

        ResultSet rs2 = statement.executeQuery(query2);

        while (rs2.next()){
            System.out.println(rs2.getString("department")+ "-- "+rs2.getInt("pass_grade"));
        }

        System.out.println("========================================");

        //Task-3: List department name, campus and highest grades of students from every department
        String query3 = "SELECT department, campus, (SELECT MAX(grade) FROM students s WHERE d.department = s.department) AS max_grade FROM departments d";

        ResultSet rs3 = statement.executeQuery(query3);
        while (rs3.next()){
            System.out.println(rs3.getString("department")+"-- "+rs3.getString("campus")+"-- " + rs3.getInt("max_grade"));
        }

        // Close statement and connection
        statement.close();
        con.close();

    }
}