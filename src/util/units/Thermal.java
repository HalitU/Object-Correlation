/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.units;

import util.units.unitTypes.ThermalType;

/**
 *
 * @author ovatman
 */
public class Thermal extends Unit{
	private ThermalType type;
    
    public Thermal(double amount,ThermalType type) {
        super(amount);
        this.type=type;
    }

    private double toThermal() throws Exception{
        switch(type){
            case CELCIUS:
                return amount;
            case KELVIN:
                return amount - 273.15;
            case FAHRENHEIT:
                return (amount-32)/1.8;
        }
        throw new Exception("Unknown type!!!");
    }
    
    private void fromThermal(double thermal) throws Exception{
        switch(type){
	        case CELCIUS:
	        	amount = thermal;
	        	break;
	        case KELVIN:
	            amount = thermal + 273.15;
	            break;
	        case FAHRENHEIT:
	            amount = thermal*1.8 + 32;
	            break;
	        default:
	            throw new Exception("Unknown type!!!");
        }
    }
    
    @Override
    public Unit convertTo(Unit destination) throws Exception {
        if(!(destination instanceof Thermal))
            throw new Exception("Unconvertible");
        
        ((Thermal)destination).fromThermal(this.toThermal());
        return destination; 
    }

    @Override
    public void convertFrom(Unit source) throws Exception {
        if(!(source instanceof Thermal))
            throw new Exception("Unconvertible");
        
        this.fromThermal(((Thermal)source).toThermal());
    }
    
    @Override
    public String toString() {
        return "Thermal{" + amount + " type=" + type + '}';
    }
}
