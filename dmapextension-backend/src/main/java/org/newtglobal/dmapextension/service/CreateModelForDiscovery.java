package org.newtglobal.dmapextension.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import spoon.Launcher;
import spoon.compiler.Environment;
import spoon.reflect.CtModel;

@Component
public class CreateModelForDiscovery {

	
	@Value("${jspTestFilePath}")
	String jsp_Test_Path_dir;
	

	private static final Logger LOGGER = LoggerFactory.getLogger(CreateModelForDiscovery.class);
	
	public void buildModelForEachFile(List<String> targetJavaFiles, String appName) {
		try {
			
			if (!targetJavaFiles.isEmpty()) {
				List scannedFiles = new CopyOnWriteArrayList(targetJavaFiles);
				Iterator iterator = scannedFiles.iterator();
				while (iterator.hasNext()) {
					String filePath = (String) iterator.next();
					
					if (filePath.contains(".java")) {
						buildModel(filePath, "", appName, null);
					}
					
				}
			}

		} catch (Exception e) {
			LOGGER.error("Exception occurred --> ", e);
		}

	}

	public void buildModel(String dirPath, String applicationRunId, String appName, List<Map<String, Object>> decoratorsRs) {
		try {
			
			Launcher launcher = new Launcher();
			launcher.addInputResource(dirPath);
			Environment e = launcher.getEnvironment();
			e.setPrettyPrinterCreator(() -> new RoundBracketMinimiser(launcher.getEnvironment()));
			launcher.buildModel();
			CtModel model = launcher.getModel();
			
		} catch (Exception e) {
			LOGGER.error("Exception occurred --> ", e);
		}

	}

	
	
}
