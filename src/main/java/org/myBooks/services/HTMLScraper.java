package org.myBooks.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.myBooks.beans.Book;
import org.myBooks.beans.Vendor;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class HTMLScraper {
    public static List<Book> scrap(Vendor v, String isbn) {
        switch(v.getName()) {
            case "Crossword.in":
                return Crossword.scrap(v, isbn);
            case "Amazon.in":
                return Amazon.scrap(v, isbn);
        }
        return null;
    }
}
