package com.restApi;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import exceptions.CantFindProductException;
import facade.PurchasingFacade;
import javaBeans.Product;


@Path("/pruchasingfacde")
public class PruchasingFacdeResorce  {

	

	PurchasingFacade purchaing = new PurchasingFacade();

	
	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Product> getAllProduct(){
		return purchaing.getAllProduct();
		
	}
	
	@Path("/")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void createProduct(Product product) {
		purchaing.createProduct(product);
	}

	@Path("/{id}")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public void removeProductOptions (@PathParam("id") long id) throws CantFindProductException {
		purchaing.removeProductOptions(id);
	}

	@Path("/{id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void updatePurchasingMan (Product product) throws CantFindProductException {
		
		purchaing.updateProduct(product);
	}
	
//	@GET
//	@Path("/product/{id}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Collection<Product> getAllProductByPurchasingMan(@QueryParam( "price") double price,@QueryParam( "date") Date date) throws CantFindPurchasingManProductsExceptions  {
//		price=-1;
//		Collection<Product> getAllProduct =  purchaing.getProduct(id);
//		if(price>=0) {
//			getAllProduct= purchaing.getProductsByPrice(price);
//		}
//		
//		if(date !=null) {
//			getAllProduct= purchaing.getProductByDate(date);
//			
//		}
//		return getAllProduct;
//	
//	}

	
}
