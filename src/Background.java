package checkpoint4;

import java.awt.*;

/**
 * Objects from this class represent backgrounds, which are animatable.
 */
public class Background extends GameObject
{
    private Control control;
    private GameState state;

    /**
     * Constructs a background object with control and gamestate objects.
     *
     * @param control any control object
     * @param state any gamestate object
     */
    public Background(Control control, GameState state)
    {
        super();
        this.control = control;
        this.state = state;
    }

    @Override
    public void update(double elapsedTime)
    {
    }

    /**
     * Loads an image from a file and draws it.
     *
     * @param g any graphics object
     */
    @Override
    public void draw(Graphics g)
    {
        g.drawImage(control.loadImage("background.png"), 0, 0, null);
    }
}
