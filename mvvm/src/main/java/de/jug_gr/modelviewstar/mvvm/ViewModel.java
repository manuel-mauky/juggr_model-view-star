package de.jug_gr.modelviewstar.mvvm;

import de.jug_gr.modelviewstar.business.Book;
import de.jug_gr.modelviewstar.business.ErrorObject;
import de.jug_gr.modelviewstar.business.LibraryService;
import eu.lestard.advanced_bindings.api.ObjectBindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ViewModel {

    private final LibraryService libraryService;
    private StringProperty searchString = new SimpleStringProperty("");

    private StringProperty bookTitle = new SimpleStringProperty();
    private StringProperty bookAuthor = new SimpleStringProperty();
    private StringProperty bookDescription = new SimpleStringProperty();

    private ObservableList<BookTO> books = FXCollections.observableArrayList();

    private ObjectProperty<BookTO> selectedBook = new SimpleObjectProperty<>();

    private StringProperty error = new SimpleStringProperty();

    public ViewModel(LibraryService libraryService){
        this.libraryService = libraryService;

        bookTitle.bind(ObjectBindings.map(selectedBook, BookTO::getTitle));
        bookAuthor.bind(ObjectBindings.map(selectedBook, BookTO::getAuthor));
        bookDescription.bind(ObjectBindings.map(selectedBook, BookTO::getDescription));

    }


    public void search(){
        Consumer<ErrorObject> errorHandler = err -> error.set(err.getMessage());

        final List<Book> result = libraryService.search(searchString.get(), errorHandler);

        books.clear();
        books.addAll(result
                .stream()
                .map(bookWithoutDescription -> libraryService.showDetails(bookWithoutDescription, errorHandler))
                .map(BookTO::new)
                .collect(Collectors.toList()));
    }


    public StringProperty searchStringProperty() {
        return searchString;
    }

    public StringProperty bookTitleProperty() {
        return bookTitle;
    }

    public StringProperty bookAuthorProperty() {
        return bookAuthor;
    }

    public StringProperty bookDescriptionProperty() {
        return bookDescription;
    }

    public ObservableList<BookTO> booksProperty(){
        return books;
    }

    public ObjectProperty<BookTO> selectedBookProperty(){
        return selectedBook;
    }

    public StringProperty errorProperty(){
        return error;
    }


}
