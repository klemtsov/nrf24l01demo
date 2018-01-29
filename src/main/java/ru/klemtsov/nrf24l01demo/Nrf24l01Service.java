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
    volatile private boolean started = false;

    public Nrf24l01Service() {
        nrf24L01 = NRF24L01.getInstance();
        nrf24L01.setReceiveListener(data -> {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.printf("%s - Данные получены %s\n", dateFormat.format(new Date()), data);
        });
        thread = new Thread(() -> {
            while (!stop) {
                if (stop) {
                    System.out.printf("Stopped\n");
                    return;
                }
                if (!started) {
                    nrf24L01.start();
                    started = true;
                }
                int[] txaddr = new int[]{0, 0, 0, 0, 0};
                int[] txdata = new int[]{1};
                nrf24L01.send(122, 1, 10, 5, txaddr, 1, txdata);
                System.out.printf("sended %s\n", new Date());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            ;
            started = false;
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
