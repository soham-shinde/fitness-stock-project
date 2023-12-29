package com.fitsta.fitsta.DTO;

import java.util.List;

public class UpdatePlanRequest {

    private Integer id;
    private String name;
    private String type;
    private String features;
    private Integer price;
    private Integer duration;
    private Integer planstrainer;
    private List<Integer> plansPurchasedplans;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getFeatures() {
        return features;
    }
    public void setFeatures(String features) {
        this.features = features;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public Integer getDuration() {
        return duration;
    }
    public void setDuration(Integer duration) {
        this.duration = duration;
    }
    public Integer getPlanstrainer() {
        return planstrainer;
    }
    public void setPlanstrainer(Integer planstrainer) {
        this.planstrainer = planstrainer;
    }
    public List<Integer> getPlansPurchasedplans() {
        return plansPurchasedplans;
    }
    public void setPlansPurchasedplans(List<Integer> plansPurchasedplans) {
        this.plansPurchasedplans = plansPurchasedplans;
    }


}
