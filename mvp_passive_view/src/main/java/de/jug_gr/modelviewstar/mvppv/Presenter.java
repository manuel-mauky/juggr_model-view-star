package de.jug_gr.modelviewstar.mvppv;

import de.jug_gr.modelviewstar.business.Book;

import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class Presenter {

    private final View view;

    public Presenter(Model model, View view){
        this.view = view;

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

        model.addErrorObserver(error ->
            view.setError(error.getMessage()));

        view.onSearch(()->
            model.search(view.getSearchString()));

        view.onSelect(() -> {
            final int selectedIndex = view.getSelectedIndex();
            if (selectedIndex >= 0 && selectedIndex < model.getBooks().size()) {
                final Book book = model.getBooks().get(selectedIndex);

                if (book != null) {
                    view.setTitle(book.getTitle());
                    view.setAuthor(book.getAuthor());
                    view.setDescription(book.getDesc());
                }
            } else {
                view.setTitle("");
                view.setAuthor("");
                view.setDescription("");
                view.setError("");
            }
        });
    }

    public View getView(){
        return view;
    }

}
