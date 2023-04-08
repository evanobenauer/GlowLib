package org.util.glowlib.event;

/**
 * An Event Action is a runnable code block that can be subscribed or unsubscribed to an event. It has the option to function as
 * a simple block that may be subscribed, but has the ability to subscribe itself
 */
public abstract class EventAction implements Runnable {


    private final EventE event;


    public EventAction(EventE event) {
        this.event = event;
    }

    public EventAction(EventE event, boolean subscribeOnInstantiation) {
        this(event);
        if (subscribeOnInstantiation) subscribe();
    }

    public EventAction() {
        this(null, false);
    }


    @Override
    public abstract void run();


    public boolean subscribe() {
        try {
            return getEvent().subscribeAction(this);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean unsubscribe() {
        try {
            return getEvent().unsubscribeAction(this);
        } catch (Exception e) {
            return false;
        }
    }


    public EventE getEvent() {
        return event;
    }

}