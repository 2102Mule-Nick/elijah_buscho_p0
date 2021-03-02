package com.revature.pojo;

import java.util.ArrayList;

public class Project {

	private static int projectCount = 0;
	private int projectId;
	private String projectName;
	private ArrayList<Integer> employees;
	private ArrayList<Integer> tasks;

	public Project() {
		this(null, new ArrayList<Integer>(), new ArrayList<Integer>());
	}
	
	public Project(String projectName) {
		this(projectName, new ArrayList<Integer>(), new ArrayList<Integer>());
	}
	
	public Project(String projectName, ArrayList<Integer> employees, ArrayList<Integer> tasks) {
		super();
		this.projectId = projectCount;
		projectCount++;
		this.projectName = projectName;
		this.employees = employees;
		this.tasks = tasks;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public ArrayList<Integer> getEmployees() {
		return employees;
	}

	public void setEmployees(ArrayList<Integer> employees) {
		this.employees = employees;
	}

	public ArrayList<Integer> getTasks() {
		return tasks;
	}

	public void setTasks(ArrayList<Integer> tasks) {
		this.tasks = tasks;
	}

	public int getProjectId() {
		return projectId;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Project) {
			Project project = (Project) obj;
			if(this.projectId == project.projectId && this.projectName.equals(project.projectName)) {
//				if(this.employees != null) {
//					
//				}
//				else {
//					if(this.employees == project.employees) {
//						if(this.tasks != null) {
//							if()
//						}
//						else {
//							if(this.tasks == project.tasks) {
//								return true;
//							}
//						}
//					}
//				}
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		//return "TaskId: "+ getTaskId() + "\nTaskName: " + getTaskName() + "\nDescription: " + getDescription() + "\nEmployeeId:" + employeeId;
		return "ProjectId: " + this.projectId + "\nProjectName: " + getProjectName();
	}

}
