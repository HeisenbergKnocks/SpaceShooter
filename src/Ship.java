import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

/* class to represent a Ship object in a game */
public class Ship {

	private int x, y; 		
	private boolean alive; 	
	private int width, height;
	private Image img; 		
	private int vx, vy; 	
	private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);

	//array of projectiles for ship
	Projectile[] projectiles = new Projectile[10];
		
	//constructor
	public Ship(String fileName) {
		x = 200;
		y = 200;
		vx = 0;
		vy = 0;
		width = 172;
		height = 78;
		
		//do not touch
		img = getImage(fileName);
		updateShip();
	}
	
	//shoot method that will active a Projectile in the array
	public void fire(){
		
		for(int i = 0; i < projectiles.length; i++){
			if(!projectiles[i].active){
				projectiles[i].active = true; //turn this one
				
				//move projectile to location of ship
				projectiles[i].setX(x);
				projectiles[i].setY(y);
				
				//exit immediately after activating one projectile
				break; //break will exit the nearest loop
			}
		}
	}
	
	
	//2nd constructor allows placement (location) of the object
	public Ship(String fileName, int paramX, int paramY){
		x = paramX;
		y = paramY;
		vx = 0;
		vy = 0;
		
		//helper functions to handle img drawing
		img = getImage(fileName);
		updateShip();
		
		//add projectile objects in the array of Projectiles
		for(int i = 0; i < projectiles.length; i++){
			projectiles[i] = new Projectile("GoodSpaceship.png", x, y, 0, -5);
		}
		
		//helper function for location of image
		//if you update x and y, call updateShip
		updateShip();
		
	}
	
	
	public void setVx(int vx) { //check that this is good!!!!!!!!!!!!!
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
		
		//draw active projectiles for ship
		for(int i = 0; i < projectiles.length; i++){
			if(projectiles[i] != null && projectiles[i].active){
				projectiles[i].paint(g);
			}
		}
		
		
	}

	private void updateShip() {
		tx.setToTranslation(x, y);
	}

	// converts image to make it drawable in paint
	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Ship.class.getResource(path);
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
		
		updateShip();
	}

	public void setY(int y) {
		this.y = y;
		
		updateShip();
	}

	

}
