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

public class Amazon {

    private static String getBookUrl(Vendor vendor, String search) {
        String searchUrl = vendor.getQueryUrl().replaceAll("ISBN10", search);
        try {
            Document doc = Jsoup.connect(searchUrl).get();
            Elements pages = doc.getElementsByAttributeValue("class", "a-link-normal");
            Element page = pages.get(1);
            String bookUrl = vendor.getHome() + page.attr("href");
            bookUrl = bookUrl.replace("#customerReviews","");
            return bookUrl;
        }
        catch(Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static Float getPrice(Node cur) {
        Float price = null;
        if(cur.childNodeSize() < 3) return null;
        String val = cur.childNode(1).childNode(0).toString().replace("₹&nbsp;","");
        try{
            price = Float.parseFloat(val);
        }catch (Exception e) {
            return null;
        }
        return price;
    }

    public static List<Book> scrap(Vendor vendor, String search) {
        List<Book> books = new LinkedList<>();
        String bookUrl = getBookUrl(vendor, search);
        if(bookUrl.length() == 0) return books;
        try {
            Document doc = Jsoup.connect(bookUrl).get();
            Elements buttons= doc.getElementsByAttributeValue("class", "a-button-text");
            Elements elms = new Elements();

            for(Element e: buttons ) {
                if(e.toString().contains("class=\"a-button-text\" role=\"button\"") && e.childNodeSize() >= 3)
                    elms.add(e);
            }

            for(Element e : elms) {
                String format = "";
                Float val = null;
                boolean pr = false;
                for(Element j : e.children()) {
                    if(j.tagName().equals("span")) {
                        Node cur = j;
                        if(pr) {
                            if(format.equals("Hardcover") ||
                               format.equals("Kindle Edition") ||
                               format.equals("Paperback") ||
                               format.equals("Mass Market Paperback")
                            ) {
                                val = getPrice(cur);
                                if(val != null) {
                                    Book book = new Book();
                                    book.setPath(bookUrl);
                                    book.setFormat(format);
                                    book.setOurPrice(val);
                                    book.setVendor("Amazon");
                                    books.add(book);
                                }
                                break;
                            }
                        }
                        else {
                            while(cur.childNodeSize() != 0) cur = cur.childNode(0);
                            format = cur.toString();
                            pr = true;

                        }
                    }
                }

            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return books;
    }


}

