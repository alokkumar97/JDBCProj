package com.nt.jdbc.ScallableSt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertTestMySQL {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		try {
			//read inputs
			sc=new Scanner(System.in);
			int eid=0;
			String ename=null, job= null,addrs=null;
			double sal=0.0;
			if(sc!=null) {
				System.out.println("Enter Employee Number::");
				eid=sc.nextInt();  //gives  5742
				System.out.println("Enter Employee Name ::");
				ename=sc.next();  //gives  anil
				System.out.println("Enter Employee  Salary::");
				sal=sc.nextDouble(); //gives 65520.00
				System.out.println("Enter Employee Design::");
				job=sc.next(); //gives  clerk
				System.out.println("Enter Employee address::");
				addrs=sc.next(); //gives  vizag
				
			}
		   //convert input values as required form the SQL query
			   ename="'"+ename+"'"; //gives 'anil'
			   job="'"+job+"'"; //gives 'anil'
			   addrs="'"+addrs+"'"; //gives  'vizag'
			  //Load jdbc driver class
			   Class.forName("com.mysql.cj.jdbc.Driver");
			   //establish the connection
			   con=DriverManager.getConnection("jdbc:mysql:///jdbc@conn1", "root","alokp");
			   //create Statement object
			   if(con!=null)
				   st=con.createStatement();
			   //prepare SQL query
			         //insert into student values(5472,'anil','vizag',65.88)
			     String query="INSERT INTO `jdbc@conn1`.`emp details` VALUES("+eid+","+ename+","+sal+","+job+","+addrs+")";
			     System.out.println(query);
			   //send and execute SQL query in Db s/w
			     int count=0;
			     if(st!=null)
			    	 count=st.executeUpdate(query);
			     //process the result
			     if(count==0)
			    	 System.out.println("Record not inserted");
			     else 
			    	 System.out.println("Record  inserted");
			
		}//try
		catch(SQLException se) {
			if(se.getErrorCode()==1)
				System.err.println("Duplicate or empty value can not inserted to PK column SNO");
			else if(se.getErrorCode()==12899)
				System.err.println("given value is larger than col size");
			else if (se.getErrorCode()>=900 && se.getErrorCode()<=1000)
				System.err.println("Query syntax problem");
			else {
				System.err.println(" unknown problem");
				se.printStackTrace();
			}
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
