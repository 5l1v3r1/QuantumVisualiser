package view;

import model.Sort;
import model.StateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import static model.State.*;

/**
 * @author Stanley Hitchcock
 */
public class EvaluationPanel extends JPanel  {

    private final StateModel model;
    private final StateView view;
    private final JComboBox<String> results;
    private final JButton resultButton;
    private final JButton answerButton;
    private final ContinuedFraction continuedFraction;
    private final MainFrame mainFrame;
    private final JButton factorButton;
    private final JButton percentageButton;
    private final JButton rsaButton;

    public EvaluationPanel(StateModel stateModel, StateView stateView, MainFrame frame) {
        mainFrame = frame;
        model = stateModel;
        view = stateView;
//        model.getState().getSort();
        results = new JComboBox<String>();
        resultButton = new JButton("Show Results");
        answerButton = new JButton("Test Answer");
        final ArrayList<Integer> initArray = new ArrayList<Integer>();
        initArray.add(-1);
        continuedFraction = new ContinuedFraction(initArray,model,-1,this);
        factorButton = new JButton("Factors");
        percentageButton = new JButton("% of Success");
        rsaButton = new JButton("RSA");


        rsaButton.setVisible(false);
        factorButton.setVisible(false);
        percentageButton.setVisible(false);

        resultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                results.removeAllItems();
                ArrayList<Sort> sort = model.getState().getSort();
//                double total = 0;
                for (int i = sort.size()-1; i >= 0 && sort.size()-i < 10000 ; i--) {
                    results.addItem(sort.get(i).toString());
//                    total += sort.get(i).getMod();
                }
//                System.out.println("Sort Updated, total at " + total);
            }
        });

        answerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String answer = results.getItemAt(results.getSelectedIndex());
                    int ans = Integer.parseInt(answer.split("\\p{P}")[1]);
                    if (ans == 0) {
                        JOptionPane.showMessageDialog(mainFrame, "Even though 0 measures at a high probability,\n" +
                                "it does not give any useful information.\n" +
                                "Try another measurement", "", JOptionPane.ERROR_MESSAGE);
                    }
                    else {
//                System.out.println(ans);

//                for (Integer i : model.getState().continuedFraction(ans)) {
//                    System.out.println(i);
//                }
//                System.out.println(model.getState().answerCheckExtended(ans));
//                        System.out.println("Setting Input");
                        continuedFraction.setInput(model.getState().answerReturn(ans));
//                        System.out.println("Setting Answer");
                        continuedFraction.setAnswer(ans);
//                for (Integer integer : continuedFraction.input) {
//                    System.out.println(integer);
//                }
//                        System.out.println("Repainting Cont. Fraction");
                        continuedFraction.repaint();
//                        System.out.println("Repainting");
//                        repaint();
//                        System.out.println("Done");
//                    System.out.println("Period is " + continuedFraction.period);
//                    if (continuedFraction.period != -1) {
//                        factorButtonAdd();
//                    }
//                    repaint();
                    }
                }
                catch(NullPointerException e1) {
//                    System.out.println(e1);
                }
            }
        });


        setLayout(new BorderLayout());
//        GridBagConstraints gc = new GridBagConstraints();

//        gc.gridy = 0;
//        gc.gridx = 0;

//        add(resultButton,gc);
        Box box = new Box(BoxLayout.X_AXIS);
        box.add(resultButton);
        box.add(results);
        box.add(answerButton);

//        gc.gridy++;
//        gc.gridwidth = 2;

        add(box,BorderLayout.NORTH);

        add(continuedFraction,BorderLayout.CENTER);

        Box box2 = new Box(BoxLayout.Y_AXIS);
        box2.add(factorButton);
        box2.add(percentageButton);
        box2.add(rsaButton);

        add(box2, BorderLayout.SOUTH);

    }

    public void resetEval() {
        ArrayList<Integer> reset = new ArrayList<Integer>();
        reset.add(-1);
        continuedFraction.setInput(reset);
    }

