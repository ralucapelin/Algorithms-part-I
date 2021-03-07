/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double[] values;
    private final int te;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            IllegalArgumentException exc = new IllegalArgumentException("gresit ");
            throw exc;
        }

        te = trials;
        values = new double[trials];
        for (int i = 1; i <= trials; i++) {

            Percolation perc = new Percolation(n);
            while (!perc.percolates()) {
                int r = StdRandom.uniform(1, n + 1);
                int j = StdRandom.uniform(1, n + 1);
                if (!perc.isOpen(r, j))
                    perc.open(r, j);


            }
            values[i - 1] = perc.numberOfOpenSites() * 1.0 / n / n;


        }


    }

    public double mean() {
        return StdStats.mean(values);
    }

    public double stddev() {
        return StdStats.stddev(values);
    }

    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(te);
    }

    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(te);
    }

    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]);


        int t = Integer.parseInt(args[1]);


        PercolationStats pe = new PercolationStats(n, t);

        StdOut.println("mean = " + pe.mean());
        StdOut.println("stddev = " + pe.stddev());
        StdOut.println("95% confidence interval =  [" + pe.confidenceLo() + ", " + pe.confidenceHi()
                               + " ]");


    }
}
