package com.restApi;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.StatusType;

import org.glassfish.jersey.server.model.ParamQualifier;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import enums.ClientType;
import facade.AdminFacade;
import facade.PurchasingFacade;
import facade.WorkersFacade;

class LoginCreds {
	public String name;
	public String password;
	public ClientType type;
}

class LoginResponse {
	public String error;
	public String token;
}

@Path("/tokens")
public class AuthResource {
	
	@Path("/")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response autoLogin(LoginCreds creds) {
		AdminFacade admin = new AdminFacade();
		PurchasingFacade purch = new PurchasingFacade();
		WorkersFacade worker = new WorkersFacade();

		boolean loginOK = false;
		switch(creds.type) {
		case ADMIN:
			loginOK = admin.login(creds.name, creds.password, creds.type) != null;
		break;
		case PURCHASING:
			loginOK = purch.login(creds.name, creds.password, creds.type) != null;
		break;
		case WORKERS:
			loginOK = worker.login(creds.name,creds. password, creds.type) != null;
		break;
		default:
			LoginResponse content = new LoginResponse();
			content.error = "wrong type";
			
			return Response
					.status(Status.BAD_REQUEST)
					.entity(content)
					.build();
		}
		
		if(!loginOK) {
			LoginResponse content = new LoginResponse();
			content.error = "wrong name or password";
			
			return Response
					.status(Status.BAD_REQUEST)
					.entity(content)
					.build();
		} else {
			Algorithm algo = Algorithm.HMAC256("HF*O#OFIAHfishfsf7shifh");
			String token = JWT.create()
					.withClaim("name", creds.name)
					.withClaim("type", creds.type.toString())
					.sign(algo);
			
			LoginResponse content = new LoginResponse();
			content.token = token;
			
			return Response
					.status(Status.OK)
					.entity(content)
					.build();
		}
	}
}
