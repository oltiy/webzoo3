package com.restApi;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import facade.AdminFacade;
import javaBeans.PurchasingMan;
import javaBeans.Workers;

@Path("/admin")
public class AdminResorce {

	
	AdminFacade admin = new AdminFacade();
	
//	@GET
//	@Path("/")
//	@Produces(MediaType.APPLICATION_JSON)
//	public AdminFacade login(String name, String password, ClientType type) {
//		return admin.login(name, password, type);
//		
//	}
//	
//	
	@POST
	@Path("/purchasing-men")
    @Consumes(MediaType.APPLICATION_JSON)
	public void createPurchasingMan( PurchasingMan purchasingMan) {
	
		admin.createPurchasingMan(purchasingMan);
	}
	
	
	@DELETE
	@Path("/purchasing-men/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void removePurchasingMan (@PathParam("id") int id) {
		admin.removePurchasingMan(id);
	}
	
	@PUT
	@Path("/purchasing-men/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updatePurchasingMan (PurchasingMan purchasingMan) {
		
		admin.updatePurchasingMan(purchasingMan);
	}
	
	@GET
	@Path("/purchasing-men")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<PurchasingMan> getPurchasingMen(@QueryParam( "name")String name, @QueryParam("product")long  productId)  {
		
		Collection<PurchasingMan> allPurchasingMan =  admin.getAllPurchasingMen();
		
		
		if(name !=null) {
			
			allPurchasingMan=  allPurchasingMan.stream().filter(p -> p.getPurch_name().equals(name))
					.collect(Collectors.toList());
		}
		
		
		return allPurchasingMan;
	
	}

	@GET
	@Path("/purchasing-men/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public PurchasingMan getSpecificPurchasingMan(@PathParam("id") int id) {
		return admin.getPurchasingMan(id);
	}
	
	
	@POST
	@Path("/worker")
    @Consumes(MediaType.APPLICATION_JSON)
	public void createWorker( Workers workers) {
	
		admin.createWorker(workers);
	}
	
	@DELETE
	@Path("/worker/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void removeWorker (@PathParam("id") long id) {
		admin.removeWorker(id);
	}
	
	
	@PUT
	@Path("/worker/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateWorker(Workers workerToUpdate){
		
		admin.updateWorker(workerToUpdate);
	}
	
	@GET
	@Path("/worker/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Workers getworker(@PathParam("id") long id) {
		return admin.getworker(id);
	}
	
	
	@GET
	@Path("/worker")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Workers> getAllWorker(@QueryParam( "name")String name)  {
		
		Collection<Workers> allWorker =  admin.getAllWorker();
		
		
		if(name !=null) {
			
			allWorker=  allWorker.stream().filter(p -> p.getWorker_name().equals(name))
					.collect(Collectors.toList());
		}
		
		
		return allWorker;
	
	}
	
	
	
}	
