package org.newtglobal.dmapextension.utility;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	@Value("${dateFormatForCreatedDateAndTime}")
	private String dateFormatForCreatedDateAndTime;

	@Value("${dateFormatForAppRunId}")
	private String dateFormatForAppRunId;

	@Value("${jre_log_files_by_code}")
	private String jre_log_files_by_code;

	@Value("${project_upload_path}")
	private String project_upload_path;

	private static final Logger LOGGER = LoggerFactory.getLogger(Utils.class);

	private String preferredTimezone = "";
	
	public String appNameForJson = "";

	public String generatedAppRunId = "";



	public boolean isNull(String value) {
		boolean flag = false;
		try {
			if (value == null || value.equals("") || value.equals(" ")) {
				return true;
			}
		} catch (Exception e) {
			LOGGER.error(Constants.EXCEPTION_MESSAGE, e);
		}

		return flag;
	}

	public File getPath(String appName) {
		String appPath = "";
		File filePath = null;
		try {
			if (!isNull(appName)) {
				appNameForJson = appName;
				appPath = project_upload_path + appName;
			}
			filePath = new File(appPath);
		} catch (Exception e) {
			LOGGER.error(Constants.EXCEPTION_MESSAGE, e);
		}

		return filePath;
	}

		
}
