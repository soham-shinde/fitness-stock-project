package com.fitsta.fitsta.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitsta.fitsta.Component.Validation;
import com.fitsta.fitsta.DTO.CreateTaskRequest;
import com.fitsta.fitsta.Entity.Task;
import com.fitsta.fitsta.Entity.Trainer;
import com.fitsta.fitsta.Service.TaskServices;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskServices taskServices;

    @Autowired
    private Validation validation;

    @GetMapping("/{id}")
    public ResponseEntity<Task> get(@PathVariable("id") Integer id, @RequestHeader(name = "Token", required = true) String token){

        if(!validation.isValidTask(token)){return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();}

        Task found = this.taskServices.getTask(id);
        if(found != null){
            return ResponseEntity.ok(found);
        }
        else{
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @PostMapping("/create")
    public ResponseEntity<String> createTask(@RequestBody CreateTaskRequest requestTask, @RequestHeader(name = "Token", required = true) String token){

        if(!validation.isValidTask(token)){return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();}

        String result = taskServices.createTask(requestTask);

        if(result.equals("Success")){
            return ResponseEntity.status(HttpStatus.OK).body("{\"Success\":\"Operation successful.\"}");
        }
        else{
            System.out.println("Error while new task creation : " + result);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"Error\":\"Failed to create new task!\"}");
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") Integer id, @RequestHeader(name = "Token", required = true) String token){

        if(!validation.isValidTask(token)){return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();}
        
        String result = this.taskServices.deleteTask(id);

        if(result.equals("Success")){
            return ResponseEntity.status(HttpStatus.OK).body("Task deleted successfully.");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to delete task!");
        }
    }


    @GetMapping("/list")
    public ResponseEntity<List<Task>> listTasks(@RequestHeader(name = "Token", required = true) String token){

        if(!validation.isValidTask(token)){return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();}

        List<Task> tasksList = this.taskServices.listTasks();
        if(tasksList != null){
            return ResponseEntity.ok(tasksList);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @GetMapping("/list/user/{id}")
    public ResponseEntity<List<Task>> listTasksByUser(@PathVariable("id") Integer id, @RequestHeader(name = "Token", required = true) String token){

        if(!validation.isValidTask(token)){return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();}

        List<Task> tasksList = this.taskServices.listTasksByUser(id);
        if(tasksList != null){
            return ResponseEntity.ok(tasksList);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @GetMapping("/list/trainer/{id}")
    public ResponseEntity<List<Task>> listTasksByTrainer(@PathVariable("id") Integer id, @RequestHeader(name = "Token", required = true) String token){

        if(!validation.isValidTask(token)){return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();}

        List<Task> tasksList = this.taskServices.listTasksByTrainer(id);
        if(tasksList != null){
            return ResponseEntity.ok(tasksList);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @GetMapping("/markcomplete/{id}")
    public ResponseEntity<String> markAsComplete(@PathVariable("id") Integer id, @RequestHeader(name = "Token", required = true) String token){

        if(!validation.isValidTask(token)){return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();}

        String result = this.taskServices.markAsComplete(id);

        if(result.equals("Success")){
            return ResponseEntity.status(HttpStatus.OK).body("{\"Success\":\"Operation successful.\"}");
        }
        else{
            System.out.println("Error while updating task completion status : " + result);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"Error\":\"Failed to update task!\"}");
        }
    }

}
