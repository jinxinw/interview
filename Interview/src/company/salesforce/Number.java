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
public class Number {
    private int number;
    
    public Number (int number) {
        this.number = number;
    }
    
    public int getNumber() {
        return number;
    }
    
    public void increase() {
        number++;
    }
    
    public void decrease() {
        number--;
    }
}
