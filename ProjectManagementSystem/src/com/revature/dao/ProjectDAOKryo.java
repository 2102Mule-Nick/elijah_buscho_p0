package com.revature.dao;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.revature.pojo.Employee;
import com.revature.pojo.Project;
import com.revature.pojo.Task;

public class ProjectDAOKryo implements ProjectDAO {

	private Kryo kryo = new Kryo();

	private Logger log = Logger.getRootLogger();

	private static final String FOLDER_NAME = "data\\";
	
	private static final String PREFIX = "p";

	private static final String FILE_EXTENSION = ".dat";
	
	@Override
	public void createProject(Project project) {
		try (Output output = new Output(
				new FileOutputStream(FOLDER_NAME + PREFIX + project.getProjectId() + FILE_EXTENSION))) {
			// Output output = new Output(outputStream);
			kryo.writeObject(output, project);
			output.close();
		} catch (FileNotFoundException e) {
			// log.error("could not open file", e);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Project> getAllProjects() {
		List<String> list = new ArrayList<String>();
		File dir = new File(FOLDER_NAME);
		if (dir.isDirectory()) {
			FileFilter filter = file -> {
				//boolean flag = false;
				if (file.isFile() && !file.isDirectory()) {
					String filename = file.getName();
					if (!filename.startsWith(FOLDER_NAME + PREFIX)) {
						return true;
					}
				}
				return false;

			};
			File[] fileNames = dir.listFiles(filter);
			List<Project> projects = new ArrayList<Project>();
			for (File file : fileNames) {
				//list.add(file.getName());
				try (Input input = new Input(new FileInputStream(file))) {
					//Input input = new Input(inputStream);
					Project project = kryo.readObject(input, Project.class);
					input.close();
					//System.out.println(task);
					projects.add(project);
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return projects;
		}
		return null;

	}

	@Override
	public void removeProject(Project project) {
		
		try (Input input = new Input(new FileInputStream(FOLDER_NAME + PREFIX + project.getProjectId() + FILE_EXTENSION))) {
			// Input input = new Input(inputStream);
			Project projectRetrieved = kryo.readObject(input, Project.class);
			input.close();
			
			File taskfile = new File(FOLDER_NAME + PREFIX + projectRetrieved.getProjectId() + FILE_EXTENSION);
			if(taskfile.delete())
				System.out.println("file deleted");
			else
				System.out.println("delete failed");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Project getProjectById(int projectId) {
		try (Input input = new Input(new FileInputStream(FOLDER_NAME + PREFIX + projectId + FILE_EXTENSION))) {
			// Input input = new Input(inputStream);
			Project project = kryo.readObject(input, Project.class);
			input.close();
			
			return project;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void updateProject(int projectId, Project project) {
		
		try (Input input = new Input(new FileInputStream(FOLDER_NAME + PREFIX + projectId + FILE_EXTENSION))) {
			// Input input = new Input(inputStream);
			Project projectRetrieved = kryo.readObject(input, Project.class);
			input.close();
			//System.out.println(task);
			projectRetrieved.setEmployees(project.getEmployees());
			projectRetrieved.setTasks(project.getTasks());
			projectRetrieved.setProjectName(project.getProjectName());
			try (Output output = new Output(
					new FileOutputStream(FOLDER_NAME + PREFIX + projectRetrieved.getProjectId() + FILE_EXTENSION))) {
				// Output output = new Output(outputStream);
				kryo.writeObject(output, projectRetrieved);
				output.close();
			} catch (FileNotFoundException e) {
				// log.error("could not open file", e);
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	public ProjectDAOKryo() {
		super();
		kryo.register(Task.class);
		kryo.register(Project.class);
		kryo.register(Employee.class);
		kryo.register(ArrayList.class);
	}

}
