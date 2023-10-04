/**
 * This class constructs a path object and creates methods to access points, add points, count the total
 * number of points, draw the path between the points and convert the points to a string.
 *
 * @author Tyler J. Zoutte, Mikhail Ahmed
 * @version March 25, 2023
 */
package checkpoint4;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Objects from this class represent a paths made up of points.
 */
public class Path
{
    // Initialize variables
    private ArrayList pathPoints;
    private int x;
    private int y;
    private ArrayList segments;

    /**
     * Constructs a path object that is an arraylist of points.
     */
    public Path()
    {
         pathPoints = new ArrayList<Point>();
    }

    /**
     * Constructs a path object that is an arraylist of points from a text file..
     *
     * @param filename any text file
     */
    public Path(String filename)
    {
        try
        {
            Scanner scanner = new Scanner(new File(filename));
            pathPoints = new ArrayList<Point>();
            int pointCount = scanner.nextInt();
            for (int i = 0; i < pointCount - 1; i++) {
                int x = (int) scanner.nextDouble();
                int y = (int) scanner.nextDouble();
                pathPoints.add(new Point(x, y));
            }
            scanner.close();
        }
        catch (IOException e)
        {
            System.out.println("Could not read " + filename);
        }
    }

    /**
     * Adds a point to the path.
     *
     * @param p any point
     */
    public void addPoint(Point p)
    {
        pathPoints.add(p);
    }

    /**
     * Returns the point at a position in the path.
     *
     * @param pos an integer from 0 to the last point in the path
     * @return the point at the position
     */
    public Point getPoint(int pos)
    {
        return (Point) pathPoints.get(pos);
    }

    /**
     * Returns the amount of points in the path.
     *
     * @return the amount of points in the path.
     */
    public int getPointCount()
    {
        return pathPoints.size();
    }

    /**
     * Draws red lines between all the points in the path from the first to the last.
     *
     * @param g graphics object
     */
    public void drawPath(Graphics g)
    {
        // If the arraylist is not null and has more than one point, loop though the points in the arraylist by
        // position and draw lines from the previous point to the point at the position.
        if (pathPoints != null && getPointCount() > 1)
        {
            for (int i = 1; i < getPointCount(); i++)
            {
                Point lastPoint = getPoint(i-1);
                Point thisPoint = getPoint(i);
                g.setColor(Color.RED);
                g.drawLine((int) lastPoint.getX(), (int) lastPoint.getY(), (int) thisPoint.getX(), (int) thisPoint.getY());
            }
        }
    }

    /**
     * Converts the point at a position in the path to a string on its own line.
     *
     * @param pos an integer from 0 to the last point in the path
     * @return the point at the position as a string
     */
    public String toString(int pos)
    {
        // Convert the point at a position to a string with the X coordinate first with a space in between,
        // then start a new line.
        String points = getPoint(pos).getX() + " " + getPoint(pos).getY() + "\n";
        return points;
    }

    /**
     * Given a percentage between 0% and 100%, this method calculates
     * the location along the path that is exactly this percentage
     * along the path. The location is returned in a Point object
     * (integer x and y), and the location is a screen coordinate.
     *
     * If the percentage is less than 0%, the starting position is
     * returned. If the percentage is greater than 100%, the final
     * position is returned.
     *
     * Callers must not change the x or y coordinates of any returned
     * point object (or the caller could be changing the path).
     *
     * @param percentTraveled a distance along the path
     * @return the screen coordinate of this position along the path
     */
    public Point convertToCoordinates(double percentTraveled)
    {
        // Create an array of segment lengths and use it to calculate the total length.
        double totalLength = 0;
        double[] segments = new double[getPointCount() - 1];
        for (int i = 0; i < getPointCount() - 1; i++)
        {
            // Calculate each segment length and store it in the array, add to total length as it goes.
            double deltaX = getPoint(i + 1).x - getPoint(i).x;
            double deltaY = getPoint(i + 1).y - getPoint(i).y;
            double segLength = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
            totalLength += segLength;
            segments[i] = segLength;
        }

        // Set boundary conditions
        if (percentTraveled < 0)
            return getPoint(0);
        else if (percentTraveled > 1)
            return getPoint(getPointCount() - 1);
        else
        {
            // Calculate total distance, distance remaining initially is the total distance.
            double totalDistance = totalLength * percentTraveled;
            double distanceRemaining = totalDistance;
            double segPercent = 0;
            int lastSegment = 0;
            // Loop through segments and subtract each from the distance remaining until the segment is greater than
            // the distance remaining, meaning this is the segment the object is currently on. Then store this segment
            // , calculate the percentage of the segment the object has gone and break.
            for (int i = 0; i < segments.length; i++)
            {
                if (distanceRemaining > segments[i])
                    distanceRemaining -= segments[i];
                else
                {
                    segPercent = distanceRemaining / segments[i];
                    lastSegment = i;
                    break;
                }
            }
            // Calculate the delta x and y of the last segment, use the segment percentage to find the
            // coordinates where the object currently is. Return these as a point.
            double xStart = getPoint(lastSegment).x;
            double xEnd = getPoint(lastSegment + 1).x;
            double yStart = getPoint(lastSegment).y;
            double yEnd = getPoint(lastSegment + 1).y;
            double xCoordinate = (1 - segPercent) *xStart + segPercent * xEnd;
            double yCoordinate = (1 - segPercent) *yStart + segPercent * yEnd;

            return new Point((int) xCoordinate,(int) yCoordinate);
        }
    }
}
