/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.units;

import util.units.unitTypes.SpeedType;
import util.units.unitTypes.TemporalType;

/**
 *
 * @author ovatman
 */
public class Temporal extends Unit{

    private TemporalType type;
    
    public Temporal(double amount,TemporalType type) {
        super(amount);
        this.type=type;
    }
    private double toTemporal() throws Exception{
        switch(type){
            case HOUR:
                return amount;
            case SECOND:
                return amount/3600;
            case MINUTE:
                return amount/60;
            case MILLISECOND:
            	return amount/360000;
        }
        throw new Exception("Unknown type!!!");
    }
    
    private void fromTemporal(double temporal) throws Exception{
        switch(type){
	        case HOUR:
	        	amount = temporal;
	        	break;
	        case SECOND:
	            amount = temporal*3600;
	            break;
	        case MINUTE:
	            amount = temporal*60;
	            break;
	        case MILLISECOND:
	        	amount = temporal*360000;
	        	break;
	        default:
	            throw new Exception("Unknown type!!!");
        }
    }
    
    @Override
    public Unit convertTo(Unit destination) throws Exception {
        if(!(destination instanceof Temporal))
            throw new Exception("Unconvertible");
        
        ((Temporal)destination).fromTemporal(this.toTemporal());
        return destination; 
    }

    @Override
    public void convertFrom(Unit source) throws Exception {
        if(!(source instanceof Temporal))
            throw new Exception("Unconvertible");
        
        this.fromTemporal(((Temporal)source).toTemporal());
    }
    @Override
    public String toString() {
        return "Temporal{" + amount + " type=" + type + '}';
    }  
}
