package com.nt.jdbc.ScallableSt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LoginAppMySQL {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			//read inputs
			sc=new Scanner(System.in);
			String user=null, pass=null;
			if(sc!=null) {
				System.out.println("enter  username:: ");
				user=sc.nextLine(); //gives raja
				System.out.println("enter  password:: ");
				pass=sc.nextLine(); //gives rani
			}
			//convert input values as required for the SQL query
			user="'"+user+"'"; //gives 'raja'
			pass="'"+pass+"'"; //gives 'rani'
			
			/*//Load jdbc driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//estblish the connection
			  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");*/
			
			//Load jdbc driver class
			Class.forName("com.mysql.cj.jdbc.Driver");
			//estblish the connection
			  con=DriverManager.getConnection("jdbc:mysql:///jdbc@conn1","root", "alokp");
			  
			 //create Statement object
			  if(con!=null)
			  st=con.createStatement();
			    //prepare SQL query
			           // select count(*) from  userslist where  uname='raja' and pwd='rani';
			     String query=" SELECT COUNT(*) FROM  `jdbc@conn1`.`userlist`  WHERE  UNAME="+user+"AND PASS="+pass;
			     System.out.println(query);
			    //send and execute SQL query in Db s/w
			     if(st!=null)
			    	 rs=st.executeQuery(query);
			     //Process the ResultSet 
			      if(rs!=null) {
			    	  int count=0;
			    	   rs.next();
			    	  count=rs.getInt(1);
			    	   if(count==0)
			    		   System.out.println("INVALID CREDENTIALS");
			    	   else
			    		   System.out.println("VALID CREDENTIALS");
			      }
		}//try
	    catch(SQLException se) {
	    	se.printStackTrace();
	    }
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close jdbc objs
			try {
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(st!=null)
					st.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(con!=null)
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(sc!=null)
					sc.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}//finally
	}//main
}//class
