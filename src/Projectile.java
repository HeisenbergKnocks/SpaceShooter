import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

/* class to represent a Projectile object in a game */
public class Projectile {

	public boolean active = false;
	private int x, y; 		
	private boolean alive; 	
	private int width, height;
	private Image img; 		
	private int vx, vy; 	
	private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);
	
	//constructor
	public Projectile(String fileName) {
		x = 200;
		y = 200;
		vx = 0;
		vy = 0;
		width = 172;
		height = 78;
		
		//do not touch
		img = getImage(fileName);
		updateProjectile();
	}

	//2nd constructor - allows placement (location) of the object
	public Projectile(String fileName, int paramX, int paramY, int paramVx, int paramVy){
		x = paramX;
		y = paramY;
		vx = paramVx;
		vy = paramVy;
		
		//helper functions to handle img drawing
		img = getImage(fileName);
		updateProjectile();
		
		//helper function for location of image
		//if you update x and y, call updateProjectile
		updateProjectile();
		
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
		y += vy;
		updateProjectile();
	}

	private void updateProjectile() {
		tx.setToTranslation(x, y);
	}

	// converts image to make it drawable in paint
	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Projectile.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
		
		updateProjectile();
	}

	public void setY(int y) {
		this.y = y;
		
		updateProjectile();
	}

	

}
