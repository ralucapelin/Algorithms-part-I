/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first, last;
    private int n = 0;

    private class Node {
        Item item;
        Node next;
    }

    public Deque() {

    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    public void addFirst(Item item) {
        if (item == null) {
            IllegalArgumentException exc = new IllegalArgumentException("WRONG ");
            throw exc;
        }

        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        n++;
    }

    public void addLast(Item item) {
        if (item == null) {
            IllegalArgumentException exc = new IllegalArgumentException("WRONG ");
            throw exc;
        }
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty())
            first = last;
        else oldlast.next = last;
        n++;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            NoSuchElementException exc = new NoSuchElementException("WRONG ");
            throw exc;
        }
        n--;
        Item item = first.item;
        first = first.next;
        if (isEmpty())
            last = null;
        return item;

    }

    public Item removeLast() {
        if (isEmpty()) {
            NoSuchElementException exc = new NoSuchElementException("WRONG ");
            throw exc;
        }

        Item item = first.item;
        first = first.next;
        n--;
        return item;

    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {

            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException("WRONG ");
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException("WRONG ");
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Deque<String> s = new Deque<String>();
        In in = new In(args[0]);
        while (!in.isEmpty()) {
            String i = in.readString();
            s.addFirst(i);
        }
        StdOut.println(s.size());
        StdOut.println(s.first.item);
        s.removeLast();
        StdOut.println(s.first.item);
    }


}
