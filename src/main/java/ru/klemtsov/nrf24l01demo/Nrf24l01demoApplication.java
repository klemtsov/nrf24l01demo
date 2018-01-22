package ru.klemtsov.nrf24l01demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import ru.klemtsov.nrf24l01demo.driver.NRF24L01;
import ru.klemtsov.nrf24l01demo.driver.ReceiveListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class Nrf24l01demoApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(Nrf24l01demoApplication.class);

		springApplication.addListeners(new ApplicationListener<ContextClosedEvent>() {
			@Override
			public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
				System.out.println("SHUTDOWN nrf24L01...");
				Nrf24l01Service nrf24l01Service =  contextClosedEvent.getApplicationContext().getBean(Nrf24l01Service.class);
				nrf24l01Service.getNrf24L01().shutdown();
			}
		});

		springApplication.run(args);

		SpringApplication.run(Nrf24l01demoApplication.class, args);

	}
}
