package de.jug_gr.modelviewstar.mvpab;

import de.jug_gr.modelviewstar.business.Book;
import de.jug_gr.modelviewstar.business.LibraryService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import javax.inject.Inject;
import java.util.List;

public class MainPresenter {

    @FXML
    public TextField searchTextField;
    @FXML
    public ListView<Book> bookList;
    @FXML
    public Label titleLabel;
    @FXML
    public Label authorLabel;
    @FXML
    public Label descriptionLabel;
    @FXML
    public Label errorLabel;

    private ObservableList<Book> books = FXCollections.observableArrayList();

    @Inject
    LibraryService libraryService;

    public void initialize(){
        titleLabel.setText("");
        authorLabel.setText("");
        descriptionLabel.setText("");
        errorLabel.setText("");

        bookList.setItems(books);

        bookList.setCellFactory((ListView<Book> param)-> new ListCell<Book>(){
            @Override
            protected void updateItem(Book item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    setText(item.getTitle());
                }
            }
        });


        bookList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)->{
            if(newValue != null){
                final Book booksWithDetails = libraryService.showDetails(newValue, err -> errorLabel.setText(err.getMessage()));

                titleLabel.setText(booksWithDetails.getTitle());
                authorLabel.setText(booksWithDetails.getAuthor());
                descriptionLabel.setText(booksWithDetails.getDesc());
            }else{
                titleLabel.setText("");
                authorLabel.setText("");
                descriptionLabel.setText("");
                errorLabel.setText("");
            }
        });
    }

    public void searchButtonPressed() {
        final List<Book> foundBooks = libraryService.search(searchTextField.getText(), err -> errorLabel.setText(err.getMessage()));
        books.clear();
        books.addAll(foundBooks);
    }


}
