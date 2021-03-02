package com.revature.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.revature.dao.ProjectDAOKryo;
import com.revature.pojo.Project;

class ProjectDAOKryoTest {

	@Test
	void testGetProjectByID() {
		ProjectDAOKryo projectDAO = new ProjectDAOKryo();
		Project project = new Project("Project 1");
		
		projectDAO.createProject(project);
		
		Project projectRetrieved = projectDAO.getProjectById(project.getProjectId());
		System.out.println(projectRetrieved);
		System.out.println(project);
		assertTrue(project.equals(projectRetrieved), "Projects should be equal");
	}
	
	@Test
	void testGetAllProjects() {
		ProjectDAOKryo projectDAO = new ProjectDAOKryo();
		Project project1 = new Project("Project1");
		Project project2 = new Project("Project2");
		
		projectDAO.createProject(project1);
		projectDAO.createProject(project2);
		
		List<Project> projectsRetrieved = projectDAO.getAllProjects();
		
		assertTrue(projectsRetrieved.contains(project1), "Get first project");
		assertTrue(projectsRetrieved.contains(project2), "Get second project");
	}
	
	@Test
	void testRemoveProject() {
		ProjectDAOKryo projectDAO = new ProjectDAOKryo();
		Project project1 = new Project("ProjectRemove");
		
		projectDAO.createProject(project1);
		projectDAO.removeProject(project1);
		
		List<Project> projectsRetrieved = projectDAO.getAllProjects();
		//tasksRetrieved.contains(task1);
		assertFalse(projectsRetrieved.contains(project1), "Project should not exist in list of all projects");
	}
	
	@Test
	void testUpdateProject() {
		ProjectDAOKryo projectDAO = new ProjectDAOKryo();
		Project project1 = new Project("StartProject");
		Project project2 = new Project("EndProject");
		
		projectDAO.createProject(project1);
		projectDAO.updateProject(project1.getProjectId(), project2);
		
		Project projectRetrieved = projectDAO.getProjectById(project1.getProjectId());
		//tasksRetrieved.contains(task1);
		assertEquals(project1.getProjectId(), projectRetrieved.getProjectId(), "projectId should not be changed");
		assertEquals(project2.getProjectName(), projectRetrieved.getProjectName(), "projectName should be updated");
	}

}
