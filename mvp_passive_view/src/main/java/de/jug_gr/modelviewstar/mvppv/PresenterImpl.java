package de.jug_gr.modelviewstar.mvppv;

import de.jug_gr.modelviewstar.business.Book;
import de.jug_gr.modelviewstar.business.LibraryService;

import java.util.List;
import java.util.stream.Collectors;

public class PresenterImpl implements Presenter {

    private LibraryService backend;
    private final Model model;
    private final View view;

    public PresenterImpl(LibraryService backend, Model model, View view){
        this.backend = backend;
        this.model = model;
        this.view = view;
        view.setPresenter(this);

        view.setTitle("");
        view.setAuthor("");
        view.setDescription("");
        view.setError("");


        model.addBooksChangeObserver(()->{
            final List<String> bookTitles = model
                .getBooks()
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());

            view.setBooks(bookTitles);
        });

        model.addSelectedBookObserver(book -> {
            if (book != null) {
                view.setTitle(book.getTitle());
                view.setAuthor(book.getAuthor());
                view.setDescription(book.getDesc());
            } else {
                view.setTitle("");
                view.setAuthor("");
                view.setDescription("");
                view.setError("");
            }
        });

        model.addErrorObserver(error ->
            view.setError(error.getMessage()));
    }

    @Override
    public void search() {
        List<Book> foundBooks = backend.search(view.getSearchString(), model::error);

        model.setBooks(foundBooks);
    }

    @Override
    public void select() {
        final int selectedIndex = view.getSelectedIndex();

        if (selectedIndex >= 0 && selectedIndex < model.getBooks().size()) {
            final Book book = model.getBooks().get(selectedIndex);

            model.selectBook(book);
        }
    }
}
