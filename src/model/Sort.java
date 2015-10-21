package model;

import java.text.DecimalFormat;

/**
 * For sorting measurements by their probability
 * @author Stanley Hitchcock
 */
public class Sort implements Comparable<Sort> {
    private long i;
    private double real;
    private double imag;

    public Sort(long i, double real, double imag) {
        this.i = i;
        this.real = real;
        this.imag = imag;
    }

    public long getI() {
        return i;
    }

    public void setI(long i) {
        this.i = i;
    }

    public double getReal() {
        return real;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public double getImag() {
        return imag;
    }

    public void setImag(double imag) {
        this.imag = imag;
    }

    public double getMod() {
        return Math.sqrt(Math.pow(real,2) + Math.pow(imag,2));
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.###");
        double percentage = getMod()*100;
        return "[" + i + "] " + df.format(percentage) + "%";
//        return "[" + i + "] " + getReal();
    }

    @Override
    public int compareTo(Sort o) {
        return Double.compare(this.getMod(),o.getMod());
//        return Double.compare(this.getReal(),o.getReal());
    }
}
