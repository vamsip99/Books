package org.myBooks.controller;

import com.google.gson.Gson;
import org.myBooks.beans.Book;
import org.myBooks.services.BookLinksService;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import java.util.List;

@Path("/bookLinks")
public class BookLinksController {
    @GET
    public Response getBookLinks() {
        String isbn10 ="0143450832";
        List<Book> bookLinks = BookLinksService.getBookLinks(isbn10);
        Gson gson = new Gson();
        return Response.ok(gson.toJson(bookLinks)).status(Response.Status.ACCEPTED).build();
    }
}
