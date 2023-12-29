package com.fitsta.fitsta.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitsta.fitsta.Component.Validation;
import com.fitsta.fitsta.DTO.CreateOrderRequest;
import com.fitsta.fitsta.Entity.Orders;
import com.fitsta.fitsta.Service.OrderServices;


@RestController
@RequestMapping("/api/order")
public class OrdersController {

    @Autowired
    private OrderServices orderServices;

    @Autowired
    private Validation validation;

    @GetMapping("/{id}")
    public ResponseEntity<Orders> get(@PathVariable("id") Integer id, @RequestHeader(name = "Token", required = true) String token){

        if(!validation.isValidOrder(token)){return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();}

        Orders found = this.orderServices.getOrder(id);
        if(found != null){
            return ResponseEntity.ok(found);
        }
        else{
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody CreateOrderRequest requestOrder, @RequestHeader(name = "Token", required = true) String token){

        if(!validation.isValidOrder(token)){return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();}

        String result = orderServices.createOrder(requestOrder);

        if(result.equals("Success")){
            return ResponseEntity.status(HttpStatus.OK).body("{\"Success\":\"Operation successful.\"}");
        }
        else{
            System.out.println("Error while new order creation : " + result);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"Error\":\"Failed to create new order!\"}");
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable("id") Integer id, @RequestHeader(name = "Token", required = true) String token){

        if(!validation.isValidOrder(token)){return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();}
        
        String result = this.orderServices.deleteOrder(id);

        if(result.equals("Success")){
            return ResponseEntity.status(HttpStatus.OK).body("Order deleted successfully.");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to delete order!");
        }
    }


    @GetMapping("/list")
    public ResponseEntity<List<Orders>> listOrders(@RequestHeader(name = "Token", required = true) String token){

        if(!validation.isValidOrder(token)){return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();}

        List<Orders> ordersList = this.orderServices.listOrders();
        if(ordersList != null){
            return ResponseEntity.ok(ordersList);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @GetMapping("/list/user/{id}")
    public ResponseEntity<List<Orders>> listOrdersByUser(@PathVariable("id") Integer id, @RequestHeader(name = "Token", required = true) String token){

        if(!validation.isValidOrder(token)){return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();}

        List<Orders> ordersList = this.orderServices.listOrdersByUser(id);
        if(ordersList != null){
            return ResponseEntity.ok(ordersList);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
