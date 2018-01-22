package ru.klemtsov.nrf24l01demo;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.klemtsov.nrf24l01demo.driver.NRF24L01;

@Component
public class UsedBeans {

    @Bean
    public NRF24L01 getNRF24L01Service(){
        return NRF24L01.getInstance();
    }
}
