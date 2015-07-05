package de.jug_gr.modelviewstar.flux.stores;

import de.jug_gr.modelviewstar.business.Book;
import de.jug_gr.modelviewstar.business.ErrorObject;
import de.jug_gr.modelviewstar.business.LibraryService;
import de.jug_gr.modelviewstar.flux.Dispatcher;
import de.jug_gr.modelviewstar.flux.actions.Action;
import de.jug_gr.modelviewstar.flux.actions.SearchAction;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Singleton
public class ListStore extends StoreBase {

    private List<Book> books = new ArrayList<>();

    private ErrorObject error;

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

            books.clear();
            books.addAll(libraryService.search(searchAction.getSearchString(), this::setError));

            publishOnChange();
        }
    }


    private void setError(ErrorObject error) {
        this.error = error;
    }


    public List<Book> getBooks() {
        return Collections.unmodifiableList(books);
    }

    public Optional<ErrorObject> getError() {
        return Optional.ofNullable(error);
    }
}
