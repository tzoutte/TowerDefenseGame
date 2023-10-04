package checkpoint4;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Objects in the class represent animatables in the current and next game state.
 *
 * @author Tyler Zoutte and Mikhail Ahmed
 * @version April 4, 2023
 */
public class GameState
{
    // Initializing instance variables
    private List<GameObject> currentFrameObjects;
    private List<GameObject> nextFrameObjects;
    private long money = 750000;
    private long citizens = 8000000000l;

    private int mouseX;
    private int mouseY;
    private double elapsedTime;
    private long lastFrameStartTime;
    private boolean gameOver;

    /**
     * Construct a game state object with a list of current animatables.
     */
    public GameState()
    {
        currentFrameObjects = new ArrayList<GameObject>();
        this.citizens = citizens;
        this.money = money;
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.elapsedTime = elapsedTime;
        lastFrameStartTime = System.currentTimeMillis();
        gameOver = false;
    }

    /**
     * Returns a list of objects in the current game state.
     *
     * @return list of animatables
     */
    public List<GameObject> getCurrentObjects()
    {
        return currentFrameObjects;
    }

    /**
     * This method accesses the money in the Tower Defense game.
     *
     * @return money or game currency.
     */
    public long getMoney()
    {
        return money;
    }

    /**
     * This method accesses the amount or lives of citizens in Tower Defense.
     *
     * @return citizen lives in game.
     */
    public long getCitizens()
    {
        return citizens;
    }

    /**
     * This method converts money to String.
     *
     * @return String version of money
     */
    public String moneyString()
    {
        return "$ " + this.money;
    }

    /**
     * this methods converts citizen lives to String.
     *
     * @return String version of citizens
     */
    public String citizensString()
    {
        return this.citizens + " citizens";
    }

    /**
     * This mutator allows the amount of money to be changed.
     *
     * @param i adjusts current amount.
     */
    public void changeMoney(int i)
    {
        this.money += i;
    }

    /**
     * This method changes the amount of citizen lives.
     *
     * @param i adjusts current amount
     */
    public void changeCitizens(int i)
    {
        this.citizens += i;
        if (citizens <= 0)
            gameOver = true;
    }

    /**
     * This method returns a boolean for whether
     * the game is over or not
     *
     * @return true or false
     */
    public boolean getGameStatus()
    {
        return gameOver;
    }

    public double getElapsedTime()
    {
        return elapsedTime;
    }
    /**
     * This mutator changes the mouse's location.
     *
     * @param x position in x direction
     * @param y position in y direction
     */
    public void setMouseLocation(int x, int y)
    {
        this.mouseX = x;
        this.mouseY = y;
    }

    /**
     * This accessor method returns a Point located at the
     * mouse's position.
     *
     * @return a Point type.
     */
    public Point getMouseLocation()
    {
        // Point with location of mouse's x and y
        return new Point(this.mouseX, this.mouseY);
    }

    /**
     * Creates a new list of the objects for the next frame of the game
     * that is a copy of the current frame objects.
     */
    public void startFrame()
    {
        long currentFrameStartTime = System.currentTimeMillis();
        elapsedTime = (currentFrameStartTime - lastFrameStartTime) / 1000.0;
        lastFrameStartTime = currentFrameStartTime;
        nextFrameObjects = new ArrayList<GameObject>();
        nextFrameObjects.addAll(currentFrameObjects);
    }

    /**
     * Overwrites the list of current frame objects with the next frame objects.
     */
    public void finishFrame()
    {
        // Loop through every game object and removes expired objects
        for (GameObject g : currentFrameObjects)
        {
            if (g.hasExpired() == true)
                nextFrameObjects.remove(g);
        }
        // currentFrameObjects is updated
        currentFrameObjects = nextFrameObjects;
        nextFrameObjects = null;
    }

    /**
     * Adds an animatable to the list of next frame objects.
     *
     * @param a any animatable
     */
    public void addGameObject(GameObject a)
    {
        nextFrameObjects.add(a);
    }

    public Targetable findNearest(Point p)
    {
        Targetable nearest = null;
        outer:
        // Looping through all objects in current frame
        for (int i = 0; i < currentFrameObjects.size(); i++)
        {
            // Finding Targetable types
            if (currentFrameObjects.get(i) instanceof Targetable)
            {
                nearest = (Targetable) currentFrameObjects.get(i);
                break outer;
            }
        }

        for (GameObject g : currentFrameObjects)
        {
            if (nearest != null && g instanceof Targetable)
            {
                if (p.distance(((Targetable) g).getLocation()) < p.distance(nearest.getLocation()))
                    nearest = (Targetable) g;
            }
        }
        return nearest;
    }
}
