/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {

    private final int x;
    private final WeightedQuickUnionUF perc;
    private boolean[] oNode;

    public Percolation(int n) {
        if (n <= 0) {
            IllegalArgumentException exc = new IllegalArgumentException("gresit ");
            throw exc;
        }
        x = n;
        perc = new WeightedQuickUnionUF(x * x + 2);
        oNode = new boolean[n * n];

        for (int i = 1; i <= x; i++) {
            perc.union((x - 1) * x + i - 1, x * x + 1);
            perc.union(i - 1, x * x);
        }

    }

    public void open(int row, int col) {
        if (row < 1 || col < 1 || row > x || col > x) {
            IllegalArgumentException exc = new IllegalArgumentException("gresit ");
            throw exc;
        }
        oNode[(row - 1) * x + col - 1] = true;
        if (row < x)
            if (isOpen(row + 1, col))
                perc.union((row - 1) * x + col - 1, row * x + col - 1);
        if (row > 1)
            if (isOpen(row - 1, col))
                perc.union((row - 1) * x + col - 1, (row - 2) * x + col - 1);
        if (col < x)
            if (isOpen(row, col + 1))
                perc.union((row - 1) * x + col - 1, (row - 1) * x + col);
        if (col > 1)
            if (isOpen(row, col - 1))
                perc.union((row - 1) * x + col - 1, (row - 1) * x + col - 2);


    }

    public boolean isOpen(int row, int col) {

        if (row < 1 || col < 1 || row > x || col > x) {
            IllegalArgumentException exc = new IllegalArgumentException("gresit ");
            throw exc;
        }

        if (oNode[(row - 1) * x + col - 1])
            return true;
        /* if (row < x)
            if (perc.find((row - 1) * x + col - 1) == perc.find(row * x + col - 1))
                return true;
        if (row >= 2)
            if (perc.find((row - 1) * x + col - 1)
                    == perc.find((row - 2) * x + col - 1))
                return true;
        if (col < x)
            if (perc.find((row - 1) * x + col - 1) == perc.find((row - 1) * x + col))
                return true;
        if (col >= 2)
            if (perc.find((row - 1) * x + col - 1) == perc.find((row - 1) * x + col - 2))
                return true;

*/
        return false;
    }

    public boolean isFull(int row, int col) {

        if (row < 1 || col < 1 || row > x || col > x) {
            IllegalArgumentException exc = new IllegalArgumentException("gresit ");
            throw exc;
        }

        if (row == 1 && isOpen(row, col)) {
            perc.union(col - 1, x * x);
            return true;
        }

        if (isOpen(row, col)) {
            if (perc.find((row - 1) * x + col - 1) == perc.find(x * x))
                return true;
        }

        return false;
    }

    public int numberOfOpenSites() {
        int open = 0;
        for (int i = 1; i <= x; i++)
            for (int j = 1; j <= x; j++)
                if (isOpen(i, j))
                    open++;
        return open;
    }

    public boolean percolates() {
        if (perc.find(x * x + 1) == perc.find(x * x))
            return true;
        return false;
    }

    /* public static void main(String[] args) {

    }*/
}
