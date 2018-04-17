package particleStrategy.moveStrategy;

import particleStrategy.MoveStrategy;
import particleStrategy.Particle;

public class DownStrategy implements MoveStrategy{

	@Override
	public void move(Particle[][] map, int xCos, int yCos, Particle par) {
		if(yCos==0){
			map[yCos][xCos]=null;
			par.setyCos(map.length-1);
			map[map.length-1][xCos]=par;
		}
		else{
			map[yCos--][xCos]=null;
			map[yCos][xCos]=par;
			par.setyCos(yCos);
		}
	}
}
