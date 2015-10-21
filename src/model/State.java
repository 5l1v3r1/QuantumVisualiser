package model;

import java.util.*;
import org.jtransforms.fft.DoubleFFT_1D;
import pl.edu.icm.jlargearrays.DoubleLargeArray;

import static model.Fraction.multiContinuedFractionEval;

/**
 * The State class, the core class for most variables and methods
 * @author Stanley Hitchcock
 */
public class State {
    private final int n;
    private final int dimension;
    private final DoubleLargeArray doubles;
    private final ArrayList<Sort> sort;
    private final int semiprime;
    private final int message;
    private boolean qftd;

//    private double ampTotal;
//    private final StateSort[] stateSort;
//    private Complex[] amplitudes;
//    private final DoubleLargeArray doubles2;

//    public State(int n){
//        this.n = n;
//        dimension = (int)Math.pow(2, n);
////        amplitudes = new Complex[dimension];
////        stateSort = new StateSort[dimension];
//        doubles = new DoubleLargeArray(dimension*2);
//        sort = new ArrayList<Sort>(dimension);
////        doubles2 = new DoubleLargeArray(dimension*2);
////        initZero();
////        ampTotal = 0;
//    }

    public State(int n, int semiprime ,int message) {
        this.n = n;
        dimension = (int)Math.pow(2,n);
        doubles = new DoubleLargeArray(dimension*2);
        sort = new ArrayList<Sort>(dimension);
        this.semiprime = semiprime;
        this.message = message;
        qftd = false;
//        System.out.println("qbits = " + n + "\nsemiprime = " + semiprime + "\nmessage = " + message);
    }

    public int getSemiprime() {
        return semiprime;
    }

    public int getMessage() {
        return message;
    }

    public int getN() {
        return n;
    }

    public ArrayList<Sort> getSort() {
        return sort;
    }

    public int getDim() {
        return dimension;
    }

