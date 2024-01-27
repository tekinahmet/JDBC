import javax.swing.plaf.PanelUI;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class JDBCUtils {
    public static Connection connection;  // class variable .. larger scope
    public static Statement statement;
    public static ResultSet resultSet;
    //Step 1: Create connection
    public static Connection connectToDatabase(String hostName, String dataBaseName, String userName, String password) {
        //jdbc:postgresql://localhost:5432/postgres, "postgres", "nur851611nil"
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://" +hostName+":5432/"+dataBaseName, userName, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
    // Step 2: Create Statement
    public static Statement createStatement() {
        try {
            //Statement statement = connection.createStatement(); // local variable
            statement = connection.createStatement(); // class variable
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return statement;
    }
    // Method to execute Query
    public static boolean execute(String query){
        boolean result;
        try {
            result = statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    // Method to drop a table
    public static void dropTable(String tableName){
        try {
            statement.execute("Drop TABLE " + tableName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    // The method to put column data into list
    public static List<Object> getColumnList(String columnName, String tableName){
        List<Object> list = new ArrayList<>();
        String query = "SELECT "+columnName+" FROM "+tableName;
        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        while (true){
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                list.add(resultSet.getObject(1));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return list;
    }
    // Close statement and connection
    public static void closeStatementAndConnection(){
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}