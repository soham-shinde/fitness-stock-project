package com.fitsta.fitsta.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fitsta.fitsta.Entity.Trainer;

public interface TrainerRepository extends CrudRepository<Trainer, Integer>{

    Trainer findByUsernameAndPassword(String username, String password);
}
