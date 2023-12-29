package com.fitsta.fitsta.Component;

import java.util.Properties;
import java.util.Random;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.SimpleMailMessage;

import com.fitsta.fitsta.DTO.ContactUsRequest;
import com.fitsta.fitsta.Entity.Orders;
import com.fitsta.fitsta.Entity.Plans;
import com.fitsta.fitsta.Entity.User;

public class EmailSender {


    public static String sendOrderInvoice(Orders order) {
        JavaMailSender mailSender = configureMailSender();
        sendOrderReceipt(mailSender, order);
        return "Success";
    }


    private static JavaMailSender configureMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("buddhashorse@gmail.com");
        mailSender.setPassword("nwobhdbffsqknbnh");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        // props.put("mail.debug", "true");

        return mailSender;
    }


    public static String sendOTP(String rEmail) {
        JavaMailSender mailSender = configureMailSender();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(rEmail);
        message.setSubject("Fitsta Login OTP");
        String otp = generateOTP();
        String msg = "Your Fitsta Login OTP : "+ otp;        
        message.setText(msg);
        mailSender.send(message);
        System.out.println("Receipt sent successfully to " + rEmail);
        return otp;
    }


    public static void contactUsMail(ContactUsRequest newRequest) {
        JavaMailSender mailSender = configureMailSender();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("buddhashorse@gmail.com");
        message.setSubject("New Request to contact us");

        String msg = "";
        msg += "\nNew Request to contact us : \n";
        msg += "Name : " + newRequest.getName() + "\n";
        msg += "Contact Details : " + newRequest.getContactno() + "\n";
        msg += "Message : " + newRequest.getMsg() + "\n";
        
        message.setText(msg);
        mailSender.send(message);
        System.out.println("Contact us mail sent successfully.");
    }

    private static void sendOrderReceipt(JavaMailSender mailSender, Orders order) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(order.getOrderUser().getUsername());
        message.setSubject("Your Fitsta Receipt");

        String msg = "";
        msg += "\nOrder Details: \n";
        msg += "Id: " + order.getId() + "\n";
        msg += "Date : " + order.getOrderDate() + "\n";
        msg += "Total Amount : " + order.getTotalAmount() + "\n";

        msg += "\nCustomer Details: \n";
        msg += "Id: " + order.getOrderUser().getId() + "\n";
        msg += "Name: " + order.getOrderUser().getName() + "\n";
        msg += "Contact No. : " + order.getOrderUser().getContactno() + "\n";
        msg += "Address : " + order.getOrderUser().getAddress() + "\n";

        msg += "\nProduct Details: \n";
        msg += "Id : " + order.getOrderProduct().getId() + "\n";
        msg += "Name : " + order.getOrderProduct().getName() + "\n";
        msg += "Description : " + order.getOrderProduct().getDescription() + "\n";
        msg += "Image 1 : " + order.getOrderProduct().getImage1() + "\n";
        msg += "Image 2 : " + order.getOrderProduct().getImage2() + "\n";
        msg += "Price : " + order.getOrderProduct().getProductPrice() + "\n";
        
        message.setText(msg);
        mailSender.send(message);
        System.out.println("Receipt sent successfully to " + order.getOrderUser().getUsername());
    }


    // private static void sendPlanReceipt(JavaMailSender mailSender, Plans plan) {
    //     SimpleMailMessage message = new SimpleMailMessage();
    //     message.setTo(plan.get().getUsername());
    //     message.setSubject("Your Fitsta Receipt");

    //     String msg = "";
    //     msg += "\nOrder Details: \n";
    //     msg += "Id: " + order.getId() + "\n";
    //     msg += "Date : " + order.getOrderDate() + "\n";
    //     msg += "Total Amount : " + order.getTotalAmount() + "\n";

    //     msg += "\nCustomer Details: \n";
    //     msg += "Id: " + order.getOrderUser().getId() + "\n";
    //     msg += "Name: " + order.getOrderUser().getName() + "\n";
    //     msg += "Contact No. : " + order.getOrderUser().getContactno() + "\n";
    //     msg += "Address : " + order.getOrderUser().getAddress() + "\n";

    //     msg += "\nProduct Details: \n";
    //     msg += "Id : " + order.getOrderProduct().getId() + "\n";
    //     msg += "Name : " + order.getOrderProduct().getName() + "\n";
    //     msg += "Description : " + order.getOrderProduct().getDescription() + "\n";
    //     msg += "Image 1 : " + order.getOrderProduct().getImage1() + "\n";
    //     msg += "Image 2 : " + order.getOrderProduct().getImage2() + "\n";
    //     msg += "Price : " + order.getOrderProduct().getProductPrice() + "\n";
        
    //     message.setText(msg);
    //     mailSender.send(message);
    //     System.out.println("Receipt sent successfully to " + order.getOrderUser().getUsername());
    // }


    private static String generateOTP() {
        // Generate a random 6-digit OTP
        Random random = new Random();
        int otpValue = 100000 + random.nextInt(900000);
        return String.valueOf(otpValue);
    }
}
