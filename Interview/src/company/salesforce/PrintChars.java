/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package company.salesforce;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author xinweijin
 */
public class PrintChars {

    private int number;

    private Lock lock = new ReentrantLock();
    private Condition a = lock.newCondition();
    private Condition b = lock.newCondition();
    private Condition c = lock.newCondition();

    public PrintChars() {
        this.number = 0;
    }

    public void printA() {
        lock.lock();
        try {
            while (number < 10) {
                System.out.println(Thread.currentThread().getName());
                System.out.println("A");
                number++;
                b.signal();
                try {
                    a.await();
                } catch (InterruptedException ex) {
                    Logger.getLogger(PrintChars.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public void printB() {
        lock.lock();
        try {
            while (number < 10) {
                System.out.println(Thread.currentThread().getName());
                System.out.println("B");
                number++;
                c.signal();
                try {
                    b.await();
                } catch (InterruptedException ex) {
                    Logger.getLogger(PrintChars.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public void printC() {
        lock.lock();
        try {
            while (number < 10) {
                System.out.println(Thread.currentThread().getName());
                System.out.println("C");
                number++;
                a.signal();
                try {
                    c.await();
                } catch (InterruptedException ex) {
                    Logger.getLogger(PrintChars.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } finally {
            lock.unlock();
        }
    }
    
    public static void main(String[] args) {
        PrintChars pc = new PrintChars();
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                pc.printA();
            }
        });
        
        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                pc.printB();
            }
        });
        
        Thread c = new Thread(new Runnable() {
            @Override
            public void run() {
                pc.printC();
            }
        });
        
        a.start();
        b.start();
        c.start();
    }

}
