package com.fitsta.fitsta.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitsta.fitsta.DTO.TrainerListRequest;
import com.fitsta.fitsta.Entity.Plans;
import com.fitsta.fitsta.Entity.Trainer;
import com.fitsta.fitsta.Repository.PlansRepository;
import com.fitsta.fitsta.Repository.TrainerRepository;

@Service
public class TrainerServices {

    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private PlansRepository plansRepository;


    public String[] login(String username, String pass){
        Trainer trainer = this.trainerRepository.findByUsernameAndPassword(username, pass);
        String[] data = new String[4];
        if (trainer != null){
            data[0]=""+trainer.getId();
            data[1]=""+trainer.getUsername();
            data[2]="trainer";
            data[3]="Success";
            return data;
        }else{
            data[0]="";
            data[1]="";
            data[2]="";
            data[3]="failed";
            return data;
        }
    }


    public String createTrainer(Trainer newTrainer){
        try {
            this.trainerRepository.save(newTrainer);
            return "Success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }


    public Trainer getTrainer(Integer id){
        Optional<Trainer> gotTrainer =  this.trainerRepository.findById(id);
        if (gotTrainer.isPresent()){
            return gotTrainer.get();
        }else{
            return null;
        }
    }


    public String deleteTrainer(Integer id){

// Check Later
//  Note : Here only deleting plans mapped with trainer, might need to set users and tasks trainer to null in order to not to del them 
        
        Optional<Trainer> trainer = trainerRepository.findById(id);
        if(trainer.isPresent()){
            List<Plans> plans = trainer.get().getPlans();
            for (Plans plan : plans) {
                plansRepository.delete(plan);
            }
            trainerRepository.delete(trainer.get());
            return ("Success");
        }else{
            return ("Trainer Not Found!");
        }
    }


    public List<TrainerListRequest> listTrainers(){
        try {
            List<Trainer> allTrainers = (List<Trainer>) this.trainerRepository.findAll();

            List<TrainerListRequest> trainersList = new ArrayList<>();

            for (Trainer eachTrainer : allTrainers) {
                TrainerListRequest listItem = new TrainerListRequest();

                listItem.setId(eachTrainer.getId());
                listItem.setName(eachTrainer.getName());
                listItem.setDob(eachTrainer.getDob());
                listItem.setGender(eachTrainer.getGender());
                listItem.setContactno(eachTrainer.getContactno());
                listItem.setImage(eachTrainer.getImage());
                listItem.setSpecialization(eachTrainer.getSpecialization());
                listItem.setExperience(eachTrainer.getExperience());
                listItem.setUsername(eachTrainer.getUsername());
                listItem.setPassword(eachTrainer.getPassword());

                trainersList.add(listItem);
            }

            return trainersList;
        }
        catch (Exception e) {return null;}
    }

}
