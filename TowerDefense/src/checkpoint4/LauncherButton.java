package checkpoint4;

import java.awt.*;

/**
 * This class represents the button that contains the Probe defense object
 * for Tower Defense. If clicked, a new Launcher object will follow the cursor
 * until clicked at a certain point in the game.
 *
 * @author Tyler Zoutte and Mikhail Ahmed
 * @version April 25, 2023
 */
public class LauncherButton extends GameObject
{
    private Control control;
    private GameState state;

    /**
     * This constructor creates Launcher Button objects.
     *
     * @param control Control type
     * @param state GameState type
     */
    public LauncherButton(Control control, GameState state)
    {
        super();
        this.control = control;
        this.state = state;
    }

    @Override
    public void update(double elapsedTime) {

    }

    /**
     * This method draws the launcher image.
     *
     * @param g Graphics object
     */
    @Override
    public void draw(Graphics g)
    {
        // Loads the launcher image from file
        drawCentered(g, control.loadImage("launcher.png"), 700, 325, 0.1);;
    }

    /**
     * This method allows a new Launcher object to be added into Tower Defense iff the mouse click
     * is clicked in the Launcher's button, or the object will not be added if not clicked precisely.
     *
     * @return true or false
     */
    @Override
    public boolean consumeClick()
    {
        int halfImageWidth = (int) (control.loadImage("launcher.png").getWidth() * scale / 2);
        int halfImageHeight = (int) (control.loadImage("launcher.png").getHeight() * scale / 2);
        if (state.getMouseLocation().x > 700 - halfImageWidth && state.getMouseLocation().x < 700 + halfImageHeight &&
            state.getMouseLocation().y > 325 - halfImageHeight && state.getMouseLocation().y < 325 + halfImageHeight
            && state.getMoney() >= 1000000)
        {
            state.addGameObject(new Launcher(control, state));
            state.changeMoney(-750000);
            return true;
        }
        else
            return false;
    }
}
