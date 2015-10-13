package arcada.race;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.List;

import javax.swing.*;

public class Sky extends JPanel implements ActionListener, Runnable {
	
	private static final long serialVersionUID = 1L; //technical line... added by eclipse 

	Timer mainTimer = new Timer(20, this);
	
	Image image = new ImageIcon("res/Sky.jpg").getImage();
		
	User player = new User();
	
	Thread enemiesFactory = new Thread(this);
	List<Enemy> enemies = new ArrayList<Enemy>();
	
	public Sky(){
		mainTimer.start();
		enemiesFactory.start();
		addKeyListener(new myKeyAdapter()); //listen to pressed buttons
		setFocusable(true);					//
	}
	
	private class myKeyAdapter extends KeyAdapter{
		public void keyPressed(KeyEvent e){ 
			player.keyPressed(e);
		}
		
		public void keyReleased(KeyEvent e){
			player.keyReleased(e);
		}
	}
	
	public void paint(Graphics g){
		g = (Graphics2D) g;
		g.drawImage(image, player.layer1, -25, null);
		g.drawImage(image, player.layer2, -25, null);
		
	
		g.drawImage(player.image, player.x, player.y, null);
		
		double speed = 800 + (2000/User.MAX_SP) * player.sp ;	// speed calculation
		g.setColor(Color.BLUE);
		Font font = new Font("Arial",Font.ITALIC, 20);
		g.setFont(font);
		g.drawString("Speed: " + speed + " km/ph", 10, 20);
		
		Iterator<Enemy> it = enemies.iterator(); 
		while(it.hasNext()){
			Enemy enemy = it.next();
			if(enemy.x >= 640 || enemy.x <= -640){
				it.remove(); 	//delete enemy if it out of the map
			} else {
				enemy.move();
				g.drawImage(enemy.image, enemy.x, enemy.y, null);
			}
			
			if(player.getRect().intersects(enemy.getRect())){		// badaboom
				g.drawImage(player.boom, player.x + 50, player.y - 150, null);
			}
		}
		
	}

	public void actionPerformed(ActionEvent e) {
		player.move();		// "move" user
		repaint();			// repaint background
		
		testCollisionWithEnemies();
	}
	
	private void testCollisionWithEnemies(){
		Iterator<Enemy> it = enemies.iterator(); 
		while(it.hasNext()){
			Enemy enemy = it.next();
			if(player.getRect().intersects(enemy.getRect())){
				//it.remove();					// delete enemy after contact
				JOptionPane.showMessageDialog(null, "You lost!");
				System.exit(1);
			}
		}
	}

	public void run() {
		while(true){
			Random random = new Random();
			try {
				Thread.sleep(random.nextInt(2000));
				enemies.add(new Enemy(630, 
									  random.nextInt(300), 
									  random.nextInt(60), 
									  this));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}	
}
