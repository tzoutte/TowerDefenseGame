package checkpoint4;

import java.awt.*;

/**
 * This class represents the button that contains the Probe defense object
 * for Tower Defense. If clicked, a new Probe object will follow the cursor
 * until clicked at a certain point in the game.
 *
 * @author Tyler Zoutte and Mikhail Ahmed
 * @version April 18, 2023
 */
public class ProbeButton extends GameObject
{
    private Control control;
    private GameState state;

    /**
     * This constructor creates Probe Button objects.
     *
     * @param control Control type
     * @param state GameState type
     */
    public ProbeButton(Control control, GameState state)
    {
        super();
        this.control = control;
        this.state = state;
    }

    @Override
    public void update(double elapsedTime) {

    }

    /**
     * This method draws the probe image.
     *
     * @param g Graphics object
     */
    @Override
    public void draw(Graphics g)
    {
        // Loads the probe image from file
        drawCentered(g, control.loadImage("probe.png"), 700, 475, 1);
    }

    /**
     * This method allows a new Probe object to be added into Tower Defense iff the mouse click
     * is clicked in the probe's button, or the object will not be added if not clicked precisely.
     *
     * @return true or false
     */
    @Override
    public boolean consumeClick()
    {
        int halfImageWidth = (int) (control.loadImage("probe.png").getWidth() * scale / 2);
        int halfImageHeight = (int) (control.loadImage("probe.png").getHeight() * scale / 2);
        if (state.getMouseLocation().x > 700 - halfImageWidth && state.getMouseLocation().x < 700 + halfImageHeight &&
            state.getMouseLocation().y > 475 - halfImageHeight && state.getMouseLocation().y < 475 + halfImageHeight
            && state.getMoney() >= 500000)
        {
            state.addGameObject(new Probe(control, state));
            state.changeMoney(-500000);
            return true;
        }
        else
            return false;
    }
}
