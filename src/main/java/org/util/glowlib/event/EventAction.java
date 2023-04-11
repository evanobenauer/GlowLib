package org.util.glowlib.event;

/**
 * An Event Action is a runnable code block that can be subscribed or unsubscribed to an event. It has the option to function as
 * a simple block that may be subscribed, but has the ability to subscribe itself
 */
public class EventAction implements Runnable {

    private final EventE event;

    private final Runnable action;


    /**
     * This is the default EventAction constructor.
     * In order to create an event code using this constructor, you must use a lambda statement as the action argument
     * If the action is to be subscribed to the event upon its instantiation, you are able to accomplish that with this
     * @param subscribeOnInstantiation
     * @param action
     */
    public EventAction(EventE event, boolean subscribeOnInstantiation, Runnable action) {
        this.event = event;
        this.action = action;
        if (subscribeOnInstantiation) subscribe();
    }

    /**
     * This constructor is able to register an action to an event. It will not be subscribed upon instantiation and must be done
     * so outside the action
     * @param event
     * @param action
     */
    public EventAction(EventE event, Runnable action) {
        this(event,false,action);
    }

    /**
     * This constructor allows the specification of an action, but does not declare an event to go along with the action.
     * The event must subscribe the action on its own as it will not be attached on event creation
     * @param action
     */
    public EventAction(Runnable action) {
        this(null, false,action);
    }

    /**
     * This constructor is a basic constructor without event declaration or action declaration.
     * In order to use this constructor, you must create a new instantiation of the EventAction class and override the
     * run() method. Code must be added there.
     */
    public EventAction() {
        this(null, false,null);
    }


    @Override
    public void run() {
        action.run();
    }


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