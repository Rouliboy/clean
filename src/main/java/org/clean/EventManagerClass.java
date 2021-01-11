package org.clean;

import java.util.ArrayList;
import java.util.Date;

public class EventManagerClass {

    public static int MAX_NB_EVENTS = 5;

    // The name
    private String name;

    // The processor
    private EventProcessor eventProcessor;

    // The constructor
    public EventManagerClass(String name) {
        this.name = name;
        this.eventProcessor = new EventProcessor();
    }

    /**
     *
     */
    public boolean is_included_in(ArrayList a) {
        try {
            return a.contains(this.name);
        } catch(Exception e) {
        }
        return false;
    }

    public void manageEvent(String theStr) {

        System.out.println("Processing : " + theStr);
        try {
            this.eventProcessor.process(MAX_NB_EVENTS, theStr);
        } catch(Exception e) {
            throw new RuntimeException("Error when processing event with name:" + theStr);
        }
    }

    public boolean hasManagedEvent(String eventName) {

        boolean hasManagedEvent = this.eventProcessor.isManaged(eventName);
        if (hasManagedEvent) {
            return true;
        } else {
            return false;
        }
    }

    public void processEventWithParam(String eventName, String processName, Date date, int max, boolean hasValue, Long milliseconds) {
        // TODO
    }

}
