import gui.mainw;
//import algo.algo1;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class main  {

    public static void main(String[] args)  {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {  }

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

    }

}

/*
	public static void main(String args[]){
		Connection conn = null;
		Statement state = null;
		double total_fuel, total_cost;
		try{
            //String db = "jdbc:mysql://localhost/project?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String user = "root";
            String db = "jdbc:mysql://localhost:3306/project";
			String pass = "tanish2000";
			//String pass = "";
            String country_name, name;
            Country countryx, countries[];
            double cost, coordinates[];
            int max, n, i, j, size;
            Port starting_point = null, px, ports[];
            ResultSet result = null, result1 = null, result2 = null;
            conn = DriverManager.getConnection(db, user, pass);
            state = conn.createStatement();
            Map<String, Integer> country_count = new HashMap<String, Integer>();
            Map<Integer, Port> port_map;
            try{
	            result = state.executeQuery("Select country from ports;");
	            while(result.next()){
	                country_name = result.getString("country");
	                if(country_count.containsKey(country_name)){
	                	country_count.replace(country_name, country_count.get(country_name) + 1);
	                }
	                else{
	                	country_count.put(country_name, 1);
	                }
	            }
	        }
	        finally{
	        	if(result != null){result.close();}
	        }
            System.out.println("List of Countries: ");
            max = country_count.size();
            for(Map.Entry<String, Integer> element: country_count.entrySet()){
            	System.out.println(element.getKey());
            }

            do{
            	System.out.print("Enter the no. of countries you want excluding the starting point: ");
            	n = sc.nextInt();
            }while(n <= 0 || n > max - 1);
            sc.nextLine();
            System.out.print("Enter the source country: ");
            country_name = sc.nextLine();
            try{
	            result1 = state.executeQuery("Select name from ports where country = \"" + country_name + "\";");
	            while(result1.next()){
	            	name = result1.getString("name");
	            	System.out.println(name);
	            }
	        }
	        catch(Exception e){System.out.print(e);}
	        finally{
	        	if(result1 != null){result1.close();}
	        	result1 = null;
	        }
            System.out.print("Enter the starting port: ");
            name = sc.nextLine();
            try{
	            result1 = state.executeQuery("Select * from ports where name = \"" + name + "\";");
	            if(result1.next()){
		            coordinates = getCoordinates(result1.getString("lat") + " " + result1.getString("longitude"));
		            starting_point = new Port(Double.parseDouble(result1.getString("cost")), result1.getString("name"), coordinates);
		        }
	        }
	        finally{
	        	if(result1 != null){result1.close();}
	        	result1 = null;
	        }
            countries = new Country[n];
            for(i = 0; i < n; i++){
            	System.out.print("Enter the country name: ");
            	country_name = sc.nextLine();
            	try{
	            	result1 = state.executeQuery("Select * from ports where country = \"" + country_name + "\";");
	            	size = 0;
	            	port_map = new HashMap<Integer, Port>();
	            	j = 0;
	            	while(result1.next()){
	            		coordinates = getCoordinates(result1.getString("lat") + " " + result1.getString("longitude"));
	            		px = new Port(Double.parseDouble(result1.getString("cost")), result1.getString("name"), coordinates);
	            		port_map.put(j, px);
	            		j++;
	            	}
	            	size = j;
	            	ports = new Port[size];
	            	for(j = 0; j < size; j++){
	            		ports[j] = port_map.get(j);
	            	}
	            	countryx = new Country(country_name, ports);
	            	countries[i] = countryx;
	            }
	            finally{
	            	if(result1 != null){result1.close();}
	        		result1 = null;
	            }
            }
            System.out.print("Enter the total fuel (fuel in terms of mileage/distance): ");
            total_fuel = sc.nextDouble();
            System.out.print("Enter the total money: ");
            total_cost = sc.nextDouble();
            if(state != null){state.close();}
            if(conn != null){conn.close();}
            getRatios(total_fuel, total_cost);
            countries = countryOrder(countries, starting_point);
			getDistances(countries, starting_point);
			int[] path = getPath(countries, starting_point);
			for(i = 0; i < path.length; i++){
				System.out.println(countries[i].name + " " + countries[i].ports[path[i]].name + " fuel "+countries[i].dist+" amt "+countries[i].amt);
			}
        }catch(Exception e){
            System.out.println(e);
        }
	}
	*/
