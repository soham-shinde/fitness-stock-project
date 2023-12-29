package com.fitsta.fitsta.Component;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fitsta.fitsta.Entity.Logins;
import com.fitsta.fitsta.Repository.LoginsRepository;

@Component
public class Validation {
    
    @Autowired
    private LoginsRepository loginsRepository;

    public boolean isValidProduct(String token){
        if (token != null) {
            List<Logins> foundLogin = this.loginsRepository.findAllByToken(token);
            if(foundLogin.size() != 1){
                System.out.println("\n Authentication Error : Empty login!");
                return false;
            }
            else{
                if(foundLogin.get(0).getType().equals("user") || foundLogin.get(0).getType().equals("trainer")|| foundLogin.get(0).getType().equals("admin")){
                    return true;
                }
                else{
                    System.out.println("\n Authentication Error : Invalid user login!");
                    return false;
                }
            }
        } else {
            System.out.println("\nAuthentication Error : Token not received!");
            return false;
        }
    }

    public boolean isValidTrainer(String token){
        if (token != null) {
            List<Logins> foundLogin = this.loginsRepository.findAllByToken(token);
            if(foundLogin.size() != 1){
                System.out.println("\n Authentication Error : Empty login!");
                return false;
            }
            else{
                if(foundLogin.get(0).getType().equals("user") || foundLogin.get(0).getType().equals("admin") || foundLogin.get(0).getType().equals("trainer")){
                    return true;
                }
                else{
                    System.out.println("\n Authentication Error : Invalid user login!");
                    return false;
                }
            }
        } else {
            System.out.println("\nAuthentication Error : Token not received!");
            return false;
        }
    }

    public boolean isValidUser(String token){
        if (token != null) {
            List<Logins> foundLogin = this.loginsRepository.findAllByToken(token);
            if(foundLogin.size() != 1){
                System.out.println("\n Authentication Error : Empty login!");
                return false;
            }
            else{
                if(foundLogin.get(0).getType().equals("user") || foundLogin.get(0).getType().equals("admin") || foundLogin.get(0).getType().equals("trainer")){
                    return true;
                }
                else{
                    System.out.println("\n Authentication Error : Invalid user login!");
                    return false;
                }
            }
        } else {
            System.out.println("\nAuthentication Error : Token not received!");
            return false;
        }
    }

    public boolean isValidPlans(String token){
        if (token != null) {
            List<Logins> foundLogin = this.loginsRepository.findAllByToken(token);
            if(foundLogin.size() != 1){
                System.out.println("\n Authentication Error : Empty login!");
                return false;
            }
            else{
                if(foundLogin.get(0).getType().equals("user") || foundLogin.get(0).getType().equals("admin") || foundLogin.get(0).getType().equals("trainer")){
                    return true;
                }
                else{
                    System.out.println("\n Authentication Error : Invalid user login!");
                    return false;
                }
            }
        } else {
            System.out.println("\nAuthentication Error : Token not received!");
            return false;
        }
    }

    public boolean isValidPlansPurchase(String token){
        if (token != null) {
            List<Logins> foundLogin = this.loginsRepository.findAllByToken(token);
            if(foundLogin.size() != 1){
                System.out.println("\n Authentication Error : Empty login!");
                return false;
            }
            else{
                if(foundLogin.get(0).getType().equals("user") || foundLogin.get(0).getType().equals("trainer")|| foundLogin.get(0).getType().equals("admin")){
                    return true;
                }
                else{
                    System.out.println("\n Authentication Error : Invalid user login!");
                    return false;
                }
            }
        } else {
            System.out.println("\nAuthentication Error : Token not received!");
            return false;
        }
    }

    public boolean isValidTask(String token){
        if (token != null) {
            List<Logins> foundLogin = this.loginsRepository.findAllByToken(token);
            if(foundLogin.size() != 1){
                System.out.println("\n Authentication Error : Empty login!");
                return false;
            }
            else{
                if(foundLogin.get(0).getType().equals("user") || foundLogin.get(0).getType().equals("trainer")|| foundLogin.get(0).getType().equals("admin")){
                    return true;
                }
                else{
                    System.out.println("\n Authentication Error : Invalid user login!");
                    return false;
                }
            }
        } else {
            System.out.println("\nAuthentication Error : Token not received!");
            return false;
        }
    }

    public boolean isValidOrder(String token){
        if (token != null) {

            List<Logins> foundLogin = this.loginsRepository.findByToken(token);
            
            System.out.println("Token found : "+foundLogin.get(0).getToken());
            System.out.println("Token found : "+foundLogin.get(0).getType());
            System.out.println("Token found : "+foundLogin.get(0).getType().equals("user"));

            if(foundLogin.size() != 1){
                System.out.println("\n Authentication Error : Empty login!");
                return false;
            }
            else{
                if(foundLogin.get(0).getType().equals("user") || foundLogin.get(0).getType().equals("trainer")|| foundLogin.get(0).getType().equals("admin")){
                    return true;
                }
                else{
                    System.out.println("\n Authentication Error : Invalid user login!");
                    return false;
                }
            }
        } else {
            System.out.println("\nAuthentication Error : Token not received!");
            return false;
        }
    }


}
