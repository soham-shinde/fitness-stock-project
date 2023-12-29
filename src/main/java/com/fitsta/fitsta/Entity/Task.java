package com.fitsta.fitsta.Entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date WorkoutDate;
    private String Workout;
    private String Diet;
    private String Duration;
    private String Calories;
    private Boolean Iscompleted;

    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    private Trainer taskTrainer;

    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    private User taskUser;


    public Task(Integer id, Date workoutDate, String workout, String diet, String duration, String calories,
            Boolean iscompleted, Trainer taskTrainer, User taskUser) {
        this.id = id;
        WorkoutDate = workoutDate;
        Workout = workout;
        Diet = diet;
        Duration = duration;
        Calories = calories;
        Iscompleted = iscompleted;
        this.taskTrainer = taskTrainer;
        this.taskUser = taskUser;
    }

    public Task() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getWorkoutDate() {
        return WorkoutDate;
    }

    public void setWorkoutDate(Date workoutDate) {
        WorkoutDate = workoutDate;
    }

    public String getWorkout() {
        return Workout;
    }

    public void setWorkout(String workout) {
        Workout = workout;
    }

    public String getDiet() {
        return Diet;
    }

    public void setDiet(String diet) {
        Diet = diet;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public String getCalories() {
        return Calories;
    }

    public void setCalories(String calories) {
        Calories = calories;
    }

    public Boolean getIscompleted() {
        return Iscompleted;
    }

    public void setIscompleted(Boolean iscompleted) {
        Iscompleted = iscompleted;
    }

    public Trainer getTaskTrainer() {
        return taskTrainer;
    }

    public void setTaskTrainer(Trainer taskTrainer) {
        this.taskTrainer = taskTrainer;
    }

    public User getTaskUser() {
        return taskUser;
    }

    public void setTaskUser(User taskUser) {
        this.taskUser = taskUser;
    }



}
