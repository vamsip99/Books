/*
package org.myBooks.Tests;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;

import static org.junit.Assert.assertEquals;

public class TestJersey extends JerseyTest {

    @Path("hello")
    public static class HelloResource {
        @GET
        public String getHello() {
            return "Hello World!";
        }
    }
    @Override
    protected Application configure() {
        return new ResourceConfig(org.myBooks.controller.BookLinksController.class);
    }

    @Test
    public void test() {

        final String hello = target("/booklinks").request().get(String.class);
        assertEquals("ok", hello);
       // assertEquals("should return status 200", 200, response.getStatus());
    }
}
*/
