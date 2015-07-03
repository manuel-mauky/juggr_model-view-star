package de.jug_gr.modelviewstar.mvc;

import de.jug_gr.modelviewstar.business.Book;
import de.jug_gr.modelviewstar.business.ErrorObject;
import de.jug_gr.modelviewstar.business.LibraryService;

import javax.inject.Singleton;
import java.util.List;
import java.util.function.Consumer;

public class Controller {

    private final Model model;
    private final View view;

    private LibraryService libraryService;

    public Controller(Model model, View view, LibraryService libraryService){
        this.model = model;
        this.view = view;
        this.libraryService = libraryService;

        Consumer<ErrorObject> errorConsumer = model::error;

        view.onSearch(searchString -> {
            final List<Book> bookList = libraryService.search(searchString, errorConsumer);

            model.setBooks(bookList);
        });

        view.onSelect(index -> {

            if(index >= 0 && index < model.getBooks().size()){
                final Book book = model.getBooks().get(index);

                final Book bookWithDetails = libraryService.showDetails(book, errorConsumer);

                model.selectBook(bookWithDetails);
            }else{
                model.selectBook(null);
            }
        });
    }

    public View getView(){
        return view;
    }

}
