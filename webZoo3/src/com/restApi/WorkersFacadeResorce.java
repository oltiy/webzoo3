package com.restApi;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import enums.ProductType;
import exceptions.CantFindProductException;
import facade.WorkersFacade;
import javaBeans.Product;
import javaBeans.Workers;


@Path("/worker")
public class WorkersFacadeResorce {

	WorkersFacade workerFacade = new WorkersFacade();
	private Workers worker;
	private ProductType type;
	
 @PATCH
 @Path("/")
 @Consumes(MediaType.APPLICATION_JSON)
 public void TakeTouUseProduct(@QueryParam("workerLogin")long workerLogin, @QueryParam("workerLogin") long productId) throws CantFindProductException{
	   
	 workerFacade.usedProduct(workerLogin, productId);
	 
 }
 
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Product> getAllPurchasedProducts() throws CantFindProductException  {
		
		Collection<Product> allProductsbyWorker = workerFacade.getAllPurchasedProducts();
		
	
		
		
		return allProductsbyWorker;
	
	}
 
	
}
