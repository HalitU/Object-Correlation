package particleStrategy.correlationClasses;

import particleStrategy.ParticlePath;

public class CorrelationCompareOD {
	ParticlePath path1;
	ParticlePath path2;
	
	private double totalOD1=0;
	private double totalOD2=0;
	private double totalOD1OD2=0;
	private double totalOD1Sqr=0;
	private double totalOD2Sqr=0;
	public double correlationOD=0;	
	private String[] correlationList;
	
	public CorrelationCompareOD(ParticlePath path1,ParticlePath path2){
		this.path1=path1;
		this.path2=path2;
		correlationList = new String[path1.getOriginDistance().size()];
	}	
	public void compare(){
		for(int i=0;i<path1.getX().size();i++){
			totalOD1+=path1.getOriginDistance().get(i);
			totalOD2+=path2.getOriginDistance().get(i);
			totalOD1OD2+=path1.getOriginDistance().get(i) *	path2.getOriginDistance().get(i);
			totalOD1Sqr+=path1.getOriginDistance().get(i) * path1.getOriginDistance().get(i);
			totalOD2Sqr+=path2.getOriginDistance().get(i) * path2.getOriginDistance().get(i);
			correlationOD=
					(((path1.getX().size())*totalOD1OD2)-(totalOD1*totalOD2))
					/
					Math.sqrt(
								((path1.getX().size())*totalOD1Sqr-totalOD1*totalOD1)
								*
								((path2.getX().size())*totalOD2Sqr-totalOD2*totalOD2)
							);
			correlationList[i]=correlationOD+"";
		
		}
		correlationOD=
				(((path1.getX().size())*totalOD1OD2)-(totalOD1*totalOD2))
				/
				Math.sqrt(
							((path1.getX().size())*totalOD1Sqr-totalOD1*totalOD1)
							*
							((path2.getX().size())*totalOD2Sqr-totalOD2*totalOD2)
						);	
	}
	public String[] getCorrelationList(){
		return correlationList;
	}
	@Override
	public String toString(){
		return correlationOD + "";
	}
}
