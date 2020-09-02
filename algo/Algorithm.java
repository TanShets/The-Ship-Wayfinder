package algo;

import java.util.*;
import java.sql.*;
class Port{
	double cost;
	String name;
	double[] distances;
	double[] coordinates;
	/*
	Port(double cost, String name, double[] distances){
		this.cost = cost;
		this.name = name;
		this.distances = distances;
	}
	*/
	Port(double cost, String name, double[] coordinates){
		this.cost = cost;
		this.name = name;
		this.coordinates = coordinates;
	}
	public void port_distances(Country countryx){
		this.distances = new double[countryx.ports.length];
		for(int i = 0; i < countryx.ports.length; i++){
			this.distances[i] = Algorithm.distance(this.coordinates, countryx.ports[i].coordinates);
		}
	}

	public int getNextPort(Country countryx){
		double min_cost = -1, toll;
		int lowest = -1;
		for(int i = 0; i < this.distances.length; i++){
			toll = Algorithm.getCost(this.distances[i], countryx.ports[i].cost);
			if(lowest == -1 || toll < min_cost){
				min_cost = toll;
				lowest = i;
			}
		}
		return lowest;
	}
}

class Country{
	 public double avgCoord[];
	 public Port[] ports;
	 public String name;
	 double dist, amt;
	Country(String name, Port[] ports){
		this.name = name;
		this.ports = ports;
	}
	void findAvg(){
		double sum1 = 0, sum2 = 0;
		for(int i = 0; i < this.ports.length; i++){
			sum1 += this.ports[i].coordinates[0];
			sum2 += this.ports[i].coordinates[1];
		}
		this.avgCoord = new double[2];
		this.avgCoord[0] = sum1 / this.ports.length;
		this.avgCoord[1] = sum2 / this.ports.length;
	}

	void findDistances(Country countryx){
		for(int i = 0; i < this.ports.length; i++){
			this.ports[i].port_distances(countryx);
		}
	}
}

public class Algorithm{
	final static Scanner sc = new Scanner(System.in);
	final static double radius = 6371;
	public static double tot_cost, tot_fuel, ratio1, ratio2;
	public Port starting_point, px, ports[];
	public  Country countryx;
	public  Country countries[];
	public int[] path;
	Connection conn;
	Statement state;
	ResultSet result1, result;
	public String country_names[];
	public String solution_names[][];
	public double[][] cost_fuel;
	static String user = "root";
	static String db = "jdbc:mysql://localhost:3306/project";
	//static String db = "jdbc:mysql://localhost:3306/project?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	static String pass = "tanish2000";
	//static String pass = "";
	public String port_names[];
	public static double distance(double[] coordinate1, double[] coordinate2){
		double dist = -1;
		double difflat = Math.toRadians(coordinate1[0] - coordinate2[0]);
		double difflong = Math.toRadians(coordinate1[1] - coordinate2[1]);
		double lat1 = Math.toRadians(coordinate1[0]);
		double lat2 = Math.toRadians(coordinate2[0]);
		double sinlat, cos1, cos2, sinlong, final_val;
		sinlat = Math.pow(Math.sin(difflat / 2), 2);
		sinlong = Math.pow(Math.sin(difflong / 2), 2);
		cos1 = Math.cos(lat1);
		cos2 = Math.cos(lat2);
		final_val = sinlat + (cos1 * cos2 * sinlong);
		dist = 2 * Algorithm.radius * Math.asin(Math.sqrt(final_val));
		return dist;
	}

	public static Country[] countryOrder(Country[] old_Order, Port starting_point){
		Country[] new_Order = new Country[old_Order.length];
		boolean[] order = new boolean[old_Order.length];
		int i, j, lowest = -1;
		double dist, min_dist = -1;
		for(i = 0; i < old_Order.length; i++){
			old_Order[i].findAvg();
			dist = Algorithm.distance(starting_point.coordinates, old_Order[i].avgCoord);
			if(min_dist == -1 || min_dist > dist){
				min_dist = dist;
				lowest = i;
			}
			order[i] = true;
		}

		new_Order[0] = old_Order[lowest];
		order[lowest] = false;
		for(i = 1; i < old_Order.length; i++){
			lowest = -1;
			min_dist = -1;
			for(j = 0; j < old_Order.length; j++){
				if(order[j]){
					dist = Algorithm.distance(new_Order[i - 1].avgCoord, old_Order[j].avgCoord);
					if(min_dist == -1 || min_dist > dist){
						min_dist = dist;
						lowest = j;
					}
				}
			}
			new_Order[i] = old_Order[lowest];
			order[lowest] = false;
		}
		return new_Order;
	}

