/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Board {
    private final int[][] tile;
    private final int n;

    private int man = -1;

    public Board(int[][] tiles) {
        n = tiles.length;
        tile = new int[n][n];
        for (int k = 0; k < n; k++)
            tile[k] = tiles[k].clone();

    }

    public String toString() {
        String size = String.valueOf(n);
        StringBuilder x = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                String y = String.valueOf(tile[i][j]);
                x.append(" ");
                x.append(y);
            }
            x.append("%n");
        }
        return String.format(size + "%n" + x);
    }

    public int dimension() {
        return n;
    }


    public int hamming() {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tile[i][j] != (i * n + j + 1) && tile[i][j] > 0) {
                    count++;

                }
            }
        }
        return count;
    }

    public int manhattan() {
        if (man == -1) {
            man = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (tile[i][j] > 0) {
                        int dist = abs(i - ((tile[i][j] - 1) / n)) + abs(
                                j - ((tile[i][j] - 1) % n));
                        // if (dist > 0)
                        //   StdOut.println(tile[i][j] + " " + dist);
                        man = man + dist;
                    }
                }
            }
            return man;
        }
        else return man;

    }

    private int abs(int a) {
        if (a >= 0)
            return a;
        else return -a;
    }

    public boolean isGoal() {
        if (manhattan() == 0)
            return true;
        return false;
    }

    public boolean equals(Object y) {
        if (y == null || y.getClass() != this.getClass())
            return false;
        Board obj = (Board) y;
        if (obj.dimension() != this.dimension())
            return false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.tile[i][j] != obj.tile[i][j])
                    return false;
            }
        }
        return true;
    }

    public Iterable<Board> neighbors() {
        Stack<Board> neighbor = new Stack<Board>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tile[i][j] == 0) {
                    if (i == 0) {
                        if (j == 0) {
                            int[][] cpy2 = new int[n][n];
                            for (int k = 0; k < n; k++)
                                cpy2[k] = tile[k].clone();
                            swap(cpy2, i, j, i + 1, j);
                            neighbor.push(new Board(cpy2));
                            int[][] cpy1 = new int[n][n];
                            for (int k = 0; k < n; k++)
                                cpy1[k] = tile[k].clone();
                            swap(cpy1, i, j, i, j + 1);
                            neighbor.push(new Board(cpy1));
                        }
                        else if (j == n - 1) {
                            int[][] cpy1 = new int[n][n];
                            for (int k = 0; k < n; k++)
                                cpy1[k] = tile[k].clone();
                            swap(cpy1, i, j, i, j - 1);
                            neighbor.push(new Board(cpy1));
                            int[][] cpy2 = new int[n][n];
                            for (int k = 0; k < n; k++)
                                cpy2[k] = tile[k].clone();
                            swap(cpy2, i, j, i + 1, j);
                            neighbor.push(new Board(cpy2));
                        }
                        else {
                            int[][] cpy1 = new int[n][n];
                            for (int k = 0; k < n; k++)
                                cpy1[k] = tile[k].clone();
                            swap(cpy1, i, j, i, j - 1);
                            neighbor.push(new Board(cpy1));
                            int[][] cpy2 = new int[n][n];
                            for (int k = 0; k < n; k++)
                                cpy2[k] = tile[k].clone();
                            swap(cpy2, i, j, i + 1, j);
                            neighbor.push(new Board(cpy2));
                            int[][] cpy3 = new int[n][n];
                            for (int k = 0; k < n; k++)
                                cpy3[k] = tile[k].clone();
                            swap(cpy3, i, j, i, j + 1);
                            neighbor.push(new Board(cpy3));
                        }
                    }
                    else if (i == n - 1) {
                        if (j == 0) {
                            int[][] cpy2 = new int[n][n];
                            for (int k = 0; k < n; k++)
                                cpy2[k] = tile[k].clone();
                            swap(cpy2, i, j, i - 1, j);
                            neighbor.push(new Board(cpy2));
                            int[][] cpy1 = new int[n][n];
                            for (int k = 0; k < n; k++)
                                cpy1[k] = tile[k].clone();
                            swap(cpy1, i, j, i, j + 1);
                            neighbor.push(new Board(cpy1));

                        }
                        else if (j == n - 1) {
                            int[][] cpy2 = new int[n][n];
                            for (int k = 0; k < n; k++)
                                cpy2[k] = tile[k].clone();
                            swap(cpy2, i, j, i - 1, j);
                            neighbor.push(new Board(cpy2));
                            int[][] cpy1 = new int[n][n];
                            for (int k = 0; k < n; k++)
                                cpy1[k] = tile[k].clone();
                            swap(cpy1, i, j, i, j - 1);
                            neighbor.push(new Board(cpy1));
                        }
                        else {
                            int[][] cpy2 = new int[n][n];
                            for (int k = 0; k < n; k++)
                                cpy2[k] = tile[k].clone();
                            swap(cpy2, i, j, i - 1, j);
                            neighbor.push(new Board(cpy2));
                            int[][] cpy1 = new int[n][n];
                            for (int k = 0; k < n; k++)
                                cpy1[k] = tile[k].clone();
                            swap(cpy1, i, j, i, j - 1);
                            neighbor.push(new Board(cpy1));
                            int[][] cpy3 = new int[n][n];
                            for (int k = 0; k < n; k++)
                                cpy3[k] = tile[k].clone();
                            swap(cpy3, i, j, i, j + 1);
                            neighbor.push(new Board(cpy3));
                        }
                    }
                    else if (j == 0) {
                        int[][] cpy2 = new int[n][n];
                        for (int k = 0; k < n; k++)
                            cpy2[k] = tile[k].clone();
                        swap(cpy2, i, j, i - 1, j);
                        neighbor.push(new Board(cpy2));
                        int[][] cpy3 = new int[n][n];
                        for (int k = 0; k < n; k++)
                            cpy3[k] = tile[k].clone();
                        swap(cpy3, i, j, i + 1, j);
                        neighbor.push(new Board(cpy3));
                        int[][] cpy1 = new int[n][n];
                        for (int k = 0; k < n; k++)
                            cpy1[k] = tile[k].clone();
                        swap(cpy1, i, j, i, j + 1);
                        neighbor.push(new Board(cpy1));
                    }
                    else if (j == n - 1) {
                        int[][] cpy2 = new int[n][n];
                        for (int k = 0; k < n; k++)
                            cpy2[k] = tile[k].clone();
                        swap(cpy2, i, j, i - 1, j);
                        neighbor.push(new Board(cpy2));
                        int[][] cpy1 = new int[n][n];
                        for (int k = 0; k < n; k++)
                            cpy1[k] = tile[k].clone();
                        swap(cpy1, i, j, i, j - 1);
                        neighbor.push(new Board(cpy1));
                        int[][] cpy3 = new int[n][n];
                        for (int k = 0; k < n; k++)
                            cpy3[k] = tile[k].clone();
                        swap(cpy3, i, j, i + 1, j);
                        neighbor.push(new Board(cpy3));
                    }
                    else {
                        int[][] cpy2 = new int[n][n];
                        for (int k = 0; k < n; k++)
                            cpy2[k] = tile[k].clone();
                        swap(cpy2, i, j, i - 1, j);
                        neighbor.push(new Board(cpy2));
                        int[][] cpy1 = new int[n][n];
                        for (int k = 0; k < n; k++)
                            cpy1[k] = tile[k].clone();
                        swap(cpy1, i, j, i, j - 1);
                        neighbor.push(new Board(cpy1));
                        int[][] cpy3 = new int[n][n];
                        for (int k = 0; k < n; k++)
                            cpy3[k] = tile[k].clone();
                        swap(cpy3, i, j, i + 1, j);
                        neighbor.push(new Board(cpy3));
                        int[][] cpy4 = new int[n][n];
                        for (int k = 0; k < n; k++)
                            cpy4[k] = tile[k].clone();
                        swap(cpy4, i, j, i, j + 1);
                        neighbor.push(new Board(cpy4));
                    }
                }
            }
        }
        return neighbor;
    }


    public Board twin() {
        int[][] cpy = new int[n][n];
        for (int k = 0; k < n; k++)
            cpy[k] = tile[k].clone();
        if (tile[0][0] != 0)
            if (tile[0][1] != 0)
                swap(cpy, 0, 0, 0, 1);
            else swap(cpy, 0, 0, 1, 0);
        else swap(cpy, 0, 1, 1, 0);

        Board nb = new Board(cpy);
        return nb;
    }

    private void swap(int[][] v, int i, int j, int k, int l) {
        v[i][j] = v[i][j] ^ v[k][l];
        v[k][l] = v[i][j] ^ v[k][l];
        v[i][j] = v[i][j] ^ v[k][l];
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);
        StdOut.println(initial.hamming());
    }
}
