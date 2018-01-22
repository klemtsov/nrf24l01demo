package ru.klemtsov.nrf24l01demo;


import ru.klemtsov.nrf24l01demo.driver.NRF24L01;
import ru.klemtsov.nrf24l01demo.driver.ReceiveListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Nrf24l01Service {
    private NRF24L01 nrf24L01;

    public Nrf24l01Service() {
        nrf24L01 = NRF24L01.getInstance();
        /*nrf24L01.setReceiveListener(new ReceiveListener() {
            @Override
            public void dataReceived(int[] data) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                System.out.printf("%s - Данные получены %s\n", dateFormat.format(new Date()), data);
            }
        });*/
    }

    public NRF24L01 getNrf24L01() {
        return nrf24L01;
    }
}
