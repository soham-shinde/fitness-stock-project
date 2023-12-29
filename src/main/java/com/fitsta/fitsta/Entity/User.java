package com.fitsta.fitsta.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;


@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Date dob;
    private String gender;
    private String contactno;
    private String address;
    private Integer weight;
    private Integer height;
    private String username;
    private String password;

    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    private Trainer trainer;

    @OneToOne(mappedBy = "enrolleduser", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIdentityReference(alwaysAsId = true)
    private PlansPurchase userPlansPurchase;

    @OneToMany(mappedBy = "taskUser", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIdentityReference(alwaysAsId = true)
    private List<Task> tasks = new ArrayList<>();

    @OneToMany(mappedBy = "orderUser", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIdentityReference(alwaysAsId = true)
    private List<Orders> orders = new ArrayList<>();

    
    public User(Integer id, String name, Date dob, String gender, String contactno, String address, Integer weight,
            Integer height, String username, String password, Trainer trainer, PlansPurchase userPlansPurchase,
            List<Task> tasks, List<Orders> orders) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.contactno = contactno;
        this.address = address;
        this.weight = weight;
        this.height = height;
        this.username = username;
        this.password = password;
        this.trainer = trainer;
        this.userPlansPurchase = userPlansPurchase;
        this.tasks = tasks;
        this.orders = orders;
    }

    public User() {
    }

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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
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

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public PlansPurchase getUserPlansPurchase() {
        return userPlansPurchase;
    }

    public void setUserPlansPurchase(PlansPurchase userPlansPurchase) {
        this.userPlansPurchase = userPlansPurchase;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    
    
    
  
    // @ManyToOne
    // private Trainer trainer;

    // @ManyToOne
    // private Plans plan;

    // @OneToOne(mappedBy = "user")
    // private PlansPurchase planpurchse;

    // @OneToMany(mappedBy = "user")
    // private Task task;

    // @OneToMany(mappedBy = "user")
    // private Order order;

}