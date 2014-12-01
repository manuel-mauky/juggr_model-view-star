package de.jug_gr.modelviewstar.mvvmfx;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MainView implements FxmlView<MainViewModel> {


    @FXML
    private Label titleLabel;

    @FXML
    private Label authorLabel;

    @FXML
    private Button rentButton;

    @FXML
    private TextField lendTextField;

    @FXML
    private Label returnName;

    @FXML
    private TextField searchTextField;

    @FXML
    private Button returnButton;

    @FXML
    private Button searchButton;

    @FXML
    private Label descriptionLabel;

    @FXML
    private ListView<BookViewModel> bookList;

    @FXML
    private ProgressIndicator listProgress;

    @FXML
    private ProgressIndicator detailProgress;

    @FXML
    private Label errorLabel;

    @InjectViewModel
    private MainViewModel viewModel;

    public void initialize(){
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

    public void lendButtonPressed() {
        viewModel.lendBook();
    }

    public void returnButtonPressed() {
        viewModel.returnBook();
    }
}
