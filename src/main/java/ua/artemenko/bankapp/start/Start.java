package ua.artemenko.bankapp.start;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.artemenko.bankapp.controller.Controller;

public class Start {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("config/app-config.xml");
        Controller controller = (Controller) context.getBean("controller");
        controller.start();
    }
}
