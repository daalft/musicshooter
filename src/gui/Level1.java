package gui;

import java.io.IOException;

import javax.sound.midi.MidiUnavailableException;

public class Level1 extends BoardLevel1 {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public Level1() throws IOException, MidiUnavailableException {
		super();
		noteValue = new String[4];
		octave = new int[4];
		pos = new int[4][2];
		pos[0][0] = 25;
		pos[0][1] = 25;
		pos[1][0] = 75;
		pos[1][1] = 50;
		pos[2][0] = 25;
		pos[2][1] = 75;
		pos[3][0] = 100;
		pos[3][1] = 25;
		}
	
		
	}

	

