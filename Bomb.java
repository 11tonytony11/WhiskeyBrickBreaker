import java.util.Random;
import javax.swing.*;
import java.awt.*;

public class Bomb extends Thread
{
    BallsAndBoxesPanel panel;
    int x, y, width, height;
    AePlayWave voice;
    Random random;
    ImageIcon ii;
    String type;
    Image img;

    /* ------------------------------------------------------------------
     * This function is the Bomb c'tor
     * Input:  game panel, bomb type
     * Output: None
    /* ------------------------------------------------------------------ */
    public Bomb(BallsAndBoxesPanel p, String newType)
    {
        this.random = new Random();

        this.type = newType;
        this.height = 48;
        this.width  = 35;
        this.panel  = p;

        this.x = random.nextInt(500);
        this.y = 210;

        if(type.equals("bomb"))
        {
            this.ii = new ImageIcon("bomb.png");
        }
        else
        {
            this.ii = new ImageIcon("powerup.jpg");
        }

        this.img = ii.getImage();

    }
    /* ------------------------------------------------------------------
     * This function draws elements to screen
     * Input:  software graphics
     * Output: None
    /* ------------------------------------------------------------------ */
    public void draw(Graphics g)
    {
        g.drawImage(img, x,y, width,height,null);
    }
    /* ------------------------------------------------------------------
     * This function is handling the bomb behavior
     * Input:  None
     * Output: None
    /* ------------------------------------------------------------------ */
    public void run()
    {
        while (true)
        {
            y += 1;

            if(BombIntersectsRacket(panel.racket) && this.type.equals("bomb"))
            {
                voice = new AePlayWave("alert.wav");
                voice.start();

                JOptionPane.showMessageDialog(null, "You lost", "Game over", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
            if(BombIntersectsRacket(panel.racket) && this.type.equals("powerup"))
            {
                this.panel.incScore();
                this.resetBomb();
            }
            else if(this.y + height >= panel.getHeight() && panel.getHeight() > 0)
            {
                this.resetBomb();
            }

            try
            {
                Thread.sleep(20);
            }
            catch (InterruptedException ignored) {}

        }
    }
    /* ------------------------------------------------------------------
     * This function checks if the bomb intersects with the racket
     * Input:  Racket
     * Output: True - if intersects
    /* ------------------------------------------------------------------ */
    public boolean BombIntersectsRacket(Racket r)
    {
        return ((this.x >= r.x && this.x <= r.x + r.w) && (this.y + this.height >= r.y));
    }
    /* ------------------------------------------------------------------
     * This function resets the bomb location
     * Input:  None
     * Output: None
    /* ------------------------------------------------------------------ */
    private void resetBomb()
    {
        this.y = 210;
        this.x = random.nextInt(500);

        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException ignored) {}
    }
}
