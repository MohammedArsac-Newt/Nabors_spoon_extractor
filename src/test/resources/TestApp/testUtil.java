package org.newtglobal.dmapextension.utility;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.newtglobal.dmapextension.dao.DmapExtensionDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class Utils {

	final String exceptionMessage = "Exception occurred --> "; // DEX Message : Dead Code Detected - The Following Field has no reference exceptionMessage // DEX Message : Dead Code Detected - The Following Field has no reference exceptionMessage // DEX Message : Dead Code Detected - The Following Field has no reference exceptionMessage // DEX Message : Dead Code Detected - The Following Field has no reference exceptionMessage // DEX Message : Dead Code Detected - The Following Field has no reference exceptionMessage // DEX Message : Dead Code Detected - The Following Field has no reference exceptionMessage // DEX Message : Dead Code Detected - The Following Field has no reference exceptionMessage // DEX Message : Dead Code Detected - The Following Field has no reference exceptionMessage // DEX Message : Dead Code Detected - The Following Field has no reference exceptionMessage // DEX Message : Dead Code Detected - The Following Field has no reference exceptionMessage // DEX Message : Dead Code Detected - The Following Field has no reference exceptionMessage // DEX Message : Dead Code Detected - The Following Field has no reference exceptionMessage // DEX Message : Dead Code Detected - The Following Field has no reference exceptionMessage // DEX Message : Dead Code Detected - The Following Field has no reference exceptionMessage

	@Value("${dateFormatForCreatedDateAndTime}")
	private String dateFormatForCreatedDateAndTime; // DEX Message : Dead Code Detected - The Following Field has no reference dateFormatForCreatedDateAndTime // DEX Message : Dead Code Detected - The Following Field has no reference dateFormatForCreatedDateAndTime // DEX Message : Dead Code Detected - The Following Field has no reference dateFormatForCreatedDateAndTime // DEX Message : Dead Code Detected - The Following Field has no reference dateFormatForCreatedDateAndTime // DEX Message : Dead Code Detected - The Following Field has no reference dateFormatForCreatedDateAndTime // DEX Message : Dead Code Detected - The Following Field has no reference dateFormatForCreatedDateAndTime // DEX Message : Dead Code Detected - The Following Field has no reference dateFormatForCreatedDateAndTime // DEX Message : Dead Code Detected - The Following Field has no reference dateFormatForCreatedDateAndTime // DEX Message : Dead Code Detected - The Following Field has no reference dateFormatForCreatedDateAndTime // DEX Message : Dead Code Detected - The Following Field has no reference dateFormatForCreatedDateAndTime // DEX Message : Dead Code Detected - The Following Field has no reference dateFormatForCreatedDateAndTime // DEX Message : Dead Code Detected - The Following Field has no reference dateFormatForCreatedDateAndTime // DEX Message : Dead Code Detected - The Following Field has no reference dateFormatForCreatedDateAndTime // DEX Message : Dead Code Detected - The Following Field has no reference dateFormatForCreatedDateAndTime

	@Value("${dateFormatForAppRunId}")
	private String dateFormatForAppRunId; // DEX Message : Dead Code Detected - The Following Field has no reference dateFormatForAppRunId // DEX Message : Dead Code Detected - The Following Field has no reference dateFormatForAppRunId // DEX Message : Dead Code Detected - The Following Field has no reference dateFormatForAppRunId // DEX Message : Dead Code Detected - The Following Field has no reference dateFormatForAppRunId // DEX Message : Dead Code Detected - The Following Field has no reference dateFormatForAppRunId // DEX Message : Dead Code Detected - The Following Field has no reference dateFormatForAppRunId // DEX Message : Dead Code Detected - The Following Field has no reference dateFormatForAppRunId // DEX Message : Dead Code Detected - The Following Field has no reference dateFormatForAppRunId // DEX Message : Dead Code Detected - The Following Field has no reference dateFormatForAppRunId // DEX Message : Dead Code Detected - The Following Field has no reference dateFormatForAppRunId // DEX Message : Dead Code Detected - The Following Field has no reference dateFormatForAppRunId // DEX Message : Dead Code Detected - The Following Field has no reference dateFormatForAppRunId // DEX Message : Dead Code Detected - The Following Field has no reference dateFormatForAppRunId // DEX Message : Dead Code Detected - The Following Field has no reference dateFormatForAppRunId

	@Value("${jre_log_files_by_code}")
	private String jre_log_files_by_code; // DEX Message : Dead Code Detected - The Following Field has no reference jre_log_files_by_code // DEX Message : Dead Code Detected - The Following Field has no reference jre_log_files_by_code // DEX Message : Dead Code Detected - The Following Field has no reference jre_log_files_by_code // DEX Message : Dead Code Detected - The Following Field has no reference jre_log_files_by_code // DEX Message : Dead Code Detected - The Following Field has no reference jre_log_files_by_code // DEX Message : Dead Code Detected - The Following Field has no reference jre_log_files_by_code // DEX Message : Dead Code Detected - The Following Field has no reference jre_log_files_by_code // DEX Message : Dead Code Detected - The Following Field has no reference jre_log_files_by_code // DEX Message : Dead Code Detected - The Following Field has no reference jre_log_files_by_code // DEX Message : Dead Code Detected - The Following Field has no reference jre_log_files_by_code // DEX Message : Dead Code Detected - The Following Field has no reference jre_log_files_by_code // DEX Message : Dead Code Detected - The Following Field has no reference jre_log_files_by_code // DEX Message : Dead Code Detected - The Following Field has no reference jre_log_files_by_code // DEX Message : Dead Code Detected - The Following Field has no reference jre_log_files_by_code

	@Value("${project_upload_path}")
	private String project_upload_path; // DEX Message : Dead Code Detected - The Following Field has no reference project_upload_path // DEX Message : Dead Code Detected - The Following Field has no reference project_upload_path // DEX Message : Dead Code Detected - The Following Field has no reference project_upload_path // DEX Message : Dead Code Detected - The Following Field has no reference project_upload_path // DEX Message : Dead Code Detected - The Following Field has no reference project_upload_path // DEX Message : Dead Code Detected - The Following Field has no reference project_upload_path // DEX Message : Dead Code Detected - The Following Field has no reference project_upload_path // DEX Message : Dead Code Detected - The Following Field has no reference project_upload_path // DEX Message : Dead Code Detected - The Following Field has no reference project_upload_path // DEX Message : Dead Code Detected - The Following Field has no reference project_upload_path // DEX Message : Dead Code Detected - The Following Field has no reference project_upload_path // DEX Message : Dead Code Detected - The Following Field has no reference project_upload_path // DEX Message : Dead Code Detected - The Following Field has no reference project_upload_path // DEX Message : Dead Code Detected - The Following Field has no reference project_upload_path

	private static final Logger LOGGER = LoggerFactory.getLogger(Utils.class); // DEX Message : Dead Code Detected - The Following Field has no reference LOGGER // DEX Message : Dead Code Detected - The Following Field has no reference LOGGER // DEX Message : Dead Code Detected - The Following Field has no reference LOGGER // DEX Message : Dead Code Detected - The Following Field has no reference LOGGER // DEX Message : Dead Code Detected - The Following Field has no reference LOGGER // DEX Message : Dead Code Detected - The Following Field has no reference LOGGER // DEX Message : Dead Code Detected - The Following Field has no reference LOGGER // DEX Message : Dead Code Detected - The Following Field has no reference LOGGER // DEX Message : Dead Code Detected - The Following Field has no reference LOGGER // DEX Message : Dead Code Detected - The Following Field has no reference LOGGER // DEX Message : Dead Code Detected - The Following Field has no reference LOGGER // DEX Message : Dead Code Detected - The Following Field has no reference LOGGER // DEX Message : Dead Code Detected - The Following Field has no reference LOGGER // DEX Message : Dead Code Detected - The Following Field has no reference LOGGER

	public String appNameForJson = ""; // DEX Message : Dead Code Detected - The Following Field has no reference appNameForJson // DEX Message : Dead Code Detected - The Following Field has no reference appNameForJson // DEX Message : Dead Code Detected - The Following Field has no reference appNameForJson // DEX Message : Dead Code Detected - The Following Field has no reference appNameForJson // DEX Message : Dead Code Detected - The Following Field has no reference appNameForJson // DEX Message : Dead Code Detected - The Following Field has no reference appNameForJson // DEX Message : Dead Code Detected - The Following Field has no reference appNameForJson // DEX Message : Dead Code Detected - The Following Field has no reference appNameForJson // DEX Message : Dead Code Detected - The Following Field has no reference appNameForJson // DEX Message : Dead Code Detected - The Following Field has no reference appNameForJson // DEX Message : Dead Code Detected - The Following Field has no reference appNameForJson // DEX Message : Dead Code Detected - The Following Field has no reference appNameForJson // DEX Message : Dead Code Detected - The Following Field has no reference appNameForJson // DEX Message : Dead Code Detected - The Following Field has no reference appNameForJson

	private DmapExtensionDao dmapExtensionDao; // DEX Message : Dead Code Detected - The Following Field has no reference dmapExtensionDao // DEX Message : Dead Code Detected - The Following Field has no reference dmapExtensionDao // DEX Message : Dead Code Detected - The Following Field has no reference dmapExtensionDao // DEX Message : Dead Code Detected - The Following Field has no reference dmapExtensionDao // DEX Message : Dead Code Detected - The Following Field has no reference dmapExtensionDao // DEX Message : Dead Code Detected - The Following Field has no reference dmapExtensionDao // DEX Message : Dead Code Detected - The Following Field has no reference dmapExtensionDao // DEX Message : Dead Code Detected - The Following Field has no reference dmapExtensionDao // DEX Message : Dead Code Detected - The Following Field has no reference dmapExtensionDao // DEX Message : Dead Code Detected - The Following Field has no reference dmapExtensionDao // DEX Message : Dead Code Detected - The Following Field has no reference dmapExtensionDao // DEX Message : Dead Code Detected - The Following Field has no reference dmapExtensionDao // DEX Message : Dead Code Detected - The Following Field has no reference dmapExtensionDao // DEX Message : Dead Code Detected - The Following Field has no reference dmapExtensionDao

	public String getQueryWithoutExtraSpaces(String oracleQuery) { // DEX Message : Dead Code Detected - The Following Method has no reference getQueryWithoutExtraSpaces // DEX Message : Dead Code Detected - The Following Method has no reference getQueryWithoutExtraSpaces // DEX Message : Dead Code Detected - The Following Method has no reference getQueryWithoutExtraSpaces // DEX Message : Dead Code Detected - The Following Method has no reference getQueryWithoutExtraSpaces // DEX Message : Dead Code Detected - The Following Method has no reference getQueryWithoutExtraSpaces // DEX Message : Dead Code Detected - The Following Method has no reference getQueryWithoutExtraSpaces // DEX Message : Dead Code Detected - The Following Method has no reference getQueryWithoutExtraSpaces // DEX Message : Dead Code Detected - The Following Method has no reference getQueryWithoutExtraSpaces // DEX Message : Dead Code Detected - The Following Method has no reference getQueryWithoutExtraSpaces // DEX Message : Dead Code Detected - The Following Method has no reference getQueryWithoutExtraSpaces // DEX Message : Dead Code Detected - The Following Method has no reference getQueryWithoutExtraSpaces // DEX Message : Dead Code Detected - The Following Method has no reference getQueryWithoutExtraSpaces // DEX Message : Dead Code Detected - The Following Method has no reference getQueryWithoutExtraSpaces // DEX Message : Dead Code Detected - The Following Method has no reference getQueryWithoutExtraSpaces

		String item = "";
//		try {
//			LOGGER.info("Oracle Query for getQueryWithoutExtraSpaces --> " + oracleQuery);
//			if (!isNull(oracleQuery) && !oracleQuery.contains(",")) {
//				String[] str1 = oracleQuery.split(",");
//				for (int i = 0; i < str1.length; i++) {
//					item = item + str1[i].strip() + ","; // TODO: We removing ; from item = item.replace(";,", ";");
//					item = item.replace(",", ";");
//				}
//			} else if (!isNull(oracleQuery) && oracleQuery.contains(",")) {
//				LOGGER.info("Oracle Query for getQueryWithoutExtraSpaces --> " + oracleQuery);
//				String[] str1 = oracleQuery.split(",");
//				for (int i = 0; i < str1.length; i++) {
//					item = item + str1[i].strip(); // TODO: We removing ; from item = item.replace(";,", ";");
//					item = item.replace(",", ";");
//				}
//			}
//		} catch (Exception e) {
//			LOGGER.error("Exception occurred --> " + e);
//		}
		try {
			LOGGER.info("Oracle Query for getQueryWithoutExtraSpaces --> {}", oracleQuery);
			String[] str1 = oracleQuery.split("\\s+");
			for (int i = 0; i < str1.length; i++) {
				if (i == str1.length - 1) {
					item = item + str1[i];
				} else {
					item = item + str1[i] + " ";
				}

			}
		} catch (Exception e) {
			LOGGER.error(exceptionMessage, e);
		}

		return item;
	}

	public String getCreatedDateWithTime() { // DEX Message : Dead Code Detected - The Following Method has no reference getCreatedDateWithTime // DEX Message : Dead Code Detected - The Following Method has no reference getCreatedDateWithTime // DEX Message : Dead Code Detected - The Following Method has no reference getCreatedDateWithTime // DEX Message : Dead Code Detected - The Following Method has no reference getCreatedDateWithTime // DEX Message : Dead Code Detected - The Following Method has no reference getCreatedDateWithTime // DEX Message : Dead Code Detected - The Following Method has no reference getCreatedDateWithTime // DEX Message : Dead Code Detected - The Following Method has no reference getCreatedDateWithTime // DEX Message : Dead Code Detected - The Following Method has no reference getCreatedDateWithTime // DEX Message : Dead Code Detected - The Following Method has no reference getCreatedDateWithTime // DEX Message : Dead Code Detected - The Following Method has no reference getCreatedDateWithTime // DEX Message : Dead Code Detected - The Following Method has no reference getCreatedDateWithTime // DEX Message : Dead Code Detected - The Following Method has no reference getCreatedDateWithTime // DEX Message : Dead Code Detected - The Following Method has no reference getCreatedDateWithTime // DEX Message : Dead Code Detected - The Following Method has no reference getCreatedDateWithTime
		String createdDateAndTime = null;
		try {
			Date date = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormatForCreatedDateAndTime);
			List<Map<String, Object>> rs = dmapExtensionDao.preferredTimezone();
			String timezone = rs.get(0).get("preferred_timezone").toString();
			simpleDateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
			createdDateAndTime = simpleDateFormat.format(date);
		} catch (Exception e) {
			LOGGER.error(exceptionMessage, e);
		}
		return createdDateAndTime;
	}

	public String getAppRunId() { // DEX Message : Dead Code Detected - The Following Method has no reference getAppRunId // DEX Message : Dead Code Detected - The Following Method has no reference getAppRunId // DEX Message : Dead Code Detected - The Following Method has no reference getAppRunId // DEX Message : Dead Code Detected - The Following Method has no reference getAppRunId // DEX Message : Dead Code Detected - The Following Method has no reference getAppRunId // DEX Message : Dead Code Detected - The Following Method has no reference getAppRunId // DEX Message : Dead Code Detected - The Following Method has no reference getAppRunId // DEX Message : Dead Code Detected - The Following Method has no reference getAppRunId // DEX Message : Dead Code Detected - The Following Method has no reference getAppRunId // DEX Message : Dead Code Detected - The Following Method has no reference getAppRunId // DEX Message : Dead Code Detected - The Following Method has no reference getAppRunId // DEX Message : Dead Code Detected - The Following Method has no reference getAppRunId // DEX Message : Dead Code Detected - The Following Method has no reference getAppRunId // DEX Message : Dead Code Detected - The Following Method has no reference getAppRunId
		String appRunId = null;
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormatForAppRunId);
			Date date = new Date(System.currentTimeMillis());
			appRunId = simpleDateFormat.format(date).toString();
		} catch (Exception e) {
			LOGGER.error(exceptionMessage, e);
		}
		return appRunId;
	}

	public boolean isNull(String value) { // DEX Message : Dead Code Detected - The Following Method has no reference isNull // DEX Message : Dead Code Detected - The Following Method has no reference isNull // DEX Message : Dead Code Detected - The Following Method has no reference isNull // DEX Message : Dead Code Detected - The Following Method has no reference isNull // DEX Message : Dead Code Detected - The Following Method has no reference isNull // DEX Message : Dead Code Detected - The Following Method has no reference isNull // DEX Message : Dead Code Detected - The Following Method has no reference isNull // DEX Message : Dead Code Detected - The Following Method has no reference isNull // DEX Message : Dead Code Detected - The Following Method has no reference isNull // DEX Message : Dead Code Detected - The Following Method has no reference isNull // DEX Message : Dead Code Detected - The Following Method has no reference isNull // DEX Message : Dead Code Detected - The Following Method has no reference isNull // DEX Message : Dead Code Detected - The Following Method has no reference isNull // DEX Message : Dead Code Detected - The Following Method has no reference isNull
		boolean flag = false;
		try {
			if (value == null || value.equals("") || value.equals(" ")) {
				return true;
			}
		} catch (Exception e) {
			LOGGER.error(exceptionMessage, e);
		}

		return flag;
	}

	public int checkBrackets(String expression, int index) { // DEX Message : Dead Code Detected - The Following Method has no reference checkBrackets // DEX Message : Dead Code Detected - The Following Method has no reference checkBrackets // DEX Message : Dead Code Detected - The Following Method has no reference checkBrackets // DEX Message : Dead Code Detected - The Following Method has no reference checkBrackets // DEX Message : Dead Code Detected - The Following Method has no reference checkBrackets // DEX Message : Dead Code Detected - The Following Method has no reference checkBrackets // DEX Message : Dead Code Detected - The Following Method has no reference checkBrackets // DEX Message : Dead Code Detected - The Following Method has no reference checkBrackets // DEX Message : Dead Code Detected - The Following Method has no reference checkBrackets // DEX Message : Dead Code Detected - The Following Method has no reference checkBrackets // DEX Message : Dead Code Detected - The Following Method has no reference checkBrackets // DEX Message : Dead Code Detected - The Following Method has no reference checkBrackets // DEX Message : Dead Code Detected - The Following Method has no reference checkBrackets // DEX Message : Dead Code Detected - The Following Method has no reference checkBrackets
		int endIndex = -1;
		try {

			if (expression.charAt(index) != '(') {
				endIndex = -1;
			}

			Stack<Integer> st = new Stack<>();
			st.push(index);

			for (int i = index + 1; i < expression.length(); i++) {

				if (expression.charAt(i) == '(') {
					st.push(i);
				}

				else if (expression.charAt(i) == ')') {
					if (!st.isEmpty()) {
						st.pop();
					}
					if (st.empty()) {
						endIndex = i;
						break;
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error(exceptionMessage, e);
		}

		return endIndex;
	}

	public boolean doubleQuotesCheck(String value) { // DEX Message : Dead Code Detected - The Following Method has no reference doubleQuotesCheck // DEX Message : Dead Code Detected - The Following Method has no reference doubleQuotesCheck // DEX Message : Dead Code Detected - The Following Method has no reference doubleQuotesCheck // DEX Message : Dead Code Detected - The Following Method has no reference doubleQuotesCheck // DEX Message : Dead Code Detected - The Following Method has no reference doubleQuotesCheck // DEX Message : Dead Code Detected - The Following Method has no reference doubleQuotesCheck // DEX Message : Dead Code Detected - The Following Method has no reference doubleQuotesCheck // DEX Message : Dead Code Detected - The Following Method has no reference doubleQuotesCheck // DEX Message : Dead Code Detected - The Following Method has no reference doubleQuotesCheck // DEX Message : Dead Code Detected - The Following Method has no reference doubleQuotesCheck // DEX Message : Dead Code Detected - The Following Method has no reference doubleQuotesCheck // DEX Message : Dead Code Detected - The Following Method has no reference doubleQuotesCheck // DEX Message : Dead Code Detected - The Following Method has no reference doubleQuotesCheck // DEX Message : Dead Code Detected - The Following Method has no reference doubleQuotesCheck
		try {
			if (!isNull(value)) {
				if (value.trim().startsWith("\"") && value.trim().endsWith("\"")) {
					return true;
				}
			}
		} catch (Exception e) {
			LOGGER.error(exceptionMessage, e);
		}

		return false;
	}

	public boolean dotCheck(String value) { // DEX Message : Dead Code Detected - The Following Method has no reference dotCheck // DEX Message : Dead Code Detected - The Following Method has no reference dotCheck // DEX Message : Dead Code Detected - The Following Method has no reference dotCheck // DEX Message : Dead Code Detected - The Following Method has no reference dotCheck // DEX Message : Dead Code Detected - The Following Method has no reference dotCheck // DEX Message : Dead Code Detected - The Following Method has no reference dotCheck // DEX Message : Dead Code Detected - The Following Method has no reference dotCheck // DEX Message : Dead Code Detected - The Following Method has no reference dotCheck // DEX Message : Dead Code Detected - The Following Method has no reference dotCheck // DEX Message : Dead Code Detected - The Following Method has no reference dotCheck // DEX Message : Dead Code Detected - The Following Method has no reference dotCheck // DEX Message : Dead Code Detected - The Following Method has no reference dotCheck // DEX Message : Dead Code Detected - The Following Method has no reference dotCheck // DEX Message : Dead Code Detected - The Following Method has no reference dotCheck
		try {
			if (!isNull(value)) {
				if (value.split("\\.").length > 1) {
					return true;
				}
			}
		} catch (Exception e) {
			LOGGER.error(exceptionMessage, e);
		}

		return false;
	}

	public boolean parenthesisCheck(String value) { // DEX Message : Dead Code Detected - The Following Method has no reference parenthesisCheck // DEX Message : Dead Code Detected - The Following Method has no reference parenthesisCheck // DEX Message : Dead Code Detected - The Following Method has no reference parenthesisCheck // DEX Message : Dead Code Detected - The Following Method has no reference parenthesisCheck // DEX Message : Dead Code Detected - The Following Method has no reference parenthesisCheck // DEX Message : Dead Code Detected - The Following Method has no reference parenthesisCheck // DEX Message : Dead Code Detected - The Following Method has no reference parenthesisCheck // DEX Message : Dead Code Detected - The Following Method has no reference parenthesisCheck // DEX Message : Dead Code Detected - The Following Method has no reference parenthesisCheck // DEX Message : Dead Code Detected - The Following Method has no reference parenthesisCheck // DEX Message : Dead Code Detected - The Following Method has no reference parenthesisCheck // DEX Message : Dead Code Detected - The Following Method has no reference parenthesisCheck // DEX Message : Dead Code Detected - The Following Method has no reference parenthesisCheck // DEX Message : Dead Code Detected - The Following Method has no reference parenthesisCheck
		try {
			if (!isNull(value)) {
				if (value.contains("(")) {
					return true;
				}
			}
		} catch (Exception e) {
			LOGGER.error(exceptionMessage, e);
		}

		return false;
	}

	public boolean isSQLQuery(String str) { // DEX Message : Dead Code Detected - The Following Method has no reference isSQLQuery // DEX Message : Dead Code Detected - The Following Method has no reference isSQLQuery // DEX Message : Dead Code Detected - The Following Method has no reference isSQLQuery // DEX Message : Dead Code Detected - The Following Method has no reference isSQLQuery // DEX Message : Dead Code Detected - The Following Method has no reference isSQLQuery // DEX Message : Dead Code Detected - The Following Method has no reference isSQLQuery // DEX Message : Dead Code Detected - The Following Method has no reference isSQLQuery // DEX Message : Dead Code Detected - The Following Method has no reference isSQLQuery // DEX Message : Dead Code Detected - The Following Method has no reference isSQLQuery // DEX Message : Dead Code Detected - The Following Method has no reference isSQLQuery // DEX Message : Dead Code Detected - The Following Method has no reference isSQLQuery // DEX Message : Dead Code Detected - The Following Method has no reference isSQLQuery // DEX Message : Dead Code Detected - The Following Method has no reference isSQLQuery // DEX Message : Dead Code Detected - The Following Method has no reference isSQLQuery
		String pattern = "";
		try {
			pattern = "(?i)\\b(SELECT|UPDATE|DELETE|INSERT|CREATE|ALTER|DROP|GRANT|REVOKE|CALL|TRUNCATE)\\b.*";
		} catch (Exception e) {
			LOGGER.error(exceptionMessage, e);
		}

		return Pattern.matches(pattern, str.trim());
	}

	public boolean sqlKeywordsIsPresent(String value) { // DEX Message : Dead Code Detected - The Following Method has no reference sqlKeywordsIsPresent // DEX Message : Dead Code Detected - The Following Method has no reference sqlKeywordsIsPresent // DEX Message : Dead Code Detected - The Following Method has no reference sqlKeywordsIsPresent // DEX Message : Dead Code Detected - The Following Method has no reference sqlKeywordsIsPresent // DEX Message : Dead Code Detected - The Following Method has no reference sqlKeywordsIsPresent // DEX Message : Dead Code Detected - The Following Method has no reference sqlKeywordsIsPresent // DEX Message : Dead Code Detected - The Following Method has no reference sqlKeywordsIsPresent // DEX Message : Dead Code Detected - The Following Method has no reference sqlKeywordsIsPresent // DEX Message : Dead Code Detected - The Following Method has no reference sqlKeywordsIsPresent // DEX Message : Dead Code Detected - The Following Method has no reference sqlKeywordsIsPresent // DEX Message : Dead Code Detected - The Following Method has no reference sqlKeywordsIsPresent // DEX Message : Dead Code Detected - The Following Method has no reference sqlKeywordsIsPresent // DEX Message : Dead Code Detected - The Following Method has no reference sqlKeywordsIsPresent // DEX Message : Dead Code Detected - The Following Method has no reference sqlKeywordsIsPresent
		try {
			String[] sqlKeywordsList = { "SELECT", "CREATE", "UPDATE", "DELETE", "INSERT", "ALTER", "TRUNCATE", "DROP",
					"DROP DATABASE", "DROP TABLE", "CALL" };

			for (String s : sqlKeywordsList) {
				if (value.toUpperCase().contains(s)) {
					return true;
				}
			}

		} catch (Exception e) {
			LOGGER.error(exceptionMessage, e);
		}

		return false;
	}

//	public boolean exactSqlKeywordIsPresent(String value) {
//		try {
//			List<String> sqlKeywordsList = new ArrayList<String>();
//			sqlKeywordsList.add("SELECT");
//			sqlKeywordsList.add("CREATE");
//			sqlKeywordsList.add("UPDATE");
//			sqlKeywordsList.add("DELETE");
//			sqlKeywordsList.add("INSERT");
//			sqlKeywordsList.add("ALTER");
//			sqlKeywordsList.add("TRUNCATE");
//			sqlKeywordsList.add("DROP");
//			sqlKeywordsList.add("DROP DATABASE");
//			sqlKeywordsList.add("DROP TABLE");
//			sqlKeywordsList.add("CALL");
//			for (String s : sqlKeywordsList) {
//				if (exactKeywordMatched(value, s)) {
//					return true;
//				}
//			}
//
//		} catch (Exception e) {
//			LOGGER.error(exceptionMessage, e);
//		}
//
//		return false;
//	}
//
//	private boolean exactKeywordMatched(String source, String subItem) {
//		String pattern = "\\b" + subItem + "\\b";
//		Pattern p = Pattern.compile(pattern);
//		Matcher m = p.matcher(source);
//		return m.find();
//	}

	public boolean isProcedureCall(String value) { // DEX Message : Dead Code Detected - The Following Method has no reference isProcedureCall // DEX Message : Dead Code Detected - The Following Method has no reference isProcedureCall // DEX Message : Dead Code Detected - The Following Method has no reference isProcedureCall // DEX Message : Dead Code Detected - The Following Method has no reference isProcedureCall // DEX Message : Dead Code Detected - The Following Method has no reference isProcedureCall // DEX Message : Dead Code Detected - The Following Method has no reference isProcedureCall // DEX Message : Dead Code Detected - The Following Method has no reference isProcedureCall // DEX Message : Dead Code Detected - The Following Method has no reference isProcedureCall // DEX Message : Dead Code Detected - The Following Method has no reference isProcedureCall // DEX Message : Dead Code Detected - The Following Method has no reference isProcedureCall // DEX Message : Dead Code Detected - The Following Method has no reference isProcedureCall // DEX Message : Dead Code Detected - The Following Method has no reference isProcedureCall // DEX Message : Dead Code Detected - The Following Method has no reference isProcedureCall // DEX Message : Dead Code Detected - The Following Method has no reference isProcedureCall
		try {
			if (value.contains("call") && value.contains("?")) {
				return true;
			}
		} catch (Exception e) {
			LOGGER.error(exceptionMessage, e);
		}

		return false;
	}

	public Resource loadAsResource(String filePath) { // DEX Message : Dead Code Detected - The Following Method has no reference loadAsResource // DEX Message : Dead Code Detected - The Following Method has no reference loadAsResource // DEX Message : Dead Code Detected - The Following Method has no reference loadAsResource // DEX Message : Dead Code Detected - The Following Method has no reference loadAsResource // DEX Message : Dead Code Detected - The Following Method has no reference loadAsResource // DEX Message : Dead Code Detected - The Following Method has no reference loadAsResource // DEX Message : Dead Code Detected - The Following Method has no reference loadAsResource // DEX Message : Dead Code Detected - The Following Method has no reference loadAsResource // DEX Message : Dead Code Detected - The Following Method has no reference loadAsResource // DEX Message : Dead Code Detected - The Following Method has no reference loadAsResource // DEX Message : Dead Code Detected - The Following Method has no reference loadAsResource // DEX Message : Dead Code Detected - The Following Method has no reference loadAsResource // DEX Message : Dead Code Detected - The Following Method has no reference loadAsResource // DEX Message : Dead Code Detected - The Following Method has no reference loadAsResource
		Resource resource = new FileSystemResource(filePath);
		if (resource != null && (resource.exists() || resource.isReadable())) {
			return resource;
		} else {
			throw new RuntimeException("Could not read file: " + filePath);
		}
	}

	public List<File> findFiles(String path, String fileExtension1) { // DEX Message : Dead Code Detected - The Following Method has no reference findFiles // DEX Message : Dead Code Detected - The Following Method has no reference findFiles // DEX Message : Dead Code Detected - The Following Method has no reference findFiles // DEX Message : Dead Code Detected - The Following Method has no reference findFiles // DEX Message : Dead Code Detected - The Following Method has no reference findFiles // DEX Message : Dead Code Detected - The Following Method has no reference findFiles // DEX Message : Dead Code Detected - The Following Method has no reference findFiles // DEX Message : Dead Code Detected - The Following Method has no reference findFiles // DEX Message : Dead Code Detected - The Following Method has no reference findFiles // DEX Message : Dead Code Detected - The Following Method has no reference findFiles // DEX Message : Dead Code Detected - The Following Method has no reference findFiles // DEX Message : Dead Code Detected - The Following Method has no reference findFiles // DEX Message : Dead Code Detected - The Following Method has no reference findFiles // DEX Message : Dead Code Detected - The Following Method has no reference findFiles
		return findFiles(new File(path), fileExtension1);
	}

	public List<File> findFiles(File filePath, String fileExtension1) { // DEX Message : Dead Code Detected - The Following Method has no reference findFiles // DEX Message : Dead Code Detected - The Following Method has no reference findFiles // DEX Message : Dead Code Detected - The Following Method has no reference findFiles // DEX Message : Dead Code Detected - The Following Method has no reference findFiles // DEX Message : Dead Code Detected - The Following Method has no reference findFiles // DEX Message : Dead Code Detected - The Following Method has no reference findFiles // DEX Message : Dead Code Detected - The Following Method has no reference findFiles // DEX Message : Dead Code Detected - The Following Method has no reference findFiles // DEX Message : Dead Code Detected - The Following Method has no reference findFiles // DEX Message : Dead Code Detected - The Following Method has no reference findFiles // DEX Message : Dead Code Detected - The Following Method has no reference findFiles // DEX Message : Dead Code Detected - The Following Method has no reference findFiles // DEX Message : Dead Code Detected - The Following Method has no reference findFiles // DEX Message : Dead Code Detected - The Following Method has no reference findFiles
		ArrayList<File> result = new ArrayList<>();
		try {
			File[] files = filePath
					.listFiles(f -> f.isDirectory() || f.getName().toLowerCase().endsWith(fileExtension1));
			if (files != null) {
				for (File file : files) {

					if (file.isDirectory()) {
						result.addAll(findFiles(file, fileExtension1));
					} else {
						result.add(file);
					}

				}
			}
		} catch (Exception e) {
			LOGGER.error(exceptionMessage, e);
		}
		return result;
	}

	public File getPath(String appName) { // DEX Message : Dead Code Detected - The Following Method has no reference getPath // DEX Message : Dead Code Detected - The Following Method has no reference getPath // DEX Message : Dead Code Detected - The Following Method has no reference getPath // DEX Message : Dead Code Detected - The Following Method has no reference getPath // DEX Message : Dead Code Detected - The Following Method has no reference getPath // DEX Message : Dead Code Detected - The Following Method has no reference getPath // DEX Message : Dead Code Detected - The Following Method has no reference getPath // DEX Message : Dead Code Detected - The Following Method has no reference getPath // DEX Message : Dead Code Detected - The Following Method has no reference getPath // DEX Message : Dead Code Detected - The Following Method has no reference getPath // DEX Message : Dead Code Detected - The Following Method has no reference getPath // DEX Message : Dead Code Detected - The Following Method has no reference getPath // DEX Message : Dead Code Detected - The Following Method has no reference getPath // DEX Message : Dead Code Detected - The Following Method has no reference getPath
		String appPath = "";
		File filePath = null;
		try {
			if (!isNull(appName)) {
				appNameForJson = appName;
				appPath = project_upload_path + appName;
			}
			filePath = new File(appPath);
		} catch (Exception e) {
			LOGGER.error(exceptionMessage, e);
		}

		return filePath;
	}

	public String findDifference(String start_date, String end_date) { // DEX Message : Dead Code Detected - The Following Method has no reference findDifference // DEX Message : Dead Code Detected - The Following Method has no reference findDifference // DEX Message : Dead Code Detected - The Following Method has no reference findDifference // DEX Message : Dead Code Detected - The Following Method has no reference findDifference // DEX Message : Dead Code Detected - The Following Method has no reference findDifference // DEX Message : Dead Code Detected - The Following Method has no reference findDifference // DEX Message : Dead Code Detected - The Following Method has no reference findDifference // DEX Message : Dead Code Detected - The Following Method has no reference findDifference // DEX Message : Dead Code Detected - The Following Method has no reference findDifference // DEX Message : Dead Code Detected - The Following Method has no reference findDifference // DEX Message : Dead Code Detected - The Following Method has no reference findDifference // DEX Message : Dead Code Detected - The Following Method has no reference findDifference // DEX Message : Dead Code Detected - The Following Method has no reference findDifference // DEX Message : Dead Code Detected - The Following Method has no reference findDifference

		String diff = "";
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormatForCreatedDateAndTime);
		try {

			Date d1 = sdf.parse(start_date);
			Date d2 = sdf.parse(end_date);

			int difference_In_Time = (int) (d2.getTime() - d1.getTime());
			int difference_In_Seconds = (difference_In_Time / 1000) % 60;
			int difference_In_Minutes = (difference_In_Time / (1000 * 60)) % 60;
			int difference_In_Hours = (difference_In_Time / (1000 * 60 * 60)) % 24;

			diff = String.valueOf(String.format("%02d", difference_In_Hours)) + ":"
					+ String.valueOf(String.format("%02d", difference_In_Minutes)) + ":"
					+ String.valueOf(String.format("%02d", difference_In_Seconds));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return diff;
	}

	@Autowired
	@Lazy
	public void setDmapExtensionDao(DmapExtensionDao dmapExtensionDao) { // DEX Message : Dead Code Detected - The Following Method has no reference setDmapExtensionDao // DEX Message : Dead Code Detected - The Following Method has no reference setDmapExtensionDao // DEX Message : Dead Code Detected - The Following Method has no reference setDmapExtensionDao // DEX Message : Dead Code Detected - The Following Method has no reference setDmapExtensionDao // DEX Message : Dead Code Detected - The Following Method has no reference setDmapExtensionDao // DEX Message : Dead Code Detected - The Following Method has no reference setDmapExtensionDao // DEX Message : Dead Code Detected - The Following Method has no reference setDmapExtensionDao // DEX Message : Dead Code Detected - The Following Method has no reference setDmapExtensionDao // DEX Message : Dead Code Detected - The Following Method has no reference setDmapExtensionDao // DEX Message : Dead Code Detected - The Following Method has no reference setDmapExtensionDao // DEX Message : Dead Code Detected - The Following Method has no reference setDmapExtensionDao // DEX Message : Dead Code Detected - The Following Method has no reference setDmapExtensionDao // DEX Message : Dead Code Detected - The Following Method has no reference setDmapExtensionDao // DEX Message : Dead Code Detected - The Following Method has no reference setDmapExtensionDao
		this.dmapExtensionDao = dmapExtensionDao;
	}

	public String getOSType() { // DEX Message : Dead Code Detected - The Following Method has no reference getOSType // DEX Message : Dead Code Detected - The Following Method has no reference getOSType // DEX Message : Dead Code Detected - The Following Method has no reference getOSType // DEX Message : Dead Code Detected - The Following Method has no reference getOSType // DEX Message : Dead Code Detected - The Following Method has no reference getOSType // DEX Message : Dead Code Detected - The Following Method has no reference getOSType // DEX Message : Dead Code Detected - The Following Method has no reference getOSType // DEX Message : Dead Code Detected - The Following Method has no reference getOSType // DEX Message : Dead Code Detected - The Following Method has no reference getOSType // DEX Message : Dead Code Detected - The Following Method has no reference getOSType // DEX Message : Dead Code Detected - The Following Method has no reference getOSType // DEX Message : Dead Code Detected - The Following Method has no reference getOSType // DEX Message : Dead Code Detected - The Following Method has no reference getOSType // DEX Message : Dead Code Detected - The Following Method has no reference getOSType
		return System.getProperty("os.name").toLowerCase();
	}

}
