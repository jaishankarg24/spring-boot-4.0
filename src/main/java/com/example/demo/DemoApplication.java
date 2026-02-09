package com.example.demo;

import com.example.demo.serviceInterfaces.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.LinkedHashMap;
import java.util.Map;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

//    @Autowired
//    final NotificationService notificationServiceObj; //dependency injection
//
//    public DemoApplication(NotificationService notificationServiceObj) {
//        this.notificationServiceObj = notificationServiceObj; // constructor DI/// Preferred
//    }

//    public void setNotificationServiceObj(NotificationService notificationServiceObj) {
//        this.notificationServiceObj = notificationServiceObj;
//    }

	//To get all Notification services
	@Autowired
	Map<String, NotificationService> notificationServiceMap = new LinkedHashMap<>();

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//        notificationServiceObj  = new SmsNotificationService();
//        notificationServiceObj.send("hello");

		for (var notificationService: notificationServiceMap.entrySet()) {
			System.out.println(notificationService.getKey());
			notificationService.getValue().send("Hello");
		}
	}

}
