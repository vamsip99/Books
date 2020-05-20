package org.myBooks.beans;

public class Book {
    private String vendor;
    private String isbn10;
    private String isbn13;
    private String domainName;
    private String path;
    private Integer price;
    private Integer ourPrice;

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
                ", apiPath='" + apiPath + '\'' +
                '}';
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getOurPrice() {
        return ourPrice;
    }

    public void setOurPrice(Integer ourPrice) {
        this.ourPrice = ourPrice;
    }
}
