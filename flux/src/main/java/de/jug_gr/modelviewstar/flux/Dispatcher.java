package de.jug_gr.modelviewstar.flux;

import de.jug_gr.modelviewstar.flux.actions.Action;
import de.jug_gr.modelviewstar.flux.stores.Store;

import javax.inject.Singleton;
import java.util.HashSet;
import java.util.Set;

@Singleton
public class Dispatcher {

    private Set<Store> stores = new HashSet<>();

    public void dispatch(Action action) {
        stores.forEach(store -> store.processAction(action));
    }


    public void register(Store store) {
        stores.add(store);
    }

}
