package com.faiz.emp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AccessDB {
	
	public static Connection getAccessDBConnection(){
		
		Connection conn= null;
		
		try{
			if(conn == null ){
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");	
	  		 conn=DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\faiz\\Documents\\FaizSoftwares\\AccessDB\\FaizDB.accdb");
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		System.out.println (" Connection = " + conn) ;
		return conn;
	}
	
	
	public static void main(String[] args) 
	{
		Connection conn= null;

	    try
	    {

	    	conn= getAccessDBConnection();
	        Statement stment = conn.createStatement();
	        String qry = "SELECT * FROM Emp";

	        ResultSet rs = stment.executeQuery(qry);
	        while(rs.next())
	        {
	            String id    = rs.getString("EmpId") ;
	            String fname = rs.getString("FirstName");

	            System.out.println(id + fname);
	        }
	    }
	    catch(Exception err)
	    {
	        System.out.println(err);
	    }
	    finally{
	    	try{
	    		if (conn != null){
		    		conn.close();
		    	}
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
	    	
	    }//end of finally block

	}//end of main method
}