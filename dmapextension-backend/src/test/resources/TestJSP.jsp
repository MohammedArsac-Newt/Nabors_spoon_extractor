<%@page import="java.sql.*" %>
<%@page import="javax.servlet.http.*" %>
<!DOCTYPE html>
<html>
<head>
  <title>Query Database</title>
</head>
<body>
  <%
   // Connect to the database
   Connection conn = DriverManager.getConnection("url","userid","password");
   Statement stmt = conn.createStatement();
   ResultSet rs;
   
   // Execute a SELECT statement
   // String sql = "SELECT * FROM employees";
   StringBuffer sql = new StringBuffer();
	sql.append("SELECT DISTINCT T4.USER_ID Manager_ID, T4.USER_NAME Manager_Name "); 
	sql.append("FROM ( SELECT T3.USER_ID , T3.USER_NAME FROM IP_USER T3,IP_ROLE_MEMBER M3 WHERE T3.USER_ID= '"+userId+"' ");
	sql.append("AND T3.USER_ID=M3.USER_ID AND M3.ROLE_ID = 'R03' ");
	sql.append("UNION SELECT T1.USER_ID , T1.USER_NAME FROM IP_USER T1 WHERE T1.USER_ID IN ");
	sql.append("(SELECT T2.DELEGATE_USER_ID FROM IP_DELEGATE_USER T2 WHERE T2.USER_ID= '"+userId+"' ");
	sql.append("AND trunc(SYSDATE) BETWEEN T2.START_DATE AND nvl(T2.END_DATE,trunc(sysdate) )+1 ");
	sql.append(")) T4 ORDER BY 2 ");
	// List list = session.createSQLQuery(sql.toString()).addScalar("Manager_ID").addScalar("Manager_Name").list();
   rs = stmt.executeQuery(sql.toString()).addScalar("Manager_ID").addScalar("Manager_Name").list();
   
   // Display the results
   while (rs.next()) {
     String empName = rs.getString("emp_name");
     String empId = rs.getString("emp_id");
     String department = rs.getString("department");
     
     out.print("<p>" + empName + " - " + empId + " - " + department + "</p>");
   }
   
   // Close the result set and statement
   rs.close();
   stmt.close();
   conn.close();
  %>
</body>
</html>