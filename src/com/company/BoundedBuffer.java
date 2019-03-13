package com.company;


public class BoundedBuffer {


    final Object[] items;
    boolean empty = true;
    int rear, front, n;

    public BoundedBuffer(int n) {
        this.n = n;
        items = new Object[n];
    }


    public synchronized void put(Object obj) {
        try {
            while (!empty) {
                wait();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        int position = rear;
        if (position == (n - 1)) {
            empty = false;
            notifyAll();

        }

        items[rear] = obj;
        rear = (rear + 1) % n;

    }

    public synchronized Object take() {

        try {
            while (empty) {
                wait();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }




        int pos = front;
        if (pos == n - 1) {
            empty = true;
            notify();
        }


        Object obj;

        obj = items[front];
        front = (front + 1) % n;
        return obj;
    }
}


