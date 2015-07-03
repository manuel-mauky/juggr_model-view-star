package de.jug_gr.modelviewstar.mvc;

import de.jug_gr.modelviewstar.business.Book;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javax.inject.Singleton;
import java.io.IOException;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class View extends AnchorPane{

    @FXML
    private Label titleLabel;

    @FXML
    private Label authorLabel;

    @FXML
    private TextField searchTextField;

    @FXML
    private Button searchButton;

    @FXML
    private Label descriptionLabel;

    @FXML
    private ListView<String> bookList;

    @FXML
    private Label errorLabel;

    private final Model model;

    private Consumer<String> searchButtonObserver;

    private Consumer<Integer> selectObserver;

    public View(Model model) {
        this.model = model;

        model.addBooksChangeObserver(() -> {
            bookList.getItems().clear();
            bookList.getItems().addAll(model.getBooks().stream().map(Book::getTitle).collect(Collectors.toList()));
        });

        model.addErrorObserver(err -> errorLabel.setText(err.getMessage()));

        model.addSelectedBookObserver(book -> {
            if(book != null){
                titleLabel.setText(book.getTitle());
                authorLabel.setText(book.getAuthor());
                descriptionLabel.setText(book.getDesc());
            }else{
                titleLabel.setText("");
                authorLabel.setText("");
                descriptionLabel.setText("");
                errorLabel.setText("");
            }
        });

        load();
    }

    public void initialize(){
        titleLabel.setText("");
        authorLabel.setText("");
        descriptionLabel.setText("");
        errorLabel.setText("");

        bookList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)->{
            if(selectObserver != null){
                selectObserver.accept(bookList.getSelectionModel().getSelectedIndex());
            }
        });
    }

    public void onSearch(Consumer<String> observer) {
        this.searchButtonObserver = observer;
    }

    public void onSelect(Consumer<Integer> observer) {
        this.selectObserver = observer;
    }

    public void searchButtonPressed(){
        if(searchButtonObserver != null){
            searchButtonObserver.accept(searchTextField.getText());
        }
    }

    private void load() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(this.getClass().getResource("/View.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
