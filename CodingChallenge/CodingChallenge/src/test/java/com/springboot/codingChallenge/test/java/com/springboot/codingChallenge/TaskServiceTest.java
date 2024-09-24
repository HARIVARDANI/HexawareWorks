package com.springboot.codingChallenge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.codingChallenge.Model.Task;
import com.springboot.codingChallenge.Repository.TaskRepository;
import com.springboot.codingChallenge.Repository.UserRepository;
import com.springboot.codingChallenge.Service.TaskService;
import com.springboot.codingChallenge.enums.Priority;
import com.springboot.codingChallenge.enums.Status;

@SpringBootTest
public class TaskServiceTest {
	
	@InjectMocks
	private TaskService taskService;
	
	@Mock
	private TaskRepository taskRepository;
	
	 @Mock
	private UserRepository userRepository;
	
	 @Test
	  public void getAllTasksTest() {
	  Task t1 = new Task();
	  t1.setId(1);
	  t1.setTitle("Product Development");
      t1.setDescription(" Complete the Product development for given Product");
      t1.setDueDate(LocalDate.of(2024,9,11));
      t1.setPriority(Priority.HIGH);
      t1.setStatus(Status.COMPLETED);
      
      Task t2 = new Task();
      t2.setId(2);
      t2.setTitle("Task 2");
      t2.setDescription("Description 2");
      t2.setDueDate(LocalDate.of(2024,10,1));
      t2.setPriority(Priority.HIGH);
      t2.setStatus(Status.PENDING);
      
      List<Task> list = Arrays.asList(t1,t2);
      when(taskService.getAllTasks()).thenReturn(list);
      
      int expectedNum = 2;
	  int actualNum = taskService.getAllTasks().size();
	  assertEquals(expectedNum,actualNum);
	}

}
