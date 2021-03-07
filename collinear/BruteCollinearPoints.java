/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {

    private int d = 0;
    private final LineSegment[] lineSegment;
    private final ArrayList<LineSegment> lineSeg = new ArrayList<LineSegment>();

    public BruteCollinearPoints(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException("WRING");
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null)
                throw new IllegalArgumentException("WROG");

        }
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0 && i != j)
                    throw new IllegalArgumentException("WTONG");
            }
        }
        int len = points.length;
        Point[] ppoints = Arrays.copyOf(points, len);
        Arrays.sort(ppoints);

        for (int i = 0; i < len - 3; i++) {
            for (int j = i + 1; j < len - 2; j++) {
                for (int k = j + 1; k < len - 1; k++) {
                    for (int m = k + 1; m < len; m++) {
                        /*StdOut.println(
                                points[i].slopeTo(points[j]) + " " + points[i].slopeTo(points[k])
                                        + " " + points[i]
                                        .slopeTo(points[m]));*/
                        if (ppoints[i].slopeTo(ppoints[j]) == ppoints[i].slopeTo(ppoints[k])
                                && ppoints[i].slopeTo(ppoints[k]) == ppoints[i]
                                .slopeTo(ppoints[m])) {
                            //StdOut.println("OK");
                            if (ppoints[i].compareTo(mini(i, j, k, m, ppoints)) == 0
                                    && ppoints[m].compareTo(maxi(i, j, k, m, ppoints)) == 0) {
                                lineSeg.add(new LineSegment(ppoints[i], ppoints[m]));

                                d++;
                            }

                        }

                    }

                }

            }

        }

        lineSegment = lineSeg.toArray(new LineSegment[lineSeg.size()]);

    }

    private Point mini(int i, int j, int k, int m, Point[] points) {
        if (points[i].compareTo(points[j]) < 0 && points[i].compareTo(points[k]) < 0
                && points[i].compareTo(points[m]) < 0)
            return points[i];
        if (points[j].compareTo(points[i]) < 0 && points[j].compareTo(points[k]) < 0
                && points[j].compareTo(points[m]) < 0)
            return points[j];
        if (points[k].compareTo(points[j]) < 0 && points[k].compareTo(points[i]) < 0
                && points[k].compareTo(points[m]) < 0)
            return points[k];
        return points[m];

    }

    private Point maxi(int i, int j, int k, int m, Point[] points) {
        if (points[i].compareTo(points[j]) > 0 && points[i].compareTo(points[k]) > 0
                && points[i].compareTo(points[m]) > 0)
            return points[i];
        if (points[j].compareTo(points[i]) > 0 && points[j].compareTo(points[k]) > 0
                && points[j].compareTo(points[m]) > 0)
            return points[j];
        if (points[k].compareTo(points[j]) > 0 && points[k].compareTo(points[i]) > 0
                && points[k].compareTo(points[m]) > 0)
            return points[k];
        return points[m];

    }

    public int numberOfSegments() {
        return d;
    }

    public LineSegment[] segments() {
        LineSegment[] copy = Arrays.copyOf(lineSegment, lineSegment.length);
        return copy;
    }

    public static void main(String[] args) {
        // read the n points from a file

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);

        }

        BruteCollinearPoints pee = new BruteCollinearPoints(points);
        System.out.println(pee.d);

        //StdOut.println(points[0].slopeTo(points[1]));

    }
}
