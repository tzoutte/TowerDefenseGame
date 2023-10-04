package checkpoint4;

import java.awt.*;

/**
 * Classes implementing this interface represent targetable objects such as asteroids and comets.
 *
 */
public interface Targetable
{
    // Methods for targetable objects
    public Point getLocation();

    public void setExpired(boolean b);
    public int getSize();
}
