/*
 * Title:        autoevent<p>
 * Description:  To implement a generic firing mechanism for
 * EventListenerList.<p>
 * Copyright:    Copyright (c) Steven Vickers<p>
 * Company:      School of Computer Science, University of Birmingham<p>
 * @author Steven Vickers
 * @version 1.0
 */

package view;

import java.awt.event.*;
import fyw.autoevent.*;

/**
 * Autoevent class for change events.
 *<p>
 * Listener type is <code>ChangeListener</code>. <br>
 * Handler is <code>stateChanged</code>. <br>
 * Event parameter class is <code>ChangeEvent</code>.
 * @author sjv
 * @see fyw.autoevent
 */
public class AutoActionEvent extends ActionEvent implements Notifier {
    
    /**
     * Constructor.
     * @param source source of the event
     */
    public AutoActionEvent(Object source, int id, String command) {
        super(source, id, command);
    }
    
    public AutoActionEvent(Object source, int id, String command, int modifiers) {
        super (source, id, command, modifiers);
    }
    
    public AutoActionEvent(Object source, int id, String command, long when, int modifiers){
        super(source, id, command, when, modifiers);
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
        ((ActionListener) l).actionPerformed(this);
    }
}
