<%@ include file="../JspImports.jsp" %>
<%@ page import="com.mywells.edf.data.repository.DataBroker" %>
<%@ page import="com.mywells.edf.data.entities.util.DataServiceManager" %>
<%@ page import="javax.sql.rowset.CachedRowSet" %>
<%
DataBroker broker = DataServiceManager.getInstance().getBroker();
String query = "SELECT LOGGER_DESC,LOGGER_URL FROM MYWELLS_LOGS ORDER BY LOGGER_DESC";
CachedRowSet rowset = broker.getJDBCRowSet(query);
StringBuffer options=new StringBuffer(); 
String Sql1 = "select dbms_random.value from dual";   

rowset.first();
while ((rowset.size() > 0) && (!rowset.isAfterLast())) {
	options.append("<option value='").append(rowset.getString(2)).append("' >").append(rowset.getString(1)).append("</option>");
	rowset.next();
}

rowset.close();
String Sql2 = "select unique col1 from table1";
String Sql3 = "select * from emp;";
String Sql4 = "SELECT INSTR('Melbourne, Australia', 'a', -1) into sal1 from dual";
%>

<script type="text/javascript" 
	src="pt://images/plumtree/common/private/js/jsxml/181585/PTXML.js">
</script>
<script>
function refresh<%=portletId%>() {
	var newht = screen.availHeight * .75;
	var newwd = screen.availWidth * .95;
	var topmarg = (screen.availHeight - newht)/2;
	var leftmarg = (screen.availWidth - newwd)/2;
	var openWindowProp = "toolbar=0,status=10,menubar=0,location=0,scrollbars=1,resizable=1,width="+newwd+",height="+newht+",top="+topmarg+",left="+leftmarg;
	var dataCtl=document.getElementById("logger<%=portletId%>");
	var logURL=dataCtl.options[dataCtl.selectedIndex].value;
	dataCtl.selectedIndex=0;
	if(logURL!=null&&logURL.length>0){
		var winName=Date.parse(new Date());
		var rwwin = window.open(logURL,winName,openWindowProp);	
		rwwin.focus();
	}
	return false;
}

</script>
<div id="DIVLOGBUTTON<%=portletId%>">
<form>
<table>
<tr><td>

<select class="formPulldownText" name="logger<%=portletId %>" id="logger<%=portletId %>">
	<option value="">Select Logs to view</option>
	<%=options.toString() %>
</select>&nbsp;&nbsp;&nbsp;
<input class="formEditorBtnText" type=button id="button1" name="button1" value="View Logs"  onclick="javascript:refresh<%=portletId%>()" >
</td></tr>
</table>
</form>
</div>
<div id="DIVVIEWLOG<%=portletId%>"  style="display:none;"></div>