    public boolean isQftd() {
        return qftd;
    }

//    public void updateStateSort() {
//        for (int i = 0; i < dimension; i++) {
//            stateSort[i] = new StateSort(i,amplitudes[i]);
//        }
//    }

//    public Set<StateSort> sort() {
//        Set<StateSort> output = new TreeSet<StateSort>();
//        Collections.addAll(output, stateSort);
//        for (StateSort sort : output) {
//            if (!(sort.getI() > dimension/2)) {
//                System.out.println(sort);
//            }
//        }
//        return output;
//    }



//    public void initZero(){
//        for (int x = 0; x < dimension; x++){
//            amplitudes[x] = new Complex();
//        }
//    }

//    public void clear() {
//        for (int i = 0; i < dimension*2; i++) {
//            doubles.setDouble(i,0);
//        }
//    }



//    public void initOscillation(double cycles){
//        double unitAngle = 2*Math.PI/dimension*cycles;
////        double length = 1/Math.sqrt(dimension);
//        for(int x = 0; x < dimension; x++){
//            doubles.setDouble(x*2,(Math.cos(unitAngle*x)));
////            System.out.println((Math.cos(unitAngle*x)));
//            doubles.setDouble(x*2+1,(Math.sin(unitAngle*x)));
////            amplitudes[x] = new Complex();
////            amplitudes[x].setExpi(unitAngle*x);
////            amplitudes[x].mult(length);
//
//        }
//        scale();
//    }


//    public void initSpike(int x){
////        initZero();
////        x = x%dimension;
////        if (x < 0){x += dimension;}
////        amplitudes[x] = new Complex(1.0);
//    }

//    public void initSpike(double mu, double sigma){
//        clear();
//        sigma = Math.max(sigma, 0.25);
//        mu = mu%dimension;
//        if (mu < 0) {
//            mu += dimension;
//        }
//        int mu0 = (int)(Math.ceil(mu));
//
//        double[] arr = new double[dimension];
//        for (int x = -dimension/2; x < dimension/2; x++) {
//            double a = gauss(mu0+x, mu, sigma);
//            arr[x+dimension/2] = a;
//        }
//        double sum = 0.0;
//        for (int x = 0; x < dimension; x++){
//            sum = sum + arr[x]*arr[x];
//        }
//        double length = Math.sqrt(sum);
//        for (int x = 0; x < dimension; x++){
//            int y = mu0 + x - dimension/2;
//            if (y < 0) {
//                y += dimension;
//            } else if (y >= dimension){
//                y -= dimension;
//            }
//            doubles.setDouble(2*y,arr[x]);
//        }
//        sort();
//        scale();



//        sigma = Math.max(sigma, 0.25);
//        mu = mu%dimension;
//        if (mu < 0) {
//            mu += dimension;
//        }
//        int mu0 = (int)(Math.ceil(mu));
//
//        double[] arr = new double[dimension];
//        for (int x = -dimension/2; x < dimension/2; x++) {
//            double a = gauss(mu0+x, mu, sigma);
//            arr[x+dimension/2] = a;
//        }
//        double sum = 0.0;
//        for (int x = 0; x < dimension; x++){
//            sum = sum + arr[x]*arr[x];
//        }
//        double length = Math.sqrt(sum);
//        for (int x = 0; x < dimension; x++){
//            int y = mu0 + x - dimension/2;
//            if (y < 0) {
//                y += dimension;
//            } else if (y >= dimension){
//                y -= dimension;
//            }
//            amplitudes[y] = new Complex(arr[x]/length);
//        }


//    }

//    private static double gauss(double x, double mu, double sigma){
//        return Math.pow(Math.E, -(x-mu)*(x-mu)/2/sigma/sigma);
//    }
//
//    public void initClicks(int period){
//        initClicks(period, 0);
//    }

//    public void initClicks(int period, int x0){
//        for (int i = 0; i < 2*dimension; i++) {
//            doubles.setDouble(i,0);
//        }
////        int m = (int)(Math.floor((dimension-1-x0)/period)+1);
////        double length = 1.0/Math.sqrt(m);
//        for (int k = x0*2; k < 2*dimension; k += 2*period){
//            doubles.setDouble(k,1.0);
//        }
//        scale();
//        sort();

//        initZero();
//        int m = (int)(Math.floor((dimension-1-x0)/period)+1);
//        double length = 1.0/Math.sqrt(m);
//        for (int k = x0; k < dimension; k += period){
//            amplitudes[k] = new Complex(length);
//        }
//    }

    /**
     * Initialising the state for period finding
     * @author Stanley Hitchcock
     * @param toFactor What you would like to factorise
     * @param x A random integer coprime to toFactor
     */
    public void initPeriodFind(int toFactor, int x){
//        initZero();
//        double[] function = new double[dimension];
//        function[0] = 1.0;
//        for (int i = 0; i < dimension - 1; i++) {
//            function[i+1] = (x*function[i])%toFactor;
//        }
//        for (int i = 0; i < dimension; i++) {
//            amplitudes[i] = new Complex(function[i]/toFactor);
//        }

//        System.out.println(x +"^r mod " + toFactor);


//        System.out.println("Start Mod");
        doubles.setDouble(0, 1.0);
        for (int i = 0; i < dimension - 1; i++) {
            doubles.setDouble(2*i+2,(x*doubles.get(2*i))%toFactor);
//            System.out.println(function[i]);
//            System.out.println(doubles.get(i));
        }
        for (int i = 0; i < dimension; i++) {
            doubles.setDouble(2*i+1,0);
        }

        qftd = false;

//        System.out.println("Start Mod");
//        ModExp modExp = new ModExp(doubles,x,toFactor);
//        Thread t = new Thread(modExp);
//        t.run();
//        System.out.println("Start Scale");
//        try {
//            t.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        scale();
//        System.out.println("End Scale");
    }


    public void initPeriodFind() {
        initPeriodFind(semiprime,message);
    }

    public int getQbits(){
        return n;
    }

    public int getDimension(){
        return dimension;
    }

    public DoubleLargeArray getDoubles() {
        return doubles;
    }

//    public Complex getAmplitude(int x){
//        return amplitudes[x];
//    }

//    public void setAmplitude(int x, Complex z){
//        amplitudes[x] = z;
//    }

