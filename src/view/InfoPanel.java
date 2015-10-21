package view;

import model.StateModel;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

/**
 * @author Stanley Hitchcock
 */
public class InfoPanel extends JPanel implements Observer {

    private StateModel stateModel;
    JTextArea textPane;

    public InfoPanel(StateModel model) {
        stateModel = model;
        textPane = new JTextArea("Message: " + stateModel.getState().getMessage() +
                "   Semiprime: " + stateModel.getState().getSemiprime());
        textPane.setBackground(null);
        textPane.setOpaque(false);
//        System.out.println("Message: " + stateModel.getState().getMessage());
        add(textPane);
    }

    public void setStateModel(StateModel stateModel) {
        this.stateModel = stateModel;
        textPane.setText("Message: " + stateModel.getState().getMessage() +
                "   Semiprime: " + stateModel.getState().getSemiprime());
        repaint();
    }

    @Override
    public void update(Observable o, Object arg) {
        textPane.setText("Message: " + stateModel.getState().getMessage() +
                "   Semiprime: " + stateModel.getState().getSemiprime());
    }
}
