package checkpoint4;

import java.awt.*;

/**
 * This class represents the missile that the laser cannon object
 * blasts towards comets and asteroid objects.
 *
 * @author Tyler Zoutte and Mikhail Ahmed
 * @version April 25, 2023
 */
public class Missile extends GameObject
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
     * Constructs a missile object with control and gamestate objects.
     *
     * @param control any control object
     * @param state and gamestate object
     */
    public Missile(Control control, GameState state, Targetable target, Point source)
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
        percentage += (300/pathLength) * elapsedTime;
        location = path.convertToCoordinates(percentage);

        // Expires missile and target objects when collision occurs
        if (location.distance(state.findNearest(location).getLocation()) <= target.getSize() / 2)
        {
            // Adds an explosion exclusively for targets destroyed by missile
            Explosion explosion = new Explosion(control, state, location.x, location.y);
            state.addGameObject(explosion);
            this.hasExpired = true;
            for (GameObject g : state.getCurrentObjects())
            {
                // Money acquired for missile destroying objects
                    if (g instanceof Targetable && location.distance(((Targetable) g).getLocation()) <= 50) {
                        if (g instanceof Asteroid)
                            state.changeMoney(30000);
                        else if (g instanceof Comet)
                            state.changeMoney(20000);
                        g.setExpired(true);
                    }
            }
        }
        // Expires missile if it ages too long
        else if (age >= 0.65)
            this.hasExpired = true;
    }

    /**
     * This method draws the missile image.
     *
     * @param g Graphics object
     */
    @Override
    public void draw(Graphics g)
    {
        drawCentered(g, control.loadImage("missile.png"),  location.x, location.y, 0.05);
    }
}
