import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Updater implements ActionListener
{
    BallsAndBoxesPanel panel;

    public Updater(BallsAndBoxesPanel newPanel)
    {
        this.panel = newPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        panel.repaint();
    }
}
