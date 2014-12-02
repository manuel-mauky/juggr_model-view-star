package de.jug_gr.modelviewstar.mvppv;

import javafx.scene.Parent;

import java.util.List;

public interface View {

    Parent getRoot();

    void onSearch(Runnable observer);

    void onSelect(Runnable observer);

    int getSelectedIndex();

    String getSearchString();

    void setBooks(List<String> bookTitles);

    void setTitle(String title);

    void setAuthor(String author);

    void setDescription(String description);

    void setError(String errorMessage);

}
