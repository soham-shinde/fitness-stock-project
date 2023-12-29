package com.fitsta.fitsta.DTO;

import java.util.List;

public class UpdateProductRequest {
    private Integer id;
    private List<Integer> orders;
    private String name;
    private String description;
    private String productPrice;


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public List<Integer> getOrders() {
        return orders;
    }
    public void setOrders(List<Integer> orders) {
        this.orders = orders;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getProductPrice() {
        return productPrice;
    }
    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

}
