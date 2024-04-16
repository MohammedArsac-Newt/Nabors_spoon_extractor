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
	public static void insertDescription(String jsonClassName,String jsonMethodName ,String jsonSummary) throws IOException {

		// Initialize Spoon
		Launcher launcher = new Launcher();
		launcher.addInputResource("C:\\App_Remediation\\Project_Uploads\\Rajiv");

		// Build Spoon model
		launcher.buildModel();
		CtModel model = launcher.getModel();


		// Iterate over methods and add description to the top of each method
		for (CtClass<?> clazz : model.getElements(new TypeFilter<>(CtClass.class))) {
			System.out.println("Class Found: " + clazz.getQualifiedName());
		}

		// Find the class
		CtClass<?> targetClass = model.getElements(new TypeFilter<CtClass<?>>(CtClass.class) {
			@Override
			public boolean matches(CtClass<?> element) {
				return element.getSimpleName().equals(jsonClassName);
			}
		}).stream().findFirst().orElse(null);



		if (targetClass != null) {
			// Find the method
			CtMethod<?> targetMethod = targetClass.getMethods().stream()
					.filter(method -> method.getSimpleName().equals(jsonMethodName)).findFirst().orElse(null);

			if (targetMethod != null) {

				if (!methodHasComment(targetMethod, jsonSummary)) {
					// Insert summary as comment
					CtComment comment = targetMethod.getFactory().Core().createComment().setContent(jsonSummary);
					System.out.println(comment);
					// Create a new method with the comment
					CtElement parentElement = targetMethod.getParent();
					if (parentElement instanceof CtClass) {
						CtClass<?> parentClass = (CtClass<?>) parentElement;
						// Create a copy of the target method
						CtMethod<?> modifiedMethod = targetMethod.clone();
						// Add the comment to the cloned method
						modifiedMethod.addComment(comment);  
						// Remove the original method
						parentClass.removeMethod(targetMethod);
						// Add the modified method to the class
						parentClass.addMethod(modifiedMethod);
						// Write the modified class content back to the file
						String classFilePath = parentClass.getPosition().getFile().getAbsolutePath();
						System.out.println(classFilePath);
						try (FileWriter fileWriter = new FileWriter(classFilePath)) {
							fileWriter.write(parentClass.toString());
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					System.out.println("Summary inserted successfully for method " + jsonMethodName + " in class " + jsonClassName);
				}
				else {
					System.out.println("Comment already exists for method " + jsonMethodName +
							" in class " + jsonClassName);
				}
			}else {
				System.out.println("Method " + jsonMethodName + " not found in class " + jsonClassName);
			}
		} else {
			System.out.println("Class " + jsonClassName + " not found in the Spark application");
		}
	}

	private static boolean methodHasComment(CtMethod<?> method, String commentContent) {
		List<CtComment> comments = method.getElements(new TypeFilter<>(CtComment.class));
		for (CtComment comment : comments) {
			if (comment.getContent().equals(commentContent)) {
				return true;
			}
		}
		return false;
	}

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

		for (CtClass<?> clazz : model.getElements(new spoon.reflect.visitor.filter.TypeFilter<>(CtClass.class))) {
			for (CtMethod<?> method : clazz.getMethods()) {
				for(int i=0;i<classValue.size();i++) {
					//&& method.toString().equals(classValue.get(i).getMethodContent())
					if (method.getSimpleName().equals(classValue.get(i).getMethodName())) {
						messages.put(method.getPosition().getLine(), classValue.get(i).getShort_summary());
						// Print the method's line number
						System.out.println("Line number of method " + classValue.get(i).getMethodName() + ": " + method.getPosition().getLine());
						break;
					}


				}
			}
		}

		TreeMap<Integer, String> sortedMap = new TreeMap<>(messages);

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



