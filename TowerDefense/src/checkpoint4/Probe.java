package checkpoint4;

import java.awt.*;

/**
 * This class represents the Probe defense object used for the game.
 *
 * @author Tyler Zoutte and Mikhail Ahmed
 * @version April 18, 2023
 */
public class Probe extends GameObject
{
    // Declaring instance variables
    private Control control;
    private GameState state;
    private int x, y;
    private boolean isMoving;
    private Point location;
    private double countdown;

    /**
     * THis constructor allows Probe objects to be created.
     *
     * @param control Control type
     * @param state GameState type
     */
    public Probe(Control control, GameState state)
    {
        super();
        this.control = control;
        this.state = state;
        this.location = location;
        this.x = x;
        this.y = y;
        this.isMoving = true;
        countdown = 1.0;
    }

    /**
     * This method allows the Probe object's location to update with the mouse's
     * location, and expires if out of bounds.
     *
     * @param elapsedTime time that has passed
     */
    @Override
    public void update(double elapsedTime)
    {
        location = new Point(this.x, this.y);
        countdown -= elapsedTime;

        if (isMoving == true)
        {
            // Probe's location are equal to mouse's location
            x = state.getMouseLocation().x;
            y = state.getMouseLocation().y;
        }
        else
        {
            Targetable target = state.findNearest(location);
            if (target != null && location.distance(target.getLocation()) <= 125)
            {
                if (countdown <= 0)
                {
                    state.addGameObject(new Photon(control, state, target, location));
                    countdown = 1.0;
                }
            }
        }
        if (this.x >= 795 || this.x <= 5 || this.y >= 595 || this.y <= 5)
        {
            state.changeMoney(500000);
            hasExpired = true;
        }
    }

    /**
     * This method draws the Probe image.
     *
     * @param g Graphics object
     */
    @Override
    public void draw(Graphics g)
    {
        drawCentered(g, control.loadImage("probe.png"), x, y, 1);
    }

    /**
     * This specific method allows the mouse click to be consumed iff the object is moving,
     * and will not consume a mouse click once placed.
     *
     * @return true or false
     */
    @Override
    public boolean consumeClick()
    {
        int halfImageWidth = (int) (control.loadImage("probe.png").getWidth() * scale / 2);
        int halfImageHeight = (int) (control.loadImage("probe.png").getHeight() * scale / 2);

        // Only consume the mouse click if the object is moving
        if (isMoving == true  &&  Planets.isInside(this.x, this.y) == false && this.x < 600)
        {
            isMoving = false;
            return true;
        }
        else if (state.getMouseLocation().x > this.x - halfImageWidth && state.getMouseLocation().x < this.x + halfImageHeight &&
                state.getMouseLocation().y > this.y - halfImageHeight && state.getMouseLocation().y < this.y + halfImageHeight)
        {
            isMoving = true;
            return true;
        }
        // Else when object isn't moving, it won't consume a mouse click
        else
            return false;
    }
}
