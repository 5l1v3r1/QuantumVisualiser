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


/**
 * Interface implemented by autoevent parameter classes.<p>
 * @author Steven Vickers
 * @see fyw.autoevent
 */

public interface Notifier {
    /**
     * Notify listener by calling
     * <code>listener.handler(this)</code>.<p>
     *
     * @param l listener
     * @throws ClassCastException if listener not of correct type
     */
  public void notify(Object l);

}
