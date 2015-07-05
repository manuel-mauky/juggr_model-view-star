package de.jug_gr.modelviewstar.flux.views;

import de.jug_gr.modelviewstar.business.Book;
import de.jug_gr.modelviewstar.flux.Dispatcher;
import de.jug_gr.modelviewstar.flux.actions.SelectAction;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import java.util.List;
import java.util.Optional;

public class SearchResultView {

    @FXML
    public ListView<Book> bookList;

    private final Dispatcher dispatcher;


    public SearchResultView(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public void initialize () {
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
                dispatcher.dispatch(new SelectAction(newValue));
            }
        });
    }

    public void updateResultList(List<Book> books) {
        bookList.getItems().clear();
        bookList.getItems().addAll(books);
    }

}
