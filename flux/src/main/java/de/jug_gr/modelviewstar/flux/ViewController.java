package de.jug_gr.modelviewstar.flux;

import de.jug_gr.modelviewstar.business.Book;
import de.jug_gr.modelviewstar.business.Error;
import de.jug_gr.modelviewstar.flux.actions.SearchAction;
import de.jug_gr.modelviewstar.flux.actions.SelectAction;
import de.jug_gr.modelviewstar.flux.stores.ListStore;
import de.jug_gr.modelviewstar.flux.stores.SelectedItemStore;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class ViewController {

    @FXML
    public TextField searchTextField;
    @FXML
    public ListView<Book> bookList;
    @FXML
    public Label titleLabel;
    @FXML
    public Label authorLabel;
    @FXML
    public Label errorLabel;
    @FXML
    public Label descriptionLabel;


    private ListStore listStore;

    private SelectedItemStore selectedItemStore;

    private Dispatcher dispatcher;

    public ViewController(ListStore listStore, SelectedItemStore selectedItemStore, Dispatcher dispatcher) {
        this.listStore = listStore;
        this.selectedItemStore = selectedItemStore;
        this.dispatcher = dispatcher;
    }


    public void initialize(){
        titleLabel.setText("");
        authorLabel.setText("");
        descriptionLabel.setText("");

        errorLabel.setVisible(false);


        listStore.onChange(this::updateBookList);

        selectedItemStore.onChange(this::updateDetails);

        bookList.setCellFactory(param -> new ListCell<Book>() {
            @Override
            protected void updateItem(Book item, boolean empty) {
                super.updateItem(item, empty);

                setText(item == null ? "" : item.getTitle());
            }
        });

        bookList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null) {
                dispatcher.dispatch(new SelectAction(newValue));
            }
        });
    }

    private void updateBookList() {

        final Optional<Error> error = listStore.getError();

        if(error.isPresent()) {
            errorLabel.setText(error.get().getMessage());
            errorLabel.setVisible(true);
            bookList.getItems().clear();
        } else {
            errorLabel.setVisible(false);
            bookList.getItems().clear();
            bookList.getItems().addAll(listStore.getBooks());
        }
    }

    private void updateDetails() {

        final Optional<Error> error = selectedItemStore.getError();

        if(error.isPresent()) {
            errorLabel.setText(error.get().getMessage());
            errorLabel.setVisible(true);
        }else {
            errorLabel.setVisible(false);
            final Book book = selectedItemStore.getBookWithDetails();

            titleLabel.setText(book.getTitle());
            authorLabel.setText(book.getAuthor());
            descriptionLabel.setText(book.getDesc());
        }

    }


    public void searchButtonPressed() {
        dispatcher.dispatch(new SearchAction(searchTextField.getText()));
    }
}
