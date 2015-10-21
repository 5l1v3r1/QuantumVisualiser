package model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * A Fraction class to handle my continued fractions
 * @author Stanley Hitchcock
 */
public class Fraction implements Comparable<Fraction> {

    private int num;
    private int den;

    public Fraction(int num, int den) {
//        if (den == 0) throw new IllegalArgumentException("Divide by zero");
        this.num = num;
        this.den = den;
        initialSimplify();
    }

    public Fraction(int num) {
        this.num = num;
        this.den = 1;
    }

    public Fraction(int num, Fraction den) {
        this(num*den.getDen(),den.getNum());
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getDen() {
        return den;
    }

    public void setDen(int den) {
        this.den = den;
    }

    @Override
    public String toString() {
        return num +
                "/" + den ;
    }

    public Fraction add(Fraction f) {
        return new Fraction(getNum()*f.getDen() + f.getNum()*getDen(), f.getDen()*getDen()).simplify();
    }

    public Fraction simplify() {
        int s = BigInteger.valueOf(num).gcd(BigInteger.valueOf(den)).intValue();
        if (s == 1) return this;
        else return new Fraction(num/s,den/s);
    }

    private void initialSimplify() {
        int s = BigInteger.valueOf(num).gcd(BigInteger.valueOf(den)).intValue();
        if (s != 1 && (this.getDen() != 0 && this.getNum() != 0)) {
            setNum(getNum()/s);
            setDen(getDen()/s);
        }
    }

    public static Fraction continuedFractionEval(List<Integer> list) {
        Fraction fraction = new Fraction(0,0);
        ListIterator<Integer> iter = list.listIterator(list.size());
        while (iter.hasPrevious()) {
            int i = iter.previous();
//            System.out.println(fraction.getDen() + ", " + (fraction.getDen()*i + fraction.getNum()));
            if (fraction.getDen() == 0 && fraction.getNum() == 0) fraction = new Fraction(1,i);
            else fraction = new Fraction(fraction.getDen(),(fraction.getDen()*i + fraction.getNum()));
        }
        return fraction;
    }

    public static List<Fraction> multiContinuedFractionEval(List<Integer> list) {
        List<Fraction> output = new ArrayList<Fraction>();
        List<Integer> intList = new ArrayList<Integer>();
        for (Integer i : list) {
            intList.add(i);
            output.add(continuedFractionEval(intList));
        }
//        for (Fraction fraction : output) {
//            System.out.println(fraction);
//        }
        return output;
    }


    @Override
    public int compareTo(Fraction o) {
        if ((getNum() * 1.0)/getDen() > o.getNum()*1.0 / o.getDen())
            return 1;
        else if ((getNum() * 1.0)/getDen() < o.getNum()*1.0 / o.getDen())
            return -1;
        else return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fraction fraction = (Fraction) o;

        return den == fraction.den && num == fraction.num;
    }

    public double toDouble() {
        return (1.0*num/den);
    }


//    public static void main(String[] args) {
//        System.out.println("1/4 + 1/2 = " + new Fraction(1,4).add(new Fraction(1,2)));
//        System.out.println(new Fraction(1,new Fraction(1).add(new Fraction(1,new Fraction(4).add(new Fraction(1,new Fraction(1).add(new Fraction(1,4))))))));
//        System.out.println(new Fraction(1,new Fraction(1).add(new Fraction(1,4))));
//
//        List<Integer> list = new ArrayList<Integer>();
//        list.add(1);
//        list.add(4);
//        list.add(4);
//        list.add(1);
//        System.out.println(continuedFractionEval(list));
//    }

}
