package particleStrategy.moveStrategy;

import particleStrategy.MoveStrategy;
import particleStrategy.Particle;

public class RightStrategy implements MoveStrategy {

	@Override
	public void move(Particle[][] map,int xCos, int yCos,Particle par){
		if(xCos==map[0].length-1){
			map[yCos][xCos]=null;
			par.setxCos(0);
			map[yCos][0]=par;
		}
		else{
			map[yCos][xCos++]=null;
			map[yCos][xCos]=par;
			par.setxCos(xCos);
		}
	}
}
