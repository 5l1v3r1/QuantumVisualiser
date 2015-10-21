package test;

import model.Fraction;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FractionTest {

    @Test
    public void testAdd() throws Exception {
        Fraction f1 = new Fraction(1, 2);
        Fraction f2 = new Fraction(0);
        Fraction f3 = f1.add(f2);
        assertTrue(f3.equals(f1));
    }

    @Test
    public void testAdd2() throws Exception {
        Fraction f1 = new Fraction(1, 2);
        Fraction f2 = new Fraction(0);
        assertEquals(f1,f1.add(f2));
    }

    @Test
    public void testAdd3() throws Exception {
        Fraction f1 = new Fraction(1, 2);
        Fraction f2 = new Fraction(1, 3);
        assertEquals(f1.add(f2), new Fraction(5, 6));
    }

    @Test
    public void testSimplify() throws Exception {
        Fraction f = new Fraction(2,4);
        assertEquals(new Fraction(1,2),f.simplify());
    }


    @Test
    public void testContinuedFractionEval() throws Exception {
        List<Integer> list = new ArrayList<Integer>();
        list.add(5);

        assertEquals(Fraction.continuedFractionEval(list), new Fraction(1, 5));
    }

    @Test
    public void testContinuedFractionEval2() throws Exception {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(1);

        assertEquals(Fraction.continuedFractionEval(list), new Fraction(1,2));
    }

    @Test
    public void testContinuedFractionEval3() throws Exception {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 20; i++) {
            list.add(2);
        }

        Fraction f = Fraction.continuedFractionEval(list);
        System.out.println(f.getNum()+"/"+f.getDen());

        assertEquals(Math.sqrt(2), 1 + Fraction.continuedFractionEval(list).toDouble(), 1E-10);
    }

    @Test
    public void testMultiContinuedFractionEval() throws Exception {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(1);
        list.add(1);

        List<Fraction> fractions = new ArrayList<Fraction>();
        fractions.add(new Fraction(1,1));
        fractions.add(new Fraction(1,2));
        fractions.add(new Fraction(2,3));

        assertEquals(fractions,Fraction.multiContinuedFractionEval(list));
    }
}