import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Updater implements ActionListener
{
    BallsAndBoxesPanel panel;
    /* ------------------------------------------------------------------
     * This function is the Updater c'tor
     * Input:  game panel
     * Output: None
    /* ------------------------------------------------------------------ */
    public Updater(BallsAndBoxesPanel newPanel)
    {
        this.panel = newPanel;
    }
    /* ------------------------------------------------------------------
     * This function is handling the repainting when timer is calling the event
     * Input:  e - event
     * Output: None
    /* ------------------------------------------------------------------ */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        panel.repaint();
    }
}