    /**
     * Fast Fourier Transform
     * Acts on variable doubles
     */
    public void qft(){
//        System.out.println("New DoubleFFT Class");
        DoubleFFT_1D fft = new DoubleFFT_1D(doubles.length()/2);
//        System.out.println("FFT Running");
        fft.complexForward(doubles);

//        System.out.println("Scaling");
        scale();
//        System.out.println("Sorting");
        if (!qftd) {
            sort();
            qftd = true;
        }
        else {
            qftd = false;
        }
//        for (Sort s : sort) {
//            System.out.println(s);
//        }
//        System.out.println(doubles.getDouble(0));
//        State output = new State(n);
//        output.amplitudes = double2Complex(doubles);
//        return this;
    }

    /**
     * Inverse Fast Fourier Transform
     * Acts on variable doubles
     */
    public void unqft(){
        DoubleFFT_1D fft = new DoubleFFT_1D(doubles.length()/2);
        fft.complexInverse(doubles,false);

        scale();
        sort();
//        for (Sort s : sort) {
//            System.out.println(s);
//        }
    }


//    /** calculate qft or inverse qft of this.
//     *
//     * @param inverse true if inverse qft to be calculated
//     * @return new State instance for qft of this
//     * @author Dr. Vickers
//     */
//    private State qftAux(boolean inverse){
//        State psi = new State(n);
//        double factor = Math.pow(2, -(n / 2.0));
//        double angleUnit = (inverse? -2.0: 2.0)*Math.PI/dimension;
//
////        double first = 0, second = 0, third = 0;
//
//        for(int y = 0; y < dimension; y++){
//            Complex z = new Complex();
//            for (int x = 0; x < dimension; x++){
//                Complex temp = new Complex();
//                temp.setExpi(angleUnit * x * y);
//                z = z.plus(getAmplitude(x).mult(temp));
//            }
//            psi.setAmplitude(y, z.mult(factor));

//            if (z.getReal() > first) {
//                System.out.println("NEW FIRST " + y + " " + z.getReal());
//                third = second;
//                second = first;
//                first = z.getReal();
//            }
//            else if (z.getReal() > second) {
//                System.out.println("NEW SECOND " + y + " " + z.getReal());
//                third = second;
//                second = z.getReal();
//            }
//            else if (z.getReal() > third) {
//                System.out.println("NEW THIRD " + y + " " + z.getReal());
//                third = z.getReal();
//            }
//        }
//        psi.updateStateSort();
//        psi.sort();

//        return psi;
//    }

//    public double[] modSqAmplitudes(){
//        double[] result = new double[dimension];
//
//        for (int y = 0; y < dimension; y++){
//            result[y] = amplitudes[y].modSq();
//        }
//        return result;
//    }


//    public State qftBetter() {
//        DoubleFFT_1D fft = new DoubleFFT_1D(1);
//        fft.
//    }

    /*
    If I can ever get jFFTW to work!
     */
//    public State fftw() {
//        ComplexDataArray dataArray = new ComplexDataArray(dimension);
//        for (int i = 0; i < dimension; i++) {
//            dataArray.setComplex(i,amplitudes[i]);
//        }
//        dataArray.fourier();
//        State output = new State(n);
//        for (int i = 0; i < dimension; i++) {
//            output.amplitudes[i].set(dataArray.getComplex(i));
//        }
//        return output;
//    }


    /**
     * Scales doubles to keep the total at 1
     */
    public void scale() {
//        double max = 0;
//        for (int i = 1; i < doubles.length(); i++) {
//            if (doubles.get(i) > max) max = doubles.get(i);
//        }
//        System.out.println(max);
//        for (int i = 0; i < doubles.length(); i++) {
//            doubles.setDouble(i,doubles.getDouble(i)/max);
//        }
        double total = 0;
        for (int i = 0; i < doubles.length() / 2; i++) {
            total += Math.sqrt(doubles.getDouble(2 * i) * doubles.getDouble(2 * i) + doubles.getDouble(2 * i + 1) * doubles.getDouble(2 * i + 1));
//            System.out.println(total);
        }
        for (int i = 0; i < doubles.length(); i++) {
            doubles.setDouble(i,doubles.getDouble(i)/total);
        }
//        System.out.println(total);
//        total = 0;
//        for (int i = 0; i < doubles.length() / 2; i++) {
//            total += Math.sqrt(doubles.getDouble(2 * i) * doubles.getDouble(2 * i) + doubles.getDouble(2 * i + 1) * doubles.getDouble(2 * i + 1));
//        }
//        System.out.println("Scale's Total is "+total);
    }

