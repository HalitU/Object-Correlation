package testCase;

import util.Location;
import util.units.Angular;
import util.units.Coordinate;
import util.units.Distance;
import util.units.Speed;
import util.units.Temporal;
import util.units.Thermal;
import util.units.unitTypes.AngularType;
import util.units.unitTypes.DirectionType;
import util.units.unitTypes.DistanceType;
import util.units.unitTypes.SpeedType;
import util.units.unitTypes.TemporalType;
import util.units.unitTypes.ThermalType;


public class UtilityTest {

	public static void main(String[] args) throws Exception{
		
		
		Location aLoc = new Location();
		
		aLoc.setLatitude(new Coordinate((short)12,(short)13,23.34,DirectionType.N));

		aLoc.setLongitude(new Coordinate((short)12,(short)13,23.34,DirectionType.E));
		
		System.out.println(aLoc);
		
		
		Thermal amount1 = new Thermal(0,ThermalType.FAHRENHEIT);
		Thermal amount2 = new Thermal(0,ThermalType.CELCIUS);
		Thermal amount3 = new Thermal(1,ThermalType.KELVIN);
		
		amount3.convertTo(amount1);
		System.out.println(amount1);
		
		Angular ang1 = new Angular(57.9,AngularType.DEGREE);
		Angular ang2 = new Angular(57,AngularType.RADIAN);
	
		ang2.convertFrom(ang1);
		System.out.println(ang2);

		Speed spe1 = new Speed(1.152,SpeedType.MPH);
		Speed spe2 = new Speed(0,SpeedType.KNOT);
		Speed spe3 = new Speed(0,SpeedType.KMH);
		
		spe1.convertTo(spe2);
		System.out.println(spe2);
		
		Temporal tem1 = new Temporal(1,TemporalType.SECOND);
		Temporal tem2 = new Temporal(1,TemporalType.HOUR);
		Temporal tem3 = new Temporal(60,TemporalType.MINUTE);
		Temporal tem4 = new Temporal(1,TemporalType.MILLISECOND);

		tem3.convertTo(tem2);
		System.out.println(tem2);
		
		Distance dis1 = new Distance(20,DistanceType.ML);
		Distance dis2 = new Distance(1609.344 ,DistanceType.MET);
		Distance dis3 = new Distance(30,DistanceType.KMET);
		Distance dis4 = new Distance(30,DistanceType.NAUML);
		
		dis3.convertFrom(dis2.convertTo(dis1));
		System.out.println(dis3);
	}
}

