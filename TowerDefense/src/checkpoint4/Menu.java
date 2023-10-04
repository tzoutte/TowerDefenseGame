package checkpoint4;

import java.awt.*;

/**
 * Objects in this class represent a menu, which are game objects.
 */
public class Menu extends GameObject
{
    private Control control;
    private GameState state;

    /**
     * Constructs a menu object with control and gamestate objects.
     *
     * @param control any control object
     * @param state and gamestate object
     */
    public Menu(Control control, GameState state)
    {
        super();
        this.control = control;
        this.state = state;
    }

    @Override
    public void update(double elapsedTime) {

    }

    /**
     * This method illustrates the Tower Defense Menu on
     * the right side of the window with the game's currency and citizen's lives.
     *
     * @param g graphics type.
     */
    @Override
    public void draw(Graphics g)
    {
        // Draw the menu, title, money count and citizens count.
        g.setColor(Color.GRAY);
        g.fillRect(600, 0, 200, 600);
        g.setColor(Color.RED);
        g.setFont(new Font("ARIAL", Font.BOLD, 25));
        g.drawString("TD Menu", 655, 75);
        g.setColor(Color.GREEN);
        g.setFont(new Font("ARIAL", Font.PLAIN, 15));
        g.drawString(state.moneyString(), 660, 150);
        g.setColor(Color.YELLOW);
        if (state.getCitizens() > 0)
            g.drawString(state.citizensString(), 635, 200);
        else g.drawString("0 citizens", 690, 200);
        g.setColor(Color.BLACK);
        g.drawString("$500,000", 665, 525);
        g.drawString("$1,000,000", 660, 375);
    }
}