    /**
     * Creates a sorted list of measurements
     * Stored in variable sort
     */
    private void sort() {
        sort.clear();
        for (int i = 0; i < doubles.length() / 2; i++) {
            double real = doubles.getDouble(2*i);
            double imag = doubles.getDouble(2*i+1);
            if (Math.sqrt(real*real+imag*imag) > 1E-8) {
                Sort temp = new Sort(i, doubles.getDouble(2 * i), doubles.getDouble(2 * i + 1));
//            System.out.println(temp.getMod());
                sort.add(temp);
            }
        }
        Collections.sort(sort);
//        double total = 0;
//        for (Sort sort1 : sort) {
//            System.out.println(sort1.getMod());
//            total+=sort1.getMod();
//            System.out.println(sort1 + " Total at: " + total);
//        }
//        System.out.println("Sorted Total is: " + total);
//        System.out.println(tot);
    }



    /**
     * Turns i/dimension into a continued fraction
     * @param i int
     * @return a list of integers for each denominator at each part of the continued fraction
     */
    public ArrayList<Integer> continuedFraction(int i) {
//        return continuedFraction(i,100);
        double current = (i*1.0)/dimension;
        ArrayList<Integer> output = new ArrayList<Integer>();
        int n = 0; //den = 0;
        while (1/current < dimension && n < 100) {
            current = 1/current;

            if (current > 5000) break;

            output.add((int) current);

            current -= (int)current;

            n++;
        }
//        System.out.print("\nFinal continued fraction: ");
//        for (Integer integer : output) {
//            System.out.print(integer + ", ");
//        }
//        System.out.println();
        return output;
    }

//    public ArrayList<Integer> continuedFraction(int i, int limit) {
//        double current = (i*1.0)/dimension;
//        ArrayList<Integer> output = new ArrayList<Integer>();
//        int n = 0;
//        int q0 = 0, q1 = 0, q2 = 0;
//        while (q0 < limit && q1 < limit && q2 < limit) {
////        while (q2 < 100) {
////            System.out.println(current);
//            current = 1/current;
//
//            if (n==0) {
//                q0 = (int) current;
//            }
//            else if (n==1) {
//                q1 = 1 + output.get(0)*(int)current;
//            }
//            else {
//                q2 = ((int)current)*q1 + q0;
//                q0 = q1;
//                q1 = q2;
////                System.out.print("                     q2 = " + q2);
//            }
////            System.out.println(q2);
////            System.out.println((int)current);
////            System.out.print((int) current + ", ");
//
//            if (q1 < limit && q2 < limit) output.add((int)current);
//
//            current -= (int)current;
//            n++;
//        }
//        System.out.print("\nFinal continued fraction: ");
//        for (Integer integer : output) {
//            System.out.print(integer + ", ");
//        }
//        System.out.println();
//        return output;
//    }


