package de.jug_gr.modelviewstar.flux.views;

import de.jug_gr.modelviewstar.business.Book;
import de.jug_gr.modelviewstar.business.ErrorObject;
import de.jug_gr.modelviewstar.flux.stores.ListStore;
import de.jug_gr.modelviewstar.flux.stores.SelectedItemStore;
import javafx.fxml.FXML;

import javax.inject.Singleton;
import java.util.Collections;
import java.util.Optional;

@Singleton
public class ControllerView {

    @FXML
    private DetailsView detailsController;

    @FXML
    private SearchResultView searchResultsController;

    private ListStore listStore;

    private SelectedItemStore selectedItemStore;

    public ControllerView(ListStore listStore, SelectedItemStore selectedItemStore) {
        this.listStore = listStore;
        this.selectedItemStore = selectedItemStore;
    }

    public void initialize(){
        listStore.onChange(this::updateBookList);

        selectedItemStore.onChange(this::updateDetails);
    }

    private void updateBookList() {
        final Optional<ErrorObject> error = listStore.getError();

        if(error.isPresent()) {
            detailsController.updateError(error.get());

            searchResultsController.updateResultList(Collections.emptyList());
        } else {
            detailsController.updateError(null);

            searchResultsController.updateResultList(listStore.getBooks());
        }
    }

    private void updateDetails() {
        final Optional<ErrorObject> error = selectedItemStore.getError();

        if(error.isPresent()) {
            detailsController.updateError(error.get());
        }else {
            final Book book = selectedItemStore.getBookWithDetails();

            detailsController.updateBook(book);
        }
    }
}
