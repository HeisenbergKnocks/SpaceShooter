import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Vector;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.image.*;
import java.awt.geom.AffineTransform;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

public class Driver extends JPanel implements ActionListener, KeyListener,
		MouseListener, MouseMotionListener {
	public static int life = 3;

	int screen_width = 600;
	int screen_height = 900;

	String src = new File("").getAbsolutePath() + "/src/"; // path to image
	Clip hop;
	Ship s = new Ship("GoodSpaceship.png");
	EnemyShip es = new EnemyShip("EnemyShip.png");
	Background b1 = new Background("Background1.gif", 0, 0, 1);
	Background b2 = new Background("Background2.gif", 0, 900, 1);
	
	// clip.open(audioInputStream);
	Sequencer sequencer;
	// Background bg;
	int my_variable = 0; // example

	String lost = "";
	
	//example use of new class derived from the Ship class
	Projectile proj = new Projectile("Fireball.png", 0, 0, 0, -5);
	
	int x_leave;
	int y_leave;
	int x_enter;
	int y_enter;
	
	public void paint(Graphics g) {

		super.paintComponent(g);



		g.setFont(font);
		g.setColor(Color.RED);
		// g.drawString(("my_variable:") + Integer.toString(my_variable), 0,
		// 870);
		g.setFont(font2);
		g.setColor(Color.CYAN);

		b1.paint(g);
		b2.paint(g);
		s.paint(g);
		es.paint(g);
		
		// g.drawString(life+"", 400, 400);

	}

	Font font = new Font("Courier New", 1, 50);
	Font font2 = new Font("Courier New", 1, 30);

	//
	public void update() {
		
		b1.setY(b1.getY() + b1.getVy());
		if(b1.getY() >= 900){
			b1.setY(b1.getY() - 1800);
		}
		
		b2.setY(b2.getY() + b2.getVy());
		if(b2.getY() >= 900){
			b2.setY(b2.getY() - 1800);
		}
		
		b1.updateBackground();
		b2.updateBackground();
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		update();
		repaint();
	}

	public static void main(String[] arg) {
		Driver d = new Driver();
	}

	public Driver() {

		JFrame f = new JFrame();
		f.setTitle("Space Fighter");
		f.setSize(screen_width, screen_height);
		f.setResizable(false);
		f.addKeyListener(this);
		f.addMouseListener(this);
		f.addMouseMotionListener(this);
		
	
		// Obtains the default Sequencer connected to a default device.
		try {
			sequencer = MidiSystem.getSequencer();
			// Opens the device, indicating that it should now acquire any
			// system resources it requires and become operational.
			sequencer.open();

			// create a stream from a file
			
			InputStream is = new BufferedInputStream(new FileInputStream(
					new File("Thelazysong.mid").getAbsoluteFile()));

			// Sets the current sequence on which the sequencer operates.
			// The stream must point to MIDI file data.
			sequencer.setSequence(is);

			// Starts playback of the MIDI data in the currently loaded
			// sequence.
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// player.addMouseListener(this);
		// bg = new Background("background.png");
		// do not add to frame, call paint on
		// these objects in paint method

		f.add(this);
		t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	Timer t;

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getKeyCode());

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}	

	public void reset() {

	}

	boolean on = false;
	
	@Override
	public void mousePressed(MouseEvent e) {
		//shoot a projectile on mouse press
		//invoke the shoot() method that we wrote!
		s.fire();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getX() + ":" + arg0.getY());
		
		if(Math.abs(arg0.getX() - s.getX()) <= 175 && Math.abs(arg0.getY() - s.getY()) <= 175){
			s.setX(arg0.getX() - 50);
			s.setY(arg0.getY() - 75);
		}
		
	}
  
}
