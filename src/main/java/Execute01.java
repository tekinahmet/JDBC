import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // Step 1: Registration of the Driver  (OPTIONAL => Not required anymore)
        // Class.forName("org.postgresql.Driver");

        //Step 2: Create Connection to the database
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "nur851611nil");

        if(connection != null){
            System.out.println("Connected successfully");
        }else {
            System.out.println("Connection failed");
        }

        // Step 3: Create a Statement - to execute SQL queries
        Statement statement = connection.createStatement();

        // Step 4: Execute query
        //TASK 1: create a table named "employee" with column names of : "employee_id", "employee_name", "salary"
        boolean sql1 = statement.execute("CREATE TABLE employee (employee_id INT, employee_name VARCHAR (30), employee_salary INT)");
        System.out.println("sql1= " + sql1);  // false

        /*
        execute() returns boolean value and can be used in DDL and DQL
          DDL (Data Definition Language) => Create, Update, Drop
          DQL (Data Query Language) = > Read the data  (SELECT)
          1) execute() with DDL , it always returns FALSE
          2) execute() with DQL , it can return FALSE or TRUE
          If you get the resultSet object as a result of this method, it returns TRUE
         */

        //TASK 2: add Varchar (20) column name "city" to employee table
        String query1 = "ALTER TABLE employee ADD COLUMN city VARCHAR (20)";
        boolean sql2 = statement.execute(query1);
        System.out.println("sql2 = " + sql2);


        // Task 3: Delete the employee table
        String query2 = "DROP TABLE employee";
        boolean sql3 = statement.execute(query2);
        System.out.println("sql3 = " + sql3);

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