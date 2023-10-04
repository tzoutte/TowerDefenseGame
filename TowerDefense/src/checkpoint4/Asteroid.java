package checkpoint4;

import java.awt.*;

/**
 * Objects in this class represent asteroids, which are game objects.
 */
public class Asteroid extends GameObject implements Targetable
{
    private Control control;
    private GameState state;
    private double percentage;
    private double age;
    private Point location;

    /**
     * Constructs an asteroid object with control and gamestate objects.
     *
     * @param control any control object
     * @param state and gamestate object
     */
    public Asteroid(Control control, GameState state)
    {
        super();
        this.control = control;
        this.state = state;
        this.location = new Point (800, 800);
        percentage = 0;
    }

    /**
     * Updates the percent the asteroid has traveled based on time elapsed.
     *
     * @param elapsedTime any double
     */
    @Override
    public void update(double elapsedTime)
    {
        // asteroid's travel
        location = control.getPath().convertToCoordinates(percentage);
        percentage += (1.0/20.0) * elapsedTime;

        // If asteroid completes path, causes explosion on Earth and takes lives
        if (percentage >= 1)
        {
            this.hasExpired = true;
            state.addGameObject(new Explosion(control, state, location.x, location.y));
            state.changeCitizens(-403025008);
        }


    }

    /**
     * Draws the asteroid and updates its location based on the percent it has traveled.
     *
     * @param g any graphics object
     */
    @Override
    public void draw(Graphics g)
    {
        drawCentered(g, control.loadImage("asteroid.png"), location.x, location.y, 1);
    }

    /**
     * This method accesses the location of the asteroid object.
     *
     * @return location of object as a point.
     */
    @Override
    public Point getLocation() {
        return location;
    }

    /**
     * This method accesses the size of the asteroid object.
     *
     * @return the asteroid's size as an integer
     */
    public int getSize()
    {
        return control.loadImage("asteroid.png").getWidth();
    }
}
