package particleStrategy.correlationClasses;

import java.util.ArrayList;

import particleStrategy.Particle;

public class AutoCorrelation {

	Particle par1;
	Particle par2;
	double autoCorrelation;
	double mean;
	ArrayList<Double> path1 = new ArrayList<Double>();
	ArrayList<Double> path2 = new ArrayList<Double>();
	ArrayList<Double> difference = new ArrayList<Double>();
	private ArrayList<String> results = new ArrayList<String>();
	private ArrayList<Double> autoCor = new ArrayList<Double>();
	private ArrayList<Double> autoVar = new ArrayList<Double>();
	private Double[] distanceVal;
	private int size;
	
	public AutoCorrelation(Particle par1,Particle par2){
		this.par1=par1;
		this.par2=par2;
		this.path1 = par1.getPath().getOriginDistance();
		this.path2 = par2.getPath().getOriginDistance();
		size = par1.getPath().getOriginDistance().size();
		distanceVal = new Double[par1.getPath().getOriginDistance().size()];
		for(int i=0;i<par1.getPath().getOriginDistance().size();i++){
			distanceVal[i]=path1.get(i)-path2.get(i);
		}
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
		for(int i=1;i<difference.size();i++){
			calculateAutoCorrelation(i);
			autoCor.add(autoCorrelation);
		}
		calculateVariance();
	}
	
	public ArrayList<Double> getAutoVarianceList(){
		return autoVar;
	}
	
	private void calculateVariance(){
		double passer=0;
		double varianceError=0;
		for(int i=0;i<autoCor.size();i++){
			for(int j=0;j<i;j++){
				passer+=autoCor.get(j);
			}
			varianceError = (1/(double)size)*(1+2*passer);
			autoVar.add(varianceError);
		}
	}
	
	private void calculateAutoCorrelation(int lag){
		double passer=0;
		double iteration=0;
		double iteration2=0;
		
		for(int i=0;i<difference.size();i++){
			passer+=difference.get(i);
		}
		
		mean = passer/size;
		
		for(int i=0;i<difference.size()-lag;i++){
			iteration+=(difference.get(i)-mean)*(difference.get(i+lag)-mean);
		}
		for(int i=0;i<path1.size();i++){
			iteration2+=(difference.get(i)-mean)*(difference.get(i)-mean);
		}
		autoCorrelation = iteration/iteration2;
		results.add(Double.toString(autoCorrelation));
					
	}
	
	public ArrayList<String> getResults(){
		return results;
	}
	
	public ArrayList<Double> getDifference(){
		return difference;
	}
	
	public Double getUpperCrit(){
		return 2/Math.sqrt(difference.size());
	}
	
	public Double getLowerCrit(){
		return -2/Math.sqrt(difference.size());
	}
	
	@Override
	public String toString(){
		String allResult="";
		for(int i=0;i<results.size();i++){
			allResult+=results.get(i)+ " " + autoVar.get(i) +"\n";
		}
		allResult+="UpperCritic-Val : " + Double.toString(2/Math.sqrt(difference.size())) +
					"\n" + "LowerCritic-Val : " + Double.toString(-2/Math.sqrt(difference.size())); 
		return allResult;
	}
}
