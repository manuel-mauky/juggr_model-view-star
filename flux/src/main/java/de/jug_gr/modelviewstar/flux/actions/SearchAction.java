package de.jug_gr.modelviewstar.flux.actions;

public class SearchAction implements Action {

    private final String searchString;

    public SearchAction(String searchString) {
        this.searchString = searchString;
    }

    public String getSearchString() {
        return searchString;
    }
}
