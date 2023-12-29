package com.fitsta.fitsta.DTO;

import java.time.LocalDateTime;

public class CreateTaskRequest {

    private Integer id;
    private Integer taskTrainer;
    private Integer taskUser;
    private String duration;
    private String workoutDate;
    private String workout;
    private String calories;
    private Boolean isCompleted;
    private String diet;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getTaskTrainer() {
        return taskTrainer;
    }
    public void setTaskTrainer(Integer taskTrainer) {
        this.taskTrainer = taskTrainer;
    }
    public Integer getTaskUser() {
        return taskUser;
    }
    public void setTaskUser(Integer taskUser) {
        this.taskUser = taskUser;
    }
    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }
    public String getWorkoutDate() {
        return workoutDate;
    }
    public void setWorkoutDate(String workoutDate) {
        this.workoutDate = workoutDate;
    }
    public String getWorkout() {
        return workout;
    }
    public void setWorkout(String workout) {
        this.workout = workout;
    }
    public String getCalories() {
        return calories;
    }
    public void setCalories(String calories) {
        this.calories = calories;
    }
    public Boolean getIsCompleted() {
        return isCompleted;
    }
    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
    public String getDiet() {
        return diet;
    }
    public void setDiet(String diet) {
        this.diet = diet;
    }


}
