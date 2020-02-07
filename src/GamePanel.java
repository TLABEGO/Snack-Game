import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 500, HEIGHT = 500;
	
	private Thread thread;
	
	private boolean running;
	
	private boolean right = true, left = false, up = false, down = false;
	
	private BodyPart b;
	private ArrayList<BodyPart> snack;
	
	private Apple apple;
	private ArrayList<Apple> apples;
	
	private Random r;
	
	private int xCoor = 10, yCoor = 10, size = 20;
	
	private int ticks = 0;
	

	public GamePanel() {
		
		setFocusable(true);
		addKeyListener(this);

		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		snack = new ArrayList<BodyPart>();
		
		apples = new ArrayList<Apple>();
		
		r = new Random();
		
		start(); 

	}

	public void start() {
		running = true;
		thread = new Thread(this);
		thread.start();

	}

	public void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void tick() {
		if(snack.size() == 0) {
			b = new BodyPart(xCoor, yCoor, 10);
			snack.add(b);
		}
		ticks++;
		
		if(ticks > 250000) {
			if(right) xCoor++;
			if(left) xCoor--;
			if(up) yCoor--;
			if(down) yCoor++;
			
			ticks = 0;
			b = new BodyPart(xCoor, yCoor, 10);
			snack.add(b);
			
			if(snack.size() > size) {
				snack.remove(0);
			}
			
		}
		
		if(apples.size() == 0) {
			int xCoor = r.nextInt(39);
			int yCoor = r.nextInt(39);
			
			apple = new Apple(xCoor, yCoor, 10);
			apples.add(apple);
			
		}

		for(int i = 0; i < apples.size(); i++) {
			if(xCoor == apples.get(i).getxCoor() && yCoor == apples.get(i).getyCoor()) {
				size++;
				apples.remove(i);
				 i++;
			}
		}
			//Collision on snack BodyPart
		for(int i = 0; i < snack.size(); i++) {
			if(xCoor == snack.get(i).getxCoor() && yCoor == snack.get(i).getyCoor()) {
				if(i != snack.size() - 1) {
					System.out.println("GAME OVER");
					stop();
				}
			}
		}
		
		       //Collision on border
		
		if(xCoor < 0 || xCoor > 49 || yCoor < 0 || yCoor > 49) {
			
			System.out.println("GAME OVER");
			stop();
			
		}
	}

	public void paint(Graphics g) {
		g.clearRect(0, 0, WIDTH, HEIGHT);
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		for (int i = 0; i < WIDTH / 10; i++) {
			g.drawLine(1 * 10, 0, 1 * 10, HEIGHT);
		}

		for (int i = 0; i < HEIGHT / 10; i++) {
			g.drawLine(0, 1 * 10, WIDTH, 1 * 10);
		}
		
		for( int i = 0; i < snack.size(); i++) {
			snack.get(i).draw(g);
			
		}
		
		for(int i = 0; i < apples.size(); i++) {
			apples.get(i).draw(g);
		}
	}

	@Override
	public void run() {
		while(running) {
			tick();
			repaint();
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_RIGHT && !left ) {
			right = true;
			up = false;
			down = false;
		}
		
		if(key == KeyEvent.VK_LEFT && !right ) {
			left = true;
			up = false;
			down = false;
		}
		
		if(key == KeyEvent.VK_UP && !down ) {
			up = true;
			left = false;
			right = false;
		}
		
		if(key == KeyEvent.VK_DOWN && !up ) {
			down = true;
			left = false;
			right = false;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
