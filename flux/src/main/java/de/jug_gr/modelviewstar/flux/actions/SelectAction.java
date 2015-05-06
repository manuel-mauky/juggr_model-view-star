package de.jug_gr.modelviewstar.flux.actions;

import de.jug_gr.modelviewstar.business.Book;

public class SelectAction implements Action {

    private final Book selectedBook;

    public SelectAction(Book selectedBook) {
        this.selectedBook = selectedBook;
    }

    public Book getSelectedBook() {
        return selectedBook;
    }
}
