package com.fitsta.fitsta.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitsta.fitsta.DTO.CreateTaskRequest;
import com.fitsta.fitsta.Entity.Task;
import com.fitsta.fitsta.Entity.Trainer;
import com.fitsta.fitsta.Entity.User;
import com.fitsta.fitsta.Repository.TaskRepository;
import com.fitsta.fitsta.Repository.TrainerRepository;
import com.fitsta.fitsta.Repository.UserRepository;

@Service
public class TaskServices {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TrainerRepository trainerRepository;


    public Task getTask(Integer id){
        return this.taskRepository.findById(id).orElse(null);
    }


    public String createTask(CreateTaskRequest requestTask){
        try {
            Task newTask = new Task();
            newTask.setId(requestTask.getId());
            newTask.setDuration(requestTask.getDuration());
            newTask.setWorkout(requestTask.getWorkout());
            newTask.setCalories(requestTask.getCalories());
            newTask.setDiet(requestTask.getDiet());
            newTask.setIscompleted(requestTask.getIsCompleted());
    
            Trainer taskTrainer = this.trainerRepository.findById(requestTask.getTaskTrainer()).orElse(null);
            newTask.setTaskTrainer(taskTrainer);
            User taskUser = this.userRepository.findById(requestTask.getTaskUser()).orElse(null);
            newTask.setTaskUser(taskUser);
    
            Date workoutdate = new SimpleDateFormat("dd-MM-yyyy").parse(requestTask.getWorkoutDate());
            newTask.setWorkoutDate(workoutdate);

            this.taskRepository.save(newTask);

            return "Success";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }


    public String deleteTask(Integer id){
        Optional<Task> task = taskRepository.findById(id);
        if(task.isPresent()){
            taskRepository.delete(task.get());
            return ("Success");
        }else{
            return ("Task Not Found!");
        }
    }


    public List<Task> listTasks(){
        try {
            List<Task> allTasks = (List<Task>) this.taskRepository.findAll();
            return allTasks;
        }
        catch (Exception e) {return null;}
    }


    public List<Task> listTasksByUser(Integer id){
        try {
            User user = this.userRepository.findById(id).orElse(null);
            if(user != null){
                List<Task> allTasks = (List<Task>) this.taskRepository.findByTaskUser(user);
                return allTasks;
            }
            else{
                return null;
            }
        }
        catch (Exception e) {return null;}
    }


    public List<Task> listTasksByTrainer(Integer id){
        try {
            Trainer trainer = this.trainerRepository.findById(id).orElse(null);
            if(trainer != null){
                List<Task> allTasks = (List<Task>) this.taskRepository.findByTaskTrainer(trainer);
                return allTasks;
            }else{return null;}
        }
        catch (Exception e) {return null;}
    }


    public String markAsComplete(Integer id){
        Task gotTask = this.taskRepository.findById(id).orElse(null);
        if(gotTask != null){
            gotTask.setIscompleted(true);
            this.taskRepository.save(gotTask);
            return "Success";
        }
        return "Task not found!";
    }
}
