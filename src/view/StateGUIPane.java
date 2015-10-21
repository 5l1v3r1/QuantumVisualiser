/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import model.State;
import model.StateModel;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;

/**
 * @author Stanley Hitchcock
 */
public class StateGUIPane extends JPanel {
    private final StateView view;
    private final StateModel model;
    private final JSlider slider;
    private EvaluationPanel evaluationPanel;
    private InfoPanel infoPanel;
    private final StateButtonPanel stateButtonPanel;
    private final MainFrame frame;
    
    public StateGUIPane(State initialStateModel, MainFrame mainFrame) {
        super(new BorderLayout());
        this.frame = mainFrame;
        model = new StateModel(initialStateModel);
        view = new StateView(model);
        infoPanel = new InfoPanel(model);
        add(view, BorderLayout.WEST);

        stateButtonPanel = new StateButtonPanel(model, view, frame);
        add(stateButtonPanel, BorderLayout.SOUTH);

        evaluationPanel = new EvaluationPanel(model,view,frame);
        evaluationPanel.setPreferredSize(new Dimension(700,1000));
        add(evaluationPanel, BorderLayout.EAST);
        
        slider = new JSlider(SwingConstants.VERTICAL, 1, 1000, 70);

        slider.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent e){
                double x = slider.getValue();
//                System.out.println(x);
                //Polynomial slider, can show much larger ranges
                x = Math.pow(x,2)/500;
                view.setScale(x);
            }
        });
        add(slider, BorderLayout.CENTER);
        add(infoPanel, BorderLayout.NORTH);
    }

    public void newState(int qbits, int semiprime, int message) {
        remove(evaluationPanel);
        remove(infoPanel);
        model.set(new State(qbits, semiprime, message));
        infoPanel.setStateModel(model);
        stateButtonPanel.setQbits(qbits);
        evaluationPanel = new EvaluationPanel(model,view,frame);
        evaluationPanel.setPreferredSize(new Dimension(700,1000));
        add(evaluationPanel, BorderLayout.EAST);
        infoPanel = new InfoPanel(model);
        add(infoPanel, BorderLayout.NORTH);
        repaint();
    }



    public void resetEval() {
        evaluationPanel.resetEval();
    }
}
