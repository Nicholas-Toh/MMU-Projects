import javax.swing.SwingUtilities;

public class PerilandaDriver
{
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				PlayingFrame playingFrame = new PlayingFrame();
				MainFrame mainFrame = new MainFrame();
				Controller controller = new Controller(mainFrame, playingFrame);
			}
		});
	}
}


	