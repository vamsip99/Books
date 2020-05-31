
package org.myBooks.Tests;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.myBooks.controller.BookLinksController;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import static junit.framework.TestCase.assertEquals;

public class BookServiceTest extends JerseyTest {
    @Override
    public Application configure() {
        //enable(TestProperties.LOG_TRAFFIC);
        //enable(TestProperties.DUMP_ENTITY);

        return new ResourceConfig(BookLinksController.class);
        // return new ResourceConfig(BookLinksController.class);
    }

    @Test
    public void testBookLinkControllerApi() {
        Response response = target("/bookLinks").queryParam("isbn10","Harry").request()
                .get();

        assertEquals("Http Response should be 200: ", Response.Status.ACCEPTED.getStatusCode(), response.getStatus());
        // assertEquals("Http Content-Type should be: ", MediaType.TEXT_PLAIN, response.getHeaderString(HttpHeaders.CONTENT_TYPE));

        // String content = response.readEntity(String.class);
        // assertEquals("Content of ressponse is: ", "Got it!", content);
    }

}



/*
package org.myBooks.Tests;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;
import org.myBooks.controller.BookLinksController;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class BookServiceTest extends JerseyTest {
    @Override
    public Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        System.out.println("height");
        return new ResourceConfig().register(BookLinksController.class)
                .packages("org.myBooks.controller");
        //return new ResourceConfig(TestJersey.HelloResource.class);
    }

    @Test
    public void testFetchBookLinks() {

        Response response = target("BookLinksController").queryParam("isbn10","Harry Potter").request().get();
        //final String hello = target("/booklinks").request().get(String.class);
        //assertEquals("ok", hello);
        assertEquals("should return status 200", 200, response.getStatus());
*/
/*assertNotNull("Should return booklinks list", response.getEntity().toString());
        System.out.println(response.getStatus());
        System.out.println(response.readEntity(String.class));*//*



    }





}
*/