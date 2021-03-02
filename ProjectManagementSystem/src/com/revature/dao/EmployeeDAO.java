package com.revature.dao;

import java.util.List;

import com.revature.pojo.Employee;

public interface EmployeeDAO {

	public void createEmployee(Employee employee);
	
	public List<Employee> getAllEmployees();
	
	public Employee getEmployeeById(int employeeId);
	
	public void updateEmployee(int employeeId, Employee employee);
	
	public void removeEmployee(Employee employee);
}

