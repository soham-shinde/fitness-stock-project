package com.fitsta.fitsta.Controller;

import java.text.ParseException;
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
import com.fitsta.fitsta.DTO.CreateUserRequest;
import com.fitsta.fitsta.DTO.UpdateUserRequest;
import com.fitsta.fitsta.Entity.User;
import com.fitsta.fitsta.Service.UserServices;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserServices userServices;

    @Autowired
    private Validation validation;

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody CreateUserRequest recUser){

        try {
            System.out.println(recUser.toString());
            User newUser = new User();
            newUser.setId(recUser.getId());
            newUser.setName(recUser.getName());
            Date purchasedate = new SimpleDateFormat("dd-MM-yyyy").parse(recUser.getDob());
            newUser.setDob(purchasedate);
            newUser.setGender(recUser.getGender());
            newUser.setContactno(recUser.getContactno());
            newUser.setAddress(recUser.getAddress());
            newUser.setWeight(recUser.getWeight());
            newUser.setHeight(recUser.getHeight());
            newUser.setUsername(recUser.getUsername());
            newUser.setPassword(recUser.getPassword());
    
            String result = userServices.createUser(newUser);
    
            if(!result.equals("Error")){
                return ResponseEntity.ok().body("{\"message\" : \"Operation successful\", \"id\": " + result + "}");
            }
            else{
                System.out.println("Error while new user creation : " + result);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"Error\":\"Failed to create new user!\"}");
            }
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Error while new user creation : Invalid DOB");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"Error\":\"Invalid DOB!\"}");
        }
    }


    @PostMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody UpdateUserRequest recUser){

        String result = userServices.updateUser(recUser);

        if(!result.equals("Error")){
            return ResponseEntity.ok().body("{\"message\" : \"Operation successful\", \"id\": " + result + "}");
        }
        else{
            System.out.println("Error while updating user : " + result);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"Error\":\"Failed to update user!\"}");
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Integer id, @RequestHeader(name = "Token", required = true) String token) {

        if(!validation.isValidUser(token)){return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();}

        User gotUser = this.userServices.getUser(id);
        if (gotUser != null){
            return ResponseEntity.ok(gotUser);
        }
        else{
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
 

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Integer id, @RequestHeader(name = "Token", required = true) String token){

        if(!validation.isValidProduct(token)){return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();}
        
        String result = this.userServices.deleteUser(id);

        if(result.equals("Success")){
            return ResponseEntity.status(HttpStatus.OK).body("User and associated Purchase Plans, Tasks & Orders deleted successfully.");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to delete user and associated Purchase Plans, Tasks & Orders!");
        }
    }


    @GetMapping("/list")
    public ResponseEntity<List<User>> listUsers(@RequestHeader(name = "Token", required = true) String token){

        if(!validation.isValidProduct(token)){return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();}

        List<User> usersList = this.userServices.listUsers();
        if(usersList != null){
            return ResponseEntity.ok(usersList);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
