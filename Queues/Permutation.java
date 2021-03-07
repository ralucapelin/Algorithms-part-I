/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {

        RandomizedQueue<String> s = new RandomizedQueue<String>();
        int k = Integer.parseInt(args[0]);
        In in = new In(args[1]);
        while (!StdIn.isEmpty()) {
            String i = in.readString();
            s.enqueue(i);
        }

        for (int i = 0; i < k; i++) {
            StdOut.println(s.dequeue());
        }


    }
}
