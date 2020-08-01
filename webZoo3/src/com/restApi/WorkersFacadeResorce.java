package com.restApi;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import enums.ProductType;
import exceptions.CantFindProductException;
import facade.AdminFacade;
import facade.WorkersFacade;
import javaBeans.Product;
import javaBeans.Workers;


@Path("/worker")
public class WorkersFacadeResorce {

	WorkersFacade workerFacade = new WorkersFacade();
	AdminFacade admin = new AdminFacade();

	
 @PATCH
 @Path("/")
 @Consumes(MediaType.APPLICATION_JSON)
 public void TakeTouUseProduct(@QueryParam("workerLogin")long workerLogin, @QueryParam("workerLogin") long productId) throws CantFindProductException{
	   
	 workerFacade.usedProduct(workerLogin, productId);
	
	 
 }
 
//	@GET
//	@Path("/")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Collection<Product> getAllPurchasedProducts(long id) throws CantFindProductException  {
//		
//		Collection<Product> allProductsbyWorker = workerFacade.getAllPurchasedProducts(id );
//		
//	
//		
//		
//		return allProductsbyWorker;
//	
//	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Workers> getAllWorker(@QueryParam( "name")String name)  {
		
		Collection<Workers> allWorker =  admin.getAllWorker();
		
		
		if(name !=null) {
			
			allWorker=  allWorker.stream().filter(p -> p.getWorker_name().equals(name))
					.collect(Collectors.toList());
		}
		
		
		return allWorker;
	
	}
	
	@POST
	@Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
	public void createWorker( Workers workers) {
	
		admin.createWorker(workers);
	}
	
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void removeWorker (@PathParam("id") long id) {
		admin.removeWorker(id);
	}
	
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateWorker(Workers workerToUpdate){
		
		admin.updateWorker(workerToUpdate);
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Workers getworker(@PathParam("id") long id) {
		return admin.getworker(id);
	}
	
	
	

	
	
	
	
 
	
}
