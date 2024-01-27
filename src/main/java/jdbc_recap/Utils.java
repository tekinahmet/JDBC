package jdbc_recap;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class Utils {
    public static Connection connection;
    public static Statement statement;
    public static ResultSet resultSet;
    //    Create connection with Database
//    public static Connection connectToDB()  {
//        try {
//            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres","Swar2005!");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        return connection;
//    }
    public static Connection connectToDB(String hostName, String dBName, String username, String password)  {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://"+hostName+":5432/"+dBName, username,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
    // Create statement
    public static Statement createStatement(){
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return statement;
    }
    // Create execute method
    public static boolean execute(String query){
        boolean result;
        try {
            result = statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
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
    // Create a method to Drop the table
    public static void dropTable(String tableName){
        try {
            statement.execute("DROP TABLE" +tableName );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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