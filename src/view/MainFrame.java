package view;

import model.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Stanley Hitchcock
 */
public class MainFrame extends JFrame {

    private int semiprime;
    private int message;
    private int qbits;

    MenuPanel menuPanel = new MenuPanel(this);
    StateGUIPane stateGUIPane = new StateGUIPane(new State(8,15,7),this);

    CardLayout layout = new CardLayout();

    JPanel panel = new JPanel();


    public MainFrame() {

        panel.setLayout(layout);
        panel.add(menuPanel,"Menu");
        panel.add(stateGUIPane,"State");
        add(panel);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Object[] options = {"Yes",
                        "No",};
                int n = JOptionPane.showOptionDialog(null,
                        "Are you sure you want to quit?",
                        "",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);
                if (n == 0) System.exit(0);
            }
        });


        setContentPane(panel);
        pack();
        showMenu();
        setVisible(true);
        requestFocus();
    }

    public void showState(int qbits,int semiprime, int message) {
        setSize(1600,1000);
        setResizable(false);
        setLocationRelativeTo(null);
//        stateGUIPane = new StateGUIPane(new State(x));
        stateGUIPane.newState(qbits,semiprime,message);
        layout.show(panel,"State");
        repaint();
    }

    public void showMenu() {
        setSize(500,200);
        setResizable(false);
        setLocationRelativeTo(null);
        layout.show(panel,"Menu");
        stateGUIPane.resetEval();
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
    }

}