public class Constant {
    public final String QUERY_RUN_MESSAGE = "queryRunMessage";

    public final String DESCRIPTION = "description";

    public final String DEAD_CODE_LINENO = "deadCodeLineNum";

    public final String FILE_PATH = "filePath";

    public final String NUMBER_OF_LINES = "noOfLines";

    public final String SQL_QUERY = "SELECT DBMS_RANDOM.VALUE FROM DUAL;";

    public String QUERY1 = "SELECT * FROM employee WHERE EID=6787;";

    public String QUERY2 = "SELECT employee_id,last_name, manager_id FROM employees CONNECT BY PRIOR employee_id = manager_id;";

    public static String _ALL_RTIS_SECURITY_ = "SELECT TOKEN_ID , TOKEN_NAME , MAC_ADDRESS , INSTALL_NAME , ENABLED_FLG , ENCRYPTION_KEY, CMP_NME,GRP_NME FROM RTIS_SECURITY ORDER BY TOKEN_ID";

    public static String _ALL_RTIS_WELL_ACCESS_ = "SELECT TOKEN_ID , WELL_ID_NBR , TOKEN_CREATED FROM RTIS_WELL_ACCESS WHERE TOKEN_ID = _TAG_TOKENID_TAG_ ORDER BY WELL_ID_NBR";

    public static String _NABORS_MSG_USER_LIST_ = (((((((((("select a.OBJECTID, a.name, d.value email, c.value company, b.value title, e.value phonenumber , f.value dep, g.value manager, h.value office " + " from ptusers a, ptpropertydata b, ptpropertydata c,ptpropertydata d, ptpropertydata e, ptpropertydata f, ptpropertydata g , ptpropertydata h ") + " where ") + " a.objectid = b.objectid and b.propertyid = 153 and ") + " a.objectid = c.objectid and c.propertyid = 156 and ") + " a.objectid = d.objectid and d.propertyid = 26 and ") + " a.objectid = e.objectid and e.propertyid = 152 and ") + " a.objectid = f.objectid and f.propertyid = 154 and ") + " a.objectid = g.objectid and g.propertyid = 155 and ") + " a.objectid = h.objectid and h.propertyid = 163 and ") + " UPPER(a.mappingAuthname) like UPPER('%_USER_SEARCH_STR_%') ") + "order by 2";
}