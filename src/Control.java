package checkpoint4;

import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.TreeMap;

/**
 * This program sets up and controls the game play and
 * animations for Tower Defense.
 *
 * @author Tyler Zoutte and Mikhail Ahmed
 * @version April 4, 2023
 */
public class Control implements Runnable, ActionListener, MouseListener, MouseMotionListener
{
    private GameState state;
    private View view;
    private Path path;
    private Timer timer;
    private TreeMap map;
    private boolean mousePressed;

    /**
     * This constructor allows the user to instantiate
     * new Control objects/variables.
     *
     */
    public Control()
    {
        SwingUtilities.invokeLater(this);
    }

    /**
     * This run method allows the game's commencement
     * with a path read from file, Gamestate, View,
     * background, asteroid, comet, menu and menu button objects,
     * along with a timer, mouse listener and mouse action listener.
     *
     */
    @Override
    public void run()
    {
        path = new Path("space_path_1.txt");
        state = new GameState();
        view = new View(this, state);
        map = new TreeMap<String, BufferedImage>();
        mousePressed = false;

        view.addMouseListener(this);
        view.addMouseMotionListener(this);

        state.startFrame();

        state.addGameObject(new Planets(this, state));
        state.addGameObject(new Background(this, state));
        state.addGameObject(new Menu(this, state));
        state.addGameObject(new ProbeButton(this, state));
        state.addGameObject(new LauncherButton(this, state));
        state.addGameObject(new AsteroidGenerator(this, state));
        state.addGameObject(new CometGenerator(this, state));
        state.finishFrame();

        timer = new Timer(16, this);
        timer.start();
    }

    /**
     * This method returns the set Path.
     *
     * @return a Path type
     */
    public Path getPath()
    {
        return path;
    }

    /**
     * This method loads the background image for Tower Defense.
     *
     * @param filename any given file
     * @return the background image iff not null
     */
    public BufferedImage loadImage(String filename)
    {
        // If the map doesn't contain the key, load the image and add it to the map.
        if (!map.containsKey(filename))
        {
            try
            {
                map.put(filename, javax.imageio.ImageIO.read(new File(filename)));
                return (BufferedImage) map.get(filename);
            }
            catch (IOException e)
            {
                System.out.println("Could not read " + filename);
                return null;
            }
        }
        // Otherwise retrieve the image from the map.
        else
            return (BufferedImage) map.get(filename);
    }

    /**
     * This method updates the background after
     * every change the user makes.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        // Between state frame and finish frame, loop through the game objects and update them. If mouse is pressed loop
        // through again and give each object a chance to consume the click. If the click is consumed break.
        state.startFrame();
        if (state.getGameStatus() == false) {
            for (GameObject a : state.getCurrentObjects())
                a.update(state.getElapsedTime());
            if (mousePressed == true) {
                for (GameObject a : state.getCurrentObjects())
                    if (a.consumeClick() == true)
                        break;
            }
            mousePressed = false;
        }
        else
        {
            state.addGameObject(new GameOver());
        }
        state.finishFrame();
        view.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * This method gets the location of where the mouse
     * has been pressed assuming that the mouse has been pressed.
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e)
    {
        // Get location of mouse presses.
        mousePressed = true;
        state.setMouseLocation(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * This method sets the mouse's location to where it has
     * been dragged.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseDragged(MouseEvent e)
    {
        // Get mouse location when mouse is dragged.
        state.setMouseLocation(e.getX(), e.getY());
    }

    /**
     * This method sets the mouse's location to where it has just
     * been moved.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseMoved(MouseEvent e)
    {
        // Get mouse location when mouse is moved.
        state.setMouseLocation(e.getX(), e.getY());
    }
}
