package view;

import javax.swing.*;

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
public class DoubleField extends JTextField {
    
    private double value = 0; //current value input
    
    /**
     * Constructor.
     */
    public DoubleField() {
        super("0.0");
        setColumns(10);
        setEditable(true);
    }
    
    
    /**
     * Handle "enter" action (button pressed).
     * If text field is acceptable, then converts it to
     *   double, assigns to value field.
     * 
     * @return converted number if acceptable
     * @throws NumberFormatException if not
     */
    public double getDouble()
        throws NumberFormatException {
        
        value = Double.parseDouble(getText());
        return value;
    }

    public int getInt()
        throws NumberFormatException {
        
        return (int)(Math.round(getDouble()));
    }

    /**
     * Get most recently accepted value.
     * @return the value
     */
    public double getValue() {
        return value;
    }
}
