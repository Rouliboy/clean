package org.clean;

import java.util.ArrayList;
import java.util.Date;

public class eventManagerClass {

    public static int MAX_NB_EVENTS = 5;

    // The name
    private String name;

    // The processor
    private EventProcessor eventProcessor;

    // The constructor
    public eventManagerClass(String name) {
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
            // Log
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

        // Call is managed from processor
        boolean hasManagedEvent = this.eventProcessor.isManaged(eventName);

        // Return true if isManaged, or else return false
        if (hasManagedEvent) {
            return true;
        } else {
            return false;
        }
    }

    public boolean hasCalledNotifierOnContext(final EventContext eventContext) {

        // code temporary commented out.
        // String notifierName = eventContext.getSource().getNotifier().getName();
        // String presenterUID = "AZHGT";
        // if (notifierName == presenterUID) {
        // return false;
        // }
        if (null == eventContext)
            return false;

        return eventContext.getSource().getNotifier().isCallable();
    }


    public void processEventWithParams(String eventName, String processName, Date date, int max, boolean flag, Long milliseconds) {

        boolean hasValue = Utils.hasValue(processName);
        String formattedName = Utils.formatProcessName(processName);


        if (null != formattedName && hasValue) {
            this.eventProcessor.process(MAX_NB_EVENTS, formattedName);
        } else {
            doWork(eventName, processName , date);
        }
    }

    private void doWork(String eventName, String processName, Date date) {
        MailNotifier mailNotifier = new MailNotifier();
        mailNotifier.notify(eventName, processName, date);
    }

    public Long computeEventCost(String eventName) {
        Event event = this.eventProcessor.getEvent(eventName);
        if (event.cost() < 99L && event.times() > 36) {
            return event.cost() * event.times() + 1;
        }
        return event.cost();
    }

}
