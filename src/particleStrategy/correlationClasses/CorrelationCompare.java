package particleStrategy.correlationClasses;

import java.util.ArrayList;
import java.util.List;

public class CorrelationCompare {
	
	List<int[]> path1 = new ArrayList<int[]>();
	List<int[]> path2 = new ArrayList<int[]>();
	
	private double totalX1=0;
	private double totalX2=0;
	private double totalX1X2=0;
	private double totalX1Sqr=0;
	private double totalX2Sqr=0;
	private double correlationX=0;
	
	private double totalY1=0;
	private double totalY2=0;
	private double totalY1Y2=0;
	private double totalY1Sqr=0;
	private double totalY2Sqr=0;
	private double correlationY=0;
	
	public CorrelationCompare(List<int[]> path1,List<int[]> path2){
		this.path1=path1;
		this.path2=path2;
	}
	
	public void compare(){
		
		for(int i=1;i<path1.size();i++){
			totalX1+=path1.get(i)[0];
			totalX2+=path2.get(i)[0];
			totalX1X2+=(path1.get(i)[0]*path2.get(i)[0]);
			totalX1Sqr+=(path1.get(i)[0]*path1.get(i)[0]);
			totalX2Sqr+=(path2.get(i)[0]*path2.get(i)[0]);
		}
		
		correlationX=
				(((path1.size()-1)*totalX1X2)-(totalX1*totalX2))
				/
				Math.sqrt(
								((path1.size()-1)*totalX1Sqr-totalX1*totalX1)
						*
								((path1.size()-1)*totalX2Sqr-totalX2*totalX2)
						);
		
		
		for(int i=1;i<path1.size();i++){
			totalY1+=path1.get(i)[1];
			totalY2+=path2.get(i)[1];
			totalY1Y2+=(path1.get(i)[1]*path2.get(i)[1]);
			totalY1Sqr+=(path1.get(i)[1]*path1.get(i)[1]);
			totalY2Sqr+=(path2.get(i)[1]*path2.get(i)[1]);
		}
		
		correlationY=
				(((path1.size()-1)*totalY1Y2)-(totalY1*totalY2))
				/
				Math.sqrt(
								((path1.size()-1)*totalY1Sqr-totalY1*totalY1)
						*
								((path1.size()-1)*totalY2Sqr-totalY2*totalY2)
						);
	}
	
	@Override
	public String toString(){
		return "" + correlationX + " " + correlationY;
	}
	
}
