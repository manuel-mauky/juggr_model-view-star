package fluxfx.views;

import de.jug_gr.modelviewstar.business.Book;
import de.jug_gr.modelviewstar.business.ErrorObject;
import eu.lestard.advanced_bindings.api.ObjectBindings;
import eu.lestard.fluxfx.View;
import fluxfx.stores.BooksStore;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DetailsView implements View {


    @FXML
    public Label titleLabel;
    @FXML
    public Label authorLabel;
    @FXML
    public Label descriptionLabel;
    @FXML
    public Label errorLabel;

    private final BooksStore store;

    public DetailsView(BooksStore store) {
        this.store = store;
    }

    public void initialize() {

        titleLabel.textProperty().bind(ObjectBindings.map(store.selectedBook(), Book::getTitle, ""));
        authorLabel.textProperty().bind(ObjectBindings.map(store.selectedBook(), Book::getAuthor, ""));
        descriptionLabel.textProperty().bind(ObjectBindings.map(store.selectedBook(), Book::getDesc, ""));

        errorLabel.textProperty().bind(ObjectBindings.map(store.error(), ErrorObject::getMessage, ""));
    }

}
