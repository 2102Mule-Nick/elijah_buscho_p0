package com.revature.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.revature.dao.TaskDAOKryo;
import com.revature.pojo.Task;

class TaskDAOKryoTest {

	
	
	@Test
	void testGetTaskByID() {
		TaskDAOKryo taskDAO = new TaskDAOKryo();
		Task task = new Task("Task1", "This is a task.", 3);
		
		taskDAO.createTask(task);
		
		Task taskRetrieved = taskDAO.getTaskById(task.getTaskId());
//		System.out.println(taskRetrieved.getTaskId());
//		System.out.println(task.getTaskId());
		assertEquals(task, taskRetrieved, "Tasks should be equal");
	}
	
	@Test
	void testGetAllTasks() {
		TaskDAOKryo taskDAO = new TaskDAOKryo();
		Task task1 = new Task("Task1", "This is a task.", 2);
		Task task2 = new Task("Task2", "This the second task.", 1);
		
		taskDAO.createTask(task1);
		taskDAO.createTask(task2);
		
		List<Task> tasksRetrieved = taskDAO.getAllTasks();
//		System.out.println("all");
//		System.out.println(tasksRetrieved.get(0));
//		System.out.println(tasksRetrieved.get(1));
		assertTrue(tasksRetrieved.contains(task1), "find first task");
		assertTrue(tasksRetrieved.contains(task2), "find second task");
	}
	
	@Test
	void testRemoveTask() {
		TaskDAOKryo taskDAO = new TaskDAOKryo();
		Task task1 = new Task("RemovedTask", "This is a task that should be removed.", 56);
		
		taskDAO.createTask(task1);
		taskDAO.removeTask(task1);
		
		List<Task> tasksRetrieved = taskDAO.getAllTasks();
		//tasksRetrieved.contains(task1);
		assertFalse(tasksRetrieved.contains(task1), "Task should not exist in list of all tasks");
	}
	
	@Test
	void testUpdateTask() {
		TaskDAOKryo taskDAO = new TaskDAOKryo();
		Task task1 = new Task("StartTask", "This is a task before updating.", 56);
		Task task2 = new Task("EndTask", "This is a task after updating.", 2);
		
		taskDAO.createTask(task1);
		taskDAO.updateTask(task1.getTaskId(), task2);
		
		Task taskRetrieved = taskDAO.getTaskById(task1.getTaskId());
		//tasksRetrieved.contains(task1);
		assertEquals(task1.getTaskId(), taskRetrieved.getTaskId(), "taskId should not be changed");
		assertEquals(task2.getTaskName(), taskRetrieved.getTaskName(), "taskName should be updated");
		assertEquals(task2.getDescription(), taskRetrieved.getDescription(), "description should be updated");
		assertEquals(task2.getEmployeeId(), taskRetrieved.getEmployeeId(), "employeeId should be updated");
	}

}
