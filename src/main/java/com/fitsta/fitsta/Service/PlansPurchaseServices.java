package com.fitsta.fitsta.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitsta.fitsta.DTO.CreatePlansPurchaseRequest;
import com.fitsta.fitsta.Entity.Plans;
import com.fitsta.fitsta.Entity.PlansPurchase;
import com.fitsta.fitsta.Entity.User;
import com.fitsta.fitsta.Repository.PlansPurchaseRepository;
import com.fitsta.fitsta.Repository.PlansRepository;
import com.fitsta.fitsta.Repository.UserRepository;

@Service
public class PlansPurchaseServices {

    @Autowired
    private PlansPurchaseRepository plansPurchaseRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PlansRepository plansRepository;


    public String createPlansPurchase(PlansPurchase newPlansPurchase){
        try {
            PlansPurchase savedPP = this.plansPurchaseRepository.save(newPlansPurchase);
            
            User tosaveUser = this.userRepository.findById(newPlansPurchase.getEnrolleduser().getId()).get();
            tosaveUser.setUserPlansPurchase(savedPP);

            tosaveUser.setTrainer(savedPP.getEnrolledplan().getPlanstrainer());
    
            this.userRepository.save(tosaveUser);


            return "Success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }


    public String updatePlansPurchase(CreatePlansPurchaseRequest newPlansPurchaseRequest){
        Optional<PlansPurchase> opPlansPurchase = this.plansPurchaseRepository.findById(newPlansPurchaseRequest.getId());
        if(opPlansPurchase.isPresent()){
            try {
                PlansPurchase existingPlansPurchase = opPlansPurchase.get();
                Date purchasedate;
                purchasedate = new SimpleDateFormat("dd/MM/yy").parse(newPlansPurchaseRequest.getPurchasedate());
                existingPlansPurchase.setPurchasedate(purchasedate);
                Date expiryDate = new SimpleDateFormat("dd/MM/yy").parse(newPlansPurchaseRequest.getExpirydate());
                existingPlansPurchase.setExpirydate(expiryDate);
                existingPlansPurchase.setEnrolleduser(this.userRepository.findById(newPlansPurchaseRequest.getEnrolleduserId()).get());
                existingPlansPurchase.setEnrolledplan(this.plansRepository.findById(newPlansPurchaseRequest.getEnrolledplanId()).get());
                this.plansPurchaseRepository.save(existingPlansPurchase);
                return "Success";
            }
            catch (ParseException e) {return e.getMessage();}
        }
        else{
            return "PlansPurchase with that id not found!";
        }
    }


    public PlansPurchase getPlansPurchase(Integer id){
        Optional<PlansPurchase> gotPlansPurchase =  this.plansPurchaseRepository.findById(id);
        if (gotPlansPurchase.isPresent()){
            return gotPlansPurchase.get();
        }else{
            return null;
        }
    }


    public List<PlansPurchase> getPlansPurchaseByUser(Integer id){
        User user = this.userRepository.findById(id).get();
        List<PlansPurchase> gotPlansPurchase =  this.plansPurchaseRepository.findByEnrolleduser(user);
        if (!gotPlansPurchase.isEmpty()){
            return gotPlansPurchase;
        }else{
            return null;
        }
    }


    public String deletePlansPurchase(Integer id){
        Optional<PlansPurchase> plansPurchase = this.plansPurchaseRepository.findById(id);
        if(plansPurchase.isPresent()){
            User user = plansPurchase.get().getEnrolleduser();
            if (user != null) {
                user.setUserPlansPurchase(null);
                userRepository.save(user);
            }
            Plans plans = plansPurchase.get().getEnrolledplan();
            if (plans != null) {
                plans.getPlansPurchasedplans().remove(plansPurchase.get());
                plansRepository.save(plans);
            }
            plansPurchaseRepository.delete(plansPurchase.get());
            this.plansPurchaseRepository.delete(plansPurchase.get());
            return ("Success");
        }else{
            return ("PlansPurchase Not Found!");
        }
    }


    public List<PlansPurchase> listPlansPurchases(){
        try {
            List<PlansPurchase> allPlansPurchases = (List<PlansPurchase>) this.plansPurchaseRepository.findAll();
            return allPlansPurchases;
        }
        catch (Exception e) {return null;}
    }


}
