package org.newtglobal.dmapextension.controller;


import spoon.Launcher;
import spoon.legacy.NameFilter;
import spoon.reflect.CtModel;
import spoon.reflect.code.CtBlock;
import spoon.reflect.code.CtComment;
import spoon.reflect.code.CtStatement;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.ModifierKind;
import spoon.reflect.visitor.filter.TypeFilter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.parser.ParseException;
import org.newtglobal.dmapextension.pojo.ClassExtractedValue;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import spoon.reflect.factory.Factory;
import spoon.reflect.declaration.CtType;

@Component
public class insertDescriptionLogic {


	public static void extractJson(String projectName) {
		// TODO Auto-generated method stub
		try {

			String filePath="C:\\App_Remediation\\json_report\\"+projectName+".json";
			File file = new File(filePath);
			FileReader reader = new FileReader(file);
			ObjectMapper mapper = new ObjectMapper();
			JsonNode rootNode = mapper.readTree(reader);
			JsonNode classList = rootNode.path("classList");

			for (JsonNode extractClass : classList) {
				JsonNode methods = extractClass.path("extractClass");
				List<ClassExtractedValue> classValue=new ArrayList<>();
				for (JsonNode method : methods) {

					ClassExtractedValue classExtractedValue=new ClassExtractedValue();
					String classpath = method.path("classpath").asText();
					String methodName = method.path("methodName").asText();
					String methodContent = method.path("methodContent").asText();
					String short_summary = method.path("short_summary").asText();
					

					classExtractedValue.setClasspath(classpath);
					classExtractedValue.setMethodContent(methodContent);
					classExtractedValue.setShort_summary(short_summary);
					classExtractedValue.setMethodName(methodName);
					classValue.add(classExtractedValue);
				}

				if(classValue.size()>0) {
				insertComments(classValue);
				
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void insertComments(List<ClassExtractedValue> classValue) throws IOException {

		// Initialize Spoon
		Launcher launcher = new Launcher();
		launcher.addInputResource(classValue.get(0).getClasspath());

		// Build Spoon model
		launcher.buildModel();
		CtModel model = launcher.getModel();
		Map<Integer, String> messages = new HashMap<>();
		Map<Integer, String> m = new HashMap<>();


		for (CtClass<?> clazz : model.getElements(new spoon.reflect.visitor.filter.TypeFilter<>(CtClass.class))) {
			for (CtMethod<?> method : clazz.getMethods()) {
				for(int i=0;i<classValue.size();i++) {
					//&& method.toString().equals(classValue.get(i).getMethodContent())
					if (method.getSimpleName().equals(classValue.get(i).getMethodName())) {
						messages.put(method.getPosition().getLine(), classValue.get(i).getShort_summary());
						// Print the method's line number
						m.put(method.getPosition().getLine(), classValue.get(i).getShort_summary());
						System.out.println("Line number of method " + classValue.get(i).getMethodName() + ": " + method.getPosition().getLine());
						break;
					}


				}
			}
		}

		TreeMap<Integer, String> sortedMap = new TreeMap<>(messages);
		TreeMap<Integer, String> sortedMapForCompleteSummary = new TreeMap<>(m);


		updateCommentsOnMethod(sortedMap,classValue.get(0).getClasspath());

	}


	private static void updateCommentsOnMethod(Map<Integer, String> messages,String classpath) {
		try {

			// Read the contents of the file
			BufferedReader reader = new BufferedReader(new FileReader(classpath));
			StringBuilder content = new StringBuilder();
			String line;
			int lineNumber =1;
			while ((line = reader.readLine()) != null) {
				// Inject messages at specified line numbers
				if (messages.containsKey(lineNumber)) {
					content.append("/**").append(messages.get(lineNumber)).append("*/").append("\n");
				}
				content.append(line).append("\n");
				
				lineNumber++;
			}
			reader.close();

			// Write the modified contents back to the file
			BufferedWriter writer = new BufferedWriter(new FileWriter(classpath));
			writer.write(content.toString());
			writer.close();

			System.out.println("Messages inserted successfully.");


			/*
			 * Path path = Paths.get(classpath); String line =
			 * Files.readAllLines(path).get(lineNumber).concat(comments); List<String> lines
			 * = Files.readAllLines(path, StandardCharsets.UTF_8);
			 * lines.remove(Files.readAllLines(path).get(lineNumber)); lines.add(lineNumber,
			 * line); Files.write(path, lines, StandardCharsets.UTF_8);
			 */
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}


}



