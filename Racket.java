import javax.swing.*;
import java.awt.*;

public class Racket extends Thread
{
	BallsAndBoxesPanel panel;
	Image racketImage;
	
	int x = 250, y = 600;
	int w = 120, h = 50;
	/* ------------------------------------------------------------------
 	 * This function is the racket c'tor
 	 * Input:  game panel
 	 * Output: None
	/* ------------------------------------------------------------------ */
	public Racket(BallsAndBoxesPanel panel)
	{
		this.panel = panel;
		this.racketImage = new ImageIcon("racket.png").getImage();
	}
	/* ------------------------------------------------------------------
 	 * This function is handling the Racket behavior
 	 * Input:  None
 	 * Output: None
	/* ------------------------------------------------------------------ */
	public void run()
	{
		while(true)
		{
			if(racketIntersectsBall(panel.ball))
			{
				panel.ball.diry *= -1;
				int deltaX = 1;

				if(panel.ball.x + panel.ball.width / 2 - deltaX <= x)
				{
					panel.ball.dirx *= -1;
					panel.ball.diry *= -1;
				}

				if(panel.ball.x - panel.ball.width / 2 + deltaX >= x + w + w)
				{
					panel.ball.dirx *= -1;
					panel.ball.diry *= -1;
				}
			}

			try
			{
				Thread.sleep(100);
			}
			catch (InterruptedException ignored) {}
		}	
	}
	/* ------------------------------------------------------------------
 	 * This function draws the racket
 	 * Input:  software graphics
 	 * Output: None
	/* ------------------------------------------------------------------ */
	public void drawRacket(Graphics g)
	{
		g.drawImage(racketImage, x,y, w,h,null);
	}
	/* ------------------------------------------------------------------
 	 * This function checks if the ball intersects with the racket
 	 * Input:  game ball
 	 * Output: True - if intersects
	/* ------------------------------------------------------------------ */
	public boolean racketIntersectsBall(Ball b)
	{
		Rectangle coverBox;

		int x1 = x - b.width / 2;
		int y1 = y - b.width / 2;

		int w1 = w + b.width;
		int h1 = h + b.width;

		coverBox = new Rectangle(x1,y1,w1,h1);

		return coverBox.contains(b.x, b.y);
	}
}
