package org.myBooks.services;

import org.myBooks.beans.Book;
import org.myBooks.beans.Vendor;
import org.myBooks.dao.VendorDao;

import java.util.LinkedList;
import java.util.List;

public class BookLinksService {
    public static List<Book> getBookLinks(String isbn10) {
        List<Book> books = new LinkedList<Book>();
        List<Vendor> vendors = new VendorDao().getAll();
        for(Vendor v :  vendors) {
            Book book = HTMLScraper.scrap(v, isbn10);
            if(book != null) books.add(book);
        }
        return books;
    }
}
