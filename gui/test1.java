package gui;
import algo.Algorithm;



import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import algo.Algorithm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.awt.GridBagConstraints;
import javax.swing.JRadioButton;
import java.awt.Insets;
import javax.swing.JToggleButton;
import javax.swing.*;
import java.awt.*;

public class test1 extends JFrame {
	private JPanel contentPane, portPane, solutionPaneX;
	public solutionPanel solutionPane;
	public JToggleButton getAnswer, back1, back2, finish;
	public ButtonGroup port_buttons;
	public JRadioButton[] port_set;
	public JLabel forPort, name_fuel, name_cost, solution_statement, solution_start, solution[][];
	public Algorithm data;
	public JSpinner fuel, cost;
	public SpinnerModel for_fuel, for_cost;
	public String[] country_name;
	public String countryx_name, start_port_name;
	//public Graphics g;
	/**
	 * Launch the application.
	 */
	public static void run(Algorithm data, String[] country_name) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test1 frame = new test1(data, country_name);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void run_portPane(){
		if(data.port_names != null){
			int max = 0;
			GridBagConstraints gbc = new GridBagConstraints();
			this.port_set = new JRadioButton[data.port_names.length];
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.anchor = GridBagConstraints.WEST;
			gbc.weightx = 1;
			this.portPane.add(this.forPort, gbc);
			//gbc.gridx = GridBagConstraints.WEST;
			gbc.gridy = 1;
			for(int i = 0; i < data.port_names.length; i++){
				if(max < data.port_names[i].length()){max = data.port_names[i].length();}
				this.port_set[i] = new JRadioButton(data.port_names[i]);
				this.port_buttons.add(this.port_set[i]);
				this.portPane.add(this.port_set[i], gbc);
				gbc.gridy++;
			}
			gbc.weighty = 1;
			gbc.anchor = GridBagConstraints.SOUTH;
			gbc.gridy = GridBagConstraints.SOUTH - 2;
			gbc.gridx = 0;
			this.portPane.add(this.name_fuel, gbc);
			gbc.gridx++;
			this.portPane.add(this.fuel, gbc);
			gbc.gridy++;
			gbc.gridx--;
			this.portPane.add(this.name_cost, gbc);
			gbc.gridx++;
			this.portPane.add(this.cost, gbc);
			gbc.gridy++;
			gbc.gridx = GridBagConstraints.EAST - 1;
			this.portPane.add(this.back1, gbc);
			gbc.gridx++;
			this.portPane.add(this.getAnswer, gbc);
			this.setContentPane(this.portPane);
			this.setSize(500 + max * 10, 400 + this.port_set.length * 10);
			this.setVisible(true);
			System.out.println("yyy");
		}
	}

