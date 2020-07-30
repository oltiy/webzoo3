package com.restApi;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import dbdao.PurchasingManDBDAO;
import javaBeans.PurchasingMan;
//
//@Path("/purchasing-men")
//public class ExampleHel {
//	
//	@GET
//	@Path("/")
//	@Produces(MediaType.TEXT_PLAIN)
//	public String getAllPurchasingMan() {
//		return "all";
//	}
//	
//	@GET
//	@Path("/{id}")
//	@Produces(MediaType.TEXT_PLAIN)
//	public String getSpecificPurchasingMan(@PathParam("id") int id) {
//		PurchasingManDBDAO a = new PurchasingManDBDAO();
//		PurchasingMan b= a.getSpecificPurchasingMan(id);
//		return b.getPurch_name();
//	}
//	
//	
//	@POST
//	@Path("/")
//	@Produces(MediaType.TEXT_PLAIN)
//	public String createPurchasingMan() {
//		return "all";
//	}
//	

//}
