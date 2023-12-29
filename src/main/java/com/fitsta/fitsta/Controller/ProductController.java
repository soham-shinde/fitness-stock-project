package com.fitsta.fitsta.Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fitsta.fitsta.Component.Validation;
import com.fitsta.fitsta.DTO.UpdateProductRequest;
import com.fitsta.fitsta.Entity.Product;
import com.fitsta.fitsta.Service.ProductServices;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    
    @Autowired
    private ProductServices productServices;

    @Autowired
    private Validation validation;


    @PostMapping("/create")
    public ResponseEntity<String> createProduct(
        @RequestParam("Id") Integer id,
        @RequestParam("Name") String name,
        @RequestParam("Image1") MultipartFile image1,
        @RequestParam("Image2") MultipartFile image2,
        @RequestParam("Image3") MultipartFile image3,
        @RequestParam("Image4") MultipartFile image4,
        @RequestParam("Description") String description,
        @RequestParam("ProductPrice") String productPrice,
        @RequestHeader(name = "Token", required = true) String token){

        if(!validation.isValidProduct(token)){return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();}

        Product newProduct = new Product();
        newProduct.setId(id);
        newProduct.setName(name);
        newProduct.setImage1("image1.jpg");
        newProduct.setImage2("image2.jpg");
        newProduct.setImage3("image3.jpg");
        newProduct.setImage4("image4.jpg");
        newProduct.setDescription(description);
        newProduct.setProductPrice(productPrice);

        String Path = "";
        String currTime = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());

        try {
            Path = new ClassPathResource("static/images/product/").getFile().getAbsolutePath();
            // System.out.println(Path);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Path not found!");
        }

        if (image1 != null) {
            try {
                Files.copy(image1.getInputStream(), Paths.get(Path + File.separator + currTime + "_" +  image1.getOriginalFilename()),
                StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image!");
            }
            newProduct.setImage1(ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/product/"+ currTime +"_" + image1.getOriginalFilename()).toUriString());
        } else {
            newProduct.setImage1("");
        }

        if (image2 != null) {
            try {
                Files.copy(image2.getInputStream(), Paths.get(Path + File.separator + currTime + "_" + image2.getOriginalFilename()),
                StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image!");
            }
            newProduct.setImage2(ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/product/"+currTime+"_" + image2.getOriginalFilename()).toUriString());
        } else {
            newProduct.setImage2("");
        }

        if (image3 != null) {
            try {
                Files.copy(image3.getInputStream(), Paths.get(Path + File.separator + currTime + "_" + image3.getOriginalFilename()),
                StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image!");
            }
            newProduct.setImage3(ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/product/"+currTime+"_" + image3.getOriginalFilename()).toUriString());
        } else {
            newProduct.setImage3("");
        }

        if (image4 != null) {
            try {
                Files.copy(image4.getInputStream(), Paths.get(Path + File.separator + currTime + "_" + image4.getOriginalFilename()),
                StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image!");
            }
            newProduct.setImage4(ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/product/"+currTime+"_" + image4.getOriginalFilename()).toUriString());
        } else {
            newProduct.setImage4("");
        }

        String result = productServices.createProduct(newProduct);

        if(result.equals("Success")){
            return ResponseEntity.status(HttpStatus.OK).body("{\"Success\":\"Operation successful.\"}");
        }
        else{
            System.out.println("Error while new product creation : " + result);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"Error\":\"Failed to create new product!\"}");
        }

    }
    

    @PutMapping("/update")
    public ResponseEntity<String> updateProduct(
        @RequestBody UpdateProductRequest recProduct,
        @RequestHeader(name = "Token", required = true) String token){

        if(!validation.isValidProduct(token)){return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();}

        String result = productServices.updateProduct(recProduct);

        if(result.equals("Success")){
            return ResponseEntity.status(HttpStatus.OK).body("{\"Success\":\"Operation successful.\"}");
        }
        else{
            System.out.println("Error while updating product : " + result);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"Error\":\"Failed to update product!\"}");
        }

    }
    

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Integer id) {

        Product gotProduct = this.productServices.getProduct(id);
        if (gotProduct != null){
            return ResponseEntity.ok(gotProduct);
        }
        else{
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Integer id, @RequestHeader(name = "Token", required = true) String token){

        if(!validation.isValidProduct(token)){return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();}
        
        String result = this.productServices.deleteProduct(id);

        if(result.equals("Success")){
            return ResponseEntity.status(HttpStatus.OK).body("Product and associated orders deleted successfully.");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to delete product and associated orders!");
        }
    }


    @GetMapping("/list")
    public ResponseEntity<List<Product>> listProducts(){
        List<Product> productsList = this.productServices.listProducts();
        if(productsList != null){
            return ResponseEntity.ok(productsList);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
