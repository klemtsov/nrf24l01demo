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

	private static NRF24L01 nrf24L01Service;

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(Nrf24l01demoApplication.class);
		springApplication.addListeners(new ApplicationListener<ContextStartedEvent>() {
										   @Override
										   public void onApplicationEvent(ContextStartedEvent event) {
											   System.out.println("Запуск сервиса");
											   NRF24L01 nrf24L01Service =  event.getApplicationContext().getBean(NRF24L01.class);
											   nrf24L01Service.start();
											   nrf24L01Service.setReceiveListener(new ReceiveListener() {
												   @Override
												   public void dataReceived(int[] data) {
													   DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

													   System.out.printf("%s - Данные получены %s\n", dateFormat.format(new Date()), data);
												   }
											   });
										   }
									   }

		);

		springApplication.addListeners(new ApplicationListener<ContextClosedEvent>() {
			@Override
			public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
				System.out.println("SHUTDOWN nrf24L01...");
				if (nrf24L01Service != null) {
					nrf24L01Service.shutdown();
				}
			}
		});
		springApplication.addListeners( new ApplicationListener<ContextRefreshedEvent>() {
			@Override
			public void onApplicationEvent(ContextRefreshedEvent applicationEvent) {
				System.out.println("refreshed event");
			}
		});
		springApplication.run(args);

		SpringApplication.run(Nrf24l01demoApplication.class, args);

	}
}
