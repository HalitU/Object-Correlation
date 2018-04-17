package particleStrategy.correlationClasses;

import java.util.ArrayList;

import particleStrategy.Particle;

public class CorrelationTimeParticleDistance {
	
	Particle particleOne;
	Particle particleTwo;
	ArrayList<Double> difference = new ArrayList<Double>();
	private double totalTD1=0;
	private double totalTD2=0;
	private double totalTD1TD2=0;
	private double totalTD1Sqr=0;
	private double totalTD2Sqr=0;
	private double correlationTD=0;	
	private double s=0;
	private double average=0;
	private ArrayList<Double> distanceList = new ArrayList<Double>();
	
	public CorrelationTimeParticleDistance(Particle par1,Particle par2){
		particleOne=par1;
		particleTwo=par2;
		int size=par1.getPath().getX().size();
		double dif=0;
		for(int i=0;i<size;i++){
			dif = Math.sqrt(
								(
										(par1.getPath().getX().get(i)-par2.getPath().getX().get(i))
										*
										(par1.getPath().getX().get(i)-par2.getPath().getX().get(i))
								)
								+
								(
										(par1.getPath().getY().get(i)-par2.getPath().getY().get(i))
										*
										(par1.getPath().getY().get(i)-par2.getPath().getY().get(i))
								)
							);
			difference.add(dif);
		}
		calculatePearsonCorrelation();
		calculateDeviation();
		calculateAverageDistance();
	}
	private void calculateAverageDistance(){
		for(int i=0;i<difference.size();i++){
			average+=difference.get(i);
		}
		average=average/difference.size();
	}
	
	private void calculateDeviation(){
//		double mean=0;
//		for(int i=0;i<difference.size();i++){
//			if(difference.get(i)<20)
//				mean+=20;
//			else	
//				mean+=difference.get(i);
//		}
//		mean=mean/difference.size();
		double total=0;
		for(int i=0;i<difference.size();i++){
			if(difference.get(i)<=20)
				total+=20*20;
			else
				total+=(difference.get(i))*(difference.get(i));
		}
		s=Math.sqrt(total/(difference.size()));
		distanceList.add(s);
	}
	
	private void calculatePearsonCorrelation(){
		for(int i=0;i<difference.size();i++){
			totalTD1+=difference.get(i);
			totalTD2+=(i);
			totalTD1TD2+=difference.get(i) * (i);
			totalTD1Sqr+=difference.get(i) * difference.get(i);
			totalTD2Sqr+=(i) * (i);
		}
		correlationTD=
				(((difference.size())*totalTD1TD2)-(totalTD1*totalTD2))
				/
				Math.sqrt(
							((difference.size())*totalTD1Sqr-totalTD1*totalTD1)
							*
							((difference.size())*totalTD2Sqr-totalTD2*totalTD2)
						);
	}
	public Double getAverage(){
		return average;
	}
	
	public Double getDeviation(){
		return s;
	}
	
	public String toString(){
		return Double.toString(correlationTD);
	}
}