	public void run_solutionPane(){
		if(this.data.cost_fuel == null || this.data.solution_names == null){
			this.data.show();
			if(this.data.cost_fuel == null || this.data.solution_names == null){return;}
		}
		//this.solutionPaneX = new JPanel();
		this.solutionPane = new solutionPanel(this.data, this.countryx_name, this.start_port_name);
		this.solution = new JLabel[this.data.solution_names.length][4];
		this.solutionPane.repaint();
		this.solutionPane.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = -1;
		//this.solutionPaneX.add(this.solutionPane, gbc);
		int max = 10;
		//this.solutionPane.paintComponent(this.g);
		//Graphics2D g2d = (Graphics2D)g;
		//g = new Xer();
		//g2d.drawOval(10, 10, 10, 10);
		//this.solutionPane.add(g);
		/*
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.ipadx = 1;
		gbc.insets = new Insets(10, 10, 10, 10);
		this.solution_statement = new JLabel("Starting port: ");
		this.solution_start = new JLabel(this.start_port_name);
		this.solutionPane.add(this.solution_statement, gbc);
		gbc.gridx++;
		this.solutionPane.add(this.solution_start, gbc);
		gbc.gridx--;
		gbc.gridy++;
		int max = 0;
		for(int i = 0; i < this.data.solution_names.length; i++){
			this.solution[i][0] = new JLabel(this.data.solution_names[i][0]);
			this.solution[i][1] = new JLabel(this.data.solution_names[i][1]);
			if(max < this.data.solution_names[i][1].length()){
				max = this.data.solution_names[i][1].length();
			}
			this.solution[i][2] = new JLabel("Fuel: "+this.data.cost_fuel[i][0]);
			this.solution[i][3] = new JLabel("Amount: "+this.data.cost_fuel[i][1]);
			this.solutionPane.add(this.solution[i][0], gbc);
			gbc.gridx++;
			this.solutionPane.add(this.solution[i][1], gbc);
			gbc.gridx++;
			this.solutionPane.add(this.solution[i][2], gbc);
			gbc.gridx++;
			this.solutionPane.add(this.solution[i][3], gbc);
			gbc.gridy++;
			gbc.gridx = 0;
		}
		*/
		gbc.anchor = GridBagConstraints.PAGE_END;
		gbc.weighty = 1;
		//gbc.weightx = 1;
		gbc.gridx = GridBagConstraints.EAST - 1;
		gbc.gridy = GridBagConstraints.SOUTH;
		this.solutionPane.add(this.back2, gbc);
		gbc.gridx++;
		this.solutionPane.add(this.finish, gbc);
		this.setContentPane(this.solutionPane);
		//Toolkit tk = Toolkit.getDefaultToolkit();
		//int sizex = (int)tk.getScreenSize().getWidth();
		//int sizey = (int)tk.getScreenSize().getHeight();
		this.setSize(1500, 650);
		//this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		//this.setUndecorated(true);
		this.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public test1(Algorithm data, String[] country_name) {
		this.data = data;
		this.country_name = country_name;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//setBounds(500, 500, 500, 500);
		this.setSize(700, 400);
		contentPane = new JPanel();
		portPane = new JPanel();
		//this.solutionPane = new solutionPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		portPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
			//gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
			//gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			//gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			//gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		portPane.setLayout(gbl_contentPane);
		//this.solutionPane.setLayout(gbl_contentPane);
			/*
			JToggleButton toggleButton = new JToggleButton("New toggle button");
			GridBagConstraints gbc_toggleButton = new GridBagConstraints();
			gbc_toggleButton.insets = new Insets(0, 0, 5, 0);
			gbc_toggleButton.gridx = 4;
			gbc_toggleButton.gridy = 7;
			contentPane.add(toggleButton, gbc_toggleButton);
			
			
			*/
			
			
			
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		/*	JLabel lblNewLabel = new JLabel("New label");
			
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 0;
			contentPane.add(lblNewLabel, gbc_lblNewLabel);
		*/  
              
	/*HashMap<String,Boolean> hm=new HashMap<String,Boolean>();
		      hm.put("Amit",true);    
		      hm.put("Vijay",false);    
		      hm.put("R",false);
		      hm.put("Ra",false);
		      hm.put("Rah",false);
		      hm.put("Rahu",false);
		      hm.put("l",false);
		      hm.put("ul",false);
		      hm.put("hul",false);
		*/
		     // int size=hm.size();
		      		      
		int i=0;
		int x=0;
		int y=0;
		int count=0, size;
		for(Map.Entry<String, Boolean> element: mainw.true_names.entrySet()){
			if(element.getValue()){
				count++;
			}
		}
		size = mainw.true_names.size() - count;
		count = 0;
		JRadioButton[] labels = new JRadioButton[size];
		      //JRadioButton[] rds=new JRadioButton[84];
		       //ButtonGroup[] bG = new ButtonGroup[17];
		JToggleButton tg = new JToggleButton("Next");
		JToggleButton back = new JToggleButton("Back");
		JLabel line = new JLabel("Select Source Country:");
		ButtonGroup bG = new ButtonGroup();
		this.forPort = new JLabel("Select the Starting Port:");
		this.port_buttons = new ButtonGroup();
		this.getAnswer = new JToggleButton("Get Answer");
		this.back1 = new JToggleButton("Back");
		this.for_fuel = new SpinnerNumberModel(0, 0, 500000, 1);
		this.for_cost = new SpinnerNumberModel(0, 0, 500000, 1);
		this.fuel = new JSpinner(this.for_fuel);
		this.cost = new JSpinner(this.for_cost);
		this.name_fuel = new JLabel("Total Fuel: ");
		this.name_cost = new JLabel("Total Money: ");
		this.finish = new JToggleButton("Finish");
		this.back2 = new JToggleButton("Back");
		int val = (int)Math.sqrt(size) + 1;
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.weightx = 2;
		gbc_lblNewLabel.weighty = -1;
		gbc_lblNewLabel.ipady = 1;
		gbc_lblNewLabel.gridx = x;
		gbc_lblNewLabel.gridy = y;
		y++;
		contentPane.add(line, gbc_lblNewLabel);
		for(Map.Entry<String, Boolean> element: mainw.true_names.entrySet()){
        	if(!element.getValue())
        	{
				if(x == val)
				{ 
					x=0;
					y++;
				}
				//labels[i] = new JLabel(element.getKey());
				labels[i] = new JRadioButton(element.getKey());
				bG.add(labels[i]);
				gbc_lblNewLabel.gridx = x;
				gbc_lblNewLabel.gridy = y;
				contentPane.add(labels[i], gbc_lblNewLabel);
				x++;
				i++;
			}
		}
		gbc_lblNewLabel.ipadx = 2;
		gbc_lblNewLabel.gridx = GridBagConstraints.EAST - 1;
		gbc_lblNewLabel.gridy = GridBagConstraints.SOUTH;
		gbc_lblNewLabel.anchor = GridBagConstraints.SOUTH;
		gbc_lblNewLabel.weighty = 2;
		contentPane.add(back, gbc_lblNewLabel);
		gbc_lblNewLabel.gridx += 1;
		contentPane.add(tg, gbc_lblNewLabel);

		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				JFrame j = new JFrame();
				j.setTitle("abc");
				JLabel background;
				j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				j.setSize(new Dimension(510, 400));
				mainw temp_frame = new mainw(j);
				j.getContentPane().add(temp_frame);
				ImageIcon img=new ImageIcon("Capture.jpg");
				background=new JLabel("",img,JLabel.CENTER);
				background.setBounds(0,0,900,600);
				j.setVisible(true);
				dispose();
			}
		});

		tg.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				for(int i = 0; i < labels.length; i++){
					if(labels[i].isSelected()){
						countryx_name = labels[i].getText();
						bG.clearSelection();
						break;
					}
				}
				data.generatePorts(countryx_name);
				run_portPane();
			}
		});

		this.back1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				for(int i = 0; i < labels.length; i++){
						labels[i].setSelected(false);
				}
				for(int i = 0; i < port_set.length; i++){
					portPane.remove(port_set[i]);
				}
				fuel.setValue(Integer.valueOf(0));
				cost.setValue(Integer.valueOf(0));
				portPane.remove(getAnswer);
				portPane.remove(back1);
				portPane.remove(fuel);
				portPane.remove(cost);
				setContentPane(contentPane);
				setSize(700, 400);
				setVisible(true);
			}
		});

		this.getAnswer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				for(int i = 0; i < port_set.length; i++){
					if(port_set[i].isSelected()){
						start_port_name = port_set[i].getText();
					}
					portPane.remove(port_set[i]);
				}
				data.getStartingPoint(start_port_name);
				Integer val_fuel = (Integer)fuel.getValue();
				Integer val_cost = (Integer)cost.getValue();
				if(val_fuel == 0 || val_cost == 0){
					val_fuel = new Integer(1);
					val_cost = new Integer(1);
				}
				data.getRatios(val_fuel.doubleValue(), val_cost.doubleValue());
				data.start();
				data.show();
				run_solutionPane();
			}
		});

		this.finish.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				dispose();
			}
		});

		this.back2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				//solutionPane.remove(solution_statement);
				//solutionPane.remove(solution_start);
				/*
				for(int i = 0; i < solution.length; i++){
					for(int j = 0; j < solution[i].length; j++){
						//solutionPane.remove(solution[i][j]);
					}
				}
				*/
				//solutionPane.remove(solutionPane);
				solutionPane.remove(finish);
				solutionPane.remove(back2);
				run_portPane();
			}
		});
    			/*
				Algorithm obj = new Algorithm();
    			obj.generatePorts(element.getKey());
    			int x1=x,y1=y;
    			for(int m=0; m<obj.port_names.length-1;m++)
    			{
    				rds[m] = new JRadioButton(obj.port_names[m]);
    				
    				//bG[i].add(rds[m]);
    				GridBagConstraints gbc_rdbtnNewRadioButton1 = new GridBagConstraints();
    				
    				//rds[0].setSelected(true);
    				gbc_rdbtnNewRadioButton1.insets = new Insets(5, 5, 5, 5);
    				gbc_rdbtnNewRadioButton1.gridx = x1;
    				gbc_rdbtnNewRadioButton1.gridy = y1;
    				contentPane.add(rds[m], gbc_rdbtnNewRadioButton1);
    				
    				
    				
    				x1=x1+1;
    				
    			}
				*/
	}

}

