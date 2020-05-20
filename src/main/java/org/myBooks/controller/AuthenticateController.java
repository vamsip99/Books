package org.myBooks.controller;

import com.google.gson.Gson;
import org.myBooks.beans.Customer;
import org.myBooks.dao.CustomerDao;
import org.myBooks.services.AuthenticationService;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/Authenticate")
public class AuthenticateController {
    @POST
    public Response authenticate(
            @FormParam("emailN") String userId,
            @FormParam("passwordN") String pwd)
    {
        boolean status = new AuthenticationService().authenticate(userId, pwd);
        Customer c = CustomerDao.getCustomerByUserName(userId);
        if(c != null) {
            c.setEmail("");
            c.setPwd("");
            c.setId(0);
        }
        if(status)  return Response.ok(new Gson().toJson(c)).status(Response.Status.ACCEPTED).build();
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

}
