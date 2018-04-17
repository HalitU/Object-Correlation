package particleStrategy.correlationClasses;

import java.util.ArrayList;

import particleStrategy.Particle;

public class TriangularCompare {
	private Particle particleOne;
	private Particle particleTwo;
	private ArrayList<Double> areaList = new ArrayList<Double>();
	
	public TriangularCompare(Particle par1,Particle par2){
		particleOne=par1;
		particleTwo=par2;
		calculateArea();
	}
	
	private void calculateArea(){
		double passer=0;
		for(int i=0;i<particleOne.getPath().getX().size();i++){
			passer=(
					(0*(particleOne.getPath().getY().get(i)-particleTwo.getPath().getY().get(i)))
					+
					(particleOne.getPath().getX().get(i)*(particleTwo.getPath().getY().get(i)-0))
					+
					(particleTwo.getPath().getX().get(i)*(0-particleOne.getPath().getY().get(i)))
					)/2;
			passer=Math.abs(passer);
			areaList.add(passer);
		}
	}
	public Double getArea(int count){
		return areaList.get(count);
	}
	
	public ArrayList<Double> getAreaList(){
		return areaList;
	}
}
