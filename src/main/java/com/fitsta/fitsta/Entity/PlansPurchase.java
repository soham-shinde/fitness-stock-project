package com.fitsta.fitsta.Entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class PlansPurchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private Date Purchasedate;
    private Date Expirydate;

    @OneToOne
    @JsonIdentityReference(alwaysAsId = true)
    private User enrolleduser;

    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    private Plans enrolledplan;

    public PlansPurchase(Integer id, Date purchasedate, Date expirydate, User enrolleduser, Plans enrolledplan) {
        Id = id;
        Purchasedate = purchasedate;
        Expirydate = expirydate;
        this.enrolleduser = enrolleduser;
        this.enrolledplan = enrolledplan;
    }

    public PlansPurchase() {
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Date getPurchasedate() {
        return Purchasedate;
    }

    public void setPurchasedate(Date purchasedate) {
        Purchasedate = purchasedate;
    }

    public Date getExpirydate() {
        return Expirydate;
    }

    public void setExpirydate(Date expirydate) {
        Expirydate = expirydate;
    }

    public User getEnrolleduser() {
        return enrolleduser;
    }

    public void setEnrolleduser(User enrolleduser) {
        this.enrolleduser = enrolleduser;
    }

    public Plans getEnrolledplan() {
        return enrolledplan;
    }

    public void setEnrolledplan(Plans enrolledplan) {
        this.enrolledplan = enrolledplan;
    }

}