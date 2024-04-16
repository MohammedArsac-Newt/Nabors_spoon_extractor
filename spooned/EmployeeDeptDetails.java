import java.sql.*;
public class EmployeeDeptDetails {
    String DB_URL_global = "jdbc:mysql://localhost/TUTORIALSPOINT";

    public void getSalaryDetailsarsac() {
        String DB_URL = "jdbc:mysql://localhost/TUTORIALSPOINT";
        String USER = "guest";
        String PASS = "guest123";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);Statement stmt = conn.createStatement();ResultSet rs = stmt.executeQuery("SELECT id, firstName, lastName FROM employee WHERE lastName LIKE '%sa';")) {
            while (rs.next()) {
                System.out.print("ID: " + rs.getInt("id"));
                System.out.print(", Age: " + rs.getInt("age"));
                System.out.print(", First: " + rs.getString("first"));
                System.out.println(", Last: " + rs.getString("last"));
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);Statement stmt = conn.createStatement();ResultSet rs1 = stmt.executeQuery("SELECT DBMS_RANDOM.VALUE FROM DUAL;")) {
            while (rs1.next()) {
                System.out.print("ID: " + rs.getInt("id"));
                System.out.print(", Age: " + rs.getInt("age"));
                System.out.print(", First: " + rs.getString("first"));
                System.out.println(", Last: " + rs.getString("last"));
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);Statement stmt = conn.createStatement();ResultSet rs2 = stmt.executeQuery("SELECT INSTR('Melbourne, Australia', 'a', -1) into sal1 FROM DUAL;")) {
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}