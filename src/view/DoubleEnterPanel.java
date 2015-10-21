package view;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.event.*;
import fyw.autoevent.*;

/**
 * Class for objects that allow the user to
 *  input doubles via a text box.
 * <p>The panel includes a button to be clicked
 *   to present the number.
 *   Alternatively, the enter key can be pressed.</p>
 * <p> Checks the number for syntactic correctness, repeating 
 *   if not.</p>
 * <p>Fires a state change autoevent when the number is ready.</p>
 * @author Steven Vickers
 */
public class DoubleEnterPanel extends JPanel {
    private final JTextField theTextField; //for entering the value
    private final JButton theButton; // click to present value
    
      // listener list for state change autoevent
    private final AutoEventListenerList listeners = new AutoEventListenerList();
      // the same event parameter object can be used each time
    private final AutoChangeEvent theChangeEvent = new AutoChangeEvent(this);
    
    private double value; //current value input
    
    /**
     * Constructor.
     * @param label to appear on button.
     */
    public DoubleEnterPanel(String label) {
        super(new GridLayout(2, 1));
        
        ActionListener enterListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enterValue();
            }
        };
        
        theTextField = new JTextField("");
        theTextField.setEditable(true);
        theTextField.addActionListener(enterListener);
        add(theTextField);
        
        theButton = new JButton(label);
        theButton.addActionListener(enterListener);
        add(theButton);
    }
    
    /**
     * Add state change listener.
     * @param theListener the listener
     */
    public void addChangeListener(ChangeListener theListener) {
        listeners.add(ChangeListener.class, theListener);
    }
    
    /**
     * Remove state change listener.
     * @param theListener the listener
     */
    public void removeChangeListener(ChangeListener theListener) {
        listeners.remove(ChangeListener.class, theListener);
    }
    
    /**
     * Handle "enter" action (enter in text field,
     *   or button pressed).
     * If text field is acceptable, then converts it to
     *   double, assigns to value field, and fires state
     *   change event.
     */
    private void enterValue() {
        boolean OK = true;
        double x = 0.0;
        try {
            x = Double.parseDouble(theTextField.getText());
        } catch (NumberFormatException e) {
            OK = false;
        }
        if (OK) {
            value = x;
            listeners.fireEvent(ChangeListener.class, theChangeEvent);
        }
    }
    
    /**
     * Get most recently accepted value.
     * @return the value
     */
    public double getValue() {
        return value;
    }
}
