package com.example.newProject.O1O2O3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class EvenOdd extends Thread {

    int value;
    PrintSequence printSequence;

    public EvenOdd(int value, PrintSequence printSequence){
        this.value = value;
        this.printSequence = printSequence;
    }

    public void run() {
        if(this.value == 0) {
            this.printSequence.printZero();
        } else if (this.value == 1) {
            this.printSequence.printOdd();
        } else {
            this.printSequence.printEven();
        }
    }
}

class PrintSequence {

    int limit;
    int currentNumber = 1;
    boolean zeroPrinted = false;
    Lock lock;
    Condition zeroCondition;
    Condition evenCondition;
    Condition oddCondition;

    public PrintSequence(int limit){
        this.limit = limit;
        lock = new ReentrantLock();
        zeroCondition = lock.newCondition();
        evenCondition = lock.newCondition();
        oddCondition = lock.newCondition();
    }


    public void printZero() {

        while(currentNumber < limit){
            lock.lock();

            try {
                while (zeroPrinted) {
                    zeroCondition.await();
                }

                System.out.println("the number is  "+ 0);
                zeroPrinted = true;
                oddCondition.signal();
                evenCondition.signal();

            } catch(InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public void printOdd() {
        while(currentNumber < limit){
            lock.lock();

            try {
                while (!zeroPrinted || isEven(currentNumber)) {
                    oddCondition.await();
                }

                System.out.println("the number is  "+ currentNumber);
                zeroPrinted = false;
                currentNumber++;
                zeroCondition.signal();

            } catch(InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public void printEven() {
        while(currentNumber < limit){
            lock.lock();

            try {
                while (!zeroPrinted || isOdd(currentNumber)) {
                    evenCondition.await();
                }

                System.out.println("the number is  "+ currentNumber);
                zeroPrinted = false;
                currentNumber++;
                zeroCondition.signal();

            } catch(InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }


    public boolean isEven(int number) {
        return (number %2 == 0);
    }

    public boolean isOdd(int number) {
        return (number %2 != 0);
    }

}



public class PrintNumber {
    public static void main(String[] args) {

        PrintSequence printSequence = new PrintSequence(20);

        EvenOdd zeroThread = new EvenOdd(0, printSequence);
        EvenOdd evenThread = new EvenOdd(1, printSequence);
        EvenOdd oddThread = new EvenOdd(2, printSequence);

        zeroThread.start();
        evenThread.start();
        oddThread.start();
    }
}
