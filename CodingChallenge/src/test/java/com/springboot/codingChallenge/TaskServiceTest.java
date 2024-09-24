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
	  t1.setTitle("Product Testing");
      t1.setDescription(" Complete the Product testing for given Product");
      t1.setDueDate(LocalDate.of(2024,11,11));
      t1.setPriority(Priority.HIGH);
      t1.setStatus(Status.PENDING);
      
      Task t2 = new Task();
      t2.setId(2);
      t2.setTitle("Product Development");
      t2.setDescription(" Complete the Product Development for given Product ");
      t2.setDueDate(LocalDate.of(2024,10,1));
      t2.setPriority(Priority.HIGH);
      t2.setStatus(Status.COMPLETED);
            
      Task t3 = new Task();
      t3.setId(102);
      t3.setTitle("Market analysis ");
      t3.setDescription("  Generate the statistice of market");
      t3.setDueDate(LocalDate.of(2024,7,12));
      t3.setPriority(Priority.HIGH);
      t3.setStatus(Status.COMPLETED);
                  
      List<Task> list = Arrays.asList(t1,t2,t3);
      when(taskService.getAllTasks()).thenReturn(list);
      
      int expectedNum = 3;
	  int actualNum = taskService.getAllTasks().size();
	  assertEquals(expectedNum,actualNum);
	}

}
