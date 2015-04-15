package com.faiz.emp.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


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
		return "Form Param empId =" + empId ;
	}


}