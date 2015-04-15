package com.faiz.emp.rest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.faiz.emp.dao.AccessDB;


@Path("/rest/status")
public class EmpRestService {
	
	
	private String api_version ="1.0";
	
	@GET
	@Produces (MediaType.TEXT_HTML)
	public String getStatus(){
		return "<p> This is Employee Rest Project </p>";
	}
	
	@GET
	@Path("/version")
	@Produces (MediaType.TEXT_HTML)
	public String getVersion(){
		return "Version of Emp Rest Project (Author:Faiz) = " + api_version;
	}
	
	
	@POST
	@Path("/empDet")
	public String getFormParam( @FormParam("empId") String empId){
		
		System.out.println(" Form Parameter = " + empId);
		//http://localhost:8080/FaizRestProject/api/v3/status/hp
		return "Form Param empId =" + getEmpData(empId) ;
	}
	
	
public String getEmpData(String empId){
		
		Connection conn= null;
		JSONArray jsonArray = new JSONArray();

	    try
	    {

	    	conn= AccessDB.getAccessDBConnection();
	        Statement stment = conn.createStatement();
	        String qry = "SELECT * FROM Emp where EmpId = " + empId ;

	        ResultSet rs = stment.executeQuery(qry);
	        ResultSetMetaData rsmd = rs.getMetaData();
	        while(rs.next())
	        {
	        	int columns = rsmd.getColumnCount();
	        	JSONObject jsonObject = new JSONObject();
	        	for(int i=1 ; i < columns; i++){
	        		String columnName = rsmd.getColumnName(i);
	        		String columnVal = rs.getString(columnName);
	        		jsonObject.put(columnName, columnVal);
	        		System.out.println(columnName +"  " +  columnName);
	        	}
	     
	        	jsonArray.put(jsonObject);
	            
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

	    return "DB Data = " +  jsonArray;
	}


}