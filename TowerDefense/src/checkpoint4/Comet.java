package checkpoint4;

import java.awt.*;

/**
 * Objects in this class represent comets, which are game objects.
 */
public class Comet extends GameObject implements Targetable
{
    private Control control;
    private GameState state;
    private double percentage;
    private Point location;

    /**
     * Constructs a comet object with control and gamestate objects.
     *
     * @param control any control object
     * @param state and gamestate object
     */
    public Comet(Control control, GameState state)
    {
        super();
        this.control = control;
        this.state = state;
        this.location = new Point(800, 800);
        percentage = 0;
    }

    /**
     * Updates the percent the comet has traveled based on time elapsed.
     *
     * @param elapsedTime any double
     */
    @Override
    public void update(double elapsedTime)
    {
        // comet's travel
        location = control.getPath().convertToCoordinates(percentage);
        percentage += (1.0/15.0) * elapsedTime;

        // If comet completes path, it causes an explosion on Earth and takes lives
        if (percentage >= 1)
        {
            this.hasExpired = true;
            state.addGameObject(new Explosion(control, state, location.x, location.y));
            state.changeCitizens(-206007601);
        }
    }

    /**
     * Draws the comet and updates its location based on the percent it has traveled.
     *
     * @param g any graphics object
     */
    @Override
    public void draw(Graphics g)
    {
        drawCentered(g, control.loadImage("comet.png"), location.x, location.y, 1);
    }

    /**
     * This method accesses the location of the comet object.
     *
     * @return the location as a point
     */
    @Override
    public Point getLocation() {
        return location;
    }

    /**
     * This method accesses the size of the comet object.
     *
     * @return the size as an integer
     */
    public int getSize()
    {
        return control.loadImage("comet.png").getWidth();
    }
}
