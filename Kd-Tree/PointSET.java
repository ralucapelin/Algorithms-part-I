/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdDraw;

import java.util.TreeSet;

public class PointSET {
    private TreeSet<Point2D> tset = new TreeSet<Point2D>();

    public PointSET() {

    }

    public boolean isEmpty() {
        return tset.isEmpty();
    }

    public int size() {
        return tset.size();
    }

    public void insert(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException("WRONG");
        tset.add(p);
    }

    public boolean contains(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException("WRONG");
        return tset.contains(p);
    }

    public void draw() {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        for (Point2D p : tset) {
            StdDraw.point(p.x(), p.y());
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null)
            throw new IllegalArgumentException("WRONG");
        Stack<Point2D> st = new Stack<Point2D>();
        for (Point2D p : tset) {
            if (rect.contains(p)) {
                st.push(p);
            }
        }
        return st;
    }

    public Point2D nearest(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException("WRONG");
        if (tset.isEmpty()) {
            return null;
        }
        double dist = 10000.0;
        Point2D mini = null;
        for (Point2D q : tset) {
            double d = p.distanceSquaredTo(q);
            if (d < dist) {
                dist = d;
                mini = q;
            }
        }
        return mini;
    }


    public static void main(String[] args) {

    }
}
