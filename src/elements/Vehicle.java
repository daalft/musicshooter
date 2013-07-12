package elements;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Vehicle {

	private final static String path = "./graphics/ship.png";
	private Image image;
	private int x, y, dx, dy, width, height;
	private boolean visible;
	private ArrayList<Missile> missles;
	private boolean key;
	private final static int CRAFT_SIZE = 50, BOARD_SIZE_X = 750;
	private static final int BOARD_SIZE_Y = 700;
	
	public Vehicle () throws IOException {
		image = ImageIO.read(new File(path));
		x = 150;
		y = 450;
		width = image.getWidth(null);
		height = image.getHeight(null);
		missles = new ArrayList<Missile>();
	}
	
	public void move () {
		x += dx;
		y += dy;
		if (y < 1)
			y = 1;
		if (x < 1)
			x = 1;
		if (y > BOARD_SIZE_Y-CRAFT_SIZE)
			y = BOARD_SIZE_Y-CRAFT_SIZE;
		if (x > BOARD_SIZE_X-CRAFT_SIZE)
			x = BOARD_SIZE_X-CRAFT_SIZE;
		
	}
	
	public boolean isVisible () {
		return visible;
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
	
	public void setVisible (boolean v) {
		visible = v;
	}
	
	public ArrayList<Missile> getMissiles () {
		return missles;
	}
	
	public void keyPressed (KeyEvent e) throws IOException {
		int i = e.getKeyCode();
		switch (i) {
		case KeyEvent.VK_LEFT: dx = -1; break;
		case KeyEvent.VK_RIGHT: dx = 1; break;
		case KeyEvent.VK_UP: dy = -1; break;
		case KeyEvent.VK_DOWN: dy = 1; break;
		case KeyEvent.VK_SPACE:
			if (!key)
				fire();
			key = true;
			break;
			default: break;
		}
	}
	
	public void keyReleased (KeyEvent e) {
		int i = e.getKeyCode();
		switch (i) {
		case KeyEvent.VK_LEFT: dx = 0; break;
		case KeyEvent.VK_RIGHT: dx = 0; break;
		case KeyEvent.VK_UP: dy = 0; break;
		case KeyEvent.VK_DOWN: dy = 0; break;
		case KeyEvent.VK_SPACE: key = false;
			default: break;
		}
	}
	
	public void fire() throws IOException {
		missles.add(new Missile(x+CRAFT_SIZE/2, y, 3));
		
	}
	
	public Rectangle getBounds () {
		return new Rectangle(x,y,width,height);
	}
}
