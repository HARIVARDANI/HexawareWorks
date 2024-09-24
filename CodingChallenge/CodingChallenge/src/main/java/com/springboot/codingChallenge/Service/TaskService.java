package com.springboot.codingChallenge.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.codingChallenge.Exception.TaskNotFoundException;
import com.springboot.codingChallenge.Exception.UserIdInvalid;
import com.springboot.codingChallenge.Model.Task;
import com.springboot.codingChallenge.Model.UserInfo;
import com.springboot.codingChallenge.Repository.TaskRepository;
import com.springboot.codingChallenge.Repository.UserRepository;

@Service
public class TaskService {
	
	  @Autowired
	  private TaskRepository taskRepository;
	  
	  @Autowired
	  private UserRepository userRepository;


	public Task addTask(int userId, Task task) throws UserIdInvalid {
		Optional<UserInfo> optional = userRepository.findById(userId);
		
		if(optional.isEmpty()) {
			throw new UserIdInvalid("user id is invalid");
		}
		 task.setUserInfo(optional.get());
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
	        if (task.isPresent()) {
	            taskRepository.deleteById(taskId);
	        } else {
	            throw new TaskNotFoundException("Task Id is not found");
	        }		
	}
}


