package com.apm.samples.service.activemq;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ChengBing Han
 * @date 16:37  2018/3/14
 * @description
 */
public class ActivemqService {

    @Test
    public void productor() throws Exception {

        try {

            Producter producter = new Producter();
            producter.init();
            producter.sendMessage("queue1");
        } catch (Exception e) {

            e.printStackTrace();

            throw new Exception(e.getMessage());


        }


    }


    private class ProductorMq implements Runnable {
        Producter producter;

        public ProductorMq(Producter producter) {
            this.producter = producter;
        }

        AtomicInteger index = new AtomicInteger(0);

        @Override
        public void run() {


            while (index.intValue() < 1) {

                index.addAndGet(1);
                try {
                    producter.sendMessage("Jaycekon-MQ");
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    //#================================================


    @Test
    public void consumer() throws Exception {


        try {


            Comsumer comsumer = new Comsumer();
            comsumer.init();
            comsumer.getMessage("queue1");
        } catch (Exception e) {

            throw new Exception(e.getMessage());

        }


    }

    private class ConsumerMq implements Runnable {
        Comsumer comsumer;

        public ConsumerMq(Comsumer comsumer) {
            this.comsumer = comsumer;
        }

        @Override
        public void run() {
            int count = 1;
            int index = 0;
            while (index < count) {
                index++;
                try {
                    comsumer.getMessage("Jaycekon-MQ");
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
