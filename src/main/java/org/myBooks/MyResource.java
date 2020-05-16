package org.myBooks;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.myBooks.beans.Customer;
import org.myBooks.dao.CustomerDao;
import org.myBooks.services.DBConnect;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
//    @Path("/do")
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }

//    public static void main(String arg[]) {
//        Session session = DBConnect.getSession();
//        Customer s = new Customer();
//        s.setUser_name("vamceep");
//        s.setPwd("abs");
//        s.setLast_name("ponnada");
//        s.setFirst_name("vamsi");
//        s.setEmail("vamcee.p@gmail.com");
//
//        Transaction tx = session.beginTransaction();
//        session.save(s);
//        tx.commit();
//
//
//        Query q = session.createQuery("from Customer where user_name =:v", Customer.class);
//        q.setParameter("v", "vamceep");
//        Customer c = (Customer)q.uniqueResult();
//        System.out.println(c.toString());
//    }
}
