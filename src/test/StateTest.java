package test;

import model.State;
import org.junit.Test;

import static org.junit.Assert.*;
import static model.State.*;

public class StateTest {

    @Test
    public void testModExp() throws Exception {
        assertEquals(429,modExp(182,1812,2141));
    }

    @Test
    public void testModExp2() throws Exception {
        assertEquals(3283,modExp(3121,6151,8171));
    }

    @Test
    public void testFactorise() throws Exception {
        State state = new State(14,77,27);
        int[] ints = state.factorise(10);

        int a = 7;
        int b = 11;
        assertTrue((ints[0] == a && ints[1] == b)||
                (ints[1] == a && ints[0] == b));
    }

    @Test
    public void testFactorise2() throws Exception {
        State state = new State(26,8153,5121);
        int[] ints = state.factorise(786);

        int a = 263;
        int b = 31;
        assertTrue((ints[0] == a && ints[1] == b)||
                (ints[1] == a && ints[0] == b));
    }

    @Test
    public void testFactorise3() throws Exception {
        State state = new State(24,3811,1251);
        int[] ints = state.factorise(306);

        int a = 103;
        int b = 37;
        assertTrue((ints[0] == a && ints[1] == b)||
                (ints[1] == a && ints[0] == b));
    }

    @Test
     public void testExtendedEuclid() throws Exception {
        int[] ans = extendedEuclid(121,257);
        assertEquals(1,ans[0]);
        assertEquals(17,ans[1]);
    }

    @Test
    public void testExtendedEuclid2() throws Exception {
        int[] ans = extendedEuclid(12,18);
        assertEquals(6,ans[0]);
    }

    @Test
    public void testUnencrypt() throws Exception {
        assertEquals(212,unencrypt(157,110,253,191));
    }

    @Test
    public void testUnencrypt2() throws Exception {
        assertEquals(48,unencrypt(27,10,77,17));
    }

}