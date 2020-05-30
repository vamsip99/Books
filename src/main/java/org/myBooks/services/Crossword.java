package org.myBooks.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.myBooks.beans.Book;
import org.myBooks.beans.Vendor;

import java.util.LinkedList;
import java.util.List;

public class Crossword {

    public static List<Book> scrap(Vendor vendor, String search) {
        Book book = null;
        List<Book> books = new LinkedList<Book>();
        String url;
        try {
            String searchUrl = vendor.getQueryUrl().replaceAll("ISBN10", search);
            //System.out.println("Crossword url "+searchUrl);
            Document doc = Jsoup.connect(searchUrl).get();
            Element pag = doc.getElementById("search-result-items");
            Elements e = ((Element)pag.childNode(1)).select("a");
            String bookUrl = vendor.getHome() + e.attr("href");
            doc = Jsoup.connect(bookUrl).get();
            Element ele = doc.getElementById("pricing_summary");
            Elements eles = ((Element)(ele.childNode(1))).getElementsByAttributeValue("class", "our_price");
            ele = eles.first();
            Node pr = ele.childNode(2).childNode(1).childNode(1);
            Float price = Float.parseFloat(pr.toString());
            book = new Book();
            book.setOurPrice(price);
            book.setPath(bookUrl);
            book.setVendor("Crossword.in");
            books.add(book);

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return books;
    }
}
