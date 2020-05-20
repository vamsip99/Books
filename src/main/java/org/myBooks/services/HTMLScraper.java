package org.myBooks.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.myBooks.beans.Book;
import org.myBooks.beans.Vendor;

import java.io.IOException;

public class HTMLScraper {
    public static Book scrap(Vendor v, String s) {
        switch(v.getName()) {
            case "crossword.in":
                return crosswordScrap(v, s);
        }
        return null;
    }

    public static Book crosswordScrap(Vendor vendor, String isbn) {

//        String url = "https://www.crossword.in/search?q=0143450832";
//        url = "https://www.crossword.in/search?q=1526626172";
//        getBookUrl(url, "https://www.crossword.in");

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
        catch(IOException e) {
            e.printStackTrace();
        }
        return book;
    }
}
