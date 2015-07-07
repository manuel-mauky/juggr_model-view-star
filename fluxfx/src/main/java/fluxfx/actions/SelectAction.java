package fluxfx.actions;

import de.jug_gr.modelviewstar.business.Book;
import eu.lestard.fluxfx.Action;

public class SelectAction implements Action {

    private final Book selectedBook;

    public SelectAction(Book selectedBook) {
        this.selectedBook = selectedBook;
    }

    public Book getSelectedBook() {
        return selectedBook;
    }
}
