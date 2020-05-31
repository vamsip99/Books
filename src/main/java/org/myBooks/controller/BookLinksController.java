

package org.myBooks.controller;

import com.google.gson.Gson;
import org.myBooks.beans.Book;
import org.myBooks.services.BookLinksService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/bookLinks")
public class BookLinksController {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    public Response getBookLinks(@QueryParam("isbn10") String isbn10) {
        System.out.println("isbn10 :: "+isbn10);
        List<Book> bookLinks = BookLinksService.getBookLinks(isbn10);
        //System.out.println(bookLinks);
        Gson gson = new Gson();
        return Response.ok(gson.toJson(bookLinks)).status(Response.Status.ACCEPTED).build();
    }

    public static void main(String args[]) {}
}




//package org.myBooks.controller;
//
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.QueryParam;
//import javax.ws.rs.core.Response;
//
//@Path("/bookLinks")
//public class BookLinksController {
//
//    @GET
//    public Response getBookLinks(@QueryParam("isbn10") String isbn10) {
////        isbn10 ="0143450832";
//        //isbn10 = isbn10.replaceAll(" ", "%20");
//        /*List<Book> bookLinks = BookLinksService.getBookLinks(isbn10);
//        //System.out.println(bookLinks);
//        Gson gson = new Gson();
//        return Response.ok(gson.toJson(bookLinks)).status(Response.Status.ACCEPTED).build();*/
//        return Response.ok().status(Response.Status.ACCEPTED).build();
//    }
//   /*@Path("/test")
//    @GET
//    public String getTest(){
//        return "ok";
//    }*/
//}