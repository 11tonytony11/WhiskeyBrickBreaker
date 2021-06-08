import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.awt.*;


public class BallsAndBoxesPanel extends JPanel
{
	public static int score = 0;
	public static JLabel scoreGUI = new JLabel("Score: " + String.valueOf(score), SwingConstants.CENTER);

	Racket racket;
	Bomb bomb;
	Box [][] mat;
	Ball ball;

	public BallsAndBoxesPanel()
	{
		ball   = new Ball(300,400,10,Color.RED,this);
		racket = new Racket(this);
		mat    = new Box[5][7];
		bomb   = new Bomb(this);


		for(int i = 0; i < mat.length; i++)
		{
			for(int j = 0 ; j < mat[i].length; j++)
			{
				mat[i][j] = new Box (j * 80 + 20,i * 50 + 20,50,20,this);
			}
		}
		
		ball.start();

		for (Box[] boxes : mat)
		{
			for (Box box : boxes)
			{
				box.start();
			}
		}

		racket.start();
		bomb.start();

		addMouseMotionListener(new MML());
		setBackground(Color.WHITE);
	}
	
	public void paintComponent(Graphics g)
	{
	    super.paintComponent(g);
		ball.draw(g);
		bomb.draw(g);

		for (Box[] boxes : mat)
		{
			for (Box box : boxes)
			{
				if (box.isAlive())
				{
					box.draw(g);
				}
			}
		}
		racket.drawRacket(g);
	}

	class MML extends MouseMotionAdapter 
	{
		public void mouseMoved(MouseEvent e)
		{
			if(!racket.isPaused)
			{
				if( e.getX()<getWidth() - racket.w)
				{
					racket.x = e.getX();
				}
			}
		}	
	}

	public static void main(String[] args) 
	{
		JFrame f = new JFrame("Whiskey Breaker Alpha MS(c)");
		BallsAndBoxesPanel bbp = new BallsAndBoxesPanel();


		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(scoreGUI, BorderLayout.NORTH);
		scoreGUI.setFont(new Font("Arial", Font.ITALIC, 24));
		f.setSize(600,730);
		f.setResizable(false);
		f.setVisible(true);
		f.add(bbp, BorderLayout.CENTER);
	}
}

