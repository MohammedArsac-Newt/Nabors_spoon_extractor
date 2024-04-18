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
import spoon.reflect.code.CtBlock;
import spoon.reflect.code.CtCodeSnippetStatement;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.code.CtStatement;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtField;
//import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtParameter;
import spoon.reflect.declaration.CtType;
import spoon.reflect.reference.CtTypeReference;
import spoon.reflect.visitor.filter.TypeFilter;
import spoon.support.reflect.code.CtBlockImpl;

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
           
           
            ////
            ArrayList<ExtractClass> arrList=new ArrayList<>();
            
            for (CtClass<?> clazz : model.getElements(new spoon.reflect.visitor.filter.TypeFilter<>(CtClass.class))) {
                for (CtMethod<?> method : clazz.getMethods()) {
                	
                	ExtractClass eclass=new ExtractClass();
                	
                    String className = clazz.getSimpleName();
                    String methodName = method.getSimpleName();
                    String methodContent = method.toString();
                    

                    String packageName= clazz.getPackage().getSimpleName();
                    
                    
                    System.out.println("Package name  for "+className+" is "+ packageName);

                    //to get return type 
                    CtTypeReference<?> returnType =  method.getType();
                    String returnTypeName = returnType.getSimpleName();
                   //System.out.println(className+" "+ methodName+ " "+ returnTypeName);


               	    



                                       

                    
                   
                    
                    
                    //extract log statements from the method in a class.
                    StringBuilder logStatementsBuilder = extractLogStatementsFromMethod(method);
                    String logStatements = logStatementsBuilder.toString();     
                    
                    //extract log field name from the method in a class.

                    StringBuilder parameterAndNameBuilder = extractParameterNameFromMethod(method);
                    String parameterAndName =parameterAndNameBuilder.toString();
                    
                    String logFieldName=extractLogFieldNameFromMethod(method);
                    String returnTypeName1=extractReturnTypeNameFromMethod(method);
                    		
                    eclass.setClassName(className);
                    eclass.setMethodContent(methodContent);
                    eclass.setMethodName(methodName);
                    eclass.setClasspath(dirPath);
                    eclass.setLogStatements(logStatements);
                    eclass.setParameterAndType(parameterAndName);
                    eclass.setLogFeildName(logFieldName);
                    eclass.setReturnTypeName(returnTypeName1);
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
	
	
	
	
	
	private StringBuilder extractParameterNameFromMethod(CtMethod<?> method ) {    
     // to get parameter from methods
        StringBuilder parameters = new StringBuilder();
        for (CtParameter<?> parameter : method.getParameters()) {
            String parameterType = parameter.getType().getSimpleName();
            String parameterName = parameter.getSimpleName();
            parameters.append( parameterType + "," + parameterName +"/r  ");
        }
        System.out.println(parameters);
		return parameters;	
	}
	
	
	
	private String extractReturnTypeNameFromMethod(CtMethod<?> method ) {    
	     // to get parameter from methods
	    	//to get return type 
	        CtTypeReference<?> returnType_ =  method.getType();
	        String returnTypeName = returnType_.getSimpleName();
	        //System.out.println();
			return returnTypeName;	
		}
	
	
	public String extractLogFieldNameFromMethod(CtMethod<?> method ) {
		String loggerFieldName=null;
        CtClass<?> targetClass = (CtClass<?>) method.getDeclaringType();
        if (targetClass != null) {
        	
        	for (CtField<?> field : targetClass.getFields()) {
        		//System.out.println("field  -  "+field);
                if (field.getType().getSimpleName().equals("Logger")) {
                    loggerFieldName = field.getSimpleName();
                    break;
                }
            }

        }
        System.out.println(loggerFieldName);
        return loggerFieldName != null ? loggerFieldName : "";   
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
	    return logStatementsBuilder;
	}

}
	
	
	
