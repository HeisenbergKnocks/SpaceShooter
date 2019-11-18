import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

/* class to represent a EnemyShip object in a game */
public class EnemyShip {

	private int x, y; 		
	private boolean alive; 	
	private int width, height;
	private Image img; 		
	private int vx, vy; 	
	private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);
	private int counter = 0, rate = 50;
	
	//array of projectiles for EnemyShip
	Projectile[] projectiles = new Projectile[10];
		
	//constructor
	public EnemyShip(String fileName) {
		x = 200;
		y = 200;
		vx = 0;
		vy = 0;
		width = 172;
		height = 78;
		
		//add projectile objects in the array of Projectiles
		for(int i = 0; i < projectiles.length; i++){
			projectiles[i] = new Projectile("GoodSpaceship.png", x, y, 0, 5);
		}
		
		//do not touch
		img = getImage(fileName);
		updateEnemyShip();
	}
	
	//shoot method that will active a Projectile in the array
	public void fire(){
		
		for(int i = 0; i < projectiles.length; i++){
			if(!projectiles[i].active){
				projectiles[i].active = true; //turn this one
				
				//move projectile to location of EnemyShip
				projectiles[i].setX(x);
				projectiles[i].setY(y);
				
				//exit immediately after activating one projectile
				break; //break will exit the nearest loop
			}
		}
	}
	
	
	//2nd constructor allows placement (location) of the object
	public EnemyShip(String fileName, int paramX, int paramY){
		x = paramX;
		y = paramY;
		vx = 0;
		vy = 0;
		
		//helper functions to handle img drawing
		img = getImage(fileName);
		updateEnemyShip();
		
		//add projectile objects in the array of Projectiles
		for(int i = 0; i < projectiles.length; i++){
			projectiles[i] = new Projectile("GoodSpaceship.png", x, y, 0, 5);
		}
		
		//helper function for location of image
		//if you update x and y, call updateEnemyShip 
		updateEnemyShip();
		
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
		
		//update counter
		counter++;
		if(counter % rate == 0){ //fire every time counter is divisible by rate
			fire();
			
		}
		
		//draw active projectiles for EnemyShip
		for(int i = 0; i < projectiles.length; i++){
			if(projectiles[i] != null && projectiles[i].active){
				projectiles[i].paint(g);
			}
		}
		
		
	}

	private void updateEnemyShip() {
		tx.setToTranslation(x, y);
	}

	// converts image to make it drawable in paint
	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = EnemyShip.class.getResource(path);
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
		
		updateEnemyShip();
	}

	public void setY(int y) {
		this.y = y;
		
		updateEnemyShip();
	}

	

}

