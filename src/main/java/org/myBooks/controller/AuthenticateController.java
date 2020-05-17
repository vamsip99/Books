package org.myBooks.controller;

import org.myBooks.services.AuthenticationService;
import org.myBooks.util.Constants;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/Authenticate")
public class AuthenticateController {
    @POST
    public Response authenticate(
            @FormParam("emailN") String userId,
            @FormParam("passwordN") String pwd
    ) throws java.net.URISyntaxException
    {
        boolean status = new AuthenticationService().authenticate(userId, pwd);
        String url = Constants.HOME;
        url += status? "index.html": "Login.html";

        if(status) {
             url +="?userName="+userId;
            return Response.seeOther(new URI(url)).build();
        }
        return Response.seeOther(new URI(url)).build();

    }

}
