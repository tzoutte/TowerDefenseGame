package checkpoint4;

import javax.swing.*;
import java.awt.*;

/**
 * This class extends the JPanel which creates
 * the window for the Tower Defense game.
 *
 */
public class View extends JPanel
{
    // Initializing instance variables
    private Control control;
    private GameState state;
    public View(Control control, GameState state)
    {
        this.control = control;
        this.state = state;

        // Assigning JFrame with title
        JFrame frame = new JFrame("Tower Defense");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(this);

        // Setting Dimensions for the window
        this.setMinimumSize(new Dimension(800, 600));
        this.setPreferredSize(new Dimension(800, 600));
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * This method paints animatables that are currently in the game
     * as well as the set Path for the Tower Defense game.
     *
     * @param g  the <code>Graphics</code> context in which to paint
     */
    @Override
    public void paint(Graphics g)
    {
        // Drawing current game objects
        for (Animatable a : state.getCurrentObjects())
            a.draw(g);
        repaint();
    }
}
