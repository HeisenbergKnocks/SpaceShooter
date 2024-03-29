import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

/* class to represent a Background object in a game */
public class Background {

	private int x, y; 		
	private boolean alive; 	
	private int width, height;
	private Image img; 		
	private int vx, vy; 	
	private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);

	//constructor
	public Background(String fileName) {
		x = 200;
		y = 200;
		vx = 0;
		vy = 0;
		width = 172;
		height = 78;
		
		//do not touch
		img = getImage(fileName);
		updateBackground();
	}

	//2nd constructor allows placement (location) of the object
	public Background(String fileName, int paramX, int paramY, int paramVy){
		x = paramX;
		y = paramY;
		vx = 0;
		vy = paramVy;
		
		//helper functions to handle img drawing
		img = getImage(fileName);
		updateBackground();
		
		//helper function for location of image
		//if you update x and y, call updateBackground
		updateBackground();
		
	}
	
	
	public void setVx(int vx) { 
		vy = 0;
		this.vx = vx;
	}

	public void setVy(int vy) {
		vx = 0;
		this.vy = vy;
	}

	// draw the affinetransform
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, tx, null);
	}

	void updateBackground() {
		tx.setToTranslation(x, y);
	}

	// converts image to make it drawable in paint
	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Background.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
		
		updateBackground();
	}

	public void setY(int y) {
		this.y = y;
		
		updateBackground();
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public AffineTransform getTx() {
		return tx;
	}

	public void setTx(AffineTransform tx) {
		this.tx = tx;
	}

	public int getY() {
		return y;
	}

	public int getVx() {
		return vx;
	}

	public int getVy() {
		return vy;
	}
	
	
	

}