	public static void getDistances(Country[] countries, Port starting_point){
		starting_point.port_distances(countries[0]);
		for(int i = 0; i < countries.length - 1; i++){
			countries[i].findDistances(countries[i + 1]);
		}
	}

	public static void getRatios(double total_fuel, double total_cash){
		Algorithm.ratio1 = (total_cash) / (total_cash + total_fuel);
		Algorithm.ratio2 = 1 - Algorithm.ratio1;
	}

	public static double getCost(double dist, double toll){
		return (dist * Algorithm.ratio1) + (toll * Algorithm.ratio2);
	}

	public static int[] getPath(Country[] countries, Port starting_point){
		int[] path = new int[countries.length];
		tot_cost = 0;
		Port temp = starting_point;
		int index = -1;
		for(int i = 0; i < countries.length; i++){
			index = temp.getNextPort(countries[i]);
			path[i] = index;
			if(i == 0){
				countries[i].dist = temp.distances[index];
				countries[i].amt = countries[i].ports[index].cost;
			}
			else{
				countries[i].dist = countries[i - 1].dist + temp.distances[index];
				countries[i].amt = countries[i - 1].amt + countries[i].ports[index].cost;
			}
			tot_cost += Algorithm.getCost(temp.distances[index], countries[i].ports[index].cost);
			temp = countries[i].ports[index];
		}
		return path;
	}

	public static double[] getCoordinates(String coordinate){
		double[] coord = new double[2]; //Coordinates will be in form 24d34m56sN 130d57m21sW
		String[] latlong = coordinate.split(" ", 2);
		char[] words;
		double temp;
		int deg, min, sec;
		for(int i = 0; i < 2; i++){
			temp = 0;
			deg = latlong[i].indexOf('d');
			if(deg == -1){
				System.out.println("Error");
				coord[0] = -1;
				coord[1] = -1;
				return coord;
			}
			min = latlong[i].indexOf('m');
			temp += Double.parseDouble(latlong[i].substring(0, deg));
			if(min != -1){
				temp += Double.parseDouble(latlong[i].substring(deg + 1, min)) / 60;
				sec = latlong[i].indexOf('s');
				if(sec != -1){
					temp += Double.parseDouble(latlong[i].substring(min + 1, sec)) / 3600;
				}
			}
			words = latlong[i].toCharArray();
			if(words[words.length - 1] == 'S' || words[words.length - 1] == 'W'){
				temp *= -1;
			}
			coord[i] = temp;
		}
		return coord;
	}

	public void getCountryArray(String[] country_name){
		this.conn = null;
		this.state = null;
		this.result1 = null;
		this.countries = null;
		try{
			this.conn = DriverManager.getConnection(this.db, this.user, this.pass);
			this.state = this.conn.createStatement();
			this.countries = new Country[country_name.length];
			double[] coordinates;
			for(int i = 0; i < country_name.length; i++){
				try{
					this.result1 = state.executeQuery("Select * from ports where country = \"" + country_name[i] + "\";");
					int size = 0;
					Map<Integer, Port> port_map = new HashMap<Integer, Port>();
					int j = 0;
					while(this.result1.next()){
						coordinates = Algorithm.getCoordinates(this.result1.getString("lat") + " " + this.result1.getString("longitude"));
						this.px = new Port(Double.parseDouble(this.result1.getString("cost")), this.result1.getString("name"), coordinates);
						port_map.put(j, this.px);
						j++;
					}
					size = j;
					this.ports = new Port[size];
					for(j = 0; j < size; j++){
						this.ports[j] = port_map.get(j);
					}
					this.countryx = new Country(country_name[i], ports);
					this.countries[i] = countryx;
				}catch(Exception e){System.out.println(e);}
				finally{
					if(this.result1 != null){this.result1.close();}
					this.result1 = null;
				}
			}
		}
		catch(Exception e){System.out.println(e);}
		finally{
			try{
				if(this.state != null){this.state.close();}
				if(this.conn != null){this.conn.close();}
			}catch(Exception e){}
			this.state = null;
			this.conn = null;
		}
	}
	 
