/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.units;

import util.units.unitTypes.AngularType;
import util.units.unitTypes.DistanceType;

/**
 *
 * @author ovatman
 */
public class Angular extends Unit{
   private AngularType type;
    
    public Angular(double amount,AngularType type) {
        super(amount);
        this.type=type;
    }

    private double toDegree() throws Exception{
        switch(type){
            case DEGREE:
                return amount;
            case RADIAN:
                return amount*(180/Math.PI);
            case GRAD:
                return amount*(10.0/9.0);
        }
        throw new Exception("Unknown type!!!");
    }

    private void fromDegree(double degree) throws Exception {  
        switch(type){
            case DEGREE:
                amount=degree;
                break;
            case RADIAN:
                amount=degree*(Math.PI/180);
                break;
            case GRAD:
                amount=degree*(9.0/10.0);
                break;
            default:
                throw new Exception("Unknown type!!!");
        }
        
    }
    
    public Angular clone(){
        return new Angular(this.amount,this.type);
    }
    
    public void makeAbsoulte() throws Exception{
        fromDegree( (toDegree()+720)%360 );
    }
    
    
    @Override
    public Unit convertTo(Unit destination) throws Exception {
        if(!(destination instanceof Angular))
            throw new Exception("Unconvertible");
        
        ((Angular)destination).fromDegree(this.toDegree());
        return destination; 
    }

    @Override
    public void convertFrom(Unit source) throws Exception {
        if(!(source instanceof Angular))
            throw new Exception("Unconvertible");
        
        this.fromDegree(((Angular)source).toDegree());
    }

    @Override
    public String toString() {
        return "Angular{" + amount + " type=" + type + '}';
    }
    
    
    
}
