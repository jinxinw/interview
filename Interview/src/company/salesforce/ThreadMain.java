/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package company.salesforce;

/**
 *
 * @author Victor
 */
public class ThreadMain {
    public static void main(String[] args) {
        Number number = new Number(1);
        Thread evenThread = new Thread(new EvenThread(number));
        Thread oddThread = new Thread(new OddThread(number));
        evenThread.start();
                oddThread.start();
    }
}