	public void generatePorts(String name_country){
		this.conn = null;
		this.state = null;
		this.result1 = null;
		try{
			this.conn = DriverManager.getConnection(this.db, this.user, this.pass);
			this.state = this.conn.createStatement();
			try{
				this.result1 = state.executeQuery("Select name from ports where country = \"" + name_country + "\";");
				Map<Integer, String> port_map = new HashMap<Integer, String>();
				int j = 0, size;
				while(this.result1.next()){
					port_map.put(j, this.result1.getString("name"));
					j++;
				}
				size = j;
				this.port_names = new String[size];
				for(j = 0; j < size; j++){
					this.port_names[j] = port_map.get(j);
				}
			}catch(Exception e){System.out.println(e);}
			finally{
				if(this.result1 != null){this.result1.close();}
			}
		}catch(Exception e){}
		finally{
			try{
				if(state != null){state.close();}
				if(conn != null){conn.close();}
			}catch(Exception e){}
			this.state = null;
			this.conn = null;
		}
	}

	public void getStartingPoint(String port_name){
		this.conn = null;
		this.state = null;
		this.result1 = null;
		double[] coordinates;
		try{
			conn = DriverManager.getConnection(this.db, this.user, this.pass);
			this.state = conn.createStatement();
			try{
				this.result1 = state.executeQuery("Select * from ports where name = \"" + port_name + "\";");
				if(this.result1.next()){
					coordinates = getCoordinates(this.result1.getString("lat") + " " + this.result1.getString("longitude"));
		            this.starting_point = new Port(0, this.result1.getString("name"), coordinates); //Starting point ports will have zero cost
				}
				else{
					this.starting_point = null;
					System.out.println("Failure: Can't find starting_port");
				}
			}catch(Exception e){}
			finally{
				if(this.result1 != null){this.result1.close();}
				this.result1 = null;
			}
		}catch(Exception e){}
		finally{
			try{
				if(state != null){state.close();}
				if(conn != null){conn.close();}
			}catch(Exception e){}
			this.state = null;
			this.conn = null;
		}
	}
	public void start(){
		this.countries = Algorithm.countryOrder(this.countries, this.starting_point);
		Algorithm.getDistances(this.countries, this.starting_point);
		this.path = Algorithm.getPath(this.countries, this.starting_point);
	}

	public void show(){
		if(this.path != null){
			this.cost_fuel = new double[this.path.length][2];
			this.country_names = new String[this.path.length];
			this.solution_names = new String[this.path.length][];
			String[] temp;
			for(int i = 0; i < this.path.length; i++){
				System.out.println(this.countries[i].name+" "+this.countries[i].ports[path[i]].name+" fuel "+this.countries[i].dist+" amt "+this.countries[i].amt);
				this.cost_fuel[i][0] = this.countries[i].dist;
				this.cost_fuel[i][1] = this.countries[i].amt;
				this.country_names[i] = this.countries[i].name;
				temp = new String[this.countries[i].ports.length];
				for(int j = 0; j < this.countries[i].ports.length; j++){
					temp[j] = this.countries[i].ports[j].name;
				}
				this.solution_names[i] = temp;
			}
		}
	}
}
/*
		Country countryx, countries[];
		Port px, ports[], starting_point;
		int i, j, num, no_ports;
		String coordinates, name, country_name;
		double cost, total_fuel, total_cost, coord[];
		System.out.print("Enter the name of the starting point: ");
		name = sc.nextLine();
		System.out.print("Enter the coordinates: ");
		coord = getCoordinates(sc.nextLine());
		starting_point = new Port(0, name, coord);
		System.out.print("Enter the number of countries: ");
		num = sc.nextInt();
		countries = new Country[num];
		sc.nextLine();
		for(i = 0; i < num; i++){
			System.out.print("Enter the name of the country: ");
			country_name = sc.nextLine();
			System.out.print("Enter the number of ports of the country: ");
			no_ports = sc.nextInt();
			sc.nextLine();
			ports = new Port[no_ports];
			for(j = 0; j < no_ports; j++){
				System.out.print("Enter the name of the port: ");
				name = sc.nextLine();
				System.out.print("Enter the coordinates: ");
				coord = getCoordinates(sc.nextLine());
				System.out.print("Enter the toll: ");
				cost = sc.nextDouble();
				sc.nextLine();
				px = new Port(cost, name, coord);
				ports[j] = px;
			}
			countryx = new Country(country_name, ports);
			countries[i] = countryx;
		}
		System.out.print("Enter the total fuel: ");
		total_fuel = sc.nextDouble();
		System.out.print("Enter the total money: ");
		total_cost = sc.nextDouble();
		getRatios(total_fuel, total_cost);
		countries = countryOrder(countries, starting_point);
		getDistances(countries, starting_point);
		int[] path = getPath(countries, starting_point);
		for(i = 0; i < path.length; i++){
			System.out.println(countries[i].name + " " + countries[i].ports[path[i]].name + " fuel "+countries[i].dist+" amt "+countries[i].amt);
		}
*/