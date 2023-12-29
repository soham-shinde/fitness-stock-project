package com.fitsta.fitsta.DTO;

import java.util.Date;
import java.util.List;

public class UpdateUserRequest {
    private Integer id;
    private String name;
    private String dob;
    private String gender;
    private String contactno;
    private String address;
    private Integer weight;
    private Integer height;
    private String username;
    private String password;

    private Integer trainer;
    private Integer userPlansPurchase;
    private List<Integer> tasks;
    private List<Integer> orders;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDob() {
        return dob;
    }
    public void setDob(String dob) {
        this.dob = dob;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getContactno() {
        return contactno;
    }
    public void setContactno(String contactno) {
        this.contactno = contactno;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Integer getWeight() {
        return weight;
    }
    public void setWeight(Integer weight) {
        this.weight = weight;
    }
    public Integer getHeight() {
        return height;
    }
    public void setHeight(Integer height) {
        this.height = height;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Integer getTrainer() {
        return trainer;
    }
    public void setTrainer(Integer trainer) {
        this.trainer = trainer;
    }
    public Integer getUserPlansPurchase() {
        return userPlansPurchase;
    }
    public void setUserPlansPurchase(Integer userPlansPurchase) {
        this.userPlansPurchase = userPlansPurchase;
    }
    public List<Integer> getTasks() {
        return tasks;
    }
    public void setTasks(List<Integer> tasks) {
        this.tasks = tasks;
    }
    public List<Integer> getOrders() {
        return orders;
    }
    public void setOrders(List<Integer> orders) {
        this.orders = orders;
    }


}
