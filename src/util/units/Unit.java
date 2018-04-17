/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.units;

/**
 *
 * @author ovatman
 */
public abstract class Unit {
    protected double amount;
    
    public abstract Unit convertTo(Unit destination) throws Exception;
    public abstract void convertFrom(Unit source) throws Exception;

    public Unit(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    private void setAmount(double amount) {
        this.amount=amount;
    }
    
}
