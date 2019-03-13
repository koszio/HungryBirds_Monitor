package com.company;

import javax.swing.*;

public class Consumer extends Thread {


    boolean Stop = false;
    private BoundedBuffer boundedBuffer;
    private int n;

    public Consumer(BoundedBuffer boundedBuffer, int n) {
        this.boundedBuffer = boundedBuffer;
        this.n = n;
    }


    public void run() {
        int value = 0;

        while (true) {
            try {
                //System.out.println("Consumer " + currentThread().getId() + "   is executing");
                value = (int) boundedBuffer.take();

                System.out.println("Consumer " + currentThread().getId() + " took the element with value = " + value);

                sleep(100);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }

    }
}
