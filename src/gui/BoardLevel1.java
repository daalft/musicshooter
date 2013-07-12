package gui;

	import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import javax.swing.Timer;

	import javax.swing.JPanel;

	import elements.Missile;
import elements.Vehicle;
import elements.VisualNote;
	
public class BoardLevel1 extends JPanel implements ActionListener{
	

		/**
		 * 
		 */
		private static final long serialVersionUID = -1488781000182464770L;
		private Vehicle craft;
		private Timer timer;
		protected int[][] pos = {
				{25,25},{175,50},{250,25},{380,75},{600,10}
		};
		protected String[] noteValue = {"c", "e", "f", "h", "d"};
		protected int[] octave = {5,5,5,5,5};
		private boolean ingame;
		private ArrayList<VisualNote> notes;
		private Synthesizer synth;
		private MidiChannel [] chan ;
		private int lives;
		//private File midiFile = new File("./sound/gameover.mid");
		public BoardLevel1 () throws IOException, MidiUnavailableException {
			addKeyListener(new TAdapter());
			setFocusable(true);
			setBackground(Color.BLACK);
			setDoubleBuffered(true);
			ingame = true;
			lives = 3;
			craft = new Vehicle();
			initAliens();
			timer = new Timer(5, this);
			timer.start();
			synth = MidiSystem.getSynthesizer();
			synth.open();
		}

		private void initAliens() throws IOException {
			notes = new ArrayList<VisualNote>();
			for (int i = 0; i < pos.length; i++) {
				notes.add(new VisualNote(noteValue[i%7], octave[i], pos[i][0], pos[i][1], 700));
			}
		}
		public void paint(Graphics g) {
			super.paint(g);
			if (ingame) {
				Graphics2D g2d = (Graphics2D)g;
				g2d.drawImage(craft.getImage(), craft.getX(), craft.getY(), this);
				ArrayList<Missile> ms = craft.getMissiles();

				for (int i = 0; i < ms.size(); i++ ) {
					Missile m = ms.get(i);
					g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
				}
				for (int i = 0; i < notes.size(); i++) {
					VisualNote a = notes.get(i);
					if (a.isVisible())
						g2d.drawImage(a.getImage(), a.getX(), a.getY(), this);
				}
				
					g2d.drawString("Lives: " + lives, 5, 15);
				
				
			} else {
				String msg = "Game Over";
				Font small = new Font("Helvetica", Font.BOLD, 14);
				FontMetrics metr = this.getFontMetrics(small);
				g.setColor(Color.white);
				g.setFont(small);
				g.drawString(msg, (getWidth() - metr.stringWidth(msg)) / 2, getHeight() / 2);
				synth.close();
				
			}
			Toolkit.getDefaultToolkit().sync();
			g.dispose();
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (notes.size() == 0) {
				// next lvl
				
			}
			if (lives < 1)
				ingame=false;
			ArrayList <Missile> ms = craft.getMissiles();

			for (int i = 0; i < ms.size(); i++) {
				Missile m = ms.get(i);
				if (m.isVisible()) 
					m.move();
				else ms.remove(i);
			}
			for (int i = 0; i < notes.size(); i++) {
				VisualNote a = notes.get(i);
				if (a.isVisible()) 
					a.move();
				else notes.remove(i);
			}

			craft.move();
			try {
				checkCollisions();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			repaint();  
		}
		public void addNotify() {
	        super.addNotify();
	        
	    }
		private int lastHit;
		private void checkCollisions() throws IOException {
			// TODO Auto-generated method stub
		        ArrayList<Missile> ms = craft.getMissiles();

		        for (int i = 0; i < ms.size(); i++) {
		            Missile m = ms.get(i);

		            Rectangle r1 = m.getBounds();

		            for (int j = 0; j<notes.size(); j++) {
		                VisualNote a = notes.get(j);
		                Rectangle r2 = a.getBounds();

		                if (r1.intersects(r2)) {
		                	if (a.getSound() < lastHit) {
		                		lives--;
		                		Thread t = new SoundMaker(new VisualNote("c", 2, 0, 0, 0), 1000);
		                		t.start();
		                	} else {
		                	
		                   // TODO make a sound!
		                   Thread t = new SoundMaker(a, 200);
		                   t.start();
		                   
		                	}
		                	m.setVisible(false);
		                	a.setVisible(false);
			                   lastHit = a.getSound();
		                }
		            }
		        }
		}
		private class TAdapter extends KeyAdapter {

			public void keyReleased(KeyEvent e) {
				craft.keyReleased(e);
				
			}

			public void keyPressed(KeyEvent e) {
				try {
					craft.keyPressed(e);
				
				
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}


		
		class SoundMaker extends Thread {
			private VisualNote note;
			private final static int VELOCITY = 93;
			private int timeout;
			public SoundMaker(VisualNote n, int t) {
				note = n;
				timeout = t;
			}
			
			public void run () {
				
		        chan = synth.getChannels();
				chan[0].noteOn(note.getSound(), VELOCITY);
				try {
					Thread.sleep(timeout);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				chan[0].noteOff(note.getSound());
				
			
			}
		}
	

}
