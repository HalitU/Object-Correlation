/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.units;

import util.units.unitTypes.SpeedType;

/**
 *
 * @author ovatman
 */
public class Speed extends Unit{
    private SpeedType type;
    
    public Speed(double amount,SpeedType type) {
        super(amount);
        this.type=type;
    }
    
    private double toSpeed() throws Exception{
        switch(type){
            case MPH:
                return amount;
            case KMH:
                return amount/1.60590277778;
            case KNOT:
                return amount*1.152;
        }
        throw new Exception("Unknown type!!!");
    }
    
    private void fromSpeed(double speed) throws Exception{
        switch(type){
	        case MPH:
	        	amount = speed;
	        	break;
	        case KMH:
	            amount = speed*1.60590277778;
	            break;
	        case KNOT:
	            amount = speed/1.152;
	            break;
	        default:
	            throw new Exception("Unknown type!!!");
        }
    }
    @Override
    public Unit convertTo(Unit destination) throws Exception{
        if(!(destination instanceof Speed))
            throw new Exception("Unconvertible");
        
        ((Speed)destination).fromSpeed(this.toSpeed());
        return destination;     }

    @Override
    public void convertFrom(Unit source) throws Exception {
        if(!(source instanceof Speed))
            throw new Exception("Unconvertible");
        
        this.fromSpeed(((Speed)source).toSpeed());    }
    
    @Override
    public String toString() {
        return "Speed{" + amount + " type=" + type + '}';
    }
}
