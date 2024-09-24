package com.springboot.codingChallenge.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.codingChallenge.Exception.TaskNotFoundException;
import com.springboot.codingChallenge.Model.Task;
import com.springboot.codingChallenge.Repository.TaskRepository;

@Service
public class TaskService {
	
	  @Autowired
	  private TaskRepository taskRepository;
	  
	  
	  public Task addTask(Task task) {
			return taskRepository.save(task);
		}	
	  
	public List<Task> getAllTasks() {
		return taskRepository.findAll();
	}


	public Task getTaskById(int taskId) throws TaskNotFoundException {
	  Optional<Task> taskOptional = taskRepository.findById(taskId);	  
	  if (taskOptional.isEmpty()) {
          throw new TaskNotFoundException("Task ID is not found");
      }
	  return taskOptional.get();
	}


	public Task updateTask(int taskId, Task updatedTask) throws TaskNotFoundException {
	  Optional<Task> taskOptional = taskRepository.findById(taskId);
	  if (taskOptional.isEmpty()) {
          throw new TaskNotFoundException("Task Id isnot found");
	  }
	  Task existingTask = taskOptional.get();	  
	  existingTask.setTitle(updatedTask.getTitle());
      existingTask.setDescription(updatedTask.getDescription());
      existingTask.setDueDate(updatedTask.getDueDate());
      existingTask.setPriority(updatedTask.getPriority());
      existingTask.setStatus(updatedTask.getStatus());
      
      return taskRepository.save(existingTask);
      
      }


	public void deleteTaskById(int taskId) throws TaskNotFoundException {
		Optional<Task> task = taskRepository.findById(taskId);
	        if (task.isEmpty()) {
	        	throw new TaskNotFoundException("Task Id is not found");	            
	        } 
	          taskRepository.deleteById(taskId);  
	        }
	}



