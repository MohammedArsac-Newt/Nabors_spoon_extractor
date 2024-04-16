package org.newtglobal.dmapextension.controller;

import org.json.simple.JSONObject;
import org.newtglobal.dmapextension.service.ScanDirectoryForDiscovery;
import org.newtglobal.dmapextension.utility.Constants;
import org.newtglobal.dmapextension.utility.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
@CrossOrigin(origins = "*")
public class DmapExtensionController {

	@Autowired
	private Utils utils;

	@Autowired
	private ScanDirectoryForDiscovery scanDirectoryForDiscovery;

	
	private static final Logger LOGGER = LoggerFactory.getLogger(DmapExtensionController.class);

	
	@PostMapping(value = "/discovery")
	public JSONObject startDiscovery(@RequestBody String appName) {
		JSONObject jsonObj = new JSONObject();
		try {
			LOGGER.info("Application passed for discovery --> {}", appName);
			if (!utils.isNull(appName)) {
				jsonObj = scanDirectoryForDiscovery.scanFoldersRecursively(appName);
				jsonObj.put(Constants.STATUS, Constants.SUCCESS);

			} else {
				jsonObj.put(Constants.STATUS, Constants.FAILED);
			}
		} catch (Exception e) {
			LOGGER.error(Constants.EXCEPTION_MESSAGE, e);
			jsonObj.put(Constants.STATUS, Constants.FAILED);
		}
		return jsonObj;
	}


	@GetMapping(value = "/ping")
	public String getTestData() {
		return Constants.SUCCESS;
	}

}
