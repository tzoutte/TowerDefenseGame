package checkpoint4;

import java.awt.*;

/**
 * This class is responsible for generating a certain amount
 * of comet objects for a given number of seconds.
 *
 * @author Tyler Zoutte and Mikhail Ahmed
 * @version April 18, 2023
 */
public class CometGenerator extends GameObject
{
    private GameState state;
    private Control control;
    public CometGenerator(Control control, GameState state)
    {
        this.control = control;
        this.state = state;
    }

    private double countdown = 4.0;
    private int cometCount = 0;
    private double playTime = 0;
    private int difficulty = 0;

    @Override
    public void update(double elapsedTime)
    {
        // Difficulty increase if time played is 30 or more seconds
        playTime += elapsedTime;
        if (playTime >= 30)
        {
            difficulty++;
            playTime = 0;
        }
        countdown -= elapsedTime;

        // Creating new comet objects
        if (countdown <= 0)
        {
            state.addGameObject(new Comet(control, state));
            cometCount++;
            if (cometCount == 2)
            {
                // Difficulty as a parameter of countdown
                if (difficulty <= 35)
                {
                    countdown = 4.0 - (difficulty * 0.1);
                }
                else countdown = 0.5;
                cometCount = 0;
            }
            else
                countdown = 0.5;
        }
    }

    @Override
    public void draw(Graphics g) {

    }
}
