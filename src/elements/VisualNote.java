package elements;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class VisualNote {

	private final static String path = "./graphics/note-";
	private Image image;
	private int x,y, width, height, sound, speed, respawn_pos;
	private boolean visible;
	private String name;
	public VisualNote(String name, int oct, int x, int y, int respawn) throws IOException {
		image = ImageIO.read(new File(path + name + ".png"));
		this.x = x;
		this.y = y;
		this.name = name;
		visible = true;
		width = image.getWidth(null);
		height = image.getHeight(null);
		sound = setSound(name, oct);
		speed = 1;
		respawn_pos = respawn;
	}
	
	public String getName () {
		return name;
	}
	
	public int setSound (String s, int octave) {
		char c = s.charAt(0);
		int octa = octave * 12;
		switch(c) {
		case 'c':
		case 'd':
		case 'e':return octa + 2*((int)c-99);
		case 'f':return octa + 5;
		case 'g':return octa + 7;
		case 'a':return octa + 9;
		case 'h':return octa + 11;
		}
		return 0;
	}
	
	public int getSound () {
		return sound;
	}
	
	public Image getImage () {
		return image;
	}
	
	public int getX () {
		return x;
	}
	
	public int getY () {
		return y;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public void move () {
		if (x < 0) 
            x = respawn_pos;
        x -= speed;
	}

	public int getSpeed () {
		return speed;
	}

	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x,y,width,height);
	}
	
	public void keyPressed (KeyEvent e) throws IOException {
		int i = e.getKeyCode();
		switch (i) {
		case KeyEvent.VK_A:
			if (speed < 3)
				speed++;
			break;
		case KeyEvent.VK_S:
			if (speed > 0)
			speed--; 
			break;
				default: break;
		}
		
	}
	
	public void keyReleased (KeyEvent e) {
		int i = e.getKeyCode();
		switch (i) {
	
		case KeyEvent.VK_A: break;
		case KeyEvent.VK_S: break;
	
			default: break;
		}
	}
}
