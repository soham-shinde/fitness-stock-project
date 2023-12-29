package com.fitsta.fitsta.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fitsta.fitsta.Entity.Orders;
import com.fitsta.fitsta.Entity.User;

public interface OrderRepository extends CrudRepository<Orders, Integer>{
    List<Orders> findByOrderUser(User orderUser);
}
