package gui;

import java.io.IOException;

import javax.sound.midi.MidiUnavailableException;
import javax.swing.JFrame;

public class Game extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6708958011929656475L;

	public Game () throws IOException, MidiUnavailableException {
		add(new InfiniteBoard());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(750,550);
		setLocationRelativeTo(null);
		setTitle("Music Shooter");
	
		setVisible(true);
	}
	
	public static void main(String[] args) {
		try {
			new Game();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
