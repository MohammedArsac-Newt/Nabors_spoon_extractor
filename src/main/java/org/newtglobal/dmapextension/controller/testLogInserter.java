package org.newtglobal.dmapextension.controller;

import java.io.FileWriter;
import java.io.IOException;
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
import org.newtglobal.dmapextension.service.RoundBracketMinimiser;
import org.newtglobal.dmapextension.service.ScanDirectoryForDiscovery;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.newtglobal.dmapextension.service.CreateModelForDiscovery;

import com.google.gson.Gson;

import spoon.Launcher;
import spoon.compiler.Environment;
import spoon.reflect.CtModel;
import spoon.reflect.code.CtBlock;
import spoon.reflect.code.CtCodeSnippetStatement;
import spoon.reflect.code.CtComment;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.code.CtReturn;
import spoon.reflect.code.CtStatement;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtField;
//import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtParameter;
import spoon.reflect.declaration.CtType;
import spoon.reflect.factory.Factory;
import spoon.reflect.reference.CtTypeReference;
import spoon.reflect.visitor.Filter;
import spoon.reflect.visitor.filter.TypeFilter;
import spoon.support.reflect.code.CtBlockImpl;

@Component
public class testLogInserter {
	
	
	@Autowired
	private  CreateModelForDiscovery CreateModelForDiscovery;

	
	@Value("${jspTestFilePath}")
	String jsp_Test_Path_dir;
	
	@Value("${json_upload_path}")
	String json_Test_Path_dir;
	

	private static final Logger LOGGER =   LoggerFactory.getLogger(testLogInserter.class);
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

						
					}
					
				}
				
					            
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
            

            
            for (CtClass<?> clazz : model.getElements(new spoon.reflect.visitor.filter.TypeFilter<>(CtClass.class))) {
                for (CtMethod<?> method : clazz.getMethods()) {
                	

                	
                    String className = clazz.getSimpleName();
                    String methodName = method.getSimpleName();
                    String methodContent = method.toString();
                    String codeSnippet = "String insertedString = \"Some value\";"; // Replace with your code

//                    CtBlock<?> methodBody = method.getBody();
                    
            		String loggerFieldName=null;
                    if (clazz != null) {
                    	
                    	for (CtField<?> field : clazz.getFields()) {
                    		//System.out.println("field  -  "+field);
                            if (field.getType().getSimpleName().equals("Logger")) {
                                loggerFieldName = field.getSimpleName();
                                break;
                            }
                        }

                    }
                    
                    System.out.println("loggerFieldName  -  "+ loggerFieldName);
                 // Create a new method with the comment
					CtElement parentElement = method.getParent();
					if (parentElement instanceof CtClass) {
			            Factory factory = method.getFactory();

						
						CtClass<?> parentClass = (CtClass<?>) parentElement;
						// Create a copy of the target method
//						CtMethod<?> modifiedMethod = targetMethod.clone();
//						// Add the comment to the cloned method
//						modifiedMethod.addComment(comment);  
			            CtMethod<?> modifiedMethod = method.clone();

			            CtElement methodBody = method.getBody();
			            if (methodBody != null) {
			                // Convert body to string
			                String existingBody = methodBody.toString();

			                // Modify the body by adding your new code
			                String newBody =  loggerFieldName+".info("+"goodnews"+");" +"\n"+ existingBody;

			                // Create a new body with the modified code
			                modifiedMethod.setBody(factory.Code().createCodeSnippetStatement(newBody));
			            }

			            
			            //System.out.println(modifiedMethod);
						// Remove the original method
						parentClass.removeMethod(method);
						// Add the modified method to the class
						parentClass.addMethod(modifiedMethod);
						// Write the modified class content back to the file
						String classFilePath = dirPath;
						System.out.println(classFilePath);
						try (FileWriter fileWriter = new FileWriter(classFilePath)) {
							fileWriter.write(parentClass.toString());
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						System.out.println("inserted");
					}
                        
                        
                        
                        
                        
                        
//
//get return method line number
//                    if (methodBody != null) {
//                        List<CtReturn<?>> returnStatements = methodBody.getElements(new TypeFilter<>(CtReturn.class));
//
//                        for (CtReturn<?> returnStatement : returnStatements) {
//                            // Get the line number of the return statement
//                            int lineNumber = returnStatement.getPosition().getLine();
//                            System.out.println("Return statement found at line: " + lineNumber);
//                        }
//                    }
    
                    



                }
            }
            
            
        } catch (Exception e) {
            ((org.slf4j.Logger) LOGGER).error("Exception occurred --> ", e);
        }
        
        return null;
    }
	
	
	
	
	

	
	

}
	
	
	
