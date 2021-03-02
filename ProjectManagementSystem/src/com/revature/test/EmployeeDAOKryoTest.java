package com.revature.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.dao.EmployeeDAOKryo;
import com.revature.pojo.Employee;
import com.revature.pojo.Employee.Role;

class EmployeeDAOKryoTest {
	
	
	 //TODO: add functions that clean up after the test, and also ones that set up for the tests
	 

	@Test
	void testGetEmployeeByID() {
		EmployeeDAOKryo employeeDAO = new EmployeeDAOKryo();
		Employee employee = new Employee(Role.EMPLOYEE, "name1", "password1");
		
		employeeDAO.createEmployee(employee);
		
		Employee employeeRetrieved = employeeDAO.getEmployeeById(employee.getEmployeeId());
//		System.out.println(taskRetrieved.getTaskId());
//		System.out.println(task.getTaskId());
		assertEquals(employee, employeeRetrieved, "Employees should be equal");
	}
	
	@Test
	void testGetAllEmployees() {
		EmployeeDAOKryo employeeDAO = new EmployeeDAOKryo();
		Employee employee1 = new Employee(Role.EMPLOYEE, "name1", "password1");
		Employee employee2 = new Employee(Role.EMPLOYEE, "name2", "password2");
		
		employeeDAO.createEmployee(employee1);
		employeeDAO.createEmployee(employee2);
		
		List<Employee> employeesRetrieved = employeeDAO.getAllEmployees();
		
		assertTrue(employeesRetrieved.contains(employee1), "Get first project");
		assertTrue(employeesRetrieved.contains(employee2), "Get second project");
	}
	
	@Test
	void testRemoveEmployee() {
		EmployeeDAOKryo employeeDAO = new EmployeeDAOKryo();
		Employee employee1 = new Employee(Role.EMPLOYEE, "nameRemove", "passwordRemove");
		
		employeeDAO.createEmployee(employee1);
		employeeDAO.removeEmployee(employee1);
		
		List<Employee> tasksRetrieved = employeeDAO.getAllEmployees();
		//tasksRetrieved.contains(task1);
		assertFalse(tasksRetrieved.contains(employee1), "Task should not exist in list of all tasks");
	}
	
	@Test
	void testUpdateEmployee() {
		EmployeeDAOKryo employeeDAO = new EmployeeDAOKryo();
		Employee employee1 = new Employee(Role.EMPLOYEE, "nameStart", "passwordStart");
		Employee employee2 = new Employee(Role.MANAGER, "nameEnd", "passwordEnd");
		
		employeeDAO.createEmployee(employee1);
		employeeDAO.updateEmployee(employee1.getEmployeeId(), employee2);
		
		Employee employeeRetrieved = employeeDAO.getEmployeeById(employee1.getEmployeeId());
		//tasksRetrieved.contains(task1);
		assertEquals(employee1.getEmployeeId(), employeeRetrieved.getEmployeeId(), "employeeId should not be changed");
		assertEquals(employee2.getPassword(), employeeRetrieved.getPassword(), "password should be updated");
		assertEquals(employee2.getUsername(), employeeRetrieved.getUsername(), "username should be updated");
		assertEquals(employee2.getRole(), employeeRetrieved.getRole(), "role should be updated");
	}

}
