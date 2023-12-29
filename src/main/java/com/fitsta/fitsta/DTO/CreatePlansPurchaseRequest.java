package com.fitsta.fitsta.DTO;

public class CreatePlansPurchaseRequest {

    private Integer Id;
    private String Purchasedate;
    private String Expirydate;

    private Integer enrolleduserId;
    private Integer enrolledplanId;


    public Integer getId() {
        return Id;
    }
    public void setId(Integer id) {
        Id = id;
    }
    public String getPurchasedate() {
        return Purchasedate;
    }
    public void setPurchasedate(String purchasedate) {
        Purchasedate = purchasedate;
    }
    public String getExpirydate() {
        return Expirydate;
    }
    public void setExpirydate(String expirydate) {
        Expirydate = expirydate;
    }
    public Integer getEnrolleduserId() {
        return enrolleduserId;
    }
    public void setEnrolleduserId(Integer enrolleduserId) {
        this.enrolleduserId = enrolleduserId;
    }
    public Integer getEnrolledplanId() {
        return enrolledplanId;
    }
    public void setEnrolledplanId(Integer enrolledplanId) {
        this.enrolledplanId = enrolledplanId;
    }
}
