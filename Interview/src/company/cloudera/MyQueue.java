/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package company.cloudera;

/**
 *
 * @author Victor
 */
public class MyQueue {
    private int[] array;
    private int capacity;
    private int head;
    private int tail;
    private int size;
    
    public MyQueue(int capacity) {
        this.array = new int[capacity];
        this.capacity = capacity;
        head = -1;
        tail = -1;
        this.size = 0;
    }
    
    public void enqueue(int value) {
        if (size == capacity) {
            System.out.println("full");
            return;
        }
        if (head == -1) {
            array[0] = value;
            head = 0;
            tail = 0;
        } else {
            head = (head + 1) % capacity;
            if (head != tail) {
                array[head] = value;
            }
        }
        size++;
    }
    
    public int dequeue() {
        if (size == 0) {
            head = -1;
            tail = -1;
            System.out.println("empty");
            return -1;
        }
        int value = array[tail];
        tail = (tail + 1) % capacity;
        size--;
        return value;
    }
    
    public static void main(String[] args) {
        MyQueue queue = new MyQueue(4);
        for (int i = 0; i < 5; i++) {
            queue.enqueue(i);
        }
        for(int i = 0; i < 3; i++) {
            System.out.println(queue.dequeue());
        }
        queue.enqueue(5);
        queue.enqueue(6);
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
    }
}
