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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitsta.fitsta.Component.Validation;
import com.fitsta.fitsta.DTO.CreatePlansPurchaseRequest;
import com.fitsta.fitsta.Entity.PlansPurchase;
import com.fitsta.fitsta.Repository.PlansRepository;
import com.fitsta.fitsta.Repository.UserRepository;
import com.fitsta.fitsta.Service.PlansPurchaseServices;

@RestController
@RequestMapping("/api/planspurchase")
public class PlansPurchaseController {

    @Autowired
    private PlansPurchaseServices plansPurchaseServices;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlansRepository plansRepository;

    @Autowired
    private Validation validation;


    @GetMapping("/{id}")
    public ResponseEntity<PlansPurchase> getPlansPurchase(@PathVariable("id") Integer id, @RequestHeader(name = "Token", required = true) String token){
        
        if(!validation.isValidPlansPurchase(token)){return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();}
        
        PlansPurchase gotPlansPurchase = this.plansPurchaseServices.getPlansPurchase(id);
        if (gotPlansPurchase != null){return ResponseEntity.ok(gotPlansPurchase);}
        else{return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();}
    }


    @PostMapping("/create")
    public ResponseEntity<String> createPlansPurchase(@RequestBody CreatePlansPurchaseRequest newPlansPurchaseRequest){

        try {
            PlansPurchase newPlansPurchase = new PlansPurchase();
            newPlansPurchase.setId(newPlansPurchaseRequest.getId());
            Date purchasedate = new SimpleDateFormat("dd/MM/yy").parse(newPlansPurchaseRequest.getPurchasedate());
            newPlansPurchase.setPurchasedate(purchasedate);
            Date expiryDate = new SimpleDateFormat("dd/MM/yy").parse(newPlansPurchaseRequest.getExpirydate());
            newPlansPurchase.setExpirydate(expiryDate);
            newPlansPurchase.setEnrolleduser(this.userRepository.findById(newPlansPurchaseRequest.getEnrolleduserId()).get());
            newPlansPurchase.setEnrolledplan(this.plansRepository.findById(newPlansPurchaseRequest.getEnrolledplanId()).get());
            String result = this.plansPurchaseServices.createPlansPurchase(newPlansPurchase);
            if(result.equals("Success")){
                return ResponseEntity.status(HttpStatus.OK).body("{\"Success\":\"Operation successful.\"}");
            }
            else{
                System.out.println("Error while new Plans Purchase creation : " + result);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"Error\":\"Failed to create new Plans Purchase!\"}");
            }
        }
        catch (ParseException e) {
            System.out.println("Error while new Plans Purchase creation : ");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"Error\":\"Failed to create new Plans Purchase!\"}");
        }
    }


    @PutMapping("/update")
    public ResponseEntity<String> updatePlansPurchase(@RequestBody CreatePlansPurchaseRequest newPlansPurchaseRequest, @RequestHeader(name = "Token", required = true) String token){

        if(!validation.isValidPlansPurchase(token)){return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();}

        String result = this.plansPurchaseServices.updatePlansPurchase(newPlansPurchaseRequest);
        if(result.equals("Success")){
            return ResponseEntity.status(HttpStatus.OK).body("{\"Success\":\"Operation successful.\"}");
        }
        else{
            System.out.println("Error while new Plans Purchase updation : " + result);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"Error\":\"Failed to update new Plans Purchase!\"}");
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlansPurchase(@PathVariable("id") Integer id, @RequestHeader(name = "Token", required = true) String token){

        if(!validation.isValidPlansPurchase(token)){return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();}
        
        String result = this.plansPurchaseServices.deletePlansPurchase(id);

        if(result.equals("Success")){
            return ResponseEntity.status(HttpStatus.OK).body("PlansPurchase deleted successfully.");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to delete PlansPurchase!");
        }
    }


    @GetMapping("/list")
    public ResponseEntity<List<PlansPurchase>> listPlansPurchases(@RequestHeader(name = "Token", required = true) String token){

        if(!validation.isValidPlansPurchase(token)){return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();}

        List<PlansPurchase> plansPurchasesList = this.plansPurchaseServices.listPlansPurchases();
        if(plansPurchasesList != null){return ResponseEntity.ok(plansPurchasesList);}
        else{return ResponseEntity.status(HttpStatus.NOT_FOUND).build();}
    }



}
