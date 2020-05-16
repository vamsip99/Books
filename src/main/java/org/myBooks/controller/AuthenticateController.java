package org.myBooks.controller;

import org.myBooks.services.AuthenticationService;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/Authenticate")
public class AuthenticateController {

    @GET
//    @Path("/authenticate")
    public Response authenticate() {
        boolean status = new AuthenticationService().authenticate("vamceep", "abs");
        String msg = status?"success":"invalid";
        return Response.ok(msg).build();
    }

}