    /**
     * A fast modular exponentiation O(log(mod))
     * @param x x
     * @param exp exp
     * @param mod mod
     * @return x^exp (mod mod)
     */
    public static int modExp(int x, int exp, int mod) {
        long a = 1;
        long b = x;
        while (exp > 0) {
//            System.out.println("exp: " + exp + " a: " + a + " b: " + b);
            if(exp%2 == 1) {
                a = (a*b) % mod;
            }
            b = (b*b)%mod;
            exp = exp/2;
//            System.out.println(exp);
        }
//        System.out.println(a%mod);
        return (int)a%mod;
    }

//    private int answerCheck(int x, int message, int n) {
//        if (dimension*1.0/x == dimension/x) {
//            System.out.println("Cannot use this value");
//            return -1;
//        }
//        else {
//            Fraction f = (continuedFractionEval(this.continuedFraction(x,100)));
//            int den = f.getDen();
//            System.out.println("Denominator of eval: " + den);
//            int answer = 0;
//            int temp = den;
//            for (int i = 1; answer <= n ; i++) {
////            System.out.println(answer);
//                answer = modExp(message, temp, n);
//                if (answer % n == 1) {
//                    System.out.println("Answer Found: " + den * i);
//                    return den*i;
//                } else {
//                    temp += den;
////                    if (i == 100) System.out.println("No answer found, try another");
//                }
//            }
//
//            return -1;
//        }
//    }

//    public int answerCheckExtended(int x) {
//        return answerCheckExtended(x,message,semiprime);
//    }

//    private int answerCheckExtended(int x, int message, int n) {
////        if (dimension*1.0/x == dimension/x) {
////            System.out.println("Cannot use this value");
////            return -1;
////        }
////        else {
//            List<Integer> contFraction = this.continuedFraction(x);
//            List<Fraction> fractions = multiContinuedFractionEval(contFraction);
//            for (int i = 0; i < fractions.size(); i++) {
//                int den = fractions.get(i).getDen();
//                if (contFraction.get(i) > 2000 || den < 0) break;
//                System.out.println("Denominator of eval: " + den);
//                int answer = 0;
//                //                for (int j = 1; j <= /*(int)Math.pow(2,this.getN()/3)*/ 1; j++) {
////            System.out.println(answer);
//                    answer = modExp(message, den, n);
//                    if (den < 0) break;
//                    if (den > n) break;
//                    if (answer % n == 1) {
//                        System.out.println("Answer Found: " + den +"= "+ den);
//                        return den;
////                    if (i == 100) System.out.println("No answer found, try another");
////                    }
//                }
//            }
//
//            return -1;
//        }
//    }



    public ArrayList<Integer> answerReturn(int x) {
        return answerReturn(x,message,semiprime);
    }

    private ArrayList<Integer> answerReturn(int x, int message, int semiprime) {
        ArrayList<Integer> contFraction = this.continuedFraction(x);
        List<Fraction> fractions = multiContinuedFractionEval(contFraction);
        ArrayList<Integer> output = new ArrayList<Integer>();
        for (int i = 0; i < fractions.size(); i++) {
            int den = fractions.get(i).getDen();
            if (contFraction.get(i) > 2000 || den < 0 || den > semiprime) break;
            output.add(contFraction.get(i));
//            System.out.println("Den is now " + den);
            if (modExp(message,den,semiprime) == 1) {
//                System.out.println(modExp(message,den,semiprime));
//                System.out.println("ANSWER: "+den);
                return output;
            }
        }
//        ArrayList<Integer> notFound = new ArrayList<Integer>();
//        notFound.add(-1);
//        return notFound;
        return output;
    }

    public double percentage() {
        double total = 0;
//        int count =0;

//        for (int i = 0; i < dimension; i++) {
//            if (sort.get(i).getI() != 0 && answerCheckBool(i)) {
//                total += sort.get(i).getMod();
////                count++;
////                System.out.println(sort.get(i).getI());
//            }
////            System.out.println(total);
//        }

        for (Sort sort1 : sort) {
            if (sort1.getI() != 0 && answerCheckBool((int)sort1.getI())) {
                total += sort1.getMod();
//                count++;
//                System.out.println(sort.get(i).getI());
            }
        }
//        System.out.println(count);
        return total*100;
    }

    private boolean answerCheckBool(int x) {
        Fraction fraction = Fraction.continuedFractionEval(answerReturn(x));
        int ans = fraction.getDen();
        for (int i = ans; i < semiprime; i+= ans) {
            if (modExp(message,i,semiprime) == 1) {
                return true;
            }
        }
        return false;
    }


    /**
     * This method will factorise n if it can, throwing an IllegalArgumentException if not.
     * @param ans The order of the message mod semiprime
     * @return
     */
    public int[] factorise(int ans) {
        return factorise(message,ans,semiprime);
    }

