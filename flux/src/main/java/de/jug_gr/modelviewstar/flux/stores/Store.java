package de.jug_gr.modelviewstar.flux.stores;

import de.jug_gr.modelviewstar.flux.actions.Action;

public interface Store {

    void processAction(Action action);


    void onChange(Runnable runnable);


}
