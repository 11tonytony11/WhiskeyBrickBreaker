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
     * This function ...
     * Input:
     * Output:
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
     * This function ...
     * Input:
     * Output:
    /* ------------------------------------------------------------------ */
    public void draw(Graphics g)
    {
        g.drawImage(img, x,y, width,height,null);
    }
    /* ------------------------------------------------------------------
     * This function ...
     * Input:
     * Output:
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
                BallsAndBoxesPanel.score++;
                BallsAndBoxesPanel.scoreGUI.setText("Score: " + String.valueOf(BallsAndBoxesPanel.score));

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
     * This function ...
     * Input:
     * Output:
    /* ------------------------------------------------------------------ */
    public boolean BombIntersectsRacket(Racket r)
    {
        return ((this.x >= r.x && this.x <= r.x + this.width) && (this.y >= r.y));
    }
    /* ------------------------------------------------------------------
     * This function ...
     * Input:
     * Output:
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