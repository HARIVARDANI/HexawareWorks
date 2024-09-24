package com.springboot.codingChallenge.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.codingChallenge.Dto.MessageDto;
import com.springboot.codingChallenge.Exception.TaskNotFoundException;
import com.springboot.codingChallenge.Exception.UserIdInvalid;
import com.springboot.codingChallenge.Model.Task;
import com.springboot.codingChallenge.Service.TaskService;


@RestController
@RequestMapping("/task")
public class TaskController {
	
	 @Autowired
	 private TaskService taskService;
	 
	 @Autowired
	 private MessageDto dto;
	 
	 @PostMapping("/addTask/{userId}")
	 public ResponseEntity<?> addTask(@PathVariable int userId ,@RequestBody Task task,MessageDto dto){
		try {
			 Task addtask = taskService.addTask(userId,task);
			 return ResponseEntity.ok(addtask);
		}catch(UserIdInvalid e) {
			dto.setMsg(e.getMessage());
		 return ResponseEntity.badRequest().body(dto);			
		}
	 }
		
		@GetMapping("/getAllTask")
		public ResponseEntity<?> getAllTask(MessageDto dto){
			try {
				List<Task> tasks = taskService.getAllTasks();
				return ResponseEntity.ok(tasks);
			}catch(Exception e) {
				dto.setMsg(e.getMessage());
				 return ResponseEntity.badRequest().body(dto);
			}
		}
		
		@GetMapping("/getTaskById/{taskId}")
		public ResponseEntity<?> getTaskById(@PathVariable int taskId , MessageDto dto) {
		   try {
		          Task task = taskService.getTaskById(taskId);
		          return ResponseEntity.ok(task);
		}catch(TaskNotFoundException e) {
			dto.setMsg(e.getMessage());
			 return ResponseEntity.badRequest().body(dto);
		}
	}
		
	    @PutMapping("/updateTask/{taskId}")
		 public ResponseEntity<?> updateTask(@PathVariable int taskId, @RequestBody Task updatedTask, MessageDto dto) {
		        try {
		            Task task = taskService.updateTask(taskId,updatedTask);
		            return ResponseEntity.ok(task);
		        } catch (TaskNotFoundException e) {
		           dto.setMsg(e.getMessage());
				   return ResponseEntity.badRequest().body(dto);
		    }
	    }
	    
	    @DeleteMapping("/deleteTask/{taskId}")
	    public ResponseEntity<MessageDto> deleteTask(@PathVariable int taskId, MessageDto dto) {
	        try {
	            taskService.deleteTaskById(taskId);
	            return ResponseEntity.ok(dto);
	        }catch (TaskNotFoundException e) {
		           dto.setMsg(e.getMessage());
				   return ResponseEntity.badRequest().body(dto);
		   } 
	 }
		
}
