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
import dbdao.ProductDBDAO;
import dbdao.PurchasingManDBDAO;
import enums.ProductType;
import javaBeans.Product;
import javaBeans.PurchasingMan;
import javaBeans.Workers;

@Path("/products")
public class ProductResorce {

	ProductDBDAO productDBDAO = new ProductDBDAO();
	
	@Path("/")
	@POST	
	@AdminAuth
    @Consumes(MediaType.APPLICATION_JSON)
	public void createProduct(Product product) {
		System.out.println(product.getStart_date().toString());
		productDBDAO.createProduct(product);
	}
	
	
	@DELETE
	@Path("/{id}")
	@AdminAuth
	@Consumes(MediaType.APPLICATION_JSON)
	public void removeProduct(@PathParam("id") int id) {
		productDBDAO.removeProduct(id);
	}
	
	@PUT
	@Path("/")
	@AdminAuth
	@Consumes(MediaType.APPLICATION_JSON)
	public  void updateProduct(Product product) {
		
		productDBDAO.updateProduct(product);
		
	}
//	
	@Path("/")
	@GET
	@AdminAuth
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Product> getProducts(@QueryParam( "title") String title, @QueryParam("type") String type, @QueryParam("m") String m) {
		 
		Collection<Product> allProducts = productDBDAO.getAllProducts() ;
		if(title !=null) {
				allProducts= productDBDAO.getProductsByTitle(title);
		}
		
		if(type != null && m != null) {
			System.out.println(m);
			ProductType	typefix = ProductType.convertFromString(type);
			allProducts = allProducts.stream()
					.filter(p -> p.getType().equals(typefix))
					.collect(Collectors.toList());
		}
		
		if(type != null && m == null) {
			ProductType	typefix = ProductType.convertFromString(type);
			allProducts = allProducts.stream()
					.filter(p -> !p.getType().equals(typefix))
					.collect(Collectors.toList());
		}
		
		
		return allProducts;
	}
//}
}