    /**
     * This method will factorise n if it can, throwing an IllegalArgumentException if not.
     * @param x message
     * @param r order
     * @param n semiprime
     * @return [p,q]
     */
    private int[] factorise(int x, int r, int n) {
        if (modExp(x,r,n) != 1) {
            throw new IllegalArgumentException("x^r != 1 mod n!!!");
        }
        else if (r%2 != 0) {
            throw new IllegalArgumentException("r must be even");
        }
        else if ((modExp(x,r/2,n)+1 )% n == 0) {
            throw new IllegalArgumentException("x^(r/2) + 1 = mod n");
        }
        else {
            int m = modExp(x,r/2,n);
//            System.out.println(m-1 + ", " + m+1);
            return new int[] {extendedEuclid(m-1,n)[0],
                    extendedEuclid(m+1,n)[0]};
        }
    }

//    public Complex[] double2Complex(DoubleLargeArray d) {
//        Complex[] c = new Complex[(int)d.length()/2];
//        for (int i = 0; i < d.length()/2; i++) {
//            c[i] = new Complex(d.get(i*2),d.get(i*2+1));
//        }
//        return c;
//    }

    /**
     * The Extended Euclidean Algorithm, made with help from my cryptography notes by Dr. Ritter
     * @param a smaller number, if it is larger it will be (mod b)
     * @param b larger number
     * @return gcd = x * a + y * b, [gcd,x,y]
     */
    public static int[] extendedEuclid(int a, int b) {
        int mod = b;
        while (a>b) {
            a-=b;
        }
//        System.out.println(a + " " + b);
        int[] x = new int[] {1,0};
        int[] y = new int[] {0,1};

        while(a!=0 && b!=0) {
            if (a>b) {
                int q = a/b;
                a = a - q*b;
                x[0] = x[0] - q*y[0];
                x[1] = x[1] - q*y[1];
//                for (int i : x) {
//                    System.out.print("[" + i + "] ");
//                }
//                System.out.println();
            }
            else {
                int q = b/a;
                b = b - q*a;
                y[0] = y[0] - q*x[0];
                y[1] = y[1] - q*x[1];
//                for (int i : x) {
//                    System.out.print("[" + i + "] ");
//                }
//                System.out.println();
            }
        }
        if (y[0] < 0) y[0] = mod+y[0];
        if (x[0] < 0) x[0] = mod+x[0];
        if (a == 0) return new int[] {b,y[0],y[1]};
        else return new int[] {a,x[0],x[1]};
    }

    /**
     * RSA decryption
     * @param c ciphertext
     * @param r order
     * @param n semiprime
     * @param e other part of the public key
     * @return the original message
     */
    public static int unencrypt(int c, int r, int n, int e) {
        return modExp(c,extendedEuclid(e,r)[1],n);
    }

