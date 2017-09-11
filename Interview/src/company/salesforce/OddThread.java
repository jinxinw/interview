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
public class OddThread implements Runnable {

    private Number number;

    OddThread(Number number) {
        this.number = number;
    }

    @Override
    public void run() {
        synchronized (number) {
            while (number.getNumber() < 100) {
                if (number.getNumber() % 2 == 1) {
                    for (int i = 0; i < 10; i++) {
                        System.out.println("odd " + number.getNumber());
                        number.increase();
                        number.increase();
                    }
                    number.decrease();
                    number.notify();
                } else {
                    try {
                        number.wait();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(OddThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

}
