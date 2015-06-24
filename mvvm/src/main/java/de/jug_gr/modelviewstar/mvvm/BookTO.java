package de.jug_gr.modelviewstar.mvvm;

import de.jug_gr.modelviewstar.business.Book;

/**
 * Transfer object to decouple the View from the Model
 */
public class BookTO {

    private final Book book;

    public BookTO(Book book){
        this.book = book;
    }

    public String getTitle(){
        return book.getTitle();
    }

    public String getAuthor(){
        return book.getAuthor();
    }

    public String getDescription(){
        return book.getDesc();
    }

    @Override
    public String toString() {
        return getTitle();
    }
}
