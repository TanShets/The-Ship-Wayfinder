package gui;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;
import algo.*;
import java.sql.*;

public class mainw extends JPanel {

	/**
	 * Create the panel.
	 */
	JCheckBox[] name_country;
	JButton btnNewButton, btnNewButton_1;
	static Map<String, Boolean> true_names;
	public String country_name[];
	ActionListener ae1;
	public Algorithm data;
	public JFrame frame;
	public mainw(JFrame frame) {
		setLayout(null);
		this.frame = frame;
		data = new Algorithm();
		JPanel panel = new JPanel();
		panel.setBounds(0, 258, 537, 52);
		add(panel);
		panel.setLayout(null);
		name_country = new JCheckBox[17];
		btnNewButton = new JButton("RUN");
		ae1 = new ActionListener(){
			public void actionPerformed(ActionEvent e) 
			{
				true_names = new HashMap<String, Boolean>();
				
				int i, j = 0;
				for(i = 0; i < 17; i++){
					if(name_country[i].isSelected()){
						true_names.put(name_country[i].getText(), true);
						j++;
					}
					else{
						true_names.put(name_country[i].getText(), false);
					}
				}
				

				country_name = new String[j];
				i = 0;
				for(Map.Entry<String, Boolean> element: true_names.entrySet()){
            		if(element.getValue()){
						country_name[i] = element.getKey();
						i++;
					}
            	}

				data.getCountryArray(country_name);
				data.generatePorts("India");
				data.getStartingPoint("Mumbai");
				data.getRatios(50000, 100000);
				data.start();
				data.show();
				/*--------------After this go to new window-------------*/
				test1.run(data, country_name);
				frame.dispose();
			}
		};
		btnNewButton.addActionListener(ae1); //This is for the run button and the above function is for that
		btnNewButton.setBounds(274, 10, 85, 21);
		panel.add(btnNewButton);

		
		btnNewButton_1 = new JButton("RESET");
		btnNewButton_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				for(int i = 0; i < 17; i++){
					name_country[i].setSelected(false);
				}
			}
		});
		btnNewButton_1.setBounds(105, 10, 85, 21);
		panel.add(btnNewButton_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(153, 0, 255));
		panel_1.setBounds(0, 0, 510, 52);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SELECT COUNTRIES (excluding source)");
		lblNewLabel.setForeground(new Color(204, 102, 102));
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setFont(new Font("Candara Light", Font.BOLD, 14));
		lblNewLabel.setBounds(99, 10, 350, 42);
		panel_1.add(lblNewLabel);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.CYAN);
		panel_3.setBounds(98, 10, 310, 32);
		panel_1.add(panel_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 50, 537, 209);
		add(scrollPane);
		
		JPanel panel_2 = new JPanel();
		scrollPane.setViewportView(panel_2);
		panel_2.setLayout(null);
		
		name_country[0] = new JCheckBox("USA");
		name_country[0].setBounds(0, 21, 103, 21);
		panel_2.add(name_country[0]);
		
		name_country[1] = new JCheckBox("Argentina");
		name_country[1].setBounds(0, 67, 88, 21);
		panel_2.add(name_country[1]);
		
		name_country[2] = new JCheckBox("Singapore");
		name_country[2].setBounds(0, 113, 103, 21);
		panel_2.add(name_country[2]);
		
		name_country[3] = new JCheckBox("Japan");
		name_country[3].setBounds(0, 155, 103, 21);
		panel_2.add(name_country[3]);
		
		name_country[4] = new JCheckBox("Thailand");
		name_country[4].setBounds(411, 155, 103, 21);
		panel_2.add(name_country[4]);
		
		name_country[5] = new JCheckBox("Portugal");
		name_country[5].setBounds(125, 21, 103, 21);
		panel_2.add(name_country[5]);
		
		name_country[6] = new JCheckBox("India");
		name_country[6].setBounds(214, 186, 103, 21);
		panel_2.add(name_country[6]);
		
		name_country[7] = new JCheckBox("Russia");
		name_country[7].setBounds(271, 21, 103, 21);
		panel_2.add(name_country[7]);
		
		name_country[8] = new JCheckBox("Spain");
		name_country[8].setBounds(125, 67, 88, 21);
		panel_2.add(name_country[8]);
		
		name_country[9] = new JCheckBox("Saudi Arabia");
		name_country[9].setBounds(125, 113, 103, 21);
		panel_2.add(name_country[9]);
		
		name_country[10] = new JCheckBox("Belgium");
		name_country[10].setBounds(125, 155, 103, 21);
		panel_2.add(name_country[10]);
		
		name_country[11] = new JCheckBox("China");
		name_country[11].setBounds(271, 155, 103, 21);
		panel_2.add(name_country[11]);
		
		name_country[12] = new JCheckBox("UK");
		name_country[12].setBounds(271, 113, 103, 21);
		panel_2.add(name_country[12]);
		
		name_country[13] = new JCheckBox("Brazil");
		name_country[13].setBounds(271, 67, 103, 21);
		panel_2.add(name_country[13]);
		
		name_country[14] = new JCheckBox("Mexico");
		name_country[14].setBounds(411, 21, 103, 21);
		panel_2.add(name_country[14]);
		
		name_country[15] = new JCheckBox("France");
		name_country[15].setBounds(411, 67, 103, 21);
		panel_2.add(name_country[15]);
		
		name_country[16] = new JCheckBox("Germany");
		name_country[16].setBounds(411, 113, 103, 21);
		panel_2.add(name_country[16]);

	}
	public Map<String, Boolean> getname() {
		
		return true_names;
	}
	
}
/*
class DBConnection{
	static String user = "root";
    static String db = "jdbc:mysql://localhost:3306/project";
	static String pass = "tanish2000";
	Connection conn;
	Statement state;
	ResultSet result, result1;
	algo1 Algo;
	//Country countryx, countries[];
	//Port starting_point, px, ports[];
	String name;

	public DBConnection(){
		try{
			this.conn = DriverManager.getConnection(db, user, pass);
			this.state = conn.createStatement();
		}
		catch(Exception e){}
		this.result = null;
		this.result1 = null;
		//this.countryx = null;
		//this.starting_point = null;
		this.Algo = new algo1();
	}

	public void close(){
		try{
			if(this.state != null){state.close();}
			if(this.conn != null){conn.close();}
		}
		catch(Exception e){}
	}
}
*/