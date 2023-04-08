package org.util.glowlib.event;

/**
 * This is an event designed to have render materials placed for use in the GUI
 * This is an EXAMPLE event
 */
public class RenderEvent extends EventE {


    public RenderEvent() {
        events.add(this);
    }


    @Override
    public void post(Object... args) {
        //Add GL Code Here Probably
        super.post();
        //Add GL Code Here Probably
    }

}
