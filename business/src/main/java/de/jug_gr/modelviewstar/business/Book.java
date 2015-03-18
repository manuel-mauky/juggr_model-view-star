package de.jug_gr.modelviewstar.business;

import com.theoryinpractise.halbuilder.api.Link;

public class Book {

    private final String href;
    private final String title;
    private String author;
    private String desc;
    private Integer borrower;

    public Book(String href, String title, String author, String desc) {
        super();
        this.href = href;
        this.title = title;
        this.author = author;
        this.desc = desc;
    }

    public String getHref() {
        return href;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getBorrower() {
        return borrower;
    }

    public void setBorrower(Integer borrower) {
        this.borrower = borrower;
    }
}
