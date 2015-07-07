package fluxfx.stores;

import de.jug_gr.modelviewstar.business.Book;
import de.jug_gr.modelviewstar.business.ErrorObject;
import de.jug_gr.modelviewstar.business.LibraryService;
import eu.lestard.fluxfx.Store;
import fluxfx.actions.SearchAction;
import fluxfx.actions.SelectAction;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BooksStore extends Store {

    private ObservableList<Book> books = FXCollections.observableArrayList();

    private ObservableList<Book> readOnlyBooks = FXCollections.unmodifiableObservableList(books);

    private ReadOnlyObjectWrapper<ErrorObject> error = new ReadOnlyObjectWrapper<>();

    private ReadOnlyObjectWrapper<Book> selectedBook = new ReadOnlyObjectWrapper<>();
    private LibraryService libraryService;


    public BooksStore(LibraryService libraryService) {
        this.libraryService = libraryService;
        subscribe(SearchAction.class, this::processSearch);
        subscribe(SelectAction.class, this::processSelect);
    }

    private void processSearch(SearchAction action) {
        setError(null);

        selectedBook.set(null);

        books.clear();
        books.addAll(libraryService.search(action.getSearchString(), this::setError));
    }

    private void processSelect(SelectAction action) {
        setError(null);

        selectedBook.set(null);

        Book bookWithDetails = libraryService.showDetails(action.getSelectedBook(), this::setError);

        selectedBook.set(bookWithDetails);
    }


    private void setError(ErrorObject error) {
        this.error.set(error);
    }


    public ObservableList<Book> books() {
        return readOnlyBooks;
    }

    public ReadOnlyObjectProperty<Book> selectedBook() {
        return selectedBook.getReadOnlyProperty();
    }

    public ReadOnlyObjectProperty<ErrorObject> error() {
        return error.getReadOnlyProperty();
    }
}
