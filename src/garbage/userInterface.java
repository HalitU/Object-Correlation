package garbage;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.util.*;

import particleStrategy.*;
import particleStrategy.correlationClasses.CorrelationCompareOD;

public class userInterface extends JFrame{

	Particle par1;
	Particle par2;
	Particle[][] map;
	CorrelationCompareOD compareOD;
	JPanel panel = new JPanel();
	JLabel label;
	JLabel label2;
	ArrayList<Path[]> pathArray = new ArrayList<Path[]>();
	ArrayList<Path> pathList = new ArrayList<Path>();
	ArrayList<Path> pathList2 = new ArrayList<Path>();
	PathDrawer path; 
	userInterface userFace;
	
	public userInterface(Particle[][] map, Particle par1,Particle par2, CorrelationCompareOD compareOD){
		this.par1=par1;
		this.par2=par2;
		this.map=map;
		this.compareOD=compareOD;
		this.userFace=this;
		
		for(int i=0;i<par1.getPath().getX().size();i++){
			Path p = new Path(30+par1.getPath().getX().get(i),610-par1.getPath().getY().get(i),Color.RED);
			Path p2 = new Path(30+par2.getPath().getX().get(i),610-par2.getPath().getY().get(i),Color.BLUE);
			Path[] pathArr = new Path[2];
			pathArr[0]=p;
			pathArr[1]=p2;
			pathArray.add(pathArr);
		}
	}
	
	public void draw(){
		
		JLabel name = new JLabel();
		name.setText("Birinci Parçacýk");
		name.setBounds(900, 80, 120, 20);
		add(name);	
		
		JLabel name2 = new JLabel();
		name2.setText("Ýkinci Parçacýk");
		name2.setBounds(900, 200, 120, 20);
		add(name2);
		
		label2 = new JLabel();
		label2.setText("initial");
		label2.setBounds(900, 220, 120, 20);
		add(label2);
		
		label = new JLabel();
		label.setText("initial");
		label.setBounds(900, 100, 120, 20);
		add(label);

		path = new PathDrawer();
		path.setPreferredSize(new Dimension(850,630));
		path.setBackground(Color.WHITE);
		path.setLocation(10, 10);

		add(path);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setSize(1100, 700);
		//setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	class Path{
		int x,y;
		Color c;
		
		public Path(int x,int y,Color c){
			this.x=x;
			this.y=y;
			this.c=c;
		}
		
		public void draw(Graphics g){
			g.setColor(c);
			g.drawOval(x, y, 100, 100);
		}
	}

	
	
	class PathDrawer extends Canvas{
		private int x,y;
		private Color color;
		@Override
		public void paint(Graphics g){
			int no=0;
			//map part
			for(int i=10;i<=610;i+=10){
				no=610-i;
				if(no%20==0) g.drawString(no+"", 5, 5+i);
				g.drawLine(30, i, 830, i);
			}
			for(int i=30;i<=830;i+=10){
				no=i-30;
				if(no%40==0 && no/100==0) g.drawString(no + "", i-5, 625);
				if(no%40==0 && no/100!=0) g.drawString(no + "", i-7, 625);
				g.drawLine(i, 10, i, 610);
			}
			//object part
			for(int i=0;i<pathArray.size();i++){
				final int counter=i;
				TimerTask task = new TimerTask() {
					@Override
					public void run() {
						pathArray.get(counter)[0].draw(g);
						pathArray.get(counter)[1].draw(g);
						validate();
				        label.setText(Double.toString(par1.getPath().getOriginDistance().get(counter)));
				        label2.setText(Double.toString(par2.getPath().getOriginDistance().get(counter)));
				        validate();
					}
				};
				Timer timer = new Timer();
				timer.schedule(task, counter*100);
			}
		}
	}
}
