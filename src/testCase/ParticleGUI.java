package testCase;

import java.util.ArrayList;
import java.util.List;

import GUI.userUI;
import particleStrategy.MoveFactory;
import particleStrategy.Particle;
import particleStrategy.MoveStrategy;
import particleStrategy.correlationClasses.AutoCorrelation;
import particleStrategy.correlationClasses.CorrelationCompare;
import particleStrategy.correlationClasses.CorrelationCompareOD;
import particleStrategy.correlationClasses.CorrelationTimeParticleDistance;
import particleStrategy.correlationClasses.TriangularCompare;
import particleStrategy.moveStrategy.CompositeStrategy;
import particleStrategy.moveType.MoveType;

public class ParticleGUI {

	public static void main(String[] args){
		
		//map and MoveFactory objects
		Particle[][] map = new Particle[600][800]; //800x600 map area
		MoveFactory moveFactory = new MoveFactory();
		
		//strategy of first particle
		List<MoveStrategy> compositeMove = new ArrayList<MoveStrategy>();
		for(int i=0;i<0;i++)compositeMove.add(moveFactory.getMoveStrategy(MoveType.DOWN));
		for(int i=0;i<1;i++)compositeMove.add(moveFactory.getMoveStrategy(MoveType.RIGHT));
		for(int i=0;i<0;i++)compositeMove.add(moveFactory.getMoveStrategy(MoveType.LEFT));
		for(int i=0;i<1;i++)compositeMove.add(moveFactory.getMoveStrategy(MoveType.UP));
		MoveStrategy compositeStrategy = new CompositeStrategy(compositeMove);
		
		
		//the strategy of the second particle
		List<MoveStrategy> compositeMove2 = new ArrayList<MoveStrategy>();
		for(int i=0;i<1;i++)compositeMove2.add(moveFactory.getMoveStrategy(MoveType.UP));
		for(int i=0;i<0;i++)compositeMove2.add(moveFactory.getMoveStrategy(MoveType.LEFT));
		for(int i=0;i<0;i++)compositeMove2.add(moveFactory.getMoveStrategy(MoveType.DOWN));
		for(int i=0;i<1;i++)compositeMove2.add(moveFactory.getMoveStrategy(MoveType.RIGHT));
		MoveStrategy compositeStrategy2 = new CompositeStrategy(compositeMove2);
		
		//error movement
		List<MoveStrategy> errorMove = new ArrayList<MoveStrategy>();
		for(int i=0;i<1;i++)errorMove.add(moveFactory.getMoveStrategy(MoveType.UP));
		for(int i=0;i<0;i++)errorMove.add(moveFactory.getMoveStrategy(MoveType.LEFT));
		for(int i=0;i<2;i++)errorMove.add(moveFactory.getMoveStrategy(MoveType.DOWN));
		for(int i=0;i<1;i++)errorMove.add(moveFactory.getMoveStrategy(MoveType.RIGHT));
		MoveStrategy errorStrategy = new CompositeStrategy(errorMove);
		
		//particles with corresponding strategies
		Particle par1 = new Particle(250,250,map,compositeStrategy);
		Particle par2 = new Particle(255,255,map,compositeStrategy2);
		CorrelationTimeParticleDistance tod; 
		
		userUI ui=new userUI();	
		ui.updateComponent(par1, par2);
		ui.updateMap(par1,par2);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Movement
		double toss;
		for(int i=0;i<100;i++){
			toss=Math.random()*100;
			if(toss<20){
				par2.setMoveStrategy(errorStrategy);
				par1.move();
				par2.move();
				tod = new CorrelationTimeParticleDistance(par1, par2);
				ui.updateComponent(par1, par2);
				ui.updateMap(par1,par2);
				par2.setMoveStrategy(compositeStrategy2);
			}else{
				par1.move();
				par2.move();
				tod = new CorrelationTimeParticleDistance(par1, par2);
				ui.updateComponent(par1, par2);
				ui.updateMap(par1,par2);
			}
		}
		
//		CorrelationCompareOD compareOD = new CorrelationCompareOD(par1.getPath(), par2.getPath());
//		compareOD.compare();
//		System.out.println(compareOD.toString());
//		
//		System.out.println(ui.getDistanceCorr().getDeviation());
//		
//		System.out.println(ui.getDistanceCorr().getDeviation()/20);
//		
//		System.out.println(compareOD.correlationOD/(ui.getDistanceCorr().getDeviation()/20));
//		
//		System.out.println(ui.getObjectListOne().size());
//		System.out.println(ui.getObjectListTwo().size());
//		System.out.println(ui.getDistanceListOne().length);
//		System.out.println(ui.getDistanceListTwo().length);
//		System.out.println(ui.getCorrList().size());
//		System.out.println(ui.getTriangularArea().getAreaList().size());
//		System.out.println(ui.getAreaCorrList().size());
//		System.out.println(par1.getPath().getX().size());
//		AutoCorrelation autoCorrelation = new AutoCorrelation(par1, par2);		
//		System.out.println(autoCorrelation);
	}
}
