package view;

import model.Fraction;
import model.State;
import model.StateModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author Stanley Hitchcock
 */
public class ContinuedFraction extends JPanel {

    ArrayList<Integer> input = new ArrayList<Integer>();
    StateModel model;
    int answer;
//    int period = -1;
    JButton periodButton;
    EvaluationPanel evaluationPanel;

    public ContinuedFraction(ArrayList<Integer> input, StateModel model, int answer, EvaluationPanel evaluationPanel) {
        this.input = input;
        this.model = model;
        this.answer = answer;
        periodButton = new JButton("Factors?");
        setLayout(new BorderLayout());
        add(periodButton,BorderLayout.SOUTH);
//        periodButton.setVisible(false);
        this.evaluationPanel = evaluationPanel;
    }

    public void setInput(ArrayList<Integer> input) {
        this.input = input;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    @Override
    public void paint(Graphics g) {
//        System.out.println("PAINTING STEP 1");
        if (input.get(0) != -1) {

            int scale = 30;

            int initx = scale;
            int inity = 6 * scale;
            Graphics2D g2 = (Graphics2D) g;
//        g2.drawLine(initx,inity,initx+200,inity);
            g2.setFont(new Font("Serif", Font.BOLD, 20));


            /*
            Displaying continued fraction
             */
            int n = input.size();
            for (int i = 0; i < n; i++) {
                g2.drawLine(initx + (2 * scale * i), inity + (scale * i), initx + scale * (2 * n - 1), inity + (i * scale));
                g2.drawString("1", initx + 15 + scale * (i + n - 1), inity - 10 + scale * i);
                g2.drawString(input.get(i).toString(), initx + 15 + (2 * scale * i), inity + 35 + (scale * i));
                if (i != n - 1) g2.drawString("+", initx + 40 + (2 * scale * i), inity + 35 + (scale * i));
            }
//            System.out.println("PAINTING STEP 2");

            int length = Integer.toString((int)Math.pow(2,model.getQbits())).length();

            /*
            Displaying initial and final fractions
             */
            g2.drawLine(scale,scale*2,scale/2*length+50,scale*2);
            g2.drawString(Integer.toString(answer),scale+15,scale*2 -5);
            g2.drawString(Integer.toString((int)Math.pow(2, model.getQbits())),scale+15,scale*3 -5);
            g2.drawString("≈",scale/2*(length)+70,scale*2+5);
            g2.drawString("=",initx+scale*(2*n-1)+30,inity+(n/2*scale));

//            System.out.println("PAINTING STEP 3");


            Fraction fraction = Fraction.continuedFractionEval(input);
            g2.drawLine(initx,inity+scale*(n+2),initx+scale*2,inity+scale*(n+2));
            g2.drawString(Integer.toString(fraction.getNum()),initx+15,inity+scale*(n+2)-5);
            g2.drawString(Integer.toString(fraction.getDen()),initx+15,inity+scale*(n+3)-5);

//            System.out.println("PAINTING STEP 4");


//            System.out.println(fraction.getNum()+"/"+fraction.getDen());
            if (State.modExp(model.getState().getMessage(), fraction.getDen(), model.getState().getSemiprime()) == 1) {
                g2.drawString("Answer Found: "+ fraction.getDen(),
                            initx + 15, inity + scale * (n + 5) - 5);
                int period = fraction.getDen();
//                    System.out.println("Is this happening after? " +period);
//                System.out.println("Adding Factor Button");
                evaluationPanel.addFactorButton(period);
//                System.out.println("Adding Percentage Button");
                evaluationPanel.addPercentageButton();
//                System.out.println("Adding RSA Button");
                evaluationPanel.addRSAButton(period);

//                System.out.println("PAINTING STEP 5T");

            }
            else {
                int semiprime = model.getState().getSemiprime();
                int answer = fraction.getDen();
                boolean found = false;
                for (int i = answer; i < semiprime && !found; i+= answer) {
//                    System.out.println("Testing" + i);
                    if (State.modExp(model.getState().getMessage(), i, semiprime) == 1) {
                        g2.drawString("Answer not found, but " + fraction.getDen() + " is a factor.", initx + 15, inity + scale * (n + 5) - 5);
                        g2.drawString("Testing multiples of " + fraction.getDen() + " we find that", initx + 15, inity + scale * (n + 6) -5);
                        g2.drawString(i + " is the answer.", initx + 15, inity + scale * (n + 7) - 5);
                        evaluationPanel.addFactorButton(i);
                        evaluationPanel.addPercentageButton();
                        evaluationPanel.addRSAButton(i);
                        found = true;
                    }
                }
                if (!found) g2.drawString("Answer not found, and " + fraction.getDen() + " is not a factor.",
                        initx + 15, inity + scale * (n + 5) - 5);
//                System.out.println("Answer: " + answer + " r: " + fraction.getDen()+ " semiprime: "+ model.getState().getSemiprime() + "\n" +
//                        "Modexp: " + State.modExp(answer, fraction.getDen(), model.getState().getSemiprime()));
//                System.out.println("PAINTING STEP 5F");

            }
        }
//        System.out.println("Painted Cont. Fraction");

//        else {
//            Graphics2D g2 = (Graphics2D) g;
//            g2.drawLine(0,0,1000,1000);
//        }
    }




//    public static void main(String[] args) {
//
//        ArrayList<Integer> test = new ArrayList<Integer>();
//        test.add(3);
//        test.add(4);
//        test.add(5);
//        test.add(6);
//        test.add(7);
//
//        JFrame jFrame = new JFrame();
//        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        jFrame.setContentPane(new ContinuedFraction(test,new StateModel(new State(8,15,7)),192));
//        jFrame.setSize(600,1000);
//        jFrame.setVisible(true);
//        jFrame.setBackground(Color.WHITE);
//
//
//
//
//    }
}
