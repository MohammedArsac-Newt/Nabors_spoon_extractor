package org.newtglobal.dmapextension.controller;

public class ExtractClass {

	public String className;
	public String methodName;
	public String methodContent;
	public String classpath;
	public String logStatements;

	public String getClasspath() { 
		return classpath; 
	} 
	public void setClasspath(String classpath) {
		this.classpath = classpath; 
	}

	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getMethodContent() {
		return methodContent;
	}
	public void setMethodContent(String methodContent) {
		this.methodContent = methodContent;
	}
	public String getLogStatements() {
		return logStatements;
	}
	public void setLogStatements(String logStatements) {
		this.logStatements = logStatements;
	}





}
