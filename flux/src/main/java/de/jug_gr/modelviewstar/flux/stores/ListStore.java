package de.jug_gr.modelviewstar.flux.stores;

import de.jug_gr.modelviewstar.business.Book;
import de.jug_gr.modelviewstar.business.Error;
import de.jug_gr.modelviewstar.business.LibraryService;
import de.jug_gr.modelviewstar.flux.Dispatcher;
import de.jug_gr.modelviewstar.flux.actions.Action;
import de.jug_gr.modelviewstar.flux.actions.SearchAction;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Singleton
public class ListStore extends StoreBase {

    private List<Book> books = new ArrayList<>();

    private Error error;




    private final LibraryService libraryService;

    public ListStore(LibraryService libraryService, Dispatcher dispatcher) {
        this.libraryService = libraryService;
        dispatcher.register(this);
    }


    @Override
    public void processAction(Action action) {

        if(action instanceof SearchAction) {
            SearchAction searchAction = (SearchAction) action;
            error = null;

            books = libraryService.search(searchAction.getSearchString(), this::setError);

            publishOnChange();
        }
    }


    private void setError(Error error) {
        this.error = error;
    }


    public List<Book> getBooks() {
        return books;
    }

    public Optional<Error> getError() {
        return Optional.ofNullable(error);
    }
}