    /**
     * A very simple orderFinding algorithm, it is never used for obvious reasons
     * @param x message
     * @param mod modulus
     * @return r s.t. x^r = 1 modulo mod
     */
    private static int orderFind(int x, int mod) {
        int i = 1;
        int x0 = x;

        while (x0%mod != 1) {
            x0*=x;
            x0%=mod;
            i++;
        }
        return i;
    }

//    public static void main(String[] args) {
//        for (int i : extendedEuclid(121,257)) {
//            System.out.println(i);
//        }
//    }


//    public static void main(String[] args) {
////        State state14 = new State(14);
////        State state11 = new State(11);
////        State state8 = new State(8);
////        State state15 = new State(15);
////        State state18 = new State(18);
//////        Fraction f = (continuedFractionEval(state14.continuedFraction(3413)));
////        System.out.println(f);
////        int den = f.getDen();
////        int answer = 0;
////        int temp = den;
////        for (int i = 1; i <= 5; i++) {
////            System.out.println(answer);
////            answer = modExp(1339,temp,1517);
////            if (answer%1517 == 1) {
////                System.out.println("Answer Found!" + den*i);
////                break;
////            }
////            else {
////                temp += den;
////
////            }
////        }
//
////        state.answerCheck(969761,139001,256027);
////        state14.answerCheck();
////        int c = 151, d = 72;
////        int[] i = extendedEuclid(c,d);
////        for (int x : i) {
////                System.out.print("[" + x + "] ");
////        }
////        System.out.println();
////        System.out.println((i[1]+d)%d + "*" + c + " = 1 (mod " + d + ")");
////        state11.answerCheck(427,1339,1517);
////        state11.answerCheck(455,1339,1517);
////        state11.answerCheck(711,1339,1517);
////        state11.answerCheck(654,1339,1517);
////        state11.answerCheck(939,1339,1517);
////        state11.answerCheck(455,1339,1517);
//
////        for (Integer i : state11.factorise(1339,72,1517)) {
////            System.out.println(i);
////        }
//
//
//
////        state11.answerCheck(426,1339,1517);
////        state11.answerCheck(939,1339,1517);
////        state11.answerCheck(882,1339,1517);
////        state11.answerCheck(796,1339,1517);
////        state11.answerCheck(484,1339,1517);
////        state11.answerCheck(114,1339,1517);
//
//
////        System.out.println(state11.answerCheck(199,1339,1517));
//
////        System.out.println(state11.answerCheck(723,1339,1517));
//
////        state11.initPeriodFind(1517,1339);
////        state11.unqft();
////
////        for (int i = 0; i < 10; i++) {
////            state11.answerCheck((int)state11.getSort().get(state11.getDimension()/2-(i+2)).getI(),1339,1517);
////        }
//
//
////        System.out.println(unencrypt(1339,state14.answerCheck(3413,1339,1517),1517,151));
//
//
////        State state = new State(16);
//////
////        state.initPeriodFind(3937,2157);
////        state.unqft();
////
////        int count = 0;
//////
////        for (int i = 0; i < 100; i++) {
////            long x = state.getSort().get(state.getDimension()/2-(i+2)).getI();
//////            System.out.println(x);
//////            state.continuedFraction((int)x);
////            if (state.answerCheckExtended((int)state.getSort().get(state.getDimension()/2-(i+2)).getI(),2157,3937)
////                    != -1) count++;
//////            System.out.println("\n");
////            if (i == 100-1) {
////                System.out.println(100.0 * count / i + "% work");
////            }
////        }
////
////        for (Integer i : state.factorise(2157,210,3937)) {
////            System.out.println(i);
////        }
//
//
//        State state = new State(24,61937,27123);
//        int mod = 61937, message = 27123;
////
//        if (extendedEuclid(message,mod)[0] != 1) {
//            System.out.println(message + "has a common factor of " + extendedEuclid(message,mod)[1]);
//        }
//        System.out.println("Plotting Period Finding");
//        state.initPeriodFind(mod,message);
//        System.out.println("QFT Starting");
//        state.unqft();
//        System.out.println("Analysing Results");
//        int count = 0;
//        int period = -1;
////
//        int i = 0;
//        for (i = 0; i < 20; i++) {
//
//            long x = state.getSort().get(state.getDimension()/2-(i+2)).getI();
////            System.out.println(x);
////            state.continuedFraction((int)x);
//            int answer = state.answerCheckExtended((int)state.getSort().get(state.getDimension()/2-(i+2)).getI(),message,mod);
//            if (answer != -1) {
//                if (period == -1) {
//                    period = answer;
//                }
//                else {
//                    if (answer < period) period = answer;
//                }
//                count++;
//            }
////            System.out.println("\n");
//
//        }
//        System.out.println("\n" + 100.0 * count / i + "% work");
////        System.out.println(period);
//        if (period != -1) {
//            System.out.println("Period = " + period);
//            for (Integer k : state.factorise(message, period, mod)) {
//                System.out.println("FACTOR: " + k);
//            }
//        }
//
////        state.factorise(27123,3840,61937);
//
//
//
//    }

//    public static void main(String[] args) {
//        System.out.println(extendedEuclid(191, 110)[1]);
//
//
////        System.out.println(unencrypt(157,110,253,191));
//    }

//    public static void main(String[] args) {
//        State state = new State(14,65,18);
//        for (int i : state.factorise(4)) {
//            System.out.println(i);
//        }
//    }
}
