package org.newtglobal.dmapextension.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.json.simple.JSONObject;
import org.newtglobal.dmapextension.utility.Constants;
import org.newtglobal.dmapextension.utility.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ScanDirectoryForDiscovery {

	@Autowired
	private Utils utils;

	@Autowired
	private CreateModelForDiscovery createModelForDiscovery;

	private static final Logger LOGGER = LoggerFactory.getLogger(ScanDirectoryForDiscovery.class);

	@Value("${project_upload_path}")
	private String project_upload_path;

	@Value("${dateFormatForCreatedDateAndTime}")
	private String dateFormatForCreatedDateAndTime;

	List<String> targetJavaFiles = new ArrayList<>();

	public List<File> findFiles(File filePath, String fileExtension1, String fileExtension2, String fileExtension3,
			String fileExtension4, String fileExtension5) {
		ArrayList<File> result = new ArrayList<>();
		boolean excludeTargetFolder = false;
		try {
			File[] files = filePath.listFiles(f -> f.isDirectory() || f.getName().toLowerCase().endsWith(fileExtension1)
					|| f.getName().toLowerCase().endsWith(fileExtension2)
					|| f.getName().toLowerCase().endsWith(fileExtension3)
					|| f.getName().toLowerCase().endsWith(fileExtension4)
					|| f.getName().toLowerCase().endsWith(fileExtension5));

			if (files != null) {
				for (File file : files) {
					if (file.isDirectory()) {
						if (excludeTargetFolder && file.getName().equals("target")) {
							continue; // skip target folder
						}
						result.addAll(findFiles(file, fileExtension1, fileExtension2, fileExtension3, fileExtension4,
								fileExtension5));
					} else {
						if (file.getName().equals("pom.xml")) {
							excludeTargetFolder = true;
						}
						result.add(file);
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error(Constants.EXCEPTION_MESSAGE, e);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public JSONObject scanFoldersRecursively(String appName) {

		String fileExtension1 = ".java";
		String fileExtension2 = ".properties";
		String fileExtension3 = ".xml";
		String fileExtension4 = ".sql";
		String fileExtension5 = ".jsp";
		JSONObject jsonObject = new JSONObject();
		try {
			
			File dir = utils.getPath(appName);
			List<File> totalfilesFound = findFiles(dir, fileExtension1, fileExtension2, fileExtension3, fileExtension4,
					fileExtension5);
			for (File file : totalfilesFound) {
				targetJavaFiles.add(file.getAbsolutePath());
			}

			if (!targetJavaFiles.isEmpty()) {
				createModelForDiscovery.buildModelForEachFile(targetJavaFiles, utils.appNameForJson);
			}

			targetJavaFiles.clear();	
			
		} catch (Exception e) {
			LOGGER.error(Constants.EXCEPTION_MESSAGE, e);
		}
		return jsonObject;
	}
	
    public List<String> getTargetJavaFiles() {
        return targetJavaFiles;
    }
	

}
