package com.revature.service;

import java.util.List;

import com.revature.pojo.Employee;
import com.revature.pojo.Project;
import com.revature.pojo.Task;

public interface EmployeeService {

	/**
	 * Takes in an employee objects and returns a list of tasks assigned to that employee.
	 * @param employee
	 * @return
	 */
	public List<Task> getTasks(Employee employee);
	
	/**
	 * Takes in an employee objects and returns a list of projects assigned to that employee.
	 * @param employee
	 * @return
	 */
	public List<Project> getProjects(Employee employee);
	
	/**
	 * Takes in an employee and a taskId, and attempts to complete the task.
	 * Returns true is successful, false otherwise
	 * @param emplyee
	 * @param taskId
	 * @return
	 */
	public boolean completeTask(Employee emplyee, int taskId);
	
}
