package checkpoint4;

import java.awt.*;

/**
 * This class is responsible for generating a certain amount
 * of asteroid objects for a given number of seconds.
 *
 * @author Tyler Zoutte and Mikhail Ahmed
 * @version April 18, 2023
 */
public class AsteroidGenerator extends GameObject
{
    private GameState state;
    private Control control;
    public AsteroidGenerator(Control control, GameState state)
    {
        this.control = control;
        this.state = state;
    }

    private double countdown = 2.0;
    private int asteroidCount = 0;
    private double playTime = 0;
    private int difficulty = 0;

    /**
     * This method updates the amount of asteroid objects
     * in the game, and also increases difficulty setting.
     *
     * @param elapsedTime time
     */
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

        // Generates asteroids
        if (countdown <= 0)
        {
            state.addGameObject(new Asteroid(control, state));
            asteroidCount++;

            if (asteroidCount == 4)
            {
                // Difficulty as a parameter of countdown
                if (difficulty <= 15)
                    countdown = 2.0 - (difficulty * 0.1);
                else countdown = 0.5;
                asteroidCount = 0;
            }
            else
                countdown = 0.5;
        }
    }

    @Override
    public void draw(Graphics g) {

    }
}
