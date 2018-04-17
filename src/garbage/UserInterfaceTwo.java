package garbage;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import particleStrategy.*;
import particleStrategy.correlationClasses.CorrelationCompareOD;

public class UserInterfaceTwo{

	Particle par1;
	Particle par2;
	Particle[][] map;
	CorrelationCompareOD compareOD;
	JLabel label;
	JLabel label2;
	JFrame frame = new JFrame();
	JFrame screen = new JFrame();
	JDialog dialog;
	ArrayList<Object[]> pathArray = new ArrayList<Object[]>();
	ArrayList<Object> pathList = new ArrayList<Object>();
	ArrayList<Object> pathList2 = new ArrayList<Object>();
	ArrayList<JPanel> panelList = new ArrayList<JPanel>();
	ArrayList<JPanel> valueList = new ArrayList<JPanel>();
	ArrayList<JLabel> labelList = new ArrayList<JLabel>();
	ArrayList<JLabel> labelList2 = new ArrayList<JLabel>();
	String[] textListOne,textListTwo;
	UserInterfaceTwo userFace;
	PathDrawer path;
	Timer timer; 
	int counter=0;
	int counter2=0;
	int counter3=0;
	
	public UserInterfaceTwo(Particle[][] map, Particle par1,Particle par2, CorrelationCompareOD compareOD){
		this.par1=par1;
		this.par2=par2;
		this.map=map;
		this.compareOD=compareOD;
		this.userFace=this;
		
		textListOne = new String[par1.getPath().getOriginDistance().size()];
		textListTwo = new String[par2.getPath().getOriginDistance().size()];

		for(int i=0;i<par1.getPath().getX().size();i++){
			
			textListOne[i]=Double.toString(par1.getPath().getOriginDistance().get(i));
			textListTwo[i]=Double.toString(par2.getPath().getOriginDistance().get(i));
			
			labelList.add(new JLabel());
			labelList.get(i).setText(Double.toString(par1.getPath().getOriginDistance().get(i)));
			labelList.get(i).setBounds(900, 100, 120, 20);
			
			labelList2.add(new JLabel());
			labelList.get(i).setText(Double.toString(par2.getPath().getOriginDistance().get(i)));
			labelList.get(i).setBounds(900, 220, 120, 20);
			
			valueList.add(new JPanel(){
				protected final int count = counter2;
				protected void paintComponent(Graphics g){
					g.drawString(Double.toString(par1.getPath().getOriginDistance().get(count)), 900, 120);
					g.drawString(Double.toString(par2.getPath().getOriginDistance().get(count)), 900, 240);
				}
			});
			
			Object p = new Object(30+par1.getPath().getX().get(i),610-par1.getPath().getY().get(i),Color.RED);
			Object p2 = new Object(30+par2.getPath().getX().get(i),610-par2.getPath().getY().get(i),Color.BLUE);
			Object[] pathArr = new Object[2];
			pathArr[0]=p;
			pathArr[1]=p2;
			pathArray.add(pathArr);
			
			panelList.add(new JPanel(){
				protected final int count = counter2;
				protected void paintComponent(Graphics g){
					pathArray.get(count)[0].draw(g);
					pathArray.get(count)[1].draw(g);
				}
			});
			counter2++;
		}
	}
	
	public void draw(){

		getLabel();
		getDialog();
		
		path = new PathDrawer();
		path.setBounds(0, 0, 850, 630);
		path.setBackground(Color.WHITE);
		path.setOpaque(true);
		frame.getContentPane().add(path);
		
		for(int i=0;i<par1.getPath().getOriginDistance().size();i++){
			//update labels
			TimerTask task = new TimerTask() {
				public final int sayi=counter;
				private JPanel first=valueList.get(0);
				private JPanel second;

				@Override
				public void run() {
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							frame.getContentPane().add(panelList.get(sayi));
					        frame.validate();
					        label.setText(textListOne[sayi]);
					        label2.setText(textListOne[sayi]);
						}
					});
				}
			};
			Timer timer = new Timer();
			timer.schedule(task, (counter+1)*10);

			counter++;
		}
		
		frame.pack();
		frame.setSize(1100, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		dialog.setVisible(true);
		
	}
	
	private void getDialog(){
		
		JDialog.setDefaultLookAndFeelDecorated(true);
		dialog = new JDialog();
		dialog.setTitle("Partiküller");
		dialog.setSize(400,400);
		dialog.setLocation(1000, 200);
		dialog.setLayout(new FlowLayout());
	}
	
	private void getLabel(){
		JLabel name = new JLabel();
		name.setText("Birinci Parçacýk");
		
		label = new JLabel();
		label.setText("initial");
		
		JLabel name2 = new JLabel();
		name2.setText("Ýkinci Parçacýk");
		
		label2 = new JLabel();
		label2.setText("initial");
	
	}
	
	class Object{
		int x,y;
		Color c;
		
		public Object(int x,int y,Color c){
			this.x=x;
			this.y=y;
			this.c=c;
		}
		
		public void draw(Graphics g){
			g.setColor(c);
			g.drawOval(x, y, 0, 0);
		}
	}

	class PathDrawer extends JPanel{
		public final static int INTERVAL = 1000;
		@Override
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			//map part
			int no=0;
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
		}
	}
}
