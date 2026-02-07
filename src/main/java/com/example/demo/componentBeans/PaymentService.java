package com.example.demo.componentBeans;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.RestController;

@Component
//@Service
//@Controller
//@Repository
//@RestController
public class PaymentService {

    public void pay() {
        System.out.println("Paying...");
    }

    @PostConstruct
    public void afterInit() {
        //post init logic
        System.out.println("Before paying.");
    }

    @PreDestroy
    public void beforeDestroy() {
        //cleanup logic
        System.out.println("After payment is done");
    }
}
