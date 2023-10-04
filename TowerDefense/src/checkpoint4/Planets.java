package checkpoint4;

import java.awt.*;

/**
 * This class represents the areas of the planets in
 * the background that are permissible locations for launcher
 * objects but not probe objects.
 *
 * @author Tyler Zoutte and Mikhail Ahmed
 * @version April 25, 2023
 */
public class Planets extends GameObject
{
    private Control control;
    private GameState state;

    /**
     * This constructor creates Planets objects.
     *
     * @param control Control type
     * @param state GameState type
     */
    public Planets(Control control, GameState state)
    {
        super();
        this.control = control;
        this.state = state;
    }

    @Override
    public void update(double elapsedTime) {

    }

    /**
     * This method draws the circles in places of the planet's locations.
     *
     * @param g Graphics object
     */
    @Override
    public void draw(Graphics g)
    {
        // Jupiter
        g.drawOval(97, 211, 140,136);
        // Mars
        g.drawOval(392, 124, 65, 65);
        // Moon
        g.drawOval(459,336, 56,56);
        // Earth
        g.drawOval(435, 425, 425, 425);
    }

    /**
     * This method is used for finding the areas of the planets
     *
     * @param x integer for location
     * @param y integer for location
     * @return true or false
     */
    public static boolean isInside(int x, int y)
    {
        int jupiterRadius = 136/2;
        int marsRadius = 65/2;
        int moonRadius = 56/2;
        int earthRadius = 425/2;
        Point jupiterCenter = new Point(97 + 136/2, 211 + 140/2);
        Point marsCenter = new Point(392 + marsRadius, 124 + marsRadius);
        Point moonCenter = new Point(459 + moonRadius, 336 + moonRadius);
        Point earthCenter = new Point(435 + earthRadius, 425 + earthRadius);

        if ((jupiterCenter.x - x)*(jupiterCenter.x - x) + (jupiterCenter.y - y)*(jupiterCenter.y - y) < jupiterRadius * jupiterRadius)
        {
            return true;
        }
        else if ((marsCenter.x - x)*(marsCenter.x - x) + (marsCenter.y - y)*(marsCenter.y - y) < marsRadius * marsRadius)
        {
            return true;
        }
        else if ((moonCenter.x - x)*(moonCenter.x - x) + (moonCenter.y - y)*(moonCenter.y - y) < moonRadius * moonRadius)
        {
            return true;
        }
        else if ((earthCenter.x - x)*(earthCenter.x - x) + (earthCenter.y - y)*(earthCenter.y - y) < earthRadius * earthRadius)
        {
            return true;
        }
        else
            return false;
    }
}
