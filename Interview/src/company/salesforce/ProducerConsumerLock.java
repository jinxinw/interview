package company.salesforce;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ProducerConsumerLock {

    public static void main(String[] args) {
        PCLock2 pc = new PCLock2();
        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.get();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ProducerConsumerLock.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.put();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ProducerConsumerLock.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        consumer.start();
        producer.start();
    }
}
