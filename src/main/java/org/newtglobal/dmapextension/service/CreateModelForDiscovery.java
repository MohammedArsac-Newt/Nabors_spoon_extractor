package org.newtglobal.dmapextension.service;

import java.io.FileWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;


import org.newtglobal.dmapextension.controller.ClassList;
import org.newtglobal.dmapextension.controller.ExtractClass;
import org.newtglobal.dmapextension.controller.ExtractClassList;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import spoon.Launcher;
import spoon.compiler.Environment;
import spoon.reflect.CtModel;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtField;
//import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.reference.CtTypeReference;
import spoon.reflect.visitor.filter.TypeFilter;

@Component
public class CreateModelForDiscovery {

	
	@Value("${jspTestFilePath}")
	String jsp_Test_Path_dir;
	
	@Value("${json_upload_path}")
	String json_Test_Path_dir;
	

	private static final Logger LOGGER =   LoggerFactory.getLogger(CreateModelForDiscovery.class);
	/**/
	public void buildModelForEachFile(List<String> targetJavaFiles, String appName) {
		try {
			 Gson gson = new Gson();
			 List<ExtractClassList> classList1=new ArrayList<>();
			 ClassList cListValue=new ClassList();
			 
			if (!targetJavaFiles.isEmpty()) {
				List scannedFiles = new CopyOnWriteArrayList(targetJavaFiles);
				Collections.sort(scannedFiles);
				Iterator iterator = scannedFiles.iterator();
				cListValue.setProjectName(appName);
				 ArrayList<ExtractClassList> cxtractClassList=new ArrayList<ExtractClassList>();
		            
				while (iterator.hasNext()) {
					String filePath = (String) iterator.next();
					
					
					if (filePath.contains(".java")) {
						ExtractClassList clist = buildModel(filePath, "", appName,null);
						classList1.add(clist);
						
					}
					
				}
				
				cListValue.setClassList(classList1);
	            String jsonString = gson.toJson(cListValue);
	            System.out.println(jsonString);
	           
	           
	            
	            FileWriter fileWriter = new FileWriter("C:\\App_Remediation\\json_report\\"+appName+".json");
	            fileWriter.write(jsonString);
	            fileWriter.close();
	            

	            
			}

		} catch (Exception e) {
			((org.slf4j.Logger) LOGGER).error("Exception occurred --> ", e);
		}
		
	}
	
	
	

	public ExtractClassList buildModel(String dirPath, String applicationRunId, String appName, List<Map<String, Object>> decoratorsRs ) {
        try {
//        	System.out.println("dirPath : "+dirPath);
            Launcher launcher = new Launcher();
            launcher.addInputResource(dirPath);
            Environment e = launcher.getEnvironment();
            e.setPrettyPrinterCreator(() -> new RoundBracketMinimiser(launcher.getEnvironment()));
            launcher.buildModel();
            CtModel model = launcher.getModel();
            
            ExtractClassList list=new ExtractClassList();
           
           
            //
            ArrayList<ExtractClass> arrList=new ArrayList<>();
            
            for (CtClass<?> clazz : model.getElements(new spoon.reflect.visitor.filter.TypeFilter<>(CtClass.class))) {
                for (CtMethod<?> method : clazz.getMethods()) {
                	
                	ExtractClass eclass=new ExtractClass();
                	
                    String className = clazz.getSimpleName();
                    String methodName = method.getSimpleName();
                    String methodContent = method.toString();
                    
                    CtClass<?> targetClass = (CtClass<?>) method.getDeclaringType();
//                    System.out.println(targetClass);
                    if (targetClass != null) {
                    	
                    	for (CtField<?> field : targetClass.getFields()) {
                    		System.out.println("field  -  "+field);
                            if (field.getType().getSimpleName().equals("Logger")) {
                                String loggerFieldName = field.getSimpleName();
                                System.out.println("Found Logger field in " + className + "." + methodName + ": " + loggerFieldName);
                            }
                        }
                    	//second approach for logger java.lang.logger 
//                    	for (CtField<?> field : targetClass.getFields()) {
//                            String fieldTypeName = field.getType().getQualifiedName();  // Get qualified field type name
//                            try {
//                                Class<?> fieldClass = Class.forName(fieldTypeName);  // Use Class.forName
//                                if (Field.class.isAssignableFrom(fieldClass) && Logger.class.isAssignableFrom(fieldClass)) {
//                                    String loggerFieldName = field.getSimpleName();
//                                    System.out.println("Found potential logger field (Reflection) in " + className + "." + methodName + ": " + loggerFieldName);
//                                }
//                            } catch (ClassNotFoundException e) {
//                                // Handle potential ClassNotFoundException
//                                System.err.printf("Class not found: " + fieldTypeName + ", skipping.",e);
//                            }
//                        }
                    	//third approach for java.lang.logger
//                    	for (CtField<?> field : targetClass.getFields()) {
//                            Class<?> fieldClass = null;
//                            try {
//                                fieldClass = Class.forName(field.getType().getQualifiedName());
//
//                            } catch (ClassNotFoundException e1) {
//                                // Handle potential ClassNotFoundException
//                                System.err.printf("Class not found: " + field.getType().getQualifiedName() + ", skipping.",e1);
//                                continue;
//                            }
//                            if (fieldClass != null && Field.class.isAssignableFrom(fieldClass)) {
//                                // Check if field type implements a common logging interface (optional)
//                                if (isPotentialLoggerField(fieldClass)) {
//                                    String loggerFieldName = field.getSimpleName();
//                                    System.out.println("Found potential logger field in " + className + "." + methodName + ": " + loggerFieldName);
//                                }
//                            }
//                    	}
 
                    	//fourth approach for java.lang.logger
//                    	for (CtField<?> field : targetClass.getFields()) {
//                            CtTypeReference<?> fieldType = field.getType();
//                            try {
//                                Class<?> fieldClass = Class.forName(fieldType.getQualifiedName());
//                                if (isPotentialLoggerField(fieldClass)) {
//                                    String loggerFieldName = field.getSimpleName();
//                                    System.out.println("Found potential logger field: " + loggerFieldName);
//                                }
//                            } catch (ClassNotFoundException e1) {
//                                // Handle ClassNotFoundException
//                                System.err.printf("Class not found: " + fieldType.getQualifiedName() + ", skipping.",e1);
//                            }
//                        }
                    }
                    //extract log statements from the method in a class.
                    StringBuilder logStatementsBuilder = extractLogStatementsFromMethod(method);
                    String logStatements = logStatementsBuilder.toString();
//                  System.out.println(method.getSimpleName()+" "+ logStatements);
/*                    if (logStatements != null) {*/
//                    System.out.println(method.getSimpleName() + " - " + logStatements);
//                    }             
                    eclass.setClassName(className);
                    eclass.setMethodContent(methodContent);
                    eclass.setMethodName(methodName);
                    eclass.setClasspath(dirPath);
                    eclass.setLogStatements(logStatements);
                    arrList.add(eclass);
                }
            }
            
            list.setExtractClass(arrList);         
            return list;
            
        } catch (Exception e) {
            ((org.slf4j.Logger) LOGGER).error("Exception occurred --> ", e);
        }
        
        return null;
    }
	
	
//    private static boolean isPotentialLoggerField(Class<?> fieldClass) {
//        // Check for interfaces commonly used for logging (replace/add as needed)
//        return fieldClass.isAssignableFrom(java.util.logging.Logger.class) ||
//               fieldClass.isAssignableFrom(org.slf4j.Logger.class) ||
//               // Add checks for other logging interfaces used in your project
//               false;
//    }
//    private static boolean isPotentialLoggerField(Class<?> fieldClass) {
//        // Check if the class is assignable from common logging interfaces
//        return Logger.class.isAssignableFrom(fieldClass) || org.slf4j.Logger.class.isAssignableFrom(fieldClass);
//    }
//	
//	
//	
	
	
	private boolean isLoggingInvocation(CtInvocation<?> invocation) {
	    // Check if the invocation is related to logging
	    // For SLF4J, logger methods typically start with "debug", "info", "warn", "error", etc.
	    String methodName = invocation.getExecutable().getSimpleName();
//	    System.out.println(invocation);
	    return methodName.equals("debug") || methodName.equals("info") ||
	           methodName.equals("warn") || methodName.equals("error");
	}
	
