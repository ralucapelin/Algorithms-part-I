/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Collections;

public class Solver {

    private int mv = 0;
    private ArrayList<Board> sol = new ArrayList<Board>();
    private boolean solvable;

    public Solver(Board initial) {
        if (initial == null)
            throw new IllegalArgumentException("WRONG");
        MinPQ<SearchNode> pq = new MinPQ<SearchNode>();
        MinPQ<SearchNode> pq1 = new MinPQ<SearchNode>();
        Board b1 = initial.twin();

        SearchNode node1 = new SearchNode(b1, 0, null);
        SearchNode node = new SearchNode(initial, 0, null);
        pq.insert(node);
        pq1.insert(node1);

        int ok = 1;
        SearchNode prev = null;

        Board last;
        while (ok == 1) {
            //StdOut.println(pq.min().board.toString());
            Iterable<Board> it = pq.min().board.neighbors();
            last = pq.min().board;
            prev = pq.min();
            pq.delMin();
            mv++;

            if (last.isGoal()) {
                ok = 0;
                solvable = true;
                while (prev != null) {
                    sol.add(prev.board);
                    prev = prev.prev;
                }
                Collections.reverse(sol);
            }


            if (ok == 1)
                for (Board brd : it) {
                    SearchNode nod = new SearchNode(brd, 0, prev);
                    nod.moves = prev.moves + 1;
                    if (mv > 1) {
                        if (!brd.equals(nod.prev.prev.board))
                            pq.insert(nod);
                    }
                    else pq.insert(nod);

                }

            Iterable<Board> it1 = pq1.min().board.neighbors();

            if (pq1.delMin().board.isGoal()) {
                ok = 0;
            }

            if (ok == 1)
                for (Board brd : it1) {
                    SearchNode nod = new SearchNode(brd, 0, null);
                    pq1.insert(nod);
                }
        }
    }

    private class SearchNode implements Comparable<SearchNode> {
        Board board;
        int moves;
        SearchNode prev;
        int man;

        private SearchNode(Board board, int moves, SearchNode prev) {
            this.board = board;
            this.moves = moves;
            this.prev = prev;
            man = board.manhattan();
        }

        private int priority() {
            return man + moves - 1;
        }

        public int compareTo(SearchNode that) {
            if (priority() > that.priority())
                return 1;
            else if (priority() < that.priority())
                return -1;
            return 0;
        }

    }


    public boolean isSolvable() {
        return solvable;
    }

    public int moves() {
        if (!isSolvable())
            return -1;
        return sol.size() - 1;
    }

    public Iterable<Board> solution() {
        if (!isSolvable())
            return null;
        return sol;
    }

    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();

        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }


    }
}
