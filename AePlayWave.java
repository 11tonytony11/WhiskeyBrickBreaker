import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import java.io.IOException;
import java.io.File;


public class AePlayWave extends Thread
{

	private final int EXTERNAL_BUFFER_SIZE = 524288; // 128Kb
	enum Position { LEFT, RIGHT, NORMAL };
	private Position curPosition;
	private String filename;

	/* ------------------------------------------------------------------
 	 * This function is the Player c'tor
 	 * Input:  path to .wav file
 	 * Output: None
	/* ------------------------------------------------------------------ */
	public AePlayWave(String wavfile)
	{
		filename = wavfile;
		curPosition = Position.NORMAL;
	}
	/* ------------------------------------------------------------------
     * This function runs the audio file
 	 * Input:  None
 	 * Output: Plays the sound
	/* ------------------------------------------------------------------ */
	public void run()
	{
		File soundFile = new File(filename);
		AudioInputStream audioInputStream;
		SourceDataLine auline;
		
		if (!soundFile.exists())
		{
			System.err.println("Wave file not found: " + filename);
			return;
		}
		
		try
		{
			audioInputStream = AudioSystem.getAudioInputStream(soundFile);
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioInputStream.getFormat());
			auline = (SourceDataLine) AudioSystem.getLine(info);
			auline.open(audioInputStream.getFormat());
		}
		catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1)
		{
			e1.printStackTrace();
			return;
		}
		
		if (auline.isControlSupported(FloatControl.Type.PAN))
		{
			FloatControl pan = (FloatControl) auline.getControl(FloatControl.Type.PAN);
			if (curPosition == Position.RIGHT)
				pan.setValue(1.0f);
			else if (curPosition == Position.LEFT)
				pan.setValue(-1.0f);
		} 

		auline.start();
		int nBytesRead = 0;
		byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];

		try 
		{
			while (nBytesRead != -1) 
			{
				nBytesRead = audioInputStream.read(abData, 0, abData.length);
				if (nBytesRead >= 0)
					auline.write(abData, 0, nBytesRead);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally 
		{
			auline.drain();
			auline.close();
		}
	}
}
