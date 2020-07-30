package com.restApi;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;

import dbdao.PurchasingManDBDAO;
import enums.ProductType;
import javaBeans.Product;
import javaBeans.PurchasingMan;

import java.text.ParseException;
import java.text.SimpleDateFormat;  




@Path("/purchasing-men")
public class PurchasingManResorce {
	
	PurchasingManDBDAO purchasingManDBDAO = new PurchasingManDBDAO();
	
	@POST
	@Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
	public void creatPurchasingMan(PurchasingMan a) {
		purchasingManDBDAO.creatPurchasingMan(a);
	}
	
	
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void removePurchasingMan (@PathParam("id") int id) {
		purchasingManDBDAO.removePurchasingMan(id);
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updatePurchasingMan (PurchasingMan purchasingMan) {
		
		purchasingManDBDAO.updatePurchasingMan(purchasingMan);
		
	}
	
	
	@GET
	@Path("/")
	@Auth
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<PurchasingMan> getPurchasingMen(@QueryParam( "name")String name, @QueryParam("product")long  productId)  {
		
		Collection<PurchasingMan> allPurchasingMan =  purchasingManDBDAO.getAllPurchasingMen();
		
		
		if(name !=null) {
			
			allPurchasingMan=  purchasingManDBDAO.getPurchasingManByName(name);
		}
		
		if (productId != 0L) {
			
			allPurchasingMan= purchasingManDBDAO.getPurchasingManByProduct(productId);
		}
		
		return allPurchasingMan;
	
	}
	
	


	

	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public PurchasingMan getSpecificPurchasingMan(@PathParam("id") int id) {
		return purchasingManDBDAO.getSpecificPurchasingMan(id);
	}
	
	
	

	@GET
	@Path("/{id}/products")
	@Produces(MediaType.APPLICATION_JSON)
	public  Collection<Product> getProductByPurchasingMen(@PathParam("id") long purchasingManId, @QueryParam("type") ProductType type,
															@QueryParam("price") double price,  @QueryParam("date") String dateString){
		Collection<Product> allProducts = purchasingManDBDAO.getProductByPurchasingMen(purchasingManId);
		if(type != null) {
			allProducts = allProducts.stream()
					.filter(p -> p.getType().equals(type))
					.collect(Collectors.toList());
		}
		
		if(price != 0L) {
			allProducts= purchasingManDBDAO.getAllPurchasedProductByPrice(purchasingManId, price);
		}
		
		if(dateString !=null) {
			
			Date date = null;
			try {
				date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			allProducts= purchasingManDBDAO.getPurchasingManProductByDate(purchasingManId, date);
		}

		return allProducts;		
	}
	
}
	
	


