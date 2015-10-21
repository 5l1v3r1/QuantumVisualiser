package unused;

import model.State;

import javax.swing.*;

/**
 *
 * @author sjv
 */
public class StateFrame extends JFrame {
    
    public StateFrame(State initialState) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setContentPane(new StateGUIPane(initialState,this));
        setSize(1600,1000);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        int qbits = 8;
        State psi = new State(8,15,7);
        StateFrame frame = new StateFrame(psi);
        
    }

}
