package jdbc_recap;
import java.sql.*;
public class Recap01 {
    public static void main(String[] args) throws SQLException {
        // Step 1: Register the Driver  (OPTIONAL)
        // Class.forName("org.postgresql.Driver");
        // Step 2: Create connection with Database
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres","Swar2005!");
        if (con != null){
            System.out.println("Connected successfully");
        }else {
            System.out.println("Not connected");
        }
        // Step 3: Create statement
        Statement statement= con.createStatement();
        // Step 4: Execute SQL queries  (CRUD Operations)
        // Task 1: Create a table named “students” with column names of : “id”, “name”, “city” and “grade”
//        boolean sql = statement.execute("CREATE TABLE students (id INT, name VARCHAR(30), city VARCHAR (20), grade INT)");
//        System.out.println(sql);
        /*
        DDL: Data Definition Language: Creating db structure -> execute() -> returns FALSE
        DQL: Data Query Language: Search, Update Operations -> execute() -> return TRUE or FALSE
         */
        // Task 2: Add a column "department" to students table
        String query1 = "ALTER TABLE students ADD COLUMN department VARCHAR (20)";
//        boolean sql1 = statement.execute(query1);
//        System.out.println(sql1);
//       int numbOfRowsAdded1 = statement.executeUpdate(query1);
//       System.out.println("numbOfRowsAdded1 = " + numbOfRowsAdded1);
        // Task: Add 1 student record in students table and then print it
//        String query2 = "INSERT INTO students VALUES(100, 'Ahmet Tekin', 'Kutahya', 490, 'Comp.Eng')";
        // 1st way:
        // statement.execute(query2);
        // 2nd way:
//        int numbOfRowsAdded2 = statement.executeUpdate(query2);
//        System.out.println("numbOfRowsAdded2 = " + numbOfRowsAdded2);
        // Task: Print all data of the student where id is 100
        String query3 = "SELECT * FROM students";
        ResultSet rs1 = statement.executeQuery(query3);
        // System.out.println("rs1 = " + rs1);
        System.out.println("rs1.next() = " + rs1.next());
        System.out.println(rs1.getString("name"));
        while (rs1.next()){
            System.out.println(rs1.getInt("id"));
        }
        // Task: Add more students into the table using PrepareStatement
        String query4 = "INSERT INTO students VALUES(?, ?, ?, ?, ?)";  // Parameterised Query
        PreparedStatement prs1 =  con.prepareStatement(query4);
//        prs1.setInt(1, 101);
//        prs1.setInt(4, 499);
//        prs1.setString(2, "Sabiha");
//        prs1.setString(5, "Mathematics");
//        prs1.setString(3, "Gaziantep");
//        prs1.setInt(1, 102);
//        prs1.setInt(4, 510);
//        prs1.setString(2, "Ali Can");
//        prs1.setString(5, "Management");
//        prs1.setString(3, "Izmir");
        prs1.setInt(1, 103);
        prs1.setInt(4,500);
        prs1.setString(2,"Erkam");
        prs1.setString(5,"Chemistry");
        prs1.setString(3,"Kirikkale");
        prs1.setInt(1, 104);
        prs1.setInt(4,999);
        prs1.setString(2,"Halil");
        prs1.setString(5,"Engineer");
        prs1.setString(3,"Nazilli");
        prs1.setObject(1, 105);
        prs1.setString(5, "Science");
        prs1.setString(2, "Murat");
        prs1.setInt(4, 495);
        prs1.setString(3, "Ankara");
        prs1.setInt(1, 104);
        prs1.setInt(4,999);
        prs1.setString(2,"Halil");
        prs1.setString(5,"Engineer");
        prs1.setString(3,"Nazilli");
        int numOfRowsAdded3 = prs1.executeUpdate();
        System.out.println("numOfRowsAdded3 = " + numOfRowsAdded3);
        // Task: Drop the table students
        String lastQuery = "DROP TABLE students";
        statement.execute(lastQuery);
        // Step 5: Close connection
        if (con != null){
            statement.close();
            con.close();
            System.out.println("Connection closed!");
        }else {
            System.out.println("Still connected");
        }
    }
}
