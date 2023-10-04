package checkpoint4;

import java.awt.*;

/**
 * This class represents the photon that the Probe object
 * blasts towards comets and asteroid objects.
 *
 * @author Tyler Zoutte and Mikhail Ahmed
 * @version April 18, 2023
 */
public class Photon extends GameObject
{
    private Control control;
    private GameState state;
    private Point source;
    private Targetable target;
    private Path path = new Path();
    private double percentage;
    private Point location;
    private double pathLength;
    private double age;

    /**
     * Constructs a photon object with control and gamestate objects.
     *
     * @param control any control object
     * @param state and gamestate object
     */
    public Photon(Control control, GameState state, Targetable target, Point source)
    {
        super();
        this.control = control;
        this.state = state;
        this.percentage = 0;
        this.location = new Point(800, 800);
        this.target = target;
        path.addPoint(source);
        path.addPoint(target.getLocation());
        pathLength = source.distance(target.getLocation());
    }

    /**
     * This method updates the photon object.
     *
     * @param elapsedTime time after last frame
     */
    @Override
    public void update(double elapsedTime)
    {
        age += elapsedTime;
        percentage += (500/pathLength) * elapsedTime;
        location = path.convertToCoordinates(percentage);

        // Expires photon and target objects when collision occurs
        if (location.distance(state.findNearest(location).getLocation()) <= target.getSize() / 2)
        {
            this.hasExpired = true;
            if (target instanceof Asteroid)
                state.changeMoney(30000);
            else if (target instanceof Comet)
                state.changeMoney(20000);
            target.setExpired(true);
        }
        // Expires photon if it ages too long
        else if (age >= 0.65)
            this.hasExpired = true;
    }

    /**
     * This method draws the photon image.
     *
     * @param g Graphics object
     */
    @Override
    public void draw(Graphics g)
    {
        drawCentered(g, control.loadImage("photon.png"),  location.x, location.y, 1);
    }
}
