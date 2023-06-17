package com.ejo.glowlib.event;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

/**
 * Events are classes that can be instantiated and have their own set of actions that are executed each loop during the post method.
 * You must type the .post() method of the event in the location you want all events to be called. You may subscribe actions to the
 * event to add to the execution list. Actions may be unsubscribed
 */
public class EventE {

    private final ArrayList<EventAction> eventActions = new ArrayList<>();

    private Object[] args = new Object[]{};

    /**
     * This method is placed wherever you want the event to be executed. If a method requires specific running methods, you shall
     * extend the event class and override the post method to include said restraints/extensions. It is important when overriding this
     * method to place the super on the last line of the override
     */
    public void post(Object... args) {
        this.args = args;
        for (EventAction event : getActions()) {
            try {
                event.run();
            } catch (ConcurrentModificationException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Adds an action to the execution list
     * Subscribing an action to the event means that wherever the event is posted, the action will execute
     * @param action
     * @return
     */
    public boolean subscribeAction(EventAction action) {
        try {
            if (!getActions().contains(action))
                return getActions().add(action);
        } catch (ConcurrentModificationException e) {
            e.printStackTrace();
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        return false;
    }

    /**
     * Removes an action from the execution list
     * @param action
     * @return
     */
    public boolean unsubscribeAction(EventAction action) {
        try {
            return getActions().remove(action);
        } catch (ConcurrentModificationException e) {
            e.printStackTrace();
            return false;
        } catch (NullPointerException e) {
            return false;
        }
    }

    /**
     * Removes all currently subscribed actions from the event
     * @return
     */
    public boolean unsubscribeAllActions() {
        try {
            for (EventAction action : getActions()) {
                unsubscribeAction(action);
            }
            return true;
        } catch (ConcurrentModificationException e) {
            e.printStackTrace();
            return false;
        } catch (NullPointerException e) {
            return false;
        }
    }

    public Object[] getArgs() {
        return args;
    }

    public ArrayList<EventAction> getActions() {
        return eventActions;
    }

}
