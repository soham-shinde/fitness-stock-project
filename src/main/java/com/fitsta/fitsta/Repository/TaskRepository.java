package com.fitsta.fitsta.Repository;

import org.springframework.data.repository.CrudRepository;

import com.fitsta.fitsta.Entity.Task;
import com.fitsta.fitsta.Entity.Trainer;
import com.fitsta.fitsta.Entity.User;

import java.util.List;


public interface TaskRepository extends CrudRepository<Task, Integer>{
    
    List<Task> findByTaskUser(User taskUser);

    List<Task> findByTaskTrainer(Trainer taskTrainer);
}
