import javax.swing.*;
import java.awt.*;

public class Box extends Thread
{
	BallsAndBoxesPanel panel;
	int x, y, width, height;
	ImageIcon ii;
	Image img;

	public Box(int x,int y,int width,int height, BallsAndBoxesPanel p)
	{
		this.height = height;
		this.width  = width;
		this.panel  = p;
		this.x = x;
		this.y = y;

		this.ii  = new ImageIcon("box.jpg");
		this.img = ii.getImage();
	}

	public void draw(Graphics g)
	{
		g.drawImage(img, x,y, width,height,null);
	}

	public void run()
	{
		while (true) 
		{
			if(BoxIntersectsBall(panel.ball))
			{
				panel.ball.diry*=-1;
				int deltaX = 1;

				if(panel.ball.x + panel.ball.width / 2 - deltaX <= x )
				{
					panel.ball.dirx *= -1;
					panel.ball.diry *= -1;
				}

				if(panel.ball.x - panel.ball.width / 2 + deltaX >= x + width )
				{
					panel.ball.dirx *= -1;
					panel.ball.diry *= -1;
				}

				break;
			}
			try
			{
				Thread.sleep(3);
			}
			catch (InterruptedException ignored) {}
		}	
	}

	public  boolean  BoxIntersectsBall ( Ball b)
	{
		Rectangle coverBox;
		
		int x1 = x - b.width / 2;
		int y1 = y - b.width / 2;
		int w1 = width+b.width;
		int h1 = height+b.width;
		
		coverBox = new Rectangle(x1,y1,w1,h1);

		return coverBox.contains(b.x, b.y);
	}
}