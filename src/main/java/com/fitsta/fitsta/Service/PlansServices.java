package com.fitsta.fitsta.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitsta.fitsta.DTO.UpdatePlanRequest;
import com.fitsta.fitsta.Entity.Plans;
import com.fitsta.fitsta.Entity.PlansPurchase;
import com.fitsta.fitsta.Entity.Trainer;
import com.fitsta.fitsta.Repository.PlansPurchaseRepository;
import com.fitsta.fitsta.Repository.PlansRepository;
import com.fitsta.fitsta.Repository.TrainerRepository;

@Service
public class PlansServices {
    
    @Autowired
    private PlansRepository plansRepository;

    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private PlansPurchaseRepository plansPurchaseRepository;


    public String createPlan(Plans newPlan){
        try {
            this.plansRepository.save(newPlan);
            return "Success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }


    public String updatePlan(UpdatePlanRequest recPlan){

        Optional<Plans> optionalPlan = plansRepository.findById(recPlan.getId());
        if (!optionalPlan.isPresent()) {return "Plan not found";}

        Plans existingPlan = optionalPlan.get();
        existingPlan.setName(recPlan.getName());
        existingPlan.setType(recPlan.getType());
        existingPlan.setFeatures(recPlan.getFeatures());
        existingPlan.setPrice(recPlan.getPrice());
        existingPlan.setDuration(recPlan.getDuration());

        Trainer trainer = this.trainerRepository.findById(recPlan.getPlanstrainer()).orElse(null);
        if (trainer!=null){existingPlan.setPlanstrainer(trainer);}


        List<PlansPurchase> purchasedPlans = new ArrayList<>();
        for (Integer planPurchaseId : recPlan.getPlansPurchasedplans()) {
            Optional<PlansPurchase> optionalPurchase = plansPurchaseRepository.findById(planPurchaseId);
            optionalPurchase.ifPresent(purchasedPlans::add);
        }
        if(!purchasedPlans.isEmpty()){
            existingPlan.getPlansPurchasedplans().clear();
            existingPlan.getPlansPurchasedplans().addAll(purchasedPlans);
            // existingPlan.setPlansPurchasedplans(purchasedPlans);
        }

        try {
            this.plansRepository.save(existingPlan);
            return "Success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }


    public Plans getPlan(Integer id){
        Optional<Plans> gotPlans =  this.plansRepository.findById(id);
        if (gotPlans.isPresent()){
            return gotPlans.get();
        }else{
            return null;
        }
    }


    public String deletePlans(Integer id) {

        Optional<Plans> foundPlan = this.plansRepository.findById(id);
    
        if (foundPlan.isPresent()) {
            List<PlansPurchase> allPurchases = foundPlan.get().getPlansPurchasedplans();
    
            for (PlansPurchase eachPlansPurchase : allPurchases) {
                eachPlansPurchase.setEnrolledplan(null); // Remove the reference to the plan
                this.plansPurchaseRepository.delete(eachPlansPurchase);
            }
    
            plansRepository.delete(foundPlan.get());
            return ("Success");
        } else {
            return ("Plan Not Found!");
        }
    }
    

    public List<Plans> listPlans(){
        try {
            return (List<Plans>) this.plansRepository.findAll();
        }
        catch (Exception e) {return null;}
    }


}
