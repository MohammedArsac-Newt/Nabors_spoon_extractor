import java.sql.*;
public class EmployeeDetails {
    String DB_URL_global = "jdbc:mysql://localhost/TUTORIALSPOINT";

    public static void getEmployeeDetails() {
        // Open a connection
        String DB_URL = "jdbc:mysql://localhost/TUTORIALSPOINT";
        String USER = "guest";
        String PASS = "guest123";
        String QUERY1 = "SELECT * FROM employee WHERE EID=6787;";
        String QUERY2 = "SELECT employee_id,last_name, manager_id FROM employees CONNECT BY PRIOR employee_id = manager_id;";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);Statement stmt = conn.createStatement();ResultSet rs = stmt.executeQuery(QUERY1)) {
            // "stmt.executeQuery(QUERY1+QUERY2+QUERY3)"
            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                System.out.print("ID: " + rs.getInt("id"));
                System.out.print(", Age: " + rs.getInt("age"));
                System.out.print(", First: " + rs.getString("first"));
                System.out.println(", Last: " + rs.getString("last"));
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);Statement stmt = conn.createStatement();ResultSet rs1 = stmt.executeQuery(QUERY2)) {
            // "stmt.executeQuery(QUERY1+QUERY2+QUERY3)"
            // Extract data from result set
            // while (rs.next()) {
            // // Retrieve by column name
            // System.out.print("ID: " + rs.getInt("id"));
            // System.out.print(", Age: " + rs.getInt("age"));
            // System.out.print(", First: " + rs.getString("first"));
            // System.out.println(", Last: " + rs.getString("last"));
            // }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}