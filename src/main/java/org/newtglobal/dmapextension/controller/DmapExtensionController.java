package org.newtglobal.dmapextension.controller;

import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.newtglobal.dmapextension.service.ScanDirectoryForDiscovery;
import org.newtglobal.dmapextension.utility.Constants;
import org.newtglobal.dmapextension.utility.Utils;
import org.newtglobal.dmapextension.controller.insertDescriptionLogic;
import org.newtglobal.dmapextension.controller.testLogInserter;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@RestController
@ResponseBody
@CrossOrigin(origins = "*")
public class DmapExtensionController {
	
//	@Autowired
//	private logInsertor logInsertor;

	@Autowired
	private Utils utils;

	@Autowired
	private ScanDirectoryForDiscovery scanDirectoryForDiscovery;

	@Autowired
	private insertDescriptionLogic  insertDescription;
	
	@Autowired
	private testLogInserter testLogInserter;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DmapExtensionController.class);

	
	@PostMapping(value = "/discovery")
	public JSONObject startDiscovery(@RequestBody String appName) {
		JSONObject jsonObj = new JSONObject();
		Response response =null;
		try {
			LOGGER.info("Application passed for discovery --> {}", appName);
			if (!utils.isNull(appName)) {
				jsonObj = scanDirectoryForDiscovery.scanFoldersRecursively(appName);
				jsonObj.put(Constants.STATUS, Constants.SUCCESS);
				

			} else {
				jsonObj.put(Constants.STATUS, Constants.FAILED);
			}
			
            System.out.println("Method Extraction Completed !!");
			RestAssured.baseURI = "http://localhost:5002/api/";
			RequestSpecification httpRequest = RestAssured.given();
			httpRequest.header("Content-Type", "application/json");
			JSONObject jsonObj2 = new JSONObject();
			jsonObj2.put("flag", true);
			jsonObj2.put("path", "C:\\App_Remediation\\json_report\\"+appName+".json");
			httpRequest.body(jsonObj2);
			System.out.println("Calling the service to collect the summary of the method with Request :" +jsonObj2.toJSONString());
		
			response = httpRequest.request(Method.GET, "read_json");
			
			
		} catch (Exception e) {
			LOGGER.error(Constants.EXCEPTION_MESSAGE, e);
			jsonObj.put(Constants.STATUS, Constants.FAILED);
		}
		
		
		return jsonObj;
	}
	
	
	  @PostMapping(value = "/insertDescription")
	    public JSONObject insertDescription(@RequestBody String projectName) {
	        JSONObject response = new JSONObject();

            try {
				insertDescriptionLogic.extractJson(projectName);
			} catch (Exception e) {
				e.printStackTrace();
			}
            
            response.put("status", "success");
            response.put("message", "Descriptions inserted successfully.");
	        return response;
	    }
	
//	  @PostMapping(value = "/testLogInserter")
//	    public JSONObject testLogInserter(@RequestBody String projectName) {
//	        JSONObject response = new JSONObject();
//
//          try {
//        	  testLogInserter.testExtractJson(projectName);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//          
//          response.put("status", "success");
//          response.put("message", "Descriptions inserted successfully.");
//	        return response;
//	    }
//	
	
		
	  @PostMapping(value = "/testdiscovery")
		public JSONObject teststartDiscovery(@RequestBody String appName) {
			JSONObject jsonObj = new JSONObject();
			Response response =null;
			try {
				LOGGER.info("Application passed for testloginserter --> {}", appName);
				if (!utils.isNull(appName)) {
					jsonObj = scanDirectoryForDiscovery.scanFoldersRecursively(appName);
					jsonObj.put(Constants.STATUS, Constants.SUCCESS);
					

				} else {
					jsonObj.put(Constants.STATUS, Constants.FAILED);
				}
				
	            System.out.println("Method Extraction Completed !!");
				
				
				
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
