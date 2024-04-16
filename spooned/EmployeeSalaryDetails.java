import OracleTypes.INTEGER;
import java.sql.*;
public class EmployeeSalaryDetails {
    String abc = "hello";

    String DB_URL_global = "jdbc:mysql://localhost/TUTORIALSPOINT";

    private static String QUERY = "SELECT DBMS_RANDOM.VALUE FROM DUAL;";

    public void getSalaryDetails() {
        String DB_URL = "jdbc:mysql://localhost/TUTORIALSPOINT";
        String USER = "guest";
        String PASS = "guest123";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);Statement stmt = conn.createStatement();ResultSet rs = stmt.executeQuery(QUERY)) {
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
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);Statement stmt = conn.createStatement();ResultSet rs1 = stmt.getHqlQuery("Select * from emp;")) {
            while (rs1.next()) {
                System.out.print("ID: " + rs.getInt("id"));
                System.out.print(", Age: " + rs.getInt("age"));
                System.out.print(", First: " + rs.getString("first"));
                System.out.println(", Last: " + rs.getString("last"));
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);Statement stmt = conn.createStatement();ResultSet rs1 = stmt.getJdbcQuery("SELECT * from dept;")) {
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

    public Map getIpStartEnd(String ip1, String ip2, String ip3, String ip4, String mask) throws Exception {
        Map ipMap = new HashedMap();
        Connection connection = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            connection = session.connection();
            String procedureName = "{call GET_IP_START_END(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            CallableStatement stmt = connection.prepareCall(procedureName);
            stmt.setLong(1, Long.parseLong(ip1));
            stmt.setLong(2, Long.parseLong(ip2));
            stmt.setLong(3, Long.parseLong(ip3));
            stmt.setLong(4, Long.parseLong(ip4));
            stmt.setLong(5, Long.parseLong(mask));
            stmt.registerOutParameter(6, INTEGER);
            stmt.registerOutParameter(7, INTEGER);
            stmt.registerOutParameter(8, INTEGER);
            stmt.registerOutParameter(9, INTEGER);
            stmt.registerOutParameter(10, INTEGER);
            stmt.registerOutParameter(11, INTEGER);
            stmt.registerOutParameter(12, INTEGER);
            stmt.registerOutParameter(13, INTEGER);
            stmt.execute();
            String ipStart = (((((stmt.getInt(6) + ".") + stmt.getInt(7)) + ".") + stmt.getInt(8)) + ".") + stmt.getInt(9);
            String ipEnd = (((((stmt.getInt(10) + ".") + stmt.getInt(11)) + ".") + stmt.getInt(12)) + ".") + stmt.getInt(13);
            ipMap.put("ipStart", ipStart);
            ipMap.put("ipEnd", ipEnd);
            stmt.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            try {
                if (connection != null)
                    connection.close();

            } catch (Exception ex) {
            }
        }
        return ipMap;
    }
}