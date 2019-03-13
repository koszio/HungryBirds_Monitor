package com.company;

public class Producer extends Thread {


    int bufferLength = 10;
    private BoundedBuffer boundedBuffer;
    private int n;
    private boolean Stop = false;

    public Producer(BoundedBuffer boundedBuffer, int n) {
        this.boundedBuffer = boundedBuffer;
        this.n = n;
    }

    //This function is used in the main to stop the prod
    public void setStop() {

        Stop = true;
    }


    @Override
    public void run() {
        int value = 0;
        while (true) {

            value = (int) (Math.random() * 100);
            boundedBuffer.put(value);
            System.out.println("Producer " + currentThread().getId() + " produced" + "........... the value " + value + " in the buffer");
            try {
                sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }


}
