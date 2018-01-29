package ru.klemtsov.nrf24l01demo;


import ru.klemtsov.nrf24l01demo.driver.NRF24L01;
import ru.klemtsov.nrf24l01demo.driver.ReceiveListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Nrf24l01Service {
    private NRF24L01 nrf24L01;

    private Thread thread;
    private boolean stop = false;

    public Nrf24l01Service() {
        nrf24L01 = NRF24L01.getInstance();
        nrf24L01.setReceiveListener(new ReceiveListener() {
            @Override
            public void dataReceived(int[] data) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                System.out.printf("%s - Данные получены %s\n", dateFormat.format(new Date()), data);
            }
        });
        thread = new Thread(() -> {
            while (!stop) {
                if (stop) {
                    System.out.printf("Stopped\n");
                    return;
                }
                nrf24L01.start();
                int[] txaddr = new int[]{0, 0, 0, 0, 31};
                int[] txdata = new int[]{1};
                nrf24L01.send(1, 1, 10, 5, txaddr, 1, txdata);
                System.out.printf("sended\n");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            ;

            System.out.printf("Stopped\n");
        }
        );

    }

    public void start() {
        thread.start();
    }

    public NRF24L01 getNrf24L01() {
        return nrf24L01;
    }

    public void shutdown() {
        nrf24L01.shutdown();
    }


}
