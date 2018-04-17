package particleStrategy.moveStrategy;

import particleStrategy.MoveStrategy;
import particleStrategy.Particle;

public class UpStrategy implements MoveStrategy {
		
	@Override
	public void move(Particle[][] map,int xCos,int yCos,Particle par){

		if(yCos==map.length-1){
			map[yCos][xCos]=null;
			par.setyCos(0);
			map[0][xCos]=par;
		}
		else{
			par.setyCos(yCos+1);
			map[yCos++][xCos]=null;
			map[yCos][xCos]=par;
		}
	}	
}
