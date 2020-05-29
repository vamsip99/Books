package org.myBooks.beans;

public class Book {
    private String vendor;
    private String isbn10;
    private String isbn13;
    private String domainName;
    private String path;
    private Float price;
    private String format;
    private Float ourPrice;

    @Override
    public String toString() {
        return "Book{" +
                "vendor='" + vendor + '\'' +
                ", isbn10='" + isbn10 + '\'' +
                ", isbn13='" + isbn13 + '\'' +
                ", domainName='" + domainName + '\'' +
                ", path='" + path + '\'' +
                ", price=" + price +
                ", ourPrice=" + ourPrice +
                ", format='" + format + '\'' +
                ", apiPath='" + apiPath + '\'' +
                '}';
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    private String apiPath;

    public String getApiPath() {
        return apiPath;
    }

    public void setApiPath(String apiPath) {
        this.apiPath = apiPath;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getOurPrice() {
        return ourPrice;
    }

    public void setOurPrice(Float ourPrice) {
        this.ourPrice = ourPrice;
    }
}
