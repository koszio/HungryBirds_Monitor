package com.company;


public class Main {

    public static void main(String[] args) {

        int buffer = 10;
        int numberOfThreads = 5;
        BoundedBuffer bf = new BoundedBuffer(buffer);

        Producer prod = new Producer(bf, buffer);
        // Consumer cons = new Consumer(bf,10);


        //Create an array of threads which will consume
        Thread[] consThreads = new Thread[numberOfThreads];

        //Create each thread and store it in to the consThreads array
        for (int i = 0; i < consThreads.length; i++) {
            consThreads[i] = new Thread(new Consumer(bf, buffer), "Consumer number " + i);
            System.out.println("Thread " + i + " has been created");

        }

        prod.start();

        System.out.println("Initially the Elements in the buffer are (Should be null): ");
        System.out.print("[");
        for (int i = 0; i < buffer; i++) {

            System.out.print(" " + bf.items[i]);
        }
        System.out.print(" ]");
        System.out.println();


        //Start each one of the threads that were created and stored in the array
        for (int i = 0; i < consThreads.length; i++) {
            consThreads[i].start();
            System.out.println("Thread " + i + " have been Started");

        }
        try {
            for (int i = 0; i < consThreads.length; i++) {

                consThreads[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("Ops.............");
        }

        prod.setStop();
        prod.interrupt();


    }
}
