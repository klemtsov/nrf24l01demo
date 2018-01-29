package ru.klemtsov.nrf24l01demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import ru.klemtsov.nrf24l01demo.driver.NRF24L01;

@Configuration
public class UsedBeans {

    @Bean
    public Nrf24l01Service nrf24L01Service(){
        return new Nrf24l01Service();
        //NRF24L01 nrf = NRF24L01.getInstance();
        /*nrf.start();
        int[] txaddr = new int[]{0,0,0,0,2};
        int[] txdata = new int[]{1};
        nrf.send(1, 1, 100, 5, txaddr, 1, txdata);
        */
        //return nrf;
    }
}
