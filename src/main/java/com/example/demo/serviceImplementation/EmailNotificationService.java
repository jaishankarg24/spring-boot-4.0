package com.example.demo.serviceImplementation;

import com.example.demo.serviceInterfaces.NotificationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//@Primary
@Component
@Qualifier("emailNotif")
//@ConditionalOnProperty(name = "notification.type", havingValue = "email")
public class EmailNotificationService implements NotificationService {

    @Override
    public void send(String message) {
        System.out.println("Email sending... "+message);
    }
}
