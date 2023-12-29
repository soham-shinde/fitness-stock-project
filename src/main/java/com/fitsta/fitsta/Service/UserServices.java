package com.fitsta.fitsta.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fitsta.fitsta.DTO.UpdateUserRequest;
import com.fitsta.fitsta.Entity.Orders;
import com.fitsta.fitsta.Entity.PlansPurchase;
import com.fitsta.fitsta.Entity.Task;
import com.fitsta.fitsta.Entity.Trainer;
import com.fitsta.fitsta.Entity.User;
import com.fitsta.fitsta.Repository.OrderRepository;
import com.fitsta.fitsta.Repository.PlansPurchaseRepository;
import com.fitsta.fitsta.Repository.TaskRepository;
import com.fitsta.fitsta.Repository.TrainerRepository;
import com.fitsta.fitsta.Repository.UserRepository;

@Service
public class UserServices {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PlansPurchaseRepository plansPurchaseRepository;

    @Autowired
    private TrainerRepository trainerRepository;


    public String[] login(String username, String pass) {
        List<User> user = this.userRepository.findByUsernameAndPassword(username, pass);
        String[] data = new String[4];
        if (user != null && !user.isEmpty()) {
            data[0]=""+user.get(0).getId();
            data[1]=""+user.get(0).getUsername();
            data[2]="user";
            data[3]="Success";
            return data;
        } else {
            data[0]="";
            data[1]="";
            data[2]="";
            data[3]="failed";
            return data;
        }
    }


    public String createUser(User newUser){
        try {
            return this.userRepository.save(newUser).getId()+"";
        } catch (Exception e) {
            System.out.println("Error while creating user : "+e.getMessage());
            return "Error";
        }
    }

    
    public String updateUser(UpdateUserRequest recUser){
        User existingUser = this.userRepository.findById(recUser.getId()).orElse(null);
        if (existingUser == null) {
            System.out.println("\nError while updating user : User not found!");
            return "Error";
        }

        try {
            existingUser.setId(recUser.getId());
            existingUser.setName(recUser.getName());
            Date purchasedate = new SimpleDateFormat("dd-MM-yyyy").parse(recUser.getDob());
            existingUser.setDob(purchasedate);
            existingUser.setGender(recUser.getGender());
            existingUser.setContactno(recUser.getContactno());
            existingUser.setAddress(recUser.getAddress());
            existingUser.setWeight(recUser.getWeight());
            existingUser.setHeight(recUser.getHeight());
            existingUser.setUsername(recUser.getUsername());
            existingUser.setPassword(recUser.getPassword());
    
            // Updating trainer
            Trainer newTrainer = this.trainerRepository.findById(recUser.getTrainer()).orElse(null);
            existingUser.setTrainer(newTrainer);
    
            // Updating plan purchase
            PlansPurchase newUserPlansPurchase = this.plansPurchaseRepository.findById(recUser.getUserPlansPurchase()).orElse(null);
            existingUser.setUserPlansPurchase(newUserPlansPurchase);
    
            // Updating tasks
            existingUser.getTasks().clear();
            for (Integer taskId : recUser.getTasks()) {
                Task task = this.taskRepository.findById(taskId).orElse(null);
                if (task != null) {
                    existingUser.getTasks().add(task);
                }
            }
    
            // Updating orders
            existingUser.getOrders().clear();
            for (Integer orderId : recUser.getOrders()) {
                Orders order = this.orderRepository.findById(orderId).orElse(null);
                if (order != null) {
                    existingUser.getOrders().add(order);
                }
            }
    
            try {
                return this.userRepository.save(existingUser).getId()+"";
            } catch (Exception e) {
                System.out.println("Error while updating user : "+e.getMessage());
                return "Error";
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return "Error";
        }
    }


    public User getUser(Integer id){
        Optional<User> gotUser =  this.userRepository.findById(id);
        if (gotUser.isPresent()){
            return gotUser.get();
        }else{
            return null;
        }
    }


    public String deleteUser(Integer id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            
            if(user.get().getUserPlansPurchase() != null){
                plansPurchaseRepository.delete(user.get().getUserPlansPurchase());
            }
            
            List<Task> tasks = user.get().getTasks();
            if(!tasks.isEmpty()){
                for (Task task : tasks) {
                    this.taskRepository.delete(task);
                }
            }

            List<Orders> orders = user.get().getOrders();
            if(!orders.isEmpty()){
                for (Orders eachOrder : orders) {
                    this.orderRepository.delete(eachOrder);
                }
            }

            userRepository.delete(user.get());
            return ("Success");
        }else{
            return ("User Not Found!");
        }
    }


    public List<User> listUsers(){
        try {
            List<User> allUsers = (List<User>) this.userRepository.findAll();
            return allUsers;
        }
        catch (Exception e) {return null;}
    }




}
