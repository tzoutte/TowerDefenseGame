package checkpoint4;

import java.awt.*;

/**
 * This class represents the Launcher defense object used for the game.
 *
 * @author Tyler Zoutte and Mikhail Ahmed
 * @version April 25, 2023
 */
public class Launcher extends GameObject
{
    // Declaring instance variables
    private Control control;
    private GameState state;
    private int x, y;
    private boolean isMoving;
    private Point location;
    private double countdown;

    /**
     * This constructor allows Launcher objects to be created.
     *
     * @param control Control type
     * @param state GameState type
     */
    public Launcher(Control control, GameState state)
    {
        super();
        this.control = control;
        this.state = state;
        this.location = location;
        this.x = x;
        this.y = y;
        this.isMoving = true;
        countdown = 3;
    }

    /**
     * This method allows the Launcher object's location to update with the mouse's
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
            // Launcher's location are equal to mouse's location
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
                    state.addGameObject(new Missile(control, state, target, location));
                    countdown = 3;
                }
            }
        }

        // Expires the launcher object by this condition
        if (this.x >= 795 || this.x <= 5 || this.y >= 595 || this.y <= 5)
        {
            state.changeMoney(750000);
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
        drawCentered(g, control.loadImage("launcher.png"), x, y, 0.1);
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
        int halfImageWidth = (int) (control.loadImage("launcher.png").getWidth() * scale / 2);
        int halfImageHeight = (int) (control.loadImage("launcher.png").getHeight() * scale / 2);

        // Only consume the mouse click if the object is moving
        if (isMoving == true && Planets.isInside(this.x, this.y) == true && this.x < 600)
        {
            isMoving = false;
            return true;
        }
        // Else when object isn't moving, it won't consume a mouse click
        else if (state.getMouseLocation().x > this.x - halfImageWidth && state.getMouseLocation().x < this.x + halfImageHeight &&
                state.getMouseLocation().y > this.y - halfImageHeight && state.getMouseLocation().y < this.y + halfImageHeight)
        {
            isMoving = true;
            return true;
        }
        else
            return false;
    }
}
