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
public class oddEvenLock {

    public static void main(String[] args) {
        NumberThread nt = new NumberThread();
        Thread odd = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    nt.printOdd();
                } catch (InterruptedException ex) {
                    Logger.getLogger(oddEvenLock.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        Thread even = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    nt.printEven();
                } catch (InterruptedException ex) {
                    Logger.getLogger(oddEvenLock.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        odd.start();
        even.start();
    }
}
