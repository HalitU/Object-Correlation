package particleStrategy;

public class WriteArray {

	private String map="";
	
	public WriteArray(Particle[][] map){
		for(int i=0;i<map.length;i++){
			for(int j=0;j<map.length;j++){
				if(map[i][j]==null){
					this.map+="Null\t\t ";
				}
				else{
					this.map+=map[i][j].toString() + "\t\t ";	
				}
			}
			this.map+="\n";
		}
	}
	
	@Override
	public String toString(){
		return map;
	}
	
}
