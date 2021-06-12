import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.awt.*;

public class BallsAndBoxesPanel extends JPanel
{
	int score = 0;
	JLabel scoreGUI = new JLabel("Score: " + score, SwingConstants.CENTER);

	Racket racket;
	Bomb powerUp;
	Box[][] mat;
	Bomb bomb;
	Ball ball;

	/* ------------------------------------------------------------------
     * This function is the panel c'tor
     * Input:  None
     * Output: None
    /* ------------------------------------------------------------------ */
	public BallsAndBoxesPanel()
	{
		this.ball    = new Ball(300,400,10,Color.RED,this);
		this.powerUp = new Bomb(this, "powerup");
		this.bomb    = new Bomb(this, "bomb");
		this.racket  = new Racket(this);
		this.mat     = new Box[5][7];

		for(int i = 0; i < mat.length; i++)
		{
			for(int j = 0 ; j < mat[i].length; j++)
			{
				mat[i][j] = new Box (j * 80 + 20,i * 50 + 20,50,20,this);
			}
		}

		for (Box[] boxes : mat)
		{
			for (Box box : boxes)
			{
				box.start();
			}
		}

		this.powerUp.start();
		this.racket.start();
		this.bomb.start();
		this.ball.start();

		this.addMouseMotionListener(new MML());
		this.setBackground(Color.WHITE);
	}
	/* ------------------------------------------------------------------
	 * This function draws all the components on the screen
 	 * Input:  software graphics
 	 * Output: None
	/* ------------------------------------------------------------------ */
	public void paintComponent(Graphics g)
	{
	    super.paintComponent(g);

	    this.racket.drawRacket(g);
	    this.powerUp.draw(g);
		this.ball.draw(g);
		this.bomb.draw(g);

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
	}
	/* ------------------------------------------------------------------
	 * This function increments the score
	 * Input:  None
	 * Output: None
	/* ------------------------------------------------------------------ */
	public void incScore()
	{
		this.score++;
		this.scoreGUI.setText("Score: " + this.score);
	}
	/* ------------------------------------------------------------------
	 * This is the main function of the code
	 * Input:  None
	 * Output: None
	/* ------------------------------------------------------------------ */
	public static void main(String[] args) 
	{
		JFrame f = new JFrame("Whiskey Breaker Beta MS(c)");
		BallsAndBoxesPanel bbp = new BallsAndBoxesPanel();

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(bbp.scoreGUI, BorderLayout.NORTH);
		f.add(bbp, BorderLayout.CENTER);

		f.setSize(600,730);
		f.setResizable(false);
		f.setVisible(true);

		bbp.scoreGUI.setFont(new Font("Arial", Font.ITALIC, 24));
	}
	// -----------------------------------------------------------------------------
	// *****************************************************************************
	// -----------------------------------------------------------------------------
	class MML extends MouseMotionAdapter
	{
		public void mouseMoved(MouseEvent e)
		{
			if( e.getX()<getWidth() - racket.w)
			{
				racket.x = e.getX();
			}
		}
	}
}
