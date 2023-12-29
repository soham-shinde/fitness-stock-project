package com.fitsta.fitsta.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fitsta.fitsta.Entity.Admin;

public interface AdminRepository extends CrudRepository<Admin, Integer>{
 
    public List<Admin> findByUsernameAndPassword(String username, String password);
}
