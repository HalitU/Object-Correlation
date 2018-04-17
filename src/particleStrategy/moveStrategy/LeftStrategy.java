package particleStrategy.moveStrategy;

import particleStrategy.MoveStrategy;
import particleStrategy.Particle;

public class LeftStrategy implements MoveStrategy{

	@Override
	public void move(Particle[][] map, int xCos, int yCos, Particle par) {
		if(xCos==0){
			map[yCos][xCos]=null;
			par.setxCos(map[0].length-1);
			map[yCos][map[0].length-1]=par;
		}
		else{
			map[yCos][xCos--]=null;
			map[yCos][xCos]=par;
			par.setxCos(xCos);
		}
	}
}
