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

public class TaskDAOKryo implements TaskDAO {

	private Kryo kryo = new Kryo();

	private Logger log = Logger.getRootLogger();

	private static final String FOLDER_NAME = "data\\";
	
	private static final String PREFIX = "t";

	private static final String FILE_EXTENSION = ".dat";

	@Override
	public void createTask(Task task) {
		log.trace("create task start");
		try (Output output = new Output(
				new FileOutputStream(FOLDER_NAME + PREFIX + task.getTaskId() + FILE_EXTENSION))) {
			// Output output = new Output(outputStream);
			kryo.writeObject(output, task);
			output.close();
		} catch (FileNotFoundException e) {
			// log.error("could not open file", e);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Task getTaskById(int taskId) {

		try (Input input = new Input(new FileInputStream(FOLDER_NAME + PREFIX + taskId + FILE_EXTENSION))) {
			// Input input = new Input(inputStream);
			Task task = kryo.readObject(input, Task.class);
			input.close();
			//System.out.println(task);
			return task;

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
	public List<Task> getAllTasks() {

		
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
			File[] fileNames = dir.listFiles();
			List<Task> tasks = new ArrayList<Task>();
			for (File file : fileNames) {
				//list.add(file.getName());
				try (Input input = new Input(new FileInputStream(file))) {
					//Input input = new Input(inputStream);
					Task task = kryo.readObject(input, Task.class);
					input.close();
					//System.out.println(task);
					tasks.add(task);
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return tasks;
		}
		return null;

	}

	@Override
	public void updateTask(int taskId, Task task) {
		
		try (Input input = new Input(new FileInputStream(FOLDER_NAME + PREFIX + taskId + FILE_EXTENSION))) {
			// Input input = new Input(inputStream);
			Task taskRetrieved = kryo.readObject(input, Task.class);
			input.close();
			//System.out.println(task);
			taskRetrieved.setDescription(task.getDescription());
			taskRetrieved.setEmployeeId(task.getEmployeeId());
			taskRetrieved.setTaskName(task.getTaskName());
			try (Output output = new Output(
					new FileOutputStream(FOLDER_NAME + PREFIX + taskRetrieved.getTaskId() + FILE_EXTENSION))) {
				// Output output = new Output(outputStream);
				kryo.writeObject(output, taskRetrieved);
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

	@Override
	public void removeTask(Task task) {
		try (Input input = new Input(new FileInputStream(FOLDER_NAME + PREFIX + task.getTaskId() + FILE_EXTENSION))) {
			// Input input = new Input(inputStream);
			Task taskRetrieved = kryo.readObject(input, Task.class);
			input.close();
			
			File taskfile = new File(FOLDER_NAME + PREFIX + taskRetrieved.getTaskId() + FILE_EXTENSION);
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

	public TaskDAOKryo() {
		super();
		kryo.register(Task.class);
		kryo.register(Project.class);
		kryo.register(Employee.class);
	}

}
