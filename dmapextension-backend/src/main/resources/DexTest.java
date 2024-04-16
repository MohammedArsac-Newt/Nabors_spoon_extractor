public class DexTest { 
public static void main(String[] args) { 
   Connection conn = DriverManager.getConnection("url","userid","password");
   Statement stmt = conn.createStatement();
   ResultSet rs;
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
   rs = stmt.executeQuery(sql.toString()).addScalar("Manager_ID").addScalar("Manager_Name").list(); // DEX Message : Needs manual intervention (Changed Query : select DISTINCT T4.USER_ID Manager_ID, T4.USER_NAME Manager_Name FROM (SELECT T3.USER_ID, T3.USER_NAME FROM IP_USER T3, IP_ROLE_MEMBER M3 WHERE T3.USER_ID= ' userId ' AND T3.USER_ID=M3.USER_ID AND M3.ROLE_ID = 'R03' UNION SELECT T1.USER_ID, T1.USER_NAME FROM IP_USER T1 WHERE T1.USER_ID IN (SELECT T2.DELEGATE_USER_ID FROM IP_DELEGATE_USER T2 WHERE T2.USER_ID= ' userId ' AND date_trunc('day', statement_timestamp()) BETWEEN T2.START_DATE AND coalesce(T2.END_DATE, date_trunc('day', statement_timestamp()))+1 )) T4 ORDER BY 2)
     String empName = rs.getString("emp_name");
     String empId = rs.getString("emp_id");
     String department = rs.getString("department");
     out.print("<p>" + empName + " - " + empId + " - " + department + "</p>");
   rs.close();
   stmt.close();
   conn.close();
 } 
 }
