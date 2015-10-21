package unused;

import jfftw.Complex;

/**
 * @author Stanley Hitchcock
 */
public class StateSort implements Comparable<StateSort> {

    private int i;
    private Complex amplitude;

    public StateSort() {
        this.i = 0;
        this.amplitude = new Complex();
    }

    public StateSort(int i, Complex amplitude) {
        this.i = i;
        this.amplitude = amplitude;
    }

    public int getI() {
        return i;
    }

    public Complex getAmplitude() {
        return amplitude;
    }

    @Override
    public String toString() {
        return "[" + i + "] " + amplitude.getReal();
    }

    @Override
    public int compareTo(StateSort s) {
        Double d1 = Math.abs(amplitude.getReal());
        Double d2 = Math.abs(s.amplitude.getReal());
        return(d1.compareTo(d2));
    }
}