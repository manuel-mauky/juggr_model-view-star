package de.jug_gr.modelviewstar.mvppv;

import de.jug_gr.modelviewstar.business.*;
import de.jug_gr.modelviewstar.business.Error;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Singleton
public class Model {

    private final LibraryService libraryService;
    private List<Book> books = new ArrayList<>();

    private List<Runnable> booksObservers = new ArrayList<>();

    private List<Consumer<Error>> errorObservers = new ArrayList<>();

    public Model(LibraryService libraryService){
        this.libraryService = libraryService;
    }

    public List<Book> getBooks(){
        return books;
    }

    public void search(String searchString){
        books.clear();

        Consumer<Error> errorBroadcaster = err ->
            errorObservers.forEach(
                observer -> observer.accept(err));

        final List<Book> foundBooks = libraryService.search(searchString, errorBroadcaster);

        foundBooks.forEach(book -> {
            books.add(libraryService.showDetails(book, errorBroadcaster));
        });

        booksObservers.forEach(Runnable::run);
    }


    public void addBooksChangeObserver(Runnable observer){
        booksObservers.add(observer);
    }

    public void removeBooksChangeObserver(Runnable observer){
        booksObservers.remove(observer);
    }

    public void addErrorObserver(Consumer<Error> observer){
        errorObservers.add(observer);
    }

    public void removeErrorObserver(Consumer<Error> observer){
        errorObservers.remove(observer);
    }


}
