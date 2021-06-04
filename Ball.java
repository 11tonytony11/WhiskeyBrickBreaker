import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ball extends Thread implements ActionListener
{
	BallsAndBoxesPanel panel;
	int x, y, width;
	int dirx, diry;
    Color color;

    Timer timer;

	public Ball(int x, int y, int width, Color color, BallsAndBoxesPanel p)
	{
		this.color = color;
		this.width = width;
		this.panel = p;
		this.x = x;
		this.y = y;
		this.timer = new Timer(5, this);
		timer.start();
	}
	
	public void draw(Graphics g)
	{
		g.setColor(color);
		g.fillOval(x - width / 2,y - width / 2,width,width);
	}
	
	public void run()
	{
	    dirx = 1;
	    diry = 1;

		while (true) 
		{
			int h = panel.getHeight();
			int w = panel.getWidth();
			
			// Direction change
			if(h > 0)
			{
				if (x + width/2  > w)
						dirx = -1;

				if (x - width / 2  < 0)
						dirx = 1;

				if (y + width / 2  > h)
						diry = -1;

				if (y - width / 2 < 0)
						diry = 1;

				x += dirx;
				y += diry;

				if(y + width / 2  >= h)
				{
					JOptionPane.showMessageDialog(null, "You lost", "Game over", JOptionPane.INFORMATION_MESSAGE);
					System.exit(0);
				}

				try
				{
					Thread.sleep(5);
				}
				catch (InterruptedException ignored) {}
			}

			// NEVER EVER DELETE THIS IS WHAT MAKES THE CODE WORK AND I DON'T KNOW WHY
			System.out.print("");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.panel.repaint();
	}
}