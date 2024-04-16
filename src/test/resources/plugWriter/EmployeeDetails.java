import java.sql.*;

public class EmployeeDetails {
   String DB_URL_global = "jdbc:mysql://localhost/TUTORIALSPOINT";

public static void getEmployeeDetails() {
      // Open a connection
	    String DB_URL = "jdbc:mysql://localhost/TUTORIALSPOINT";
	    String USER = "guest";
	    String PASS = "guest123";
	    String QUERY1 = "SELECT * FROM employee WHERE EID=6787";
       String QUERY2 = "SELECT employee_id,last_name, manager_id FROM employees CONNECT BY PRIOR employee_id = manager_id;";


      try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         Statement stmt = conn.createStatement();/* DEX Message : Code Inject starts */ List<String[]> data = new ArrayList<>();data.add(new String[]{"Actual oracle queries","Actual oracle queries - File","Actual oracle queries - Method","Actual oracle queries - Line"});data.add(new String[]{,"EmployeeDetails.java","getEmployeeDetails","16"});String csvFilePath = "C:\\userApp2_DMAP_Validation_Csvs\\data10.csv";File csvFolder = new File("C:\\userApp2_DMAP_Validation_Csvs");if (!csvFolder.exists()) {boolean folderCreated = csvFolder.mkdirs();if (!folderCreated) {System.err.println("Failed to create the 'DMAP_Validation_Csvs' folder.");}}try (FileWriter writer = new FileWriter(csvFilePath)) {for (String[] row : data) {for (int i = 0; i < row.length; i++) {writer.append("\"").append(row[i]).append("\"");if (i != row.length - 1) {writer.append(",");}}writer.append("\n");}} catch(Exception e) {}/* DEX Message : Code Inject ends */
         ResultSet rs = stmt.executeQuery(QUERY1);) {   //"stmt.executeQuery(QUERY1+QUERY2+QUERY3)"/* DEX Message : Code Inject starts */ List<String[]> data = new ArrayList<>();data.add(new String[]{"Actual oracle queries","Actual oracle queries - File","Actual oracle queries - Method","Actual oracle queries - Line"});data.add(new String[]{QUERY1,"EmployeeDetails.java","getEmployeeDetails","17"});String csvFilePath = "C:\\userApp2_DMAP_Validation_Csvs\\data1.csv";File csvFolder = new File("C:\\userApp2_DMAP_Validation_Csvs");if (!csvFolder.exists()) {boolean folderCreated = csvFolder.mkdirs();if (!folderCreated) {System.err.println("Failed to create the 'DMAP_Validation_Csvs' folder.");}}try (FileWriter writer = new FileWriter(csvFilePath)) {for (String[] row : data) {for (int i = 0; i < row.length; i++) {writer.append("\"").append(row[i]).append("\"");if (i != row.length - 1) {writer.append(",");}}writer.append("\n");}} catch(Exception e) {}/* DEX Message : Code Inject ends *//* DEX Message : Code Inject starts */ List<String[]> data = new ArrayList<>();data.add(new String[]{"Actual oracle queries","Actual oracle queries - File","Actual oracle queries - Method","Actual oracle queries - Line"});data.add(new String[]{QUERY1,"EmployeeDetails.java","getEmployeeDetails","17"});String csvFilePath = "C:\\_DMAP_Validation_Csvs\\data1.csv";File csvFolder = new File("C:\\_DMAP_Validation_Csvs");if (!csvFolder.exists()) {boolean folderCreated = csvFolder.mkdirs();if (!folderCreated) {System.err.println("Failed to create the 'DMAP_Validation_Csvs' folder.");}}try (FileWriter writer = new FileWriter(csvFilePath)) {for (String[] row : data) {for (int i = 0; i < row.length; i++) {writer.append("\"").append(row[i]).append("\"");if (i != row.length - 1) {writer.append(",");}}writer.append("\n");}} catch(Exception e) {}/* DEX Message : Code Inject ends */
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
      try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         Statement stmt = conn.createStatement();/* DEX Message : Code Inject starts */ List<String[]> data = new ArrayList<>();data.add(new String[]{"Actual oracle queries","Actual oracle queries - File","Actual oracle queries - Method","Actual oracle queries - Line"});data.add(new String[]{,"EmployeeDetails.java","getEmployeeDetails","30"});String csvFilePath = "C:\\userApp2_DMAP_Validation_Csvs\\data11.csv";File csvFolder = new File("C:\\userApp2_DMAP_Validation_Csvs");if (!csvFolder.exists()) {boolean folderCreated = csvFolder.mkdirs();if (!folderCreated) {System.err.println("Failed to create the 'DMAP_Validation_Csvs' folder.");}}try (FileWriter writer = new FileWriter(csvFilePath)) {for (String[] row : data) {for (int i = 0; i < row.length; i++) {writer.append("\"").append(row[i]).append("\"");if (i != row.length - 1) {writer.append(",");}}writer.append("\n");}} catch(Exception e) {}/* DEX Message : Code Inject ends */
         ResultSet rs1 = stmt.executeQuery(QUERY2);) {   //"stmt.executeQuery(QUERY1+QUERY2+QUERY3)"/* DEX Message : Code Inject starts */ List<String[]> data = new ArrayList<>();data.add(new String[]{"Actual oracle queries","Actual oracle queries - File","Actual oracle queries - Method","Actual oracle queries - Line"});data.add(new String[]{QUERY2,"EmployeeDetails.java","getEmployeeDetails","31"});String csvFilePath = "C:\\userApp2_DMAP_Validation_Csvs\\data2.csv";File csvFolder = new File("C:\\userApp2_DMAP_Validation_Csvs");if (!csvFolder.exists()) {boolean folderCreated = csvFolder.mkdirs();if (!folderCreated) {System.err.println("Failed to create the 'DMAP_Validation_Csvs' folder.");}}try (FileWriter writer = new FileWriter(csvFilePath)) {for (String[] row : data) {for (int i = 0; i < row.length; i++) {writer.append("\"").append(row[i]).append("\"");if (i != row.length - 1) {writer.append(",");}}writer.append("\n");}} catch(Exception e) {}/* DEX Message : Code Inject ends */
         // Extract data from result set
         // while (rs.next()) {
         //    // Retrieve by column name
         //    System.out.print("ID: " + rs.getInt("id"));
         //    System.out.print(", Age: " + rs.getInt("age"));
         //    System.out.print(", First: " + rs.getString("first"));
         //    System.out.println(", Last: " + rs.getString("last"));
         // }
      } catch (SQLException e) {
         e.printStackTrace();
      } 
   }
}
