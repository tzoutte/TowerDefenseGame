package checkpoint4;

import java.awt.*;

/**
 * Classes implementing this interface represent animatables objects.
 */
public interface Animatable
{
    // Make animatable objects able to be updated and drawn.
    public void update(double elapsedTime);
    public void draw(Graphics g);
}
