package org.newtglobal.dmapextension.controller;

import java.util.List;

public class ClassList {
    public String projectName;
    
	public List<ExtractClassList> classList;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public List<ExtractClassList> getClassList() {
		return classList;
	}

	public void setClassList(List<ExtractClassList> classList) {
		this.classList = classList;
	}
	
	
}
