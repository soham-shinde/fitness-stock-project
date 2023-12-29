package com.fitsta.fitsta.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fitsta.fitsta.Entity.User;

public interface UserRepository extends CrudRepository<User, Integer>{

    public List<User> findByUsernameAndPassword(String username, String password);

}
