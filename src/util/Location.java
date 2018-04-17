/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import util.units.Coordinate;
import util.units.Distance;
import util.units.unitTypes.DirectionType;
import util.units.unitTypes.DistanceType;

/**
 *
 * @author ovatman
 */
public class Location{
    private Coordinate latitude;
    private Coordinate longitude;
    private Distance altitude;
    
    public Location(){
        latitude = new Coordinate((short)0,(short)0,(short)0, DirectionType.E);
        longitude = new Coordinate((short)0,(short)0,(short)0, DirectionType.N);
        //Set altitude
    }

    public void moveVertical(int degree, int minute, int second, DirectionType dir) {
        if(dir==latitude.getDirection()){
            if(dir==DirectionType.N){
                latitude.incrementDegree(degree);
                latitude.incrementMinute(minute);
                latitude.incrementSecond(second);
            }
            else{ 
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }
        else{
            if(dir==DirectionType.N){
                latitude.incrementDegree(degree);
                latitude.incrementMinute(minute);
                latitude.incrementSecond(second);
            }
            else{ 
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }
    }

    public Coordinate getLatitude() {
        return latitude;
    }

    public void setLatitude(Coordinate latitude) {
        this.latitude = latitude;
    }

    public Coordinate getLongitude() {
        return longitude;
    }

    public void setLongitude(Coordinate longitude) {
        this.longitude = longitude;
    }

    public Distance getAltitude() {
        return altitude;
    }

    public void setAltitude(Distance altitude) {
        this.altitude = altitude;
    }

    
    
    public double getDistanceFrom(Location to, DistanceType dunit) {
        double lon1=longitude.getAmount();
        double lon2=to.getLongitude().getAmount();
        double lat1=latitude.getAmount();
        double lat2=to.getLatitude().getAmount();
                
        
        final int R = 6371; // Radius of the earth

        Double latDistance = deg2rad(lat2 - lat1);
        Double lonDistance = deg2rad(lon2 - lon1);
        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
            + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))
            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double dist = (R * c)/1.609344; // convert to meters

        if (dunit == DistanceType.KMET) {
            dist = dist * 1.609344;
        } else if (dunit == DistanceType.MET) {
            dist = dist * 1000;
        }else if (dunit == DistanceType.NAUML) {
            dist = dist * 0.8684;
        }
        return dist;  
        
       
    }
    
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }
    private double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

    @Override
    public String toString() {
        return "Location{" + "latitude=" + latitude + ", longitude=" + longitude + ", altitude=" + altitude + '}';
    }


 
    
    public Location clone(){
        Location loc=new Location();
        
        loc.latitude=this.latitude.clone();
        
        loc.longitude=this.longitude.clone();
        
        return loc;
    }

    
}
