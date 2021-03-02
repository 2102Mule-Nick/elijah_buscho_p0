package com.revature.dao;

import java.util.List;

import com.revature.pojo.Task;

public interface TaskDAO {

	public void createTask(Task task);
	
	public List<Task> getAllTasks();
	
	public Task getTaskById(int taskId);
	
	public void updateTask(int taskId, Task task);
	
	public void removeTask(Task task);
}
