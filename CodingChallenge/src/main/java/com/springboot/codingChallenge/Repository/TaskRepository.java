package com.springboot.codingChallenge.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.codingChallenge.Model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer>{

}
