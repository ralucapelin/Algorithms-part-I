/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] q;
    private int n;

    public RandomizedQueue() {
        q = (Item[]) new Object[1];

    }

    private void resize(int capacity) {

        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++)
            copy[i] = q[i];
        q = copy;

    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException("WRONG ");
        if (q.length == n)
            resize(2 * q.length);
        q[n++] = item;
    }

    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("WRONG ");
        int x = StdRandom.uniform(n);
        Item a = q[x];
        q[x] = q[n - 1];
        q[n - 1] = null;
        n--;

        if (n > 0 && n == q.length / 4)
            resize(q.length / 2);
        return a;
    }

    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException("WRONG ");
        int x = StdRandom.uniform(n);
        return q[x];
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private int i = n;

        public boolean hasNext() {
            return i > 0;
        }

        public void remove() {
            throw new UnsupportedOperationException("WRONG ");
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException("WRONG ");

            return q[--i];
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<String> s = new RandomizedQueue<String>();
        In in = new In(args[0]);
        while (!in.isEmpty()) {
            String i = in.readString();
            s.enqueue(i);
        }
        Iterator<String> it = s.iterator();
        while (it.hasNext()) {
            StdOut.println(it.next());
        }
        StdOut.println("done ");
        StdOut.println(s.dequeue() + " OK ");
        Iterator<String> i = s.iterator();
        while (i.hasNext()) {
            StdOut.println(i.next());
        }
    }
}
