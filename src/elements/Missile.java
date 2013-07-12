package elements;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Missile {

	private final static String path = "./graphics/shot.png";
	private Image image;
	private int x, y, speed, width, height;
	private boolean visible;
	
	public Missile (int x, int y, int sp) throws IOException {
		image = ImageIO.read(new File(path));
		this.x = x;
		this.y = y;
		speed = sp;
		visible = true;
		width = image.getWidth(null);
		height = image.getHeight(null);
	}
	
	public boolean isVisible () {
		return visible;
	}
	
	public void move () {
		y -= speed;
		if (y < 0)
			visible = false;
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

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		visible = b;
	}

	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x,y,width,height);
	}
}
