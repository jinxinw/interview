/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package company.salesforce;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Victor
 */
public class PCLock2 {
    
    private static final int CAPACITY = 10;
    private final Queue<Integer> queue = new LinkedList<>();
    private final Random theRandom = new Random();
    private final Lock aLock = new ReentrantLock();
    private final Condition bufferNotFull = aLock.newCondition();
    private final Condition bufferNotEmpty = aLock.newCondition();
    
    public void put() throws InterruptedException {
        while (true) {
            aLock.lock();
            try {
                while (queue.size() == CAPACITY) {
                    System.out.println(Thread.currentThread().getName() + " : Buffer is full, waiting");
                    bufferNotEmpty.await();
                }
                int number = theRandom.nextInt();
                boolean isAdded = queue.offer(number);
                if (isAdded) {
                    System.out.printf("%s added %d into queue %n", Thread.currentThread().getName(), number);
                    System.out.println(Thread.currentThread().getName() + " : Signalling that buffer is no more empty now");
                    bufferNotFull.signalAll();
                }
                Thread.sleep(1000);
            } finally {
                aLock.unlock();
            }
        }
    }
    
    public void get() throws InterruptedException {
        while (true) {
            aLock.lock();
            try {
                while (queue.size() == 0) {
                    System.out.println(Thread.currentThread().getName() + " : Buffer is empty, waiting");
                    bufferNotFull.await();
                }
                Integer value = queue.poll();
                if (value != null) {
                    System.out.printf("%s consumed %d from queue %n", Thread.currentThread().getName(), value);
                    System.out.println(Thread.currentThread().getName() + " : Signalling that buffer may be empty now");
                    bufferNotEmpty.signalAll();
                }
                Thread.sleep(1000);
            } finally {
                aLock.unlock();
            }
        }
    }
    
}
