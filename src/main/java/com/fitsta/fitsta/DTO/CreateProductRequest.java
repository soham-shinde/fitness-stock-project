package com.fitsta.fitsta.DTO;

public class CreateProductRequest {
    private Integer Id;
    private String Name;
    private String Image1;
    private String Image2;
    private String Description;
    private String ProductPrice;

    public CreateProductRequest(Integer id, String name, String image1, String image2, String description,
            String productPrice) {
        Id = id;
        Name = name;
        Image1 = image1;
        Image2 = image2;
        Description = description;
        ProductPrice = productPrice;
    }
    public CreateProductRequest() {
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
    public String getImage1() {
        return Image1;
    }
    public void setImage1(String image1) {
        Image1 = image1;
    }
    public String getImage2() {
        return Image2;
    }
    public void setImage2(String image2) {
        Image2 = image2;
    }
    public String getDescription() {
        return Description;
    }
    public void setDescription(String description) {
        Description = description;
    }
    public String getProductPrice() {
        return ProductPrice;
    }
    public void setProductPrice(String productPrice) {
        ProductPrice = productPrice;
    }

}
