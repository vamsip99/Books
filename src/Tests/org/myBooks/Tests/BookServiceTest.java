package org.myBooks.Tests;

import org.glassfish.jersey.test.JerseyTest;

public class BookServiceTest extends JerseyTest {
   /* @Override
    public Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);

        return new ResourceConfig(BookLinksController.class);
    }

    @Test
    public void testFetchBookLinks() {

        Response response = target("/booklinks").queryParam("isbn10","1593272936").request().get();
       assertEquals("should return status 200", 200, response.getStatus());
        assertNotNull("Should return booklinks list", response.getEntity().toString());
        System.out.println(response.getStatus());
        System.out.println(response.readEntity(String.class));
    }



    */

}
