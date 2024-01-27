public class Runner {
    public static void main(String[] args) {
        //Create connection
        JDBCUtils.connectToDatabase("localHost", "postgres", "postgres", "nur851611nil");

        //Create statement
        JDBCUtils.createStatement();

        //Execute query
        JDBCUtils.execute("SELECT company_id, company, number_of_employees FROM companies WHERE number_of_employees = (SELECT MIN(number_of_employees) FROM companies)");
        JDBCUtils.dropTable("companies");//How to drop tables

        // Close statement and connection
        JDBCUtils.closeStatementAndConnection();






















    }
}
