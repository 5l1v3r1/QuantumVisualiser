package unused;

import pl.edu.icm.jlargearrays.DoubleLargeArray;

/**
 * @author Stanley Hitchcock
 */
public class ModExp implements Runnable {

    private DoubleLargeArray doubles;
    private int order;
    private int i;
    private int mod;
    private int x;

    public ModExp(DoubleLargeArray doubles, int x, int mod, int order, int i) {
        this.doubles = doubles;
        this.order = order;
        this.i = i;
        this.x = x;
        this.mod = mod;
    }

    public ModExp(DoubleLargeArray doubles, int x, int mod) {
        this.doubles = doubles;
        this.x = x;
        this.mod = mod;
        this.order = 0;
        this.i = 0;
    }



    @Override
    public void run() {
        if (order == 0) {
            doubles.set(0,1);
//            int limit = 0;
            boolean stop = false;
            int k = 0;
            while (!stop && k < doubles.length()/2 - 1) {
                doubles.set(2*k+2,(doubles.get(2*k)*x)%mod);
                k++;
                if (doubles.get(2*k) == 1) {
                    stop = true;
//                    System.out.println(k);
                    int j = (int)doubles.length()/k;
                    for (int l = 1; l < 1 + j/2; l++) {
                        if (l+1 == j) {
                            ModExp modExp = new ModExp(doubles,x,mod,k,l);
                            Thread t = new Thread(modExp);
                            t.start();
                            try {
                                t.join();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        ModExp modExp = new ModExp(doubles,x,mod,k,l);
//                        System.out.println("New Thread k = " + k + " l = " + l);
                        Thread t = new Thread(modExp);
                        t.start();
                    }
                }
            }
        }
        else {
//            System.out.println(Thread.currentThread().getId());
//            System.out.println(i);
            boolean stop = false;
            int k = order*i;
            doubles.set(2*k,1);
            while (!stop && k < doubles.length()/2 - 1) {
                doubles.set(2*k+2,(doubles.get(2*k)*x)%mod);
                k++;
                if (doubles.get(2*k) == 1) {
                    stop = true;
                }
            }

        }
    }


    public static void main(String[] args) {
        int dimension = 64;
        int x = 3;
        int toFactor = 7;

//        int dimension = 64;
//        int x = 7;
//        int toFactor = 13;

        DoubleLargeArray doubleLargeArray = new DoubleLargeArray(dimension);
        ModExp modExp = new ModExp(doubleLargeArray,x,toFactor);
        Thread t = new Thread(modExp);
        t.run();

//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        for (int i = 0; i < doubleLargeArray.length(); i++) {
            System.out.println(doubleLargeArray.get(i));
        }

//        System.out.println("DONE");


//        int dimension = 67108864;
//        int x = 1204;
//        int toFactor = 8513;
//        DoubleLargeArray doubles1 = new DoubleLargeArray(dimension);
//
//        doubles1.setDouble(0, 1.0);
//        for (int i = 0; i < dimension - 1; i++) {
//            doubles1.setDouble(2*i+2,(x*doubles1.get(2*i))%toFactor);
//            System.out.println(function[i]);
//            System.out.println(doubles.get(i));
//        }
//        for (int i = 0; i < dimension -1; i++) {
//            doubles1.setDouble(2*i+1,0);
//        }

//        System.out.println("DONE");
    }
}