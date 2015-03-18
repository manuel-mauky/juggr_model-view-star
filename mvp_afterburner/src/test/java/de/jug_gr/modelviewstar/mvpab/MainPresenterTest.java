package de.jug_gr.modelviewstar.mvpab;

import de.jug_gr.modelviewstar.business.Book;
import de.jug_gr.modelviewstar.business.LibraryService;
import de.saxsys.javafx.test.JfxRunner;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(JfxRunner.class)
public class MainPresenterTest {

    private LibraryService libraryService;

    private MainPresenter mainPresenter;

    @Before
    public void setup(){
        libraryService = mock(LibraryService.class);

        mainPresenter = new MainPresenter();
        mainPresenter.libraryService = libraryService;

        mainPresenter.searchTextField = new TextField();
        mainPresenter.bookList = new ListView<>();
        mainPresenter.titleLabel = new Label();
        mainPresenter.authorLabel = new Label();
        mainPresenter.descriptionLabel = new Label();
        mainPresenter.errorLabel = new Label();
    }

    @Test
    public void testSearch() throws InterruptedException, ExecutionException {
        // given
        Book book1 = createBook("a book starting with a", "some author", null);
        Book book1WithDescription = createBook("a book starting with a", "some author", "some description 1");
        Book book2 = createBook("another book starting with a", "some author", null);
        Book book2WithDescription = createBook("another book starting with a", "some author", "some description 2");
        Book book3 = createBook("book starting with b", "some author", null);
        Book book3WithDescription = createBook("book starting with b", "some author", "some description 3");

        when(libraryService.search(eq("a"), any())).thenReturn(Arrays.asList(book1, book2));
        when(libraryService.search(eq("b"), any())).thenReturn(Arrays.asList(book3));
        when(libraryService.search(eq(""), any())).thenReturn(Collections.emptyList());

        when(libraryService.showDetails(eq(book1), any())).thenReturn(book1WithDescription);
        when(libraryService.showDetails(eq(book2), any())).thenReturn(book2WithDescription);
        when(libraryService.showDetails(eq(book3), any())).thenReturn(book3WithDescription);


        // when
        mainPresenter.searchTextField.setText("a");
        mainPresenter.searchButtonPressed();


        // then

        CompletableFuture<Void> future = new CompletableFuture<>();

        Platform.runLater(() -> {
            future.complete(null);
            assertThat(mainPresenter.bookList.getItems()).hasSize(2);
        });

        future.get();
    }

    private Book createBook(String title, String author, String desc){
        return new Book(null, title, author, desc);
    }

}
