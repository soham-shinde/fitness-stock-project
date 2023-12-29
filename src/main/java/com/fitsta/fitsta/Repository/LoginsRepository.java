package com.fitsta.fitsta.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fitsta.fitsta.Entity.Logins;

public interface LoginsRepository extends CrudRepository<Logins, Integer>{

    List<Logins> findAllByToken(String token);

    List<Logins> findByToken(String token);

}
