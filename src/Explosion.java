package checkpoint4;

import java.awt.*;

public class Explosion extends GameObject
{
    private Control control;
    private GameState state;
    private int x;
    private int y;
    private double scale;
    private double age;
    private double maxScale;

    /**
     * This constructor allows Explosion objects to be created.
     *
     * @param control Control type
     * @param state Gamestate type
     * @param x integer used for location
     * @param y integer used for location
     */
    public Explosion(Control control, GameState state, int x, int y)
    {
        super();
        this.control = control;
        this.state = state;
        this.x = x;
        this.y = y;
    }

    @Override
    public void update(double elapsedTime)
    {
        // Ages the explosion with a scale and age factor
        age += elapsedTime;
        if (age < 0.5)
        {
            scale = 0.1 + age * 0.1;
        }
        else
            hasExpired = true;
    }

    /**
     * This method draws the explosion image
     *
     * @param g Graphics object
     */
    @Override
    public void draw(Graphics g)
    {
        drawCentered(g, control.loadImage("explosion.png"), x, y, scale);
    }
}
