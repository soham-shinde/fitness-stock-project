package com.fitsta.fitsta.Repository;

import org.springframework.data.repository.CrudRepository;

import com.fitsta.fitsta.Entity.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    
}
