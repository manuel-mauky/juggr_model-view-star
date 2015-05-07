package de.jug_gr.modelviewstar.flux.views;

import de.jug_gr.modelviewstar.business.Book;
import de.jug_gr.modelviewstar.business.ErrorObject;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DetailsView {

    @FXML
    public Label titleLabel;
    @FXML
    public Label authorLabel;
    @FXML
    public Label errorLabel;
    @FXML
    public Label descriptionLabel;


    public void initialize() {
        titleLabel.setText("");
        authorLabel.setText("");
        descriptionLabel.setText("");
        errorLabel.setText("");

        errorLabel.visibleProperty().bind(errorLabel.textProperty().isEmpty().not());
    }

    public void updateBook(Book book) {
        errorLabel.setText("");
        titleLabel.setText(book.getTitle());
        authorLabel.setText(book.getAuthor());
        descriptionLabel.setText(book.getDesc());
    }

    public void updateError(ErrorObject error) {
        errorLabel.setText(error == null ? "" : error.getMessage());
    }

}