class solutionPanel extends JPanel{
	//public Graphics g;
	public String start_port_name, countryx_name;
	public Algorithm data;
	public solutionPanel(Algorithm data, String countryx_name, String start_port_name){
		super();
		this.data = data;
		this.countryx_name = countryx_name;
		this.start_port_name = start_port_name;
	}
	@Override
	protected void paintComponent(Graphics graphy){
		super.paintComponent(graphy);
		Graphics2D g2d = (Graphics2D)graphy;
		g2d.drawString(this.countryx_name, 50, 50);
		g2d.drawString(this.start_port_name, 50, 90);
		g2d.fillOval(50, 100, 10, 10);
		int space = 0;
		for(int i = 0; i < this.data.solution_names.length; i++){
			g2d.drawString(this.data.country_names[i], 150 + 150*i, 20);
			g2d.drawString("Fuel: "+Math.round(this.data.cost_fuel[i][0]), 150 + 150*i, 40);
			g2d.drawString("Cost: "+this.data.cost_fuel[i][1], 150 + 150*i, 60);
			for(int j = 0; j < this.data.solution_names[i].length; j++){
				g2d.drawString(this.data.solution_names[i][j], 150 + 150*i, 100 + 60 * j - 10);
				g2d.fillOval(150 + 150*i, 100 + 60 * j, 10, 10);
			}
		}
		g2d.setStroke(new BasicStroke(2));
		g2d.drawLine(50, 100, 150, 100 + 60*this.data.path[0]);
		for(int i = 0; i < this.data.solution_names.length - 1; i++){
			for(int j = 0; j < this.data.solution_names[i].length; j++){
				for(int k = 0; k < this.data.solution_names[i + 1].length; k++){
					if(j == this.data.path[i] && k == this.data.path[i + 1]){
						g2d.setStroke(new BasicStroke(2));
						g2d.drawLine(150 + 150*i, 100 + 60*j, 150 + 150*(i + 1), 100 + 60*k);
					}
				}
			}
		}
	}
}