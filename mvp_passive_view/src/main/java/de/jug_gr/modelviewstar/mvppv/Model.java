package de.jug_gr.modelviewstar.mvppv;

import de.jug_gr.modelviewstar.business.Book;
import de.jug_gr.modelviewstar.business.ErrorObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Model {
    private List<Book> books = new ArrayList<>();

    private List<Runnable> booksObservers = new ArrayList<>();

    private List<Consumer<ErrorObject>> errorObservers = new ArrayList<>();

    private List<Consumer<Book>> selectedBookObservers = new ArrayList<>();

    public List<Book> getBooks(){
        return books;
    }

    public void selectBook(Book book){
        selectedBookObservers.forEach(observer -> observer.accept(book));
    }

    public void setBooks(List<Book> books){
        this.books = books;
        booksObservers.forEach(Runnable::run);
    }

    public void error(ErrorObject error){
        errorObservers.forEach(observer -> observer.accept(error));
    }

    public void addBooksChangeObserver(Runnable observer){
        booksObservers.add(observer);
    }

    public void removeBooksChangeObserver(Runnable observer){
        booksObservers.remove(observer);
    }

    public void addErrorObserver(Consumer<ErrorObject> observer){
        errorObservers.add(observer);
    }

    public void removeErrorObserver(Consumer<ErrorObject> observer){
        errorObservers.remove(observer);
    }


    public void addSelectedBookObserver(Consumer<Book> observer){
        selectedBookObservers.add(observer);
    }

    public void removeSelectedBookObserver(Consumer<Book> observer){
        selectedBookObservers.remove(observer);
    }

}
