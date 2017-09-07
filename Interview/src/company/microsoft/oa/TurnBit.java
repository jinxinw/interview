/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package company.microsoft.oa;

/**
 *
 * @author Victor
 */
public class TurnBit {
    
    public int turnBit(int n, int bit, boolean turnOn) {
        return turnOn ? (n | (1 << bit)) : (n & ~(1 << bit));
    }
    
    public static void main(String[] args) {
        TurnBit tb = new TurnBit();
        System.out.println(tb.turnBit(8, 2, true));
        System.out.println(tb.turnBit(3, 0, false));
    }
}
