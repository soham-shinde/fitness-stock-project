package com.fitsta.fitsta.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitsta.fitsta.Component.EmailSender;
import com.fitsta.fitsta.DTO.CreateOrderRequest;
import com.fitsta.fitsta.Entity.Orders;
import com.fitsta.fitsta.Entity.Product;
import com.fitsta.fitsta.Entity.User;
import com.fitsta.fitsta.Repository.OrderRepository;
import com.fitsta.fitsta.Repository.ProductRepository;
import com.fitsta.fitsta.Repository.UserRepository;

@Service
public class OrderServices {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;


    public Orders getOrder(Integer id){
        return this.orderRepository.findById(id).orElse(null);
    }


    public String createOrder(CreateOrderRequest requestOrder){
        try {
            Orders newOrder = new Orders();
            newOrder.setId(requestOrder.getId());
            newOrder.setTotalAmount(requestOrder.getTotalAmount());
    
            User orderUser = this.userRepository.findById(requestOrder.getOrderUser()).orElse(null);
            newOrder.setOrderUser(orderUser);

            Product orderProduct = this.productRepository.findById(requestOrder.getOrderProduct()).orElse(null);
            newOrder.setOrderProduct(orderProduct);
    
            Date orderdate = new SimpleDateFormat("dd/MM/yyyy").parse(requestOrder.getOrderDate());
            newOrder.setOrderDate(orderdate);

            Orders savedOrder = this.orderRepository.save(newOrder);

            EmailSender.sendOrderInvoice(savedOrder);

            return "Success";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }


    public String deleteOrder(Integer id){
        Optional<Orders> order = orderRepository.findById(id);
        if(order.isPresent()){
            orderRepository.delete(order.get());
            return ("Success");
        }else{
            return ("Order Not Found!");
        }
    }


    public List<Orders> listOrders(){
        try {
            List<Orders> allOrders = (List<Orders>) this.orderRepository.findAll();
            return allOrders;
        }
        catch (Exception e) {return null;}
    }


    public List<Orders> listOrdersByUser(Integer id){
        try {
            User user = this.userRepository.findById(id).orElse(null);
            if(user != null){
                List<Orders> allOrders = (List<Orders>) this.orderRepository.findByOrderUser(user);
                return allOrders;
            }
            else{
                return null;
            }
        }
        catch (Exception e) {return null;}
    }


}
