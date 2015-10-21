/*
 * Title:        autoevent<p>
 * Description:  To implement a generic firing mechanism for
 * EventListenerList.<p>
 * Copyright:    Copyright (c) Steven Vickers<p>
 * Company:      School of Computer Science, University of Birmingham<p>
 * @author Steven Vickers
 * @version 1.0
 */

package fyw.autoevent;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Autoevent class for change events.
 *<p>
 * Listener type is <code>ChangeListener</code>. <br>
 * Handler is <code>stateChanged</code>. <br>
 * Event parameter class is <code>ChangeEvent</code>.
 * @author sjv
 * @see fyw.autoevent
 */
public class AutoChangeEvent extends ChangeEvent implements Notifier {
    
    /**
     * Constructor.
     * @param source source of the event
     */
    public AutoChangeEvent(Object source) {
        super(source);
    }
    
    /**
     * Notify listener by calling
     *   <code>stateChanged(this)</code> on it.
     *<p>
     * This method (implementing <code>Notifier</code>) is the whole point of
     * having <code>AutoChangeEvent</code>.
     * It enforces the correct listener type
     *   (<code>ChangeListener</code>) and calls the
     *   correct handler (<code>stateChanged</code>).
     * @param l listener
     * @throws ClassCastException if listener not of type
     *   <code>ChangeListener</code>
     */
    public void notify(Object l) {
        ((ChangeListener) l).stateChanged(this);
    }
}
