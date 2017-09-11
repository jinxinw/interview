/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package company.salesforce;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Victor
 */
public class NumberThread {

    private int number = 0;

    private Lock lock = new ReentrantLock();
    private Condition odd = lock.newCondition();
    private Condition even = lock.newCondition();

    public void printEven() throws InterruptedException {
        lock.lock();
        try {
            while (number < 100) {
                if (number % 2 == 1) {
                    odd.await();
                }
                System.out.println("even " + number);
                number++;
                even.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public void printOdd() throws InterruptedException {
        lock.lock();
        try {
            while (number < 100) {
                if (number % 2 == 0) {
                    even.await();
                }
                System.out.println("odd " + number);
                number++;
                odd.signal();
            }
        } finally {
            lock.unlock();
        }
    }
}