//    public void factorButtonAdd() {
//        JButton factorButton = new JButton("Factors?");
//        System.out.println("This one factorises");
//
//        factorButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    int[] factors = model.getState().factorise(continuedFraction.period);
//                    JOptionPane.showMessageDialog(mainFrame, "" +
//                            "<html>" +
//                            "<p>From this period we can factorise</p>" +
//                            "<p>We have " +model.getState().getMessage()+ "<sup>"+ continuedFraction.period
//                            +"</sup> ≡ 1 (mod" +
//                            + model.getState().getSemiprime() + ")</p>" +
//                            "<p>Firstly, " + continuedFraction.period + " is even, so </p>" +
//                            "<p>x = " + model.getState().getMessage() + "<sup>" + continuedFraction.period + "/2" +
//                            "</html>", "" , JOptionPane.INFORMATION_MESSAGE);
////                                    g2.drawString("Answer Found! " + fraction.getDen() +
////                                            "\n Factors: " + factors[0] + " and " + factors[1], initx + 15, inity + scale * (n + 5) - 5);
//
//
//                } catch (IllegalArgumentException e1) {
////                                    g2.drawString("Answer Found! " + fraction.getDen() +
////                                                    "\n Factors can't be found for this message, try with another.",
////                                            initx + 15, inity + scale * (n + 5) - 5);
//                }
//            }
//
//        });
//        add(factorButton, BorderLayout.SOUTH);
//        repaint();
//    }

    public void addFactorButton(final int period) {
        factorButton.setVisible(true);
        for (ActionListener actionListener : factorButton.getActionListeners()) {
            factorButton.removeActionListener(actionListener);
        }
        factorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    int[] factors = model.getState().factorise(period);
                    JOptionPane.showMessageDialog(mainFrame, "<html><p>We can factorise because: </p><p></p>" +
                            "<p>1. "+ period + " is even</p>" +
                            "<p>Let x = " + model.getState().getMessage()+ "<sup>"+period+"/2"+"</sup></p>" +
                            "<p>Therefore 0 ≡ x<sup>2</sup>-1 ≡ (x-1)(x+1) (mod "+model.getState().getSemiprime()+")</p><p></p>" +
                            "<p>2. x+1 = " + model.getState().getMessage() + "<sup>"+period+ "/2</sup> +1 ≢ 0 (mod "+ model.getState().getSemiprime() + ")</p>" +
                            "<p>Which means that x-1 and x+1 both share a </p>" +
                            "<p>common prime factor with "+ model.getState().getSemiprime() + "</p><p></p>" +
                            "<p>Factors: " + factors[0] + " and " + factors[1]+ "</p></html>");
                }
                catch (IllegalArgumentException e1) {
                    if (period%2 != 0) JOptionPane.showMessageDialog(mainFrame,
                                    "<html><p>We cannot factorise because</p>" +
                                    "<p>"+ period + " is not even</p>" +
                                    "<p></p>" +
                                    "<p>Try again with another message</p></html>");
                    else JOptionPane.showMessageDialog(mainFrame,
                                    "<html><p>We cannot factorise because</p>" +
                                    "<p>"+model.getState().getMessage() + "<sup>"+period+ "/2</sup> +1 ≡ 0 (mod "+ model.getState().getSemiprime() + ")</p>" +
                                    "<p></p>" +
                                    "<p>Try again with another message</p></html>");


                }


            }
        });

    }

    public void addPercentageButton() {
        percentageButton.setVisible(true);
        for (ActionListener actionListener : percentageButton.getActionListeners()) {
            percentageButton.removeActionListener(actionListener);
        }
        try {
            percentageButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if (model.getState().getQbits() > 20) {
                        Object[] options = {"Yes",
                                "No",};
                        int n = JOptionPane.showOptionDialog(mainFrame,
                                "<html><p>This will take a while with this amount of Qbits.</p><p>Are you sure you want to wait?</p></html>",
                                "",
                                JOptionPane.YES_NO_CANCEL_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                options,
                                options[0]);
                        if (n == 0) JOptionPane.showMessageDialog(mainFrame, "<html><p>If we test every possible result weighted with the percentage </p> " +
                                "<p>likelihood of measurement, we get a total success percentage of </p><p><center><font size=\"20\">" + (int) model.getState().percentage() + "%</font></center></p></html>");
                    }
                    else JOptionPane.showMessageDialog(mainFrame, "<html><p>If we test every possible result weighted with the percentage </p> " +
                            "<p>likelihood of measurement, we get a total success percentage of </p><p><center><font size=\"20\">" + (int)model.getState().percentage() + "%</font></center></p></html>");
                }
            });
        }
        catch(IndexOutOfBoundsException e) {

        }
    }

    public void addRSAButton(final int period) {
        rsaButton.setVisible(true);
        for (ActionListener actionListener : rsaButton.getActionListeners()) {
            rsaButton.removeActionListener(actionListener);
        }
        rsaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int i = Integer.parseInt(JOptionPane.showInputDialog("<html><p>For us to decrypt the message,</p>" +
                            "<p>we first need the other number in the public key pair (e)</p></html>"));
                    if (i <= 0) {
                        JOptionPane.showMessageDialog(mainFrame,"e must be greater than 0");
                    }
                    int unencrypted = unencrypt(model.getState().getMessage(),period,model.getState().getSemiprime(),i);
                    JOptionPane.showMessageDialog(mainFrame,"<html><p>From this information we can now decrypt.</p>" +
                            "<p>Since we have found that "+model.getState().getMessage()+"<sup>"+period+"</sup>≡ 1 (mod n)</p><p></p>" +
                            "<p>Then gcd("+i+","+period+") = 1</p><p></p>" +
                            "<p>So "+i+" has an inverse "+i+"d' = 1+"+period+"k    for some k ∈ N </p>" +
                            "<p>Therefore "+model.getState().getMessage()+"<sup>d'</sup> = M<sup>"+i+"d'</sup> = "+"M<sup>1+"+period+"k</sup> ≡ M (mod n)</p><p></p>" +
                            "<p>d' = "+extendedEuclid(i,period)[1]+"</p><p></p>" +
                            "<p>M  = "+unencrypted+"</p><p></p></html>","",JOptionPane.INFORMATION_MESSAGE);
                }
                catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(mainFrame,"You must enter an integer","",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
