package de.jug_gr.modelviewstar.flux.stores;

import de.jug_gr.modelviewstar.business.Book;
import de.jug_gr.modelviewstar.business.ErrorObject;
import de.jug_gr.modelviewstar.business.LibraryService;
import de.jug_gr.modelviewstar.flux.Dispatcher;
import de.jug_gr.modelviewstar.flux.actions.Action;
import de.jug_gr.modelviewstar.flux.actions.SelectAction;

import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class SelectedItemStore extends StoreBase {

    private ErrorObject error;

    private Book bookWithDetails;

    private final LibraryService libraryService;

    public SelectedItemStore(LibraryService libraryService, Dispatcher dispatcher) {
        this.libraryService = libraryService;
        dispatcher.register(this);
    }


    @Override
    public void processAction(Action action) {
        if(action instanceof SelectAction) {
            SelectAction selectAction = (SelectAction) action;

            error = null;

            bookWithDetails = libraryService.showDetails(selectAction.getSelectedBook(), this::setError);

            publishOnChange();
        }
    }

    private void setError(ErrorObject error) {
        this.error = error;
    }

    public Book getBookWithDetails() {
        return bookWithDetails;
    }

    public Optional<ErrorObject> getError() {
        return Optional.ofNullable(error);
    }
}