	private StringBuilder extractLogStatementsFromMethod(CtMethod<?> method) {
	    List<String> logStatements = new ArrayList<>();
	    StringBuilder logStatementsBuilder = new StringBuilder();
	    boolean foundLogs = false; 
	    for (CtInvocation<?> invocation : method.getElements(new TypeFilter<>(CtInvocation.class))) {
	        if (isLoggingInvocation(invocation)) {
//	            String logStatement = method.getDeclaringType().getSimpleName() + "::" +
//	                    method.getSimpleName() + ": " +
//	                    invocation.toString();
//	            logStatements.add(logStatement);
	        	int startIndex = invocation.toString().indexOf("(");
	        	int endIndex = invocation.toString().lastIndexOf(")");
	        	String logContent = invocation.toString().substring(startIndex + 1, endIndex);

	        	// Construct the log statement
//	        	String logStatement = method.getDeclaringType().getSimpleName() + "::" +
//	        	                        method.getSimpleName() + ": " +
//	        	                        logContent;
	        	
	            logStatementsBuilder.append(logContent+"  /r  ");
	            foundLogs = true; 

	        }
	    }
//	    if (!foundLogs) {
//	        return null;
//	    }
	    return logStatementsBuilder;
	}

}
	
	
	
	
//    //extract logger instance
//    
//        CtFieldReference<?> loggerField = null;
//
//        // Get all fields of the class
//        Collection<CtFieldReference<?>> fields = targetClass.getDeclaredFields();
//
//        // Search for the logger field
//        for (CtFieldReference<?> field : fields) {
//            if (Logger.class.getName().equals(field.getType().getSimpleName())) {
//                // Found the logger field
//            	
//                loggerField = field;
//                break;
//            }
//        }
//        System.out.println(loggerField);
//
//        if (loggerField != null) {
//            // Get the initialization expression of the logger field
//            CtExpression<?> loggerInitExpression = loggerField.getFieldDeclaration().getDefaultExpression();
//
//            // Get the logger instance from the initialization expression
//            Logger loggerInstance = null;
//            if (loggerInitExpression != null && loggerInitExpression.toString().startsWith("LoggerFactory.getLogger")) {
//                String loggerName = loggerInitExpression.toString().substring(loggerInitExpression.toString().indexOf('(') + 1, loggerInitExpression.toString().indexOf(')')).trim();
//                loggerInstance = LoggerFactory.getLogger(loggerName);
//                // You can do something with the logger instance here if needed
//                System.out.println("loggerinstance from a "+ className+" is  "+ loggerInstance);
//            }
//        }
//    }catch(Exception e1) {
//    	LOGGER.info("logField Extract error",e1 );
//    }
	
