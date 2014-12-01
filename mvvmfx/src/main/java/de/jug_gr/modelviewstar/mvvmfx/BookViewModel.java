package de.jug_gr.modelviewstar.mvvmfx;

import de.jug_gr.modeviewstar.business.Book;

public class BookViewModel {

    private final Book book;

    public BookViewModel(Book book){
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
