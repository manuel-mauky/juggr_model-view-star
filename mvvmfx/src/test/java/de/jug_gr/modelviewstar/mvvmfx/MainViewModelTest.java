package de.jug_gr.modelviewstar.mvvmfx;


import de.jug_gr.modeviewstar.business.Book;
import org.junit.Test;

import static eu.lestard.assertj.javafx.api.Assertions.assertThat;

public class MainViewModelTest {

    @Test
    public void testSelectionOfBooks(){

        MainViewModel viewModel = new MainViewModel();


        Book book1 = createBook("Das Leben des Horst", "Horst", "Eine geschichte über Horst");
        Book book2 = createBook("Die Verwandlung", "Franz Kafka", "Als Gregor Samsa eines Morgens aus unruhigen Träumen erwachte...");

        final BookViewModel bookViewModel1 = new BookViewModel(book1);
        final BookViewModel bookViewModel2 = new BookViewModel(book2);

        viewModel.booksProperty().add(bookViewModel1);
        viewModel.booksProperty().add(bookViewModel2);

        assertThat(viewModel.bookTitleProperty()).hasNullValue();
        assertThat(viewModel.bookAuthorProperty()).hasNullValue();
        assertThat(viewModel.bookDescriptionProperty()).hasNullValue();


        viewModel.selectedBookProperty().set(bookViewModel1);

        assertThat(viewModel.bookTitleProperty()).hasValue("Das Leben des Horst");
        assertThat(viewModel.bookAuthorProperty()).hasValue("Horst");
        assertThat(viewModel.bookDescriptionProperty()).hasValue("Eine geschichte über Horst");

        
        viewModel.selectedBookProperty().set(bookViewModel2);
        assertThat(viewModel.bookTitleProperty()).hasValue("Die Verwandlung");
        assertThat(viewModel.bookAuthorProperty()).hasValue("Franz Kafka");
        assertThat(viewModel.bookDescriptionProperty()).hasValue("Als Gregor Samsa eines Morgens aus unruhigen Träumen erwachte...");
    }




    private Book createBook(String title, String author, String desc){
        return new Book(null, title, author, desc, null, null);
    }

}
