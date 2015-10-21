package view;

import model.State;
import model.StateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;


/**
 * @author Stanley Hitchcock
 */
public class StateButtonPanel extends JPanel {
    
    private final StateModel model;
    private final StateView view;
//    private final DoubleField arg;
//    private final DoubleField arg2;
    private final JComboBox<Integer> qbitsCombo;
    
    public StateButtonPanel(StateModel theModel, StateView theView, final MainFrame frame){
        
        model = theModel;
        view = theView;

        final int minQbits = 1;
        final int maxQbits = 30;
        Integer[] comboItems = new Integer[maxQbits-minQbits+1];
        for (int i = minQbits; i <= maxQbits; i++) {
            comboItems[i-minQbits] = i;
        }

        qbitsCombo = new JComboBox<Integer>(comboItems);
        qbitsCombo.setSelectedItem(comboItems[theModel.getState().getN() - minQbits]);
//        System.out.println(theModel.getQbits()-minQbits);
        qbitsCombo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int n = (Integer) qbitsCombo.getSelectedItem();
                model.setQbits(n);
            }
        });
        JPanel qcPane = new JPanel();
        qcPane.add(qbitsCombo);
        qcPane.setBorder(new TitledBorder(new EtchedBorder(), "Qbits"));

        
        JButton resetButton = new JButton("Menu");
        resetButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
//                model.reset();
                frame.showMenu();
            }
        });

        
        
        JButton qftButton = new JButton("<html>Quantum Fourier<br>Transform</html>");
        qftButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

//                model.set(model.getState().qft());

//                model.reset(model.getState());
                if (model.getState().getDoubles().get(0) == 0) {
                    JOptionPane.showMessageDialog(frame, "<html><p>The period find button</p>" +
                            "<p>should be pressed first</p></html>","",JOptionPane.ERROR_MESSAGE);
                }
                else {
                    if (!model.getState().isQftd()) {
                        model.getState().qft();
                        view.setQFT(true);
                        JOptionPane.showMessageDialog(frame, "<html><p>This is the Quantum Fourier Transform</p>" +
                                "<p>We should find that there are the same amount of lines as our period r</p>" +
                                "<p>" + model.getState().getMessage() + "<sup>r</sup> ≡ 1 (mod " + model.getState().getSemiprime() + ")</p></html>");
                    }
                    else {
                        model.getState().qft();
                        view.setQFT(true);
                        JOptionPane.showMessageDialog(frame, "<html><p>If we apply the Quantum Fourier Transform again</p>" +
                                "<p>We will revert back to the state at the Modular Exponentiation</p>" +
                                "</html>");
                    }
                }
            }
        });

        
        JButton unqftButton = new JButton("unqft");
        unqftButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
//                model.set(model.getState().unqft());
                model.getState().unqft();
                view.setQFT(false);
            }
        });


        ButtonGroup realImGroup = new ButtonGroup();

        
        final JButton realButton = new JButton("Real");
        realButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (view.isReal()) {
                    realButton.setText("Imaginary");
                    view.setReal(false);
                }
                else {
                    realButton.setText("Real");
                    view.setReal(true);
                }
            }
        });
//        realImGroup.add(realButton);


        final JCheckBox imButton = new JCheckBox("Absolute");
        imButton.setSelected(true);
        imButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
//                if (view.isMod()) {
                if(!imButton.isSelected()) {
//                    realButton.setText("");
//                    imButton.setSelected(false);
                    view.setMod(false);
//                    System.out.println(imButton.isSelected());

                }
                else {
//                    realButton.setText("real");
//                    imButton.setSelected(true);
                    view.setMod(true);
//                    System.out.println(imButton.isSelected());
                }
            }
        });
//        realImGroup.add(imButton);


//        arg = new DoubleField();
//        add(arg);

//        JButton oscButton = new JButton("osc. cycles");
//        oscButton.addActionListener(new ActionListener(){
//            public void actionPerformed(ActionEvent e){
////                try {
////                    State s = new State(model.getQbits());
////                    s.initOscillation(arg.getDouble());
////                    model.reset(s);
////                } catch (NumberFormatException nfe){
////
////                }
//            }
//        });
//        add(oscButton);

//        JButton spikeButton = new JButton("spike");
//        spikeButton.addActionListener(new ActionListener(){
//            public void actionPerformed(ActionEvent e){
////                try {
////                    State s = new State(model.getQbits());
//////                    s.initSpike(arg.getInt());
////                      s.initSpike(arg.getDouble(), arg2.getDouble());
////                    model.reset(s);
////                } catch (NumberFormatException nfe){
//
////                }
//            }
//        });
//        add(spikeButton);

//        JButton clicksButton = new JButton("period");
//        clicksButton.addActionListener(new ActionListener(){
//            public void actionPerformed(ActionEvent e){
////                try {
////                    State s = new State(model.getQbits());
////                    s.initClicks(arg.getInt(), arg2.getInt());
////                    model.reset(s);
////                } catch (NumberFormatException nfe){
////
////                }
//            }
//        });
//        add(clicksButton);

        JButton periodFindButton = new JButton("<html>Modular<br>Exponentiation</html>");
        periodFindButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    State s = new State(model.getQbits(),model.getState().getSemiprime(),model.getState().getMessage());
//                    s.initPeriodFind(arg.getInt(), arg2.getInt());
                    s.initPeriodFind();
                    model.set(s);

                    JOptionPane.showMessageDialog(frame,"<html><p>Here we are plotting the function</p>" +
                            "<p>f(x) = "+model.getState().getMessage()+"<sup>x</sup> (mod "+model.getState().getSemiprime()+")</p>" +
                            "<p></p><p>x from 0 to " + (model.getState().getDimension()-1) + "</p></html>");
                } catch (NumberFormatException nfe){

                }
            }
        });
        add(resetButton);
        add(qcPane);
        add(periodFindButton);
        add(qftButton);
//        add(unqftButton);
        Box realImPane = new Box(BoxLayout.Y_AXIS);
        realImPane.add(realButton);
        realImPane.add(imButton);
        add(realImPane);


        add(Box.createRigidArea(new Dimension(800,10)));

//        arg2 = new DoubleField();
//        add(arg2);
    }

    public void setQbits(int qbits) {
        qbitsCombo.setSelectedIndex(qbits-1);
    }

}
