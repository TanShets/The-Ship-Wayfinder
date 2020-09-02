# The-Ship-Wayfinder
This project makes use of MySQL.
The algorithm is based on the Assembly Line Path Concept of Dynamic Programming.

To run it, use:
javac -cp mysql-connector-java-8.0.20.jar: main.java
java -cp mysql-connector-java-8.0.20.jar: main

For now, it accesses the database set by me, however, to make it accessible to the desired database:
1. Go to algo/Algorithm.java
2. Go to Algorithm class.
3. The static strings db, user and pass must be changed in accordance with the link
   of the database, the user name and password. In case of no password, leave it blank.

Your table needs to have the name ports(no exceptions here)

The table should be of the format:
  - id int primary key
  - name varchar(50)
  - country varchar(50)
  - lat varchar(30)
  - longitude varchar(30)
  - cost double(40, 2) //Significant to two decimal places.
 
Note: lat and longitude will be in the format XdYmZs N/S(lat) W/E(longitude)
      where d = degrees, m = minutes, s = seconds

Output will be in the form of a graph of nodes and connecting edges for the desired path.

coord.txt consists of insert commands which can be used to insert into the table for your use.
