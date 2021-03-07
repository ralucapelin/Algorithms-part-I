/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private ArrayList<LineSegment> lineSeg = new ArrayList<LineSegment>();
    private LineSegment[] segments;

    public FastCollinearPoints(Point[] points) {

        if (points == null)
            throw new IllegalArgumentException("WRING");
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null)
                throw new IllegalArgumentException("WROG");

        }
        for (int ii = 0; ii < points.length; ii++) {
            for (int j = ii + 1; j < points.length; j++) {
                if (points[ii].compareTo(points[j]) == 0 && ii != j)
                    throw new IllegalArgumentException("WTONG");
            }
        }
        int len = points.length;
        Point[] ppoints = Arrays.copyOf(points, len);
        Point[] ppoints2 = Arrays.copyOf(points, len);

        Arrays.sort(ppoints2);
        // StdOut.println(Arrays.toString(ppoints2));
        for (int i = 0; i < len; i++) {

            Arrays.sort(ppoints);

            Point origine = ppoints2[i];
            Arrays.sort(ppoints, origine.slopeOrder());


            int beg = 0;
            int count = 2;
            double slope;
            slope = origine.slopeTo(ppoints[beg]);
            for (int j = 0; j < len; j++) {

                if (slope == origine.slopeTo(ppoints[j])) {
                    count++;
                    if (count >= 4 && j == len - 1) {
                        if (origine.compareTo(ppoints[beg]) <= 0) {

                            lineSeg.add(new LineSegment(origine, ppoints[len - 1]));

                        }


                    }
                }
                else if (count >= 4) {
                    if (origine.compareTo(ppoints[beg]) <= 0) {

                        lineSeg.add(new LineSegment(origine, ppoints[j - 1]));

                    }
                    count = 2;
                    beg = j;
                    slope = origine.slopeTo(ppoints[beg]);
                }
                else {
                    count = 2;
                    beg = j;
                    slope = origine.slopeTo(ppoints[beg]);
                }

            }
        }
        segments = lineSeg.toArray(new LineSegment[lineSeg.size()]);
    }

/*
    private int comparePointsLow(int origin, int low, int high, Point[] ppoints) {

        Arrays.sort(ppoints, low, high);
        if (ppoints[low].compareTo(ppoints[origin]) == 0)
            return origin;
        return -1;

    }

    private int comparePointsHigh(int origin, int low, int high, Point[] ppoints) {

        Arrays.sort(ppoints, low, high);
        if (ppoints[high - 1].compareTo(ppoints[origin]) == 0)
            return origin;
        return -1;

    }

 */

    public int numberOfSegments() {
        return segments.length;
    }

    public LineSegment[] segments() {
        LineSegment[] copy = Arrays.copyOf(segments, segments.length);
        return copy;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);

        }
        FastCollinearPoints poo = new FastCollinearPoints(points);
        StdOut.println(poo.numberOfSegments());
    }
}
