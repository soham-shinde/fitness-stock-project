package com.fitsta.fitsta.Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fitsta.fitsta.Component.Validation;
import com.fitsta.fitsta.DTO.TrainerListRequest;
import com.fitsta.fitsta.Entity.Trainer;
import com.fitsta.fitsta.Service.TrainerServices;

@RestController
@RequestMapping("/api/trainer")
public class TrainerController {
    
    @Autowired
    private TrainerServices trainerServices;

    @Autowired
    private Validation validation;

    @GetMapping("/{id}")
    public ResponseEntity<Trainer> getTrainer(@PathVariable("id") Integer id, @RequestHeader(name = "Token", required = true) String token) {

        if(!validation.isValidProduct(token)){return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();}

        Trainer gotTrainer = this.trainerServices.getTrainer(id);
        if (gotTrainer != null){
            return ResponseEntity.ok(gotTrainer);
        }
        else{
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
 

    @PostMapping("/create")
    public ResponseEntity<String> createTrainer(
        @RequestParam(value = "id") Integer id,
        @RequestParam(value = "name", required = true) String name,
        @RequestParam(value = "dob") String dob,
        @RequestParam(value = "gender") String gender,
        @RequestParam(value = "contactno") String contactno,
        @RequestParam(name = "image") MultipartFile img,
        @RequestParam(value = "specialization") String specialization,
        @RequestParam(value = "experience") String experience,
        @RequestParam(value = "username", required = true) String username,
        @RequestParam(value = "password", required = true) String password,

        @RequestHeader(name = "Token", required = true) String token
    ){

        if(!validation.isValidProduct(token)){return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();}

        Trainer newTrainer = new Trainer();

        newTrainer.setId(id);
        newTrainer.setName(name);
        try {
            newTrainer.setDob(new SimpleDateFormat("yy-dd-MM").parse(dob));
        } catch (ParseException e) {e.printStackTrace();}

        newTrainer.setGender(gender);
        newTrainer.setContactno(contactno);
        newTrainer.setSpecialization(specialization);
        newTrainer.setExperience(experience);
        newTrainer.setUsername(username);
        newTrainer.setPassword(password);

        if(img.getSize() == 0 && id!=0){
            newTrainer.setImage(this.trainerServices.getTrainer(id).getImage());
        }
        else{
            String Path = "";
            String currTime = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());

            try {
                Path = new ClassPathResource("static/images/trainer/").getFile().getAbsolutePath();
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Path not found!");
            }
            if (img != null) {
                try {
                    Files.copy(img.getInputStream(), Paths.get(Path + File.separator + currTime + "_" +  img.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    e.printStackTrace();
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image!");
                }
                newTrainer.setImage(ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/trainer/"+ currTime +"_" + img.getOriginalFilename()).toUriString());
            } else {
                newTrainer.setImage("");
            }
        }


        String result = trainerServices.createTrainer(newTrainer);

        if(result.equals("Success")){
            return ResponseEntity.status(HttpStatus.OK).body("{\"Success\":\"Operation successful.\"}");
        }
        else{
            System.out.println("Error while new trainer creation : " + result);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"Error\":\"Failed to create new trainer!\"}");
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTrainer(@PathVariable("id") Integer id, @RequestHeader(name = "Token", required = true) String token){

        if(!validation.isValidTrainer(token)){return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();}

        String result = this.trainerServices.deleteTrainer(id);

        if(result.equals("Success")){
            return ResponseEntity.status(HttpStatus.OK).body("Trainer and associated plans deleted successfully.");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to delete trainer and associated plans!");
        }
    }


    @GetMapping("/list")
    public ResponseEntity<List<TrainerListRequest>> listTrainers(@RequestHeader(name = "Token", required = true) String token){

        if(!validation.isValidTrainer(token)){return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();}

        List<TrainerListRequest> trainersList = this.trainerServices.listTrainers();
        if(trainersList != null){
            return ResponseEntity.ok(trainersList);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
