package GUI;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import particleStrategy.Particle;
import particleStrategy.correlationClasses.CorrelationCompareOD;
import particleStrategy.correlationClasses.CorrelationTimeParticleDistance;
import particleStrategy.correlationClasses.TriangleCorrelation;
import particleStrategy.correlationClasses.TriangularCompare;

public class userUI extends JFrame {
		//field
		private String[] textListOne,textListTwo;
		private int count=0,counter=0,controller=0;
		private JDialog dialog;
		private JLabel name,label,name2,label2,corName,corVal,triName,triVal,triName2,triCorr;
		private JLabel deviationName,deviationResult,resultName,result;
		private ArrayList<Object> objectList,objectList2;
		private Map map;
		private CorrelationTimeParticleDistance correlationValue;
		private TriangularCompare triComp;
		private ArrayList<String> corrList = new ArrayList<String>();
		private ArrayList<Double> sCorrList = new ArrayList<Double>();
		private ArrayList<String> areaCorrList = new ArrayList<String>();
		private TriangleCorrelation triCor;
		private CorrelationCompareOD compareOD;
		
		//main frame and initial components
 		public userUI(){
			//initial frame properties
			setTitle("Particle Movement");
			setSize(860,670);
			setLayout(new BorderLayout());
			
			//prepare initial label for dialog
			prepareDialog();
			
			//Map panel...
			map = new Map();
			setSize(860, 670);
			setLayout(new BorderLayout());
			add(map);
			
			//set frame and dialog visible...
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setVisible(true);
			dialog.setVisible(true);
			
		}
		//adds elements to UI's components
		public void updateComponent(Particle par1,Particle par2){
			compareOD = new CorrelationCompareOD(par1.getPath(),par2.getPath());
			compareOD.compare();
			textListOne = new String[par1.getPath().getOriginDistance().size()];
			textListTwo = new String[par2.getPath().getOriginDistance().size()];
			objectList = new ArrayList<Object>();
			objectList2 = new ArrayList<Object>();
			for(int i=0;i<par1.getPath().getOriginDistance().size();i++){
				textListOne[i]=Double.toString(par1.getPath().getOriginDistance().get(i));
				textListTwo[i]=Double.toString(par2.getPath().getOriginDistance().get(i));
				objectList.add(new Object(par1.getPath().getX().get(i),par1.getPath().getY().get(i),Color.RED));
				objectList2.add(new Object(par2.getPath().getX().get(i),par2.getPath().getY().get(i),Color.BLUE));
			}
		}
		//refresh and update map components
		public void updateMap(Particle par1,Particle par2){
				triComp = new TriangularCompare(par1, par2);
				correlationValue = new CorrelationTimeParticleDistance(par1, par2);
				corrList.add(correlationValue.toString());
				sCorrList.add(correlationValue.getDeviation());
				triCor = new TriangleCorrelation(triComp.getAreaList());
				areaCorrList.add(Double.toString(triCor.getCorrList()));
				ActionListener action = new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent evt) {
						// TODO Auto-generated method stub
						label.setText(textListOne[count]);
						label.repaint();
						label2.setText(textListTwo[count]);
						label2.repaint();
						corVal.setText(compareOD.getCorrelationList()[count]);
						corVal.repaint();
						if(controller==0) controller=1;
						else if((count+1)!=textListOne.length){
							triVal.setText(Double.toString(triComp.getArea(count+1)-triComp.getArea(count)));
							triCorr.setText(areaCorrList.get(count));
						}
						deviationResult.setText(Double.toString(sCorrList.get(count)/20));
						if(count==par1.getPath().getX().size()-1){
							updateFinalResult();
						}
						triVal.repaint();
						map.updateComponent(count);
						map.repaint();
						count++;
					}
				};
				Timer timer = new Timer((counter+1)*100,action);
				timer.setRepeats(false);
				timer.start();
				counter++;
		}
		//initial Dialog components
		private void prepareDialog(){
			name=new JLabel();
			name.setText("ParOne Origin Dist.");
			name.setLayout(new BorderLayout());
			name.setAlignmentX(Component.CENTER_ALIGNMENT);
			name.setOpaque(true);
			
			label = new JLabel();
			label.setText("please wait...");
			label.setBackground(Color.BLACK);
			label.setLayout(new BorderLayout());
			label.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			name2 = new JLabel();
			name2.setText("ParTwo Origin Dist.");
			name2.setBackground(Color.BLACK);
			name2.setLayout(new BorderLayout());
			name2.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			label2 = new JLabel();
			label2.setText("please wait...");
			label2.setBackground(Color.BLACK);
			label2.setLayout(new BorderLayout());
			label2.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			corName = new JLabel();
			corName.setText("Origin Distance Corr.");
			corName.setBackground(Color.BLACK);
			corName.setLayout(new BorderLayout());
			corName.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			corVal = new JLabel();
			corVal.setText("please wait...");
			corVal.setBackground(Color.BLACK);
			corVal.setLayout(new BorderLayout());
			corVal.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			triName = new JLabel();
			triName.setText("Triangular Area");
			triName.setBackground(Color.BLACK);
			triName.setLayout(new BorderLayout());
			triName.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			triVal = new JLabel();
			triVal.setText("please wait...");
			triVal.setBackground(Color.BLACK);
			triVal.setLayout(new BorderLayout());
			triVal.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			triName2 = new JLabel();
			triName2.setText("Triangular Correlation");
			triName2.setBackground(Color.BLACK);
			triName2.setLayout(new BorderLayout());
			triName2.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			triCorr = new JLabel();
			triCorr.setText("please wait...");
			triCorr.setBackground(Color.BLACK);
			triCorr.setLayout(new BorderLayout());
			triCorr.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			deviationName = new JLabel();
			deviationName.setText("Deviation of Distance");
			deviationName.setBackground(Color.BLACK);
			deviationName.setLayout(new BorderLayout());
			deviationName.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			deviationResult = new JLabel();
			deviationResult.setText("please wait...");
			deviationResult.setBackground(Color.BLACK);
			deviationResult.setLayout(new BorderLayout());
			deviationResult.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			resultName = new JLabel();
			resultName.setText("Final result");
			resultName.setBackground(Color.BLACK);
			resultName.setLayout(new BorderLayout());
			resultName.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			result = new JLabel();
			result.setText("please wait...");
			result.setBackground(Color.BLACK);
			result.setLayout(new BorderLayout());
			result.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			dialog = new JDialog();
			dialog.setTitle("Movement Information");
			dialog.setSize(160, 250);
			dialog.setLocation(900, 200);
			dialog.setLayout(new BoxLayout(dialog.getContentPane(),BoxLayout.Y_AXIS));
			dialog.add(name);
			dialog.add(label);
			dialog.add(name2);
			dialog.add(label2);
			dialog.add(corName);
			dialog.add(corVal);
//			dialog.add(triName);
//			dialog.add(triVal);
//			dialog.add(triName2);
//			dialog.add(triCorr);
			dialog.add(deviationName);
			dialog.add(deviationResult);
			dialog.add(resultName);
			dialog.add(result);
			dialog.repaint();
		}
		public void updateFinalResult(){
			result.setText("%" +
							Double.toString(
									Math.abs((compareOD.correlationOD/(getDistanceCorr().getDeviation()/20))*100)
									)
								);
			result.repaint();
		}
		//get lists used in UI
		public CorrelationTimeParticleDistance getDistanceCorr(){
			return correlationValue;
		}
		public String[] getDistanceListOne(){
			return textListOne;
		}
		public String[] getDistanceListTwo(){
			return textListTwo;
		}
		public ArrayList<String> getAreaCorrList(){
			return areaCorrList;
		}
 		public ArrayList<String> getCorrList(){
			return corrList;
		}
		public ArrayList<Object> getObjectListOne(){
			return objectList;
		}
		public ArrayList<Object> getObjectListTwo(){
			return objectList2;
		}
		public TriangularCompare getTriangularArea(){
			return triComp;
		}
		//Object to draw to map...
		private class Object{
			private int x;
			private int y;
			private Color color;
			private Graphics2D image;
			
			public Object(int xPosition,int yPosition,Color color){
				this.x=xPosition;
				this.y=yPosition;
				this.color=color;
			}
		}
		//Dialog values...
		private class Value extends JDialog{
			JLabel value=null;
			public Value(){
				setTitle("Partiküller");
			}
			public void updateLabel(JLabel value){
				if(this.value==null){
					this.value=value;
					add(value);
					repaint();
				}else{
					remove(this.value);
					this.value=value;
					add(value);
					repaint();
				}
			}
		}
		//Map...
		private class Map extends JPanel{
			private int objectCount=0;
			public Map(){
				setBackground(Color.WHITE);
			}
			@Override
			public void paintComponent(Graphics g){
				super.paintComponent(g);
				int no=0;
				for(int i=10;i<=610;i+=10){
					no=610-i;
					if(no%20==0) g.drawString(no+"", 5, 5+i);
					g.drawLine(30, i, 830, i);
				}
				for(int i=30;i<=830;i+=10){
					no=i-30;
					if(no%40==0 && no/100==0) g.drawString(no + "", i-7, 625);
					if(no%40==0 && no/100!=0) g.drawString(no + "", i-9, 625);
					g.drawLine(i, 10, i, 610);
				}
				for(int i=0;i<objectCount+1;i++){
					g.setColor(Color.RED);
					g.drawOval(30+objectList.get(i).x, 610-objectList.get(i).y, 0, 0);
					g.setColor(Color.BLUE);
					g.drawOval(30+objectList2.get(i).x, 610-objectList2.get(i).y, 0, 0);
				}
			}			
			public void updateComponent(int i){
				objectCount=i;
			}
		}

}
