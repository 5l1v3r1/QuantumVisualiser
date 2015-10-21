/*
 * Class for immutable complex numbers
 */

package unused;

/**
 *
 * @author sjv
 */
public class Complex {
    
    private final double real;
    private final double imaginary;
    
    public final static Complex zero = new Complex(0,0);
    
    public Complex(double r, double i){
        real = r;
        imaginary = i;
    }
    
    public Complex(double r){
        this(r, 0);
    }

    public double getReal(){
        return real;
    }
    
    public double getImaginary(){
        return imaginary;
    }
    
    public Complex add(Complex other){
        return new Complex(real+other.getReal(),
            imaginary+other.getImaginary());
    }
    
    public Complex sub(Complex other){
        return new Complex(real-other.getReal(),
            imaginary-other.getImaginary());
    }

    public Complex mult(Complex other){
        double r = other.getReal();
        double i = other.getImaginary();
        return new Complex(real*r - imaginary*i,
            real*i + imaginary*r);
    }
    
    static public Complex expi(double theta){
        return new Complex(Math.cos(theta), Math.sin(theta));
    }
    
    public Complex rMult(double r){
        return new Complex(real*r, imaginary*r);
    }

    public double modSq(){
        return real*real + imaginary*imaginary;
    }
}
