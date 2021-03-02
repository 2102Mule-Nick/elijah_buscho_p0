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

public class EmployeeDAOKryo implements EmployeeDAO {

	private Kryo kryo = new Kryo();
	
	private Logger log = Logger.getRootLogger();

	private static final String FOLDER_NAME = "data\\";
	
	private static final String PREFIX = "e";

	private static final String FILE_EXTENSION = ".dat";
	
	@Override
	public void createEmployee(Employee employee) {
		try (Output output = new Output(
				new FileOutputStream(FOLDER_NAME + PREFIX + employee.getEmployeeId() + FILE_EXTENSION))) {
			// Output output = new Output(outputStream);
			kryo.writeObject(output, employee);
			output.close();
		} catch (FileNotFoundException e) {
			// log.error("could not open file", e);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Employee> getAllEmployees() {
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
			List<Employee> employees = new ArrayList<Employee>();
			for (File file : fileNames) {
				//list.add(file.getName());
				try (Input input = new Input(new FileInputStream(file))) {
					//Input input = new Input(inputStream);
					Employee employee = kryo.readObject(input, Employee.class);
					input.close();
					//System.out.println(task);
					employees.add(employee);
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return employees;
		}
		return null;
	}

	@Override
	public void updateEmployee(int employeeId, Employee employee) {
		try (Input input = new Input(new FileInputStream(FOLDER_NAME + PREFIX + employeeId + FILE_EXTENSION))) {
			// Input input = new Input(inputStream);
			Employee employeeRetrieved = kryo.readObject(input, Employee.class);
			input.close();
			//System.out.println(task);
			employeeRetrieved.setRole(employee.getRole());
			employeeRetrieved.setProjects(employee.getProjects());
			employeeRetrieved.setUsername(employee.getUsername());
			employeeRetrieved.setPassword(employee.getPassword());
			
			try (Output output = new Output(
					new FileOutputStream(FOLDER_NAME + PREFIX + employeeRetrieved.getEmployeeId() + FILE_EXTENSION))) {
				// Output output = new Output(outputStream);
				kryo.writeObject(output, employeeRetrieved);
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
	public void removeEmployee(Employee employee) {
		try (Input input = new Input(new FileInputStream(FOLDER_NAME + PREFIX + employee.getEmployeeId() + FILE_EXTENSION))) {
			// Input input = new Input(inputStream);
			Employee employeeRetrieved = kryo.readObject(input, Employee.class);
			input.close();
			
			File taskfile = new File(FOLDER_NAME + PREFIX + employeeRetrieved.getEmployeeId() + FILE_EXTENSION);
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

	public EmployeeDAOKryo() {
		super();
		kryo.register(Task.class);
		kryo.register(Project.class);
		kryo.register(Employee.class);
		kryo.register(ArrayList.class);
		kryo.register(Employee.Role.class);
	}

	public Employee getEmployeeById(int employeeId) {
		try (Input input = new Input(new FileInputStream(FOLDER_NAME + PREFIX + employeeId + FILE_EXTENSION))) {
			// Input input = new Input(inputStream);
			Employee employee = kryo.readObject(input, Employee.class);
			input.close();
			
			return employee;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
