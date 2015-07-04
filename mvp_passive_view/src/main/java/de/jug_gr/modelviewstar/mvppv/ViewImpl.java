package de.jug_gr.modelviewstar.mvppv;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.List;

public class ViewImpl extends AnchorPane implements View{

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
    private Presenter presenter;


    public ViewImpl(){
        load();
    }

    public void searchButtonPressed(){
        if(presenter != null){
            presenter.search();
        }
    }

    public void initialize(){
        bookList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)->{
            if(presenter != null){
                presenter.select();
            }
        });
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

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public Parent getRoot() {
        return this;
    }

    @Override
    public int getSelectedIndex() {
        return bookList.getSelectionModel().getSelectedIndex();
    }

    @Override
    public String getSearchString() {
        return searchTextField.getText();
    }

    @Override
    public void setBooks(List<String> bookTitles) {
        bookList.getItems().clear();
        bookList.getItems().addAll(bookTitles);
    }

    @Override
    public void setTitle(String title) {
        titleLabel.setText(title);
    }

    @Override
    public void setAuthor(String author) {
        authorLabel.setText(author);
    }

    @Override
    public void setDescription(String description) {
        descriptionLabel.setText(description);
    }

    @Override
    public void setError(String errorMessage) {
        errorLabel.setText(errorMessage);
    }
}
