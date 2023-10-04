package checkpoint4;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * This class is responsible for the state of the objects
 * in the game, such as alive or expired, clicking on certain
 * game objects, and how the objects move along in the game.
 *
 */
public abstract class GameObject implements Animatable
{
    protected GameObject gameObject;
    protected boolean hasExpired;
    protected double scale;

    /**
     * This constructor creates Game Object types.
     *
     */
    public GameObject()
    {
        this.hasExpired = false;
        this.gameObject = gameObject;
    }

    /**
     * This method returns a hasExpired state for Game Object types.
     *
     * @return hasExpired state for object
     */
    public boolean hasExpired()
    {
        return hasExpired;
    }

    public void setExpired(boolean b)
    {
        this.hasExpired = b;
    }

    /**
     * This method allows for the objects that follow the path to
     * follow centered.
     *
     * @param k Graphics object
     * @param i Image object
     * @param x int for x position
     * @param y int for y position
     */
    public void drawCentered(Graphics k, BufferedImage i, int x, int y, double scale)
    {
        this.scale = scale;
        // Calculating centered x and y and drawing at the center position.
        int width = (int) (i.getWidth() * scale);
        int height = (int) (i.getHeight() * scale);
        int newX = x - width / 2;
        int newY = y - height / 2;
        k.drawImage(i, newX, newY, width, height,null);

    }

    /**
     * This method by default returns false for Game Object types.
     *
     * @return false
     */
    public boolean consumeClick()
    {
        return false;
    }
}
