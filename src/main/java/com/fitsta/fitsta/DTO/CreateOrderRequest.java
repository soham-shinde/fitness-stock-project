package com.fitsta.fitsta.DTO;

public class CreateOrderRequest {

    private Integer Id;
    private String OrderDate;
    private Integer TotalAmount;
    private Integer orderUser;
    private Integer orderProduct;


    public Integer getId() {
        return Id;
    }
    public void setId(Integer id) {
        Id = id;
    }
    public String getOrderDate() {
        return OrderDate;
    }
    public void setOrderDate(String orderDate) {
        OrderDate = orderDate;
    }
    public Integer getTotalAmount() {
        return TotalAmount;
    }
    public void setTotalAmount(Integer totalAmount) {
        TotalAmount = totalAmount;
    }
    public Integer getOrderUser() {
        return orderUser;
    }
    public void setOrderUser(Integer orderUser) {
        this.orderUser = orderUser;
    }
    public Integer getOrderProduct() {
        return orderProduct;
    }
    public void setOrderProduct(Integer orderProduct) {
        this.orderProduct = orderProduct;
    }


}
