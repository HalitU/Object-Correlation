/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.units;

import util.units.unitTypes.AngularType;
import util.units.unitTypes.CoordinateType;
import util.units.unitTypes.DirectionType;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 *
 * @author ovatman
 */
public class Coordinate extends Unit{
   private short degree;
   private short minute;
   private double second;
   
   private DirectionType direction;
   private CoordinateType type;

    public CoordinateType getType() {
        return type;
    }

   
    
    public Coordinate(short degree, short minute, double second, DirectionType direction) {
        super(degree+(((double)minute)/60)+(second/3600));
        
        this.degree=degree;
        this.minute=minute;
        this.second=second;
        
        this.direction=direction;
        this.type=CoordinateType.DECIMALSECONDS;
    }
    
    public Coordinate(short degree,double minuteDegree, DirectionType direction) {
        super(degree+(minuteDegree/60));
        
        this.degree=degree;
        this.minute= (short) minuteDegree;
        this.second= 60*(minuteDegree-((short)minuteDegree));
        this.direction=direction;
        
        this.type=CoordinateType.DECIMALMINUTES;
    }    
    
    public Coordinate(double decimalDegree, DirectionType direction) {
        super(decimalDegree);
        
        this.degree=(short)decimalDegree;
        double minuteDegree= 60*(decimalDegree-((short)decimalDegree));
        this.minute= (short) minuteDegree;
        this.second= 60*(minuteDegree-((short)minuteDegree));
        this.direction=direction;
        
        this.type=CoordinateType.DECIMALDEGREES;
    }
    

        
    public short getDegree() {
        return degree;
    }

    public void setDegree(short degree) {
        this.degree = degree;
        this.amount=degree+(((double)minute)/60)+(second/3600);
    }

    public double getMinute() {
        return minute;
    }

    public void setMinute(short minute) {
        this.minute = minute;
        this.amount=degree+(((double)minute)/60)+(second/3600);
    }

    public double getSecond() {
        return second;
    }

    public void setSecond(double second) {
        this.second = second;
        this.amount=degree+(((double)minute)/60)+(second/3600);
    }
    
    public DirectionType getDirection() {
        return direction;
    }

    public void setDirection(DirectionType direction) {
        this.direction = direction;
    }


    public String getStringasLongitude(){
        return getString(new DecimalFormat("#000"));
    }    
    
    public String getStringasLatitude(){
        return getString(new DecimalFormat("#00"));
    }

    private String getString(NumberFormat latlonFormat){
        NumberFormat lonLanFormatter = new DecimalFormat("#00.000");
        
        StringBuffer s=new StringBuffer("");
        
        s.append(latlonFormat.format(degree)).append(lonLanFormatter.format(minute)).append(",").append(direction.toString());
        return s.toString();       
    }
    
    
    public void incrementDegree(int degree) {
        this.degree+=degree;
        this.amount=this.degree+(((double)minute)/60)+(second/3600);
    }

    public void incrementMinute(int minute) {
        this.minute+=minute;
        this.amount=degree+(((double)this.minute)/60)+(second/3600);
    }

    public void incrementSecond(double second) {
        this.second+=second;
        this.amount=degree+(((double)minute)/60)+(this.second/3600);
    }
   
    private void setAmount(double amount) {
        this.amount=amount;
        
        this.degree=(short)amount;
        double minuteDegree= 60*(amount-((short)amount));
        this.minute= (short) minuteDegree;
        this.second= 60*(minuteDegree-((short)minuteDegree));
    }

    @Override
    public String toString() {
        switch(type){
            case DECIMALDEGREES:
                return "Coordinate{" + "degree=" + degree + ", minute=" + minute + ", second=" + second + ", direction=" + direction + '}';
            case DECIMALMINUTES:
                return "Coordinate{" + "degree=" + degree + ", minute=" + (minute+(second/60)) + ", direction=" + direction + '}';
            case DECIMALSECONDS:
                return "Coordinate{" + "second=" + amount + ", direction=" + direction + '}'; //ATAKAN: second= yerine degree= olmayacak mı?
        }
        return "Coordinate{" + "degree=" + degree + ", minute=" + minute + ", second=" + second + ", direction=" + direction + '}';
    }
    
    public Coordinate clone(){
        Coordinate cor = new Coordinate(this.degree,this.minute,this.second,this.direction);

        return cor;
    }

    @Override
    public Unit convertTo(Unit destination) throws Exception {
        if(!(destination instanceof Coordinate))
            throw new Exception("Unconvertible");
        
        
        ((Coordinate)destination).setAmount(this.amount);
        ((Coordinate)destination).setDirection(this.direction);
        return destination;
    }
    
    @Override
    public void convertFrom(Unit source) throws Exception {
        if(!(source instanceof Coordinate))
            throw new Exception("Unconvertible");
        
        
        this.setAmount(((Coordinate)source).getAmount());
        this.setDirection(((Coordinate)source).getDirection());
    }

    public String getinDegreeDecimalForm() {
        return ""+amount;
    }
}
