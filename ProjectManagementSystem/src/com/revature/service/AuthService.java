package com.revature.service;

import com.revature.pojo.Employee;

public interface AuthService {

	/**
	 * Takes in an Employee, and registers is with the EmployeeDAO.
	 * Returns true is registration successful, returns false otherwise.
	 * @param employee
	 * @return
	 */
	public boolean register(Employee employee);
	
	/**
	 * Takes in an Employee and test if it's credentials are valid.
	 * Returns the employee if the credentials are valid, returns null otherwise.
	 * @param employee
	 * @return
	 */
	public Employee authenticate(Employee employee);
	
	
}
