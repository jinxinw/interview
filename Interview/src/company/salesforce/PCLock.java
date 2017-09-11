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
import java.util.logging.Level;
import java.util.logging.Logger;

public class PCLock {

    private final Queue<Integer> queue = new LinkedList<>();
    private final int capacity;
    Random random = new Random();

    private final Lock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();

    public PCLock(int capacity) {
        this.capacity = capacity;
    }

    public int get() {
        lock.lock();
        try {
            while (queue.size() == 0) {
                try {
                    notFull.await();
                } catch (InterruptedException ex) {
                    Logger.getLogger(PCLock.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            int value = queue.poll();
            notEmpty.signalAll();
            return value;
        } finally {
            lock.unlock();
        }
    }

    public int put() {
        lock.lock();
        try {
            while (queue.size() == capacity) {
                try {
                    notEmpty.await();
                } catch (InterruptedException ex) {
                    Logger.getLogger(PCLock.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            int value = random.nextInt(100);
            queue.offer(value);
            notFull.signalAll();
            return value;
        } finally {
            lock.unlock();
        }
    }
}
