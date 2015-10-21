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

import javax.swing.event.EventListenerList;

/**
 * Class extending <code>EventListenerList</code> with a generic
 *   firing mechanism.
 * @author Steven Vickers
 * @see fyw.autoevent
*/

public class AutoEventListenerList extends EventListenerList {
    /*
     * invariant: If 0 <= i < listeners.length and i is even, then
     * listeners[i+1] is of type listeners[i]
     */
    
    /**
     * Notify listeners of correct type.
     * 
     * requires: <CODE>type</CODE> is listener type corresponding
     *   to class of <CODE>e</CODE>
     * @param type type of listeners to be notified
     * @param e autoevent parameter object
     */
    public void fireEvent(Class type, Notifier e) {
        Object[] listeners = getListenerList();
        int len = listeners.length;
        
        for (int i = 0; i < len; i += 2) {
            if (listeners[i] == type) {
                e.notify(listeners[i+1]);
            }
        }
    }
}
