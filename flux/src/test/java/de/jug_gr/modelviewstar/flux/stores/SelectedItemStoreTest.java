package de.jug_gr.modelviewstar.flux.stores;

import de.jug_gr.modelviewstar.business.Book;
import de.jug_gr.modelviewstar.business.ErrorObject;
import de.jug_gr.modelviewstar.business.LibraryService;
import de.jug_gr.modelviewstar.flux.Dispatcher;
import de.jug_gr.modelviewstar.flux.actions.SelectAction;
import org.junit.Before;
import org.junit.Test;

import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SelectedItemStoreTest {


    private LibraryService backend;

    private Dispatcher dispatcher;

    private SelectedItemStore store;

    @Before
    public void setup() {
        backend = mock(LibraryService.class);
        dispatcher = mock(Dispatcher.class);

        store = new SelectedItemStore(backend, dispatcher);
    }

    @Test
    public void testSelectSuccessful() {
        // given
        Book bookWithoutDetails = new Book("/1", "testTitle");

        Book bookWithDetails = new Book("/1", "testTitle", "testAuthor", "testDescription");

        when(backend.showDetails(eq(bookWithoutDetails), any())).thenReturn(bookWithDetails);


        // when
        store.processAction(new SelectAction(bookWithoutDetails));


        // then
        assertThat(store.getError()).isEmpty();
        assertThat(store.getBookWithDetails()).isEqualTo(bookWithDetails);
    }

    @Test
    public void testSelectError() {
        // given
        Book bookWithoutDetails = new Book("/1", "testTitle");
        Book bookWithDetails = new Book("/2", "otherTitle", "otherAuthor", "otherDescription");


        when(backend.showDetails(eq(bookWithoutDetails), any())).thenAnswer(invocation -> {
            final Object[] arguments = invocation.getArguments();
            assertThat(arguments.length).isEqualTo(2);
            assertThat(arguments[1]).isInstanceOf(Consumer.class);

            Consumer<ErrorObject> errorConsumer = (Consumer<ErrorObject>) arguments[1];

            errorConsumer.accept(ErrorObject.error("testError", "testDetails"));

            return null;
        });

        // when
        store.processAction(new SelectAction(bookWithoutDetails));


        // then
        assertThat(store.getError()).isPresent();
        ErrorObject error = store.getError().get();
        assertThat(error.getMessage()).isEqualTo("testError");
        assertThat(error.getDetails()).isEqualTo("testDetails");

        assertThat(store.getBookWithDetails()).isNull();
    }
}
