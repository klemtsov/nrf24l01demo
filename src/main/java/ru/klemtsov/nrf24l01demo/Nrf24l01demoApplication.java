package ru.klemtsov.nrf24l01demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextStartedEvent;
import ru.klemtsov.nrf24l01demo.driver.NRF24L01;

@SpringBootApplication
public class Nrf24l01demoApplication {

    private static Nrf24l01Service nrf24l01Service;

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Nrf24l01demoApplication.class);
        springApplication.addListeners((ApplicationListener<ContextStartedEvent>)event ->{
            nrf24l01Service = event.getApplicationContext().getBean(Nrf24l01Service.class);
            System.out.println("START nrf24L01 succeeded");

        });


        springApplication.addListeners((ApplicationListener<ContextClosedEvent>) contextClosedEvent -> {
            System.out.println("SHUTDOWN nrf24L01...");
            //NRF24L01 nrf24l01Service = contextClosedEvent.getApplicationContext().getBean(NRF24L01.class);
            nrf24l01Service.shutdown();
            System.out.println("SHUTDOWN nrf24L01 succeeded");
        });

        springApplication.run(args);

    }
}
