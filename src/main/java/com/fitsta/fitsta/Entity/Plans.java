package com.fitsta.fitsta.Entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Plans {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String Name;
    private String Type;
    private String Features;
    private Integer Price;
    private Integer Duration;

    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    private Trainer planstrainer;

    @OneToMany(mappedBy = "enrolledplan", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIdentityReference(alwaysAsId = true)
    private List<PlansPurchase> plansPurchasedplans = new ArrayList<>();


    
    public Plans(Integer id, String name, String type, String features, Integer price, Integer duration,
            Trainer planstrainer, List<PlansPurchase> plansPurchasedplans) {
        Id = id;
        Name = name;
        Type = type;
        Features = features;
        Price = price;
        Duration = duration;
        this.planstrainer = planstrainer;
        this.plansPurchasedplans = plansPurchasedplans;
    }

    public Plans() {
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getFeatures() {
        return Features;
    }

    public void setFeatures(String features) {
        Features = features;
    }

    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer price) {
        Price = price;
    }

    public Integer getDuration() {
        return Duration;
    }

    public void setDuration(Integer duration) {
        Duration = duration;
    }

    public Trainer getPlanstrainer() {
        return planstrainer;
    }

    public void setPlanstrainer(Trainer planstrainer) {
        this.planstrainer = planstrainer;
    }

    public List<PlansPurchase> getPlansPurchasedplans() {
        return plansPurchasedplans;
    }

    public void setPlansPurchasedplans(List<PlansPurchase> plansPurchasedplans) {
        this.plansPurchasedplans = plansPurchasedplans;
    }



}