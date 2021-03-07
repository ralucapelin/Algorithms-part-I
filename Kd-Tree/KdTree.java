/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class KdTree {
    private Node root = null;
    private int count = 0;
    private Node prt;
    private double c = 10.0;
    private Point2D ch;


    private static class Node {
        private Point2D p;
        private RectHV rect;
        private Node lb;
        private Node rt;
        private boolean or = true;
        private Node parent = null;


        private Node(Point2D p) {
            this.p = p;


        }
    }

    public KdTree() {

    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return count;
    }


    public void insert(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException("WRONG");
        root = put(root, p);
    }

    private Iterable<Node> keys() {
        Queue<Node> q = new Queue<Node>();
        inorder(root, q);
        return q;
    }

    private void inorder(Node x, Queue<Node> q) {
        if (x == null) return;
        inorder(x.lb, q);
        q.enqueue(x);
        inorder(x.rt, q);
    }


    private Node put(Node x, Point2D p) {
        if (p == null)
            throw new IllegalArgumentException("WRONG");
        double px = p.x();
        double py = p.y();
        if (x == null) {
            count++;
            Node w = new Node(p);
            w.parent = prt;
            if (prt != null) {
                w.or = !prt.or;
                double xmin = prt.rect.xmin();
                double xmax = prt.rect.xmax();
                double ymin = prt.rect.ymin();
                double ymax = prt.rect.ymax();
                if (prt.or) {
                    double xp = prt.p.x();
                    if (w.p.x() >= xp) {
                        xmin = xp;
                    }
                    else {
                        xmax = xp;
                    }
                    //   if (xmin < xmax)
                    w.rect = new RectHV(xmin, prt.rect.ymin(), xmax, prt.rect.ymax());
                    //   else w.rect = new RectHV(xmax, prt.rect.ymin(), xmin, prt.rect.ymax());
                }
                else {
                    double yp = prt.p.y();
                    if (w.p.y() >= yp) {
                        ymin = yp;
                    }
                    else {
                        ymax = yp;
                    }
                    //  if (ymax > ymin)
                    w.rect = new RectHV(prt.rect.xmin(), ymin, prt.rect.xmax(), ymax);
                    //   else w.rect = new RectHV(prt.rect.xmin(), ymax, prt.rect.xmax(), ymin);
                }
            }
            else {
                w.rect = new RectHV(0.0, 0.0, 1.0, 1.0);
            }

            return w;
        }

        if (!x.or) {
            double cmp = (py - x.p.y());
            if (cmp < 0) {
                prt = x;
                x.lb = put(x.lb, p);
            }
            else if (cmp > 0) {
                prt = x;
                x.rt = put(x.rt, p);
            }
            else if (px != x.p.x()) {
                prt = x;
                x.rt = put(x.rt, p);
            }
        }
        else {

            double cmp = (px - x.p.x());
            if (cmp < 0) {
                prt = x;
                x.lb = put(x.lb, p);
            }
            else if (cmp > 0) {
                prt = x;
                x.rt = put(x.rt, p);
            }
            else if (py != x.p.y()) {
                prt = x;
                x.rt = put(x.rt, p);
            }
        }
        return x;
    }

    private boolean get(Node x, Point2D p) {
        if (x == null)
            return false;
        int cmp = compareTo(x, p);
        if (cmp < 0)
            return get(x.rt, p);
        else if (cmp > 0)
            return get(x.lb, p);
        return true;
    }

    private int compareTo(Node x, Point2D p) {
        if (x.p.equals(p))
            return 0;

        double ex = p.x();
        double y = p.y();
        if (!x.or) {
            if ((y - x.p.y()) < 0) return 1;
            else return -1;
        }
        else {

            if (ex - x.p.x() < 0) return 1;
            else return -1;
        }

    }

    public boolean contains(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException("WRONG");

        return get(root, p);
    }


    public void draw() {
        Iterable<Node> it = keys();

        for (Node n : it) {

            if (!n.or) {

                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.line(n.rect.xmin(), n.p.y(), n.rect.xmax(), n.p.y());
            }
            else {
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.line(n.p.x(), n.rect.ymin(), n.p.x(), n.rect.ymax());
            }
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.point(n.p.x(), n.p.y());
        }
    }


    private void search(Node x, RectHV rect, Queue<Point2D> q) {
        if (x == null)
            return;
        if (rect.contains(x.p))
            q.enqueue(x.p);
        if (x.lb != null)
            if (x.lb.rect.intersects(rect)) {
                search(x.lb, rect, q);
            }
        if (x.rt != null)
            if (x.rt.rect.intersects(rect)) {
                search(x.rt, rect, q);
            }
    }

    private void searchNode(Node x, Point2D p) {
        if (p == null)
            throw new IllegalArgumentException("WRONG");
        double dr = 10.0, dl = 10.0;

        if (x.lb != null) {
            dl = x.lb.rect.distanceSquaredTo(p);
        }
        if (x.rt != null) {
            dr = x.rt.rect.distanceSquaredTo(p);
        }
        if (x.p.distanceSquaredTo(p) < c) {
            c = x.p.distanceSquaredTo(p);
            ch = x.p;
        }
        if (dl < dr && x.lb != null) {
            searchNode(x.lb, p);
            if (x.rt != null) {
                double od;
                if (x.or)
                    od = p.distanceSquaredTo(new Point2D(x.p.x(), p.y()));
                else od = p.distanceSquaredTo(new Point2D(p.x(), x.p.y()));
                if (od <= c)
                    searchNode(x.rt, p);
            }
        }
        else if (x.rt != null) {
            searchNode(x.rt, p);
            if (x.lb != null) {
                double od;
                if (x.or)
                    od = p.distanceSquaredTo(new Point2D(x.p.x(), p.y()));
                else od = p.distanceSquaredTo(new Point2D(p.x(), x.p.y()));
                if (od <= c)
                    searchNode(x.lb, p);
            }
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null)
            throw new IllegalArgumentException("WRONG");
        Queue<Point2D> q = new Queue<Point2D>();
        search(root, rect, q);
        return q;
    }

    public Point2D nearest(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException("WRONG");
        if (root == null)
            return null;
        c = 10.0;
        ch = root.p;
        searchNode(root, p);
        return ch;
    }


    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);
        KdTree kdtree = new KdTree();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kdtree.insert(p);

        }
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        kdtree.draw();
        StdDraw.show();
        StdOut.println(kdtree.size());
        StdOut.println(kdtree.contains(new Point2D(0.5, 0.4)));

    }
}

