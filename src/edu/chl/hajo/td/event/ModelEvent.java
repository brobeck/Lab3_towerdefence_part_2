package edu.chl.hajo.td.event;

/*
        A single event sent from model to view

        **** Nothing to do here ***
 */
public class ModelEvent {

    // All possible (so far) events listed
    public enum Type {
        CREEP_KILLED,
        CREEP_FINISHED,
        GUN_TOWER_FIRE,
        NEW_TOWER,
        LEVEL_OVER // etc..
    }

    private final Type type;
    // Data to send
    private final Object value;

    public ModelEvent(Type type, Object value) {
        this.type = type;
        this.value = value;
    }

    public ModelEvent(Type type) {
        this(type, null);
    }

    public Type getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Event [type=" + type + ", value=" + value + "]";
    }
}
