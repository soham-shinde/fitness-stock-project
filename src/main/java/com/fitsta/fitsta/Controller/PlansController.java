package com.fitsta.fitsta.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitsta.fitsta.Component.Validation;
import com.fitsta.fitsta.DTO.CreatePlanRequest;
import com.fitsta.fitsta.DTO.UpdatePlanRequest;
import com.fitsta.fitsta.Entity.Plans;
import com.fitsta.fitsta.Entity.Trainer;
import com.fitsta.fitsta.Repository.TrainerRepository;
import com.fitsta.fitsta.Service.PlansServices;

@RestController
@RequestMapping("/api/plans")
public class PlansController {

    @Autowired
    private PlansServices plansServices;

    @Autowired
    private TrainerRepository trainerRepository;
    @Autowired
    private Validation validation;


    @PostMapping("/create")
    public ResponseEntity<String> createPlan(@RequestBody CreatePlanRequest recPlan, @RequestHeader(name = "Token", required = true) String token){

        if(!validation.isValidPlans(token)){return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();}

        Trainer tempTrainer = this.trainerRepository.findById(recPlan.getPlanstrainer()).get();
        Plans newPlans = new Plans(recPlan.getId(), recPlan.getName(), recPlan.getType(), recPlan.getFeatures(), recPlan.getPrice(), recPlan.getDuration(),  tempTrainer, null);

        String result = this.plansServices.createPlan(newPlans);

        if(result.equals("Success")){
            return ResponseEntity.status(HttpStatus.OK).body("{\"Success\":\"Operation successful.\"}");
        }
        else{
            System.out.println("Error while new plan creation : " + result);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"Error\":\"Failed to create new plan!\"}");
        }
    }


    @PutMapping("/update")
    public ResponseEntity<String> updatePlan(@RequestBody UpdatePlanRequest recPlan, @RequestHeader(name = "Token", required = true) String token){

        if(!validation.isValidPlans(token)){return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();}

        String result = this.plansServices.updatePlan(recPlan);

        if(result.equals("Success")){
            return ResponseEntity.status(HttpStatus.OK).body("{\"Success\":\"Operation successful.\"}");
        }
        else{
            System.out.println("Error while updating plan : " + result);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"Error\":\"Failed to update plan!\"}");
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Plans> getPlan(@PathVariable("id") Integer id) {

        Plans gotPlans = this.plansServices.getPlan(id);
        if (gotPlans != null){
            return ResponseEntity.ok(gotPlans);
        }
        else{
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
 

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlans(@PathVariable("id") Integer id, @RequestHeader(name = "Token", required = true) String token){

        if(!validation.isValidPlans(token)){return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();}
        
        String result = this.plansServices.deletePlans(id);

        if(result.equals("Success")){
            return ResponseEntity.status(HttpStatus.OK).body("Plans deleted successfully.");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to delete plans!");
        }
    }


    @GetMapping("/list")
    public ResponseEntity<List<Plans>> listPlanss(){

        List<Plans> plansList = this.plansServices.listPlans();
        if(plansList != null){
            return ResponseEntity.ok(plansList);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
