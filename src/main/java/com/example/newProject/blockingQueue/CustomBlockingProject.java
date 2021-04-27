package com.example.newProject.blockingQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Producer extends Thread {

    CustomBQueue blockingQueue;

    public Producer(CustomBQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }
    public void run() {
        for(int i = 0 ; i < 10 ;i++) {
            this.blockingQueue.produce(i);
        }
    }

}

class Consumer extends Thread {

    CustomBQueue blockingQueue;

    public Consumer(CustomBQueue blockingQueue){
        this.blockingQueue = blockingQueue;
    }

    public void run() {
        for(int i = 0 ; i < 10 ;i++) {
            this.blockingQueue.consume();
        }
    }
}

class CustomBQueue {
    int size;
    Lock lock;
    Condition producerCondition;
    Condition consumerCondition;
    Integer[] arraQueue;
    int takeIndex = 0;
    int putIndex = 0;
    int count = 0;

    public CustomBQueue(int size){
        this.size = size;
        lock = new ReentrantLock();
        producerCondition = lock.newCondition();
        consumerCondition = lock.newCondition();
        arraQueue = new Integer[size];

    }

    public void produce(Integer item){
        lock.lock();
        try {
            while (count >= size) {
                producerCondition.await();
            }

            System.out.println("putting item number in the queue " + Thread.currentThread().getName() + " *** " + putIndex);
            arraQueue[putIndex] = item;
            count++;
            putIndex++;

            if(putIndex == size){
                putIndex = 0;
            }
            consumerCondition.signal();

        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public void consume(){

        lock.lock();
        try {
            while (count == 0) {
                consumerCondition.await();
            }

            Integer item = arraQueue[takeIndex];
            System.out.println("taking item number in the queue " + Thread.currentThread().getName() + " *** " + item);
            count--;
            takeIndex++;

            if(takeIndex == size){
                takeIndex = 0;
            }
            producerCondition.signal();

        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}

public class CustomBlockingProject {

    public static void main(String[] args) {
        CustomBQueue blockingQueue = new CustomBQueue(10);

        Producer producer = new Producer(blockingQueue);
        Producer producer2 = new Producer(blockingQueue);
        Consumer consumer = new Consumer(blockingQueue);
        Consumer consumer2 = new Consumer(blockingQueue);
        Consumer consumer3 = new Consumer(blockingQueue);

        consumer2.start();
        producer.start();
        consumer.start();
        consumer3.start();
        producer2.start();
    }





}
