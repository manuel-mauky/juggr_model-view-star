package de.jug_gr.modelviewstar.mvvm;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class CodeBehind {


    @FXML
    public TextField searchTextField;
    @FXML
    public Button searchButton;
    @FXML
    public ListView<BookTO> bookList;
    @FXML
    public Label titleLabel;
    @FXML
    public Label authorLabel;
    @FXML
    public Label descriptionLabel;
    @FXML
    public Label errorLabel;


    private ViewModel viewModel;

    public CodeBehind(ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void initialize() {
        searchTextField.textProperty().bindBidirectional(viewModel.searchStringProperty());
        titleLabel.textProperty().bind(viewModel.bookTitleProperty());
        authorLabel.textProperty().bind(viewModel.bookAuthorProperty());
        descriptionLabel.textProperty().bind(viewModel.bookDescriptionProperty());

        bookList.setItems(viewModel.booksProperty());

        viewModel.selectedBookProperty().bind(bookList.getSelectionModel().selectedItemProperty());
        errorLabel.textProperty().bind(viewModel.errorProperty());
    }

    public void searchButtonPressed() {
        viewModel.search();
    }
}
