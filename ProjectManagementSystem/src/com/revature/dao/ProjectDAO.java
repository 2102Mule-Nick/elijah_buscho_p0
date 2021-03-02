package com.revature.dao;

import java.util.List;

import com.revature.pojo.Project;

public interface ProjectDAO {

	public void createProject(Project project);
	
	public List<Project> getAllProjects();
	
	public Project getProjectById(int projectId);
	
	public void updateProject(int projectId, Project project);
	
	public void removeProject(Project project);
}