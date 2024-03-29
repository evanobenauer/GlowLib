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

    private final ArrayList<EventAction> subscribeActionQueue = new ArrayList<>();
    private final ArrayList<EventAction> unsubscribeActionQueue = new ArrayList<>();

    private Object[] args = new Object[]{};

    /**
     * This method is placed wherever you want the event to be executed. If a method requires specific running methods, you shall
     * extend the event class and override the post method to include said restraints/extensions. It is important when overriding this
     * method to place the super on the last line of the override
     */
    public void post(Object... args) {
        this.args = args;
        try {
            for (EventAction event : getActions()) event.run();
        } catch (ConcurrentModificationException ignored) {
        }
        if (getSubscribeActionQueue().isEmpty() && getUnsubscribeActionQueue().isEmpty()) return;
        for (EventAction action : getSubscribeActionQueue()) subscribeAction(action);
        for (EventAction action : getUnsubscribeActionQueue()) unsubscribeAction(action);
        getSubscribeActionQueue().clear();
        getUnsubscribeActionQueue().clear();
    }

    /**
     * Adds an action to the execution list
     * Subscribing an action to the event means that wherever the event is posted, the action will execute
     *
     * @param action
     * @return
     */
    public boolean subscribeAction(EventAction action) {
        try {
            if (!getActions().contains(action)) {
                action.isSubscribed().set(true);
                return getActions().add(action);
            }
        } catch (ConcurrentModificationException | NullPointerException e) {
            return false;
        }
        return false;
    }

    public boolean queueSubscribeAction(EventAction action) {
        try {
            if (!getActions().contains(action) && !getSubscribeActionQueue().contains(action)) {
                action.isSubscribed().set(true);
                return getSubscribeActionQueue().add(action);
            }
        } catch (NullPointerException e) {
            return false;
        }
        return false;
    }

    /**
     * Removes an action from the execution list
     *
     * @param action
     * @return
     */
    public boolean unsubscribeAction(EventAction action) {
        try {
            action.isSubscribed().set(false);
            return getActions().remove(action);
        } catch (ConcurrentModificationException | NullPointerException e) {
            return false;
        }
    }

    public boolean queueUnsubscribeAction(EventAction action) {
        try {
            if (!getUnsubscribeActionQueue().contains(action)) {
                action.isSubscribed().set(false);
                return getUnsubscribeActionQueue().add(action);
            }
        } catch (NullPointerException e) {
            return false;
        }
        return false;
    }

    /**
     * Removes all currently subscribed actions from the event
     *
     * @return
     */
    public boolean unsubscribeAllActions() {
        try {
            for (EventAction action : getActions()) unsubscribeAction(action);
            return true;
        } catch (ConcurrentModificationException | NullPointerException e) {
            return false;
        }
    }

    public boolean queueUnsubscribeAllActions() {
        try {
            for (EventAction action : getActions())
                if (!getUnsubscribeActionQueue().contains(action)) unsubscribeAction(action);
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }

    public Object[] getArgs() {
        return args;
    }

    private ArrayList<EventAction> getSubscribeActionQueue() {
        return subscribeActionQueue;
    }

    private ArrayList<EventAction> getUnsubscribeActionQueue() {
        return unsubscribeActionQueue;
    }

    public ArrayList<EventAction> getActions() {
        return eventActions;
    }

}
