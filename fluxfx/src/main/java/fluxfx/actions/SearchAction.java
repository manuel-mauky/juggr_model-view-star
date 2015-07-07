package fluxfx.actions;

import eu.lestard.fluxfx.Action;

public class SearchAction implements Action {

    private final String searchString;

    public SearchAction(String searchString) {
        this.searchString = searchString;
    }

    public String getSearchString() {
        return searchString;
    }
}
