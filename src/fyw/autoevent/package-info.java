/*
 * package-info.java
 *
 * Created on 20 October 2007, 02:33
 *
 */

/**
 * The package <code>autoevent</code> implements a generic firing mechanism for an
 *   extension of <code>EventListenerList</code>.
 * <p>
 * <code>EventListenerList</code> suffers a disadvantage.
 *   When an event is fired, it is necessary
 *   to search down the list, looking for listeners registered for that particular
 *   event class, and then call the appropriate handler method on the listener.
 *   Since the class <code>EventListenerList</code> does not know the different handler methods
 *   for all the different events, it cannot itself implement the firing method.
 *   This is done instead in the classes that use <code>EventListenerList</code> (the method
 *   <code>fireFooXXX</code> in the API). Since that includes a slightly complicated list search,
 *   it is very unmodular.
 * <p>
 * The mechanism provided here extends <code>EventListenerList</code> to include a generic fire method.
 * <br>
 * The sole extra structure needed is that each event must know what handler should be
 *   called on its listeners. This is done by extending the existing event classes
 *   by a method <code>notify(Object l)</code> that (i) checks that <code>l</code> is of the correct listener
 *   class, and (ii) calls the appropriate handler. (<code>AutoChangeEvent</code> is an example.)
 * <p>
 * Abstractly, we assume that an "event family" E is determined by:
 * <ul>
 *   <li> an interface <code>EListener</code>, to be implemented by its listeners
 *   <li> a class <code>EEvent</code>, the class of the event parameters.
 * </ul>
 *   The events in the family E correspond to the methods in <code>EListener</code>.
 *<p>
 * e.g.
 * <ul>
 *   <li> Mouse is an event family determined by the listener interface
 *     <code>MouseListener</code>, and parameter class <code>MouseEvent</code>.
 *     It has 5 events in it,
 *     corresponding to the 5 methods in <code>MouseListener</code>
 *     (<code>mouseClicked</code>, <code>mouseEntered</code>, etc.).
 *   <li> <code>MouseMotion</code> is an event family determined by the listener interface
 *     <code>MouseMotionListener</code>,
 *     and parameter class <code>MouseEvent</code>. It has 2 events
 *     in it, <code>mouseDragged</code> and <code>mouseMoved</code>.
 * </ul>
 *   Note that both these families have the same parameter class,
 *    <code>MouseEvent</code>.
 * <p>
 * To use the generic firing for a particular event, one must provide an
 *   extension of the parameter class of its event family.
 * <br>
 * e.g. to use it with <code>mouseClicked</code> events, one must provide an extension
 *   <code>AutoMouseClicked</code> of <code>MouseEvent</code>.
 *   An instance of <code>AutoMouseClicked</code> has
 *   exactly the same attributes (the event parameters) as an instance of
 *   <code>MouseEvent</code>, but contains in addition a method notify to embody the
 *   knowledge that <code>mouseClicked</code> is to be called on the listener.
 * <p>
 * Abstractly, if XXX is an event in the family E, then one must provide
 * <pre>
 *   public class AutoXXXEvent extends EEvent implements Notifier {
 *
 *      // constructors as in EEvent
 *
 *     public void notify(Object l) {
 *       ((EListener)l).XXX(this);
 *     }
 *   }
 * </pre>
 * Suppose C is a class that would otherwise have used
 *   <code>EventListenerList</code>.
 * To make it use <code>AutoEventListenerList</code>,
 * <ul>
 *   <li> use an attribute
 *       <code>private AutoEventListenerList theListeners;</code>
 *     (instead of <code>EventListenerList</code>),
 *  <li> give it methods <code>addEListener</code>, <code>removeEListener</code> just as before,
 *  <li> define methods
 *  <pre>
 *      protected void fireXXXEvent() {
 *        theListeners.fireEvent(EListener.class, new AutoXXXEvent(...));
 *      }
 *  </pre>
 *   <li> call <code>fireXXXEvent</code> in appropriate places.
 * </ul>
 * @author Steven Vickers
 * @serial exclude
*/
package fyw.autoevent;
