package edu.chl.hajo.td.event;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

/*
     Very simple event bus (observer pattern).
     All observers can register for events and an
     observable can publish (send) events.

     **** Nothing to do here ***
 */
public enum EventBus {

    INSTANCE;

    private final List<IEventHandler> handlers = new ArrayList<>();


    public void register(IEventHandler handler) {
        if (!handlers.contains(handler)) {
            handlers.add(handler);
        } else {
            throw new IllegalStateException("Handlers may only register once");
        }
    }

    private static int n;

    public void publish(ModelEvent evt) {
        final boolean debug = false;
        // Tracking all events
        if (debug) {
            out.println(n++ + ":" + evt);
        }
        for (IEventHandler h : handlers) {
            h.onModelEvent(evt);
        }
    }

    public void publish(ModelEvent.Type tag) {
        publish(new ModelEvent(tag));
    }
}
