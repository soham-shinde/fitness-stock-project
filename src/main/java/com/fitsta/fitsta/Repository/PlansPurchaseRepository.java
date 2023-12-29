package com.fitsta.fitsta.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fitsta.fitsta.Entity.PlansPurchase;
import com.fitsta.fitsta.Entity.User;

public interface PlansPurchaseRepository extends CrudRepository<PlansPurchase, Integer>{

    List<PlansPurchase> findByEnrolleduser(User enrolleduser);
    
}
