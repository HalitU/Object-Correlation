package garbage;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.ScrollPane;

import javax.swing.JFrame;
import javax.swing.JPanel;
import particleStrategy.*;

public class UserInterfaceOne extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Particle par1;
	Particle par2;
	Particle[][] map;
	
	public UserInterfaceOne(Particle[][] map, Particle par1,Particle par2){
		this.par1=par1;
		this.par2=par2;
		this.map=map;
	}
	
	public void draw(){
		
		PathDrawer path = new PathDrawer();
		path.setBackground(Color.WHITE);
		path.setPreferredSize(new Dimension(500,500));
		ScrollPane scr = new ScrollPane();
		scr.add(path);

		JFrame frame = new JFrame();
		frame.getContentPane().add(scr);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setSize(1000, 1000);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}

	class PathDrawer extends Canvas{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		@Override
		public void setPreferredSize(Dimension preferredSize) {
			// TODO Auto-generated method stub
			super.setPreferredSize(new Dimension(10000,10000));
		}
		@Override
		public void setBackground(Color c) {
			// TODO Auto-generated method stub
			super.setBackground(Color.WHITE);
		}
		@Override
		public void paint(Graphics g){
			
			int no;
			for(int i=40;i<=20*map.length+20;i+=20){
				no=map.length - (i/20-2) - 1;
				g.drawString(""+ no, 10, 6 + i);
				g.drawLine(40, i, 20*map[0].length+20, i);
			}
			for(int i=40;i<=20*map[0].length+20;i+=20){
				no=(i/20-2);
				g.drawString(""+ no, i - 5, map.length*20+40);
				g.drawLine(i, 40, i, 20*map.length+20);
			}
			for(int i=1;i<par1.getPath().getX().size();i++){
				g.setColor(Color.RED);
				g.drawRect(40 + par1.getPath().getX().get(i)*20 - 4 , 20 + 20*map.length - par1.getPath().getY().get(i)*20 - 3, 8, 8);
			}
			
			for(int i=1;i<par2.getPath().getX().size();i++){
				g.setColor(Color.BLUE);
				g.drawRect(40 + par2.getPath().getX().get(i)*20 - 4 , 20 + 20*map.length - par2.getPath().getY().get(i)*20 - 3, 8, 8);
			}
		}
	}
	
}
