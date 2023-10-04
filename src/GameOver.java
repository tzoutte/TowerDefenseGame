package checkpoint4;

import java.awt.*;

/**
 * This class is used for the user's loss in Tower Defense,
 * known as Game Over.
 *
 */
public class GameOver extends GameObject
{
    @Override
    public void update(double elapsedTime) {

    }

    /**
     * This method draws the Game Over Screen with
     * a red, bold font.
     *
     * @param g Graphics object
     */
    @Override
    public void draw(Graphics g)
    {
        g.setColor(Color.RED);
        g.setFont(new Font("ARIAL", Font.BOLD, 75));
        g.drawString("GAME OVER", 75, 340);
    }
}
