/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package company.salesforce;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Victor
 */
public class EvenThread implements Runnable {
    private Number number;

    EvenThread(Number number) {
        this.number = number;
    }
    
    public void run() {
        synchronized (number) {
            while(number.getNumber() < 100) {
                if (number.getNumber() % 2 == 0) {
                    System.out.println("Even " + number.getNumber());
                    number.increase();
                    number.notify();
                } else {
                    try {
                        number.wait();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(EvenThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }
    
}
