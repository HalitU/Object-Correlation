/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.units;

import util.units.unitTypes.DistanceType;
import util.units.unitTypes.TemporalType;

/**
 *
 * @author ovatman
 */
public class Distance extends Unit{
    
   private DistanceType type;
    
    public Distance(double amount,DistanceType type) {
        super(amount);
        this.type=type;
    }


    private double toDistance() throws Exception{
        switch(type){
            case ML:
                return amount;
            case MET:
                return amount/1609.344;
            case KMET:
                return amount/1.609344;
            case NAUML:
            	return amount*0.868976242;
        }
        throw new Exception("Unknown type!!!");
    }
    
    private void fromDistance(double distance) throws Exception{
        switch(type){
	        case ML:
	        	amount = distance;
	        	break;
	        case MET:
	            amount = distance*1609.344;
	            break;
	        case KMET:
	            amount = distance*1.609344;
	            break;
	        case NAUML:
	        	amount = distance/0.868976242;
	        default:
	            throw new Exception("Unknown type!!!");
        }
    }
    
    @Override
    public Unit convertTo(Unit destination) throws Exception {
        if(!(destination instanceof Distance))
            throw new Exception("Unconvertible");
        
        ((Distance)destination).fromDistance(this.toDistance());
        return destination; 
    }

    @Override
    public void convertFrom(Unit source) throws Exception {
        if(!(source instanceof Distance))
            throw new Exception("Unconvertible");
        
        this.fromDistance(((Distance)source).toDistance());
    }
    
    @Override
    public String toString() {
        return "Distance{" + amount + " type=" + type + '}';
    }
}
