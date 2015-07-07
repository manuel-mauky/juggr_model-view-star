package fluxfx.views;

import de.jug_gr.modelviewstar.business.Book;
import eu.lestard.fluxfx.View;
import fluxfx.actions.SelectAction;
import fluxfx.stores.BooksStore;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import java.util.Optional;

public class SearchResultView implements View {
    @FXML
    public ListView<Book> bookList;

    private final BooksStore store;

    public SearchResultView(BooksStore store) {
        this.store = store;
    }

    public void initialize() {

        bookList.setItems(store.books());

        bookList.setCellFactory(param -> new ListCell<Book>() {
            @Override
            protected void updateItem(Book item, boolean empty) {
                super.updateItem(item, empty);

                final String newTitle = Optional.ofNullable(item)
                        .map(Book::getTitle)
                        .orElse("");

                setText(newTitle);
            }
        });


        bookList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null) {
                publishAction(new SelectAction(newValue));
            }
        });
    }
}
