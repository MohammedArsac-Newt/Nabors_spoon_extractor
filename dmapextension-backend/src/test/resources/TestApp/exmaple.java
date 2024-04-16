import org.demo.example.CommonDao;
public  class CommonServiceImpl implements CommonService{
	String finalQuery = "Select * from mytable";
	
	public void method3(String conditionalParam) {
	String finalQuery;
	String q1="Select * from mytable2"; // DEX Message : ChangedQuery - Select DISTINCT from mytable WHERE age > :age // DEX Message : Changed Query - Select * from mytable WHERE age > :age // DEX Message : ChangedQuery - Select random() // DEX Message : ChangedQuery - Select DISTINCT from mytable
	String q2=" Select * from mytable";
    
	if(conditionalCheck1) {
		finalQuery = "Select UNIQUE name  from mytable2 WHERE salary > :salary";    
	}else if(conditionalCheck2){
        finalQuery = "Select UNIQUE name from mytable2 WHERE id > :id";    
    }else if(conditionalParam!=null){
		finalQuery = q1+conditionalParam;   
	} else{ 
        finalQuery = q1+q2;
    }
	Query query = entityManager.createNativeQuery(finalQuery+"AND Lastname=:lname");  
	List<Employee> results = query.getResultList();
}
	
	public void method4(String conditionalParam) {
		
		Query query = entityManager.createNativeQuery(finalQuery);  
		List<Employee> results = query.getResultList();
	}
	public void sessionExample2() {
		int var1 = 10;
		int var2 = 20;
		String query = "";
		query = (var1 > var2) ? "Select 5 from dual" : "Select 6 from dual";
		Resultset set2 = stmt.executeQuery(query);
		List<Employee> results = query3.getResultList();
	}
	
	public void sessionExample3() {
		int var1 = 10;
		int var2 = 20;
		String query = (var1 > var2) ? "Select 5 from dual" : "Select 6 from dual";
		Resultset set2 = stmt.executeQuery(query);
		List<Employee> results = query3.getResultList();
	}
	
	public void getNotifications(boolean reportJob) throws SQLException {
		PreparedStatement selStmt = null;
		java.sql.Connection conn = null;
		ResultSet rs = null;
		String q1 = "select DISTINCT * from email_reports";
		String q2 = " Where ename < = 'ename'";
		HashMap<String, String> configMap = new HashMap<>();
		configMap.put("reports", q1 + q2);
		try {

			conn = DriverManager.getConnection(Constants.DB_URL, Constants.USER, Constants.PASS);
			//Queries from hashmap
			selStmt = conn.prepareStatement((String) configMap.get("reports"));
			rs = selStmt.executeQuery();
		} catch (Exception e) {
			// TODO Auto-generated catch blockl
			e.printStackTrace();
		} finally {
			if (rs != null)
				rs.close();
			if (selStmt != null)
				selStmt.close();
			if (conn != null)
				conn.close();
		}
	}
	
	public void getNotifications3(boolean reportJob) throws SQLException {
		PreparedStatement selStmt = null;
		java.sql.Connection conn = null;
		ResultSet rs = null;
		String q1 = "select UNIQUE * from email_reports";
		String q2 = " Where ename < = 'ename'";
		HashMap<String, String> configMap = new HashMap<>();
		configMap.put("reports", q1 + q2);
		try {

			conn = DriverManager.getConnection(Constants.DB_URL, Constants.USER, Constants.PASS);
			//Queries from hashmap
			StringBuffer sql = new StringBuffer();
			sql.append("Select * from mytable");
			if(q1 == "") {
				sql.append("WHERE Id = '2'")
			}else {
				sql.append(" WHERE Id = '1'");
			}
			selStmt = conn.prepareStatement(sql.toString());
			rs = selStmt.executeQuery();
		} catch (Exception e) {
			// TODO Auto-generated catch blockl
			e.printStackTrace();
		} finally {
			if (rs != null)
				rs.close();
			if (selStmt != null)
				selStmt.close();
			if (conn != null)
				conn.close();
		}
	}

}
