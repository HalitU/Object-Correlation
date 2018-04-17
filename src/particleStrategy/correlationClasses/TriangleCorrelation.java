package particleStrategy.correlationClasses;

import java.util.ArrayList;

public class TriangleCorrelation {
	private double totalAD1,totalTD2,totalAD1TD2,totalAD1Sqr,totalTD2Sqr,correlationAD;
	private ArrayList<Double> triList = new ArrayList<Double>();
	private ArrayList<Double> corrList = new ArrayList<Double>();
	public TriangleCorrelation(ArrayList<Double> triList){
		this.triList=triList;
		calculatePearsonCorrelation();
	}
	private void calculatePearsonCorrelation(){
		for(int i=0;i<triList.size();i++){
			totalAD1+=triList.get(i);
			totalTD2+=(i);
			totalAD1TD2+=triList.get(i) * (i);
			totalAD1Sqr+=triList.get(i) * triList.get(i);
			totalTD2Sqr+=(i) * (i);
		}
		correlationAD=
				(((triList.size())*totalAD1TD2)-(totalAD1*totalTD2))
				/
				Math.sqrt(
							((triList.size())*totalAD1Sqr-totalAD1*totalAD1)
							*
							((triList.size())*totalTD2Sqr-totalTD2*totalTD2)
						);
	}
	public Double getCorrList(){
		return correlationAD;
	}
}
