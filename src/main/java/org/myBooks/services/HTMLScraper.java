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
            case "crossword.in":
                return crosswordScrap(v, isbn);
            case "Amazon.in":
                return amazoninScrap(v, isbn);
        }
        return null;
    }

    public static List<Book> crosswordScrap(Vendor vendor, String isbn) {
        Book book = null;
        String url;
        try {
            String searchUrl = vendor.getQueryUrl().replaceAll("ISBN10", isbn);
            Document doc = Jsoup.connect(searchUrl).get();
            Element pag = doc.getElementById("search-result-items");
            Elements e = ((Element)pag.childNode(1)).select("a");
            String bookUrl = vendor.getHome() + e.attr("href");
            doc = Jsoup.connect(bookUrl).get();
            Element ele = doc.getElementById("pricing_summary");

            Node pr = ele.childNode(1).childNode(3).childNode(2).childNode(1).childNode(1);
            Integer price = Integer.parseInt(pr.attr("#text"));
            book = new Book();
            book.setOurPrice(price);
            book.setPath(bookUrl);
            book.setVendor("Crossword.in");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        List<Book> books = new LinkedList<Book>();
        if(book != null) books.add(book);
        return books;
    }

    private static List<Book> amazoninScrap(Vendor vendor, String isbn) {
        List<Book> books = new LinkedList<Book>();
        String url;
        try {
            String searchUrl = vendor.getQueryUrl().replaceAll("ISBN10", isbn);
            Document doc = Jsoup.connect(searchUrl).get();
            Elements pages = doc.getElementsByAttributeValue("class", "a-link-normal");
            Element page = pages.get(1);
            String bookUrl = vendor.getHome() + page.attr("href");

            doc = Jsoup.connect(bookUrl).get();
            Element ele = doc.getElementById("formats");
            Elements elems = ele.getElementsByAttributeValue("class", "a-button a-spacing-mini a-button-toggle format");

            int i = 0;
            for(Element e : elems) {
                Element e2 = (Element)(e.childNode(0).childNode(0));
                try {
                    String cont = e2.childNode(5).childNode(1).childNode(0).toString();
                    Book b = new Book();
                    Float price = Float.parseFloat(cont.substring(8));
                    String format = e2.childNode(1).childNode(0).toString();
                    b.setPrice(price);
                    b.setFormat(format);
                    b.setVendor(vendor.getName());
                    b.setPath(bookUrl);
                    books.add(b);
                }
                catch (Exception ee){
                    ee.printStackTrace();
                }
                i++;
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return books;
    }
}
