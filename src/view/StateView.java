package view;


import model.State;
import model.StateModel;

import javax.swing.*;
import java.awt.Color;
import java.util.*;
import java.awt.*;

/**
 * Original version by Steve Vickers, complete rework by Stanley Hitchcock
 * Added correct sizing, colour, real and imaginary parts, incorporated correct array
 */
public class StateView extends JPanel implements Observer {

    private final StateModel model;
    private final int SIZE = 1000; //preferred size of panel
    private double scale = Math.pow(70,2)/500;
    private boolean isReal = true; //whether displays real or imaginary part of model
    private boolean isMod = true;
    private boolean isQFT = false;
    
    public StateView(StateModel model){
        super();
        setPreferredSize(new Dimension(SIZE-200, SIZE));
//        origin = new Point(SIZE/2, SIZE/2);
        this.model = model;
        model.addObserver(this);
        repaint();
    }
    
    public void update(Observable o, Object arg){
        repaint();
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.setColor(Color.black);
        g.translate(SIZE/2-100, SIZE/2);

        double e = 0.5;
        int major = SIZE/3; //major radius of ellipse
        g.setColor(Color.BLUE);
        g.drawOval(-major, -(int)(major*e),
            major*2, (int)(major*e*2));
        State state = model.getState();
        int dim = state.getDimension();
        double angleUnit = 2*Math.PI/dim;
        Color paleGreen = new Color(178, 255, 100);
        g.setColor(isReal ? Color.PINK: paleGreen);
        double max = 0;
        for (int i = 1; i < dim/2; i++) {
            double mod = Math.sqrt((Math.pow(state.getDoubles().get(2 * i),2)
                    + (Math.pow(state.getDoubles().get(2 * i + 1),2))));
            if (mod > max) max = mod;
        }
        if (max == 0) max = 1;
        max *= 10;
        for (int x = 0; x < dim; x++){
//            Complex amp = state.getAmplitude(x);
            double length;
//            double length = (scale/10)*(isReal ? amp.getReal(): amp.getImag());
//            if (isQFT) {
                if (isMod) {
                    double mod = (Math.pow(state.getDoubles().get(2 * x),2)
                            + (Math.pow(state.getDoubles().get(2 * x + 1),2)));
                    length = (scale)/(max) * (Math.sqrt(mod));
//                    System.out.println(mod);
                } else if (isReal) {
                    length = (scale)/(max) * state.getDoubles().get(2 * x);
                } else {
                    length = (scale)/max * state.getDoubles().get(2 * x + 1);
                }
//            }
//            else {
//                length = (scale / 10) * state.getDoubles().get(x);
//            }

//            if (isMod) length = Math.abs((scale/20)*(amp.getReal()+amp.getImag()));
//            if (isMod) length = Math.abs((scale/20)*(amp.getReal()));
            int l = Math.abs((int)(length*300));
            if (l > 255) {
                l = 255;
            }
//            System.out.println(l);

            if (x >= dim/2) {
//                g.setColor(isReal ? Color.RED: Color.GREEN);


                drawLine(g, x, l, 0, 255-l, length, angleUnit, major, e);
            }
            else {
                drawLine(g, x, l, 100, 255-l, length, angleUnit, major, e);
//            Complex amp = state.getAmplitude(x);
//            double length = (scale/10)*(isReal ? amp.getReal(): amp.getImag());
//            double ellipseX = Math.cos(x*angleUnit);
//            double ellipseY = Math.sin(x*angleUnit)*e;
//            g.drawLine((int)(ellipseX*major), -(int)(ellipseY*major),
//                (int)(ellipseX*major), -(int)((ellipseY+length)*major));



//                int l = (int)(length*150);
//                if (l > 255) {
//                    l = 255;
//                }
//                l=Math.abs(l);
//                System.out.println(l);
//                drawLine(g, x, 0, 0, l, length, angleUnit, major, e);
            }
        }
    }

    public void drawLine(Graphics g, int x, int c1, int c2, int c3, double length, double angleUnit, int major, double e) {
        Color c = new Color(c1,c2,c3);
        drawLine(g,x,c,length,angleUnit,major,e);
    }

    public void drawLine(Graphics g, int x, Color c, double length, double angleUnit, int major, double e) {
        g.setColor(c);

        double ellipseX = Math.cos(x*angleUnit);
        double ellipseY = Math.sin(x*angleUnit)*e;
        g.drawLine((int)(ellipseX*major), -(int)(ellipseY*major),
                (int)(ellipseX*major), -(int)((ellipseY+length)*major));
    }

    public void setScale(double scale){
        this.scale = scale;
        repaint();
    }

    public boolean isReal(){
        return isReal;
    }

    public boolean isMod() {
        return isMod;
    }
    
    public void setReal(boolean b){
        isReal = b;
        repaint();
    }

    public void setMod(boolean b){
        isMod = b;
        repaint();
    }

    public void setQFT() {
        isQFT = !isQFT;
        repaint();
    }

    public void setQFT(boolean b) {
        isQFT = b;
//        System.out.println("Repainting!!");
        repaint();
//        System.out.println("Done");
    }

}
