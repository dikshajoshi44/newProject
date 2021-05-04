package Trial;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class AlphaBeta extends Thread {

    PrintAlphaBeta printAlphaBeta;
    String element;

    public AlphaBeta(String element, PrintAlphaBeta printAlphaBeta){
        this.printAlphaBeta = printAlphaBeta;
        this.element = element;
    }

    public void run() {
        if(element == "alpha"){
            this.printAlphaBeta.printAlpha();
        }else {
            this.printAlphaBeta.printBeta();
        }
    }

}

class PrintAlphaBeta {

    int limit;
    int currentValue = 1;
//    boolean alphaPrinted = false;
    Lock lock;
    Condition alphaCondition;
    Condition betaCondition;

    public PrintAlphaBeta(int limit) {
        this.limit = limit;
        this.lock = new ReentrantLock();
        this.alphaCondition = lock.newCondition();
        this.betaCondition = lock.newCondition();
    }

    public void printAlpha(){

        while(currentValue < limit){

            lock.lock();

            try {
                while (isEven(currentValue)) {
                    alphaCondition.await();
                }

                System.out.println("alpha");
                currentValue++;
                betaCondition.signal();



            } catch(InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                lock.unlock();
            }

        }

    }

    public void printBeta() {
        while(currentValue < limit){

            lock.lock();

            try {
                while (isOdd(currentValue)) {
                    betaCondition.await();
                }

                System.out.println("beta");
                currentValue++;
                alphaCondition.signal();


            } catch(InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                lock.unlock();
            }

        }
    }

    public boolean isEven(int currentValue) {
        return (currentValue % 2 == 0);
    }

    public boolean isOdd(int currentValue) {
        return (currentValue % 2 != 0);
    }

}


class Employee {

    final String name = "hello";

    public Employee(){

    }

}

class MyEmploee extends Employee {


    public MyEmploee(){

    }

}

public class Interview {

    public static void main(String[] args) {
        PrintAlphaBeta printAlphaBeta = new PrintAlphaBeta(10);

        AlphaBeta thread1 = new AlphaBeta("alpha", printAlphaBeta);
        AlphaBeta thread2 = new AlphaBeta("beta", printAlphaBeta);

        thread1.start();
        thread2.start();

        Employee emp = new Employee();
//        emp.name = "jsndjs";

    }

}



