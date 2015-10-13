package arcada.race;

import java.awt.*;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class User {
	
	public static final int MAX_SP = 80;		// max speed
	public static final int MIN_SP = 2;			// min speed
	public static final int MAX_TOP = -8;
	public static final int MAX_BOT = 280;
	
	//Image image_standart = new ImageIcon(getClass().getClassLoader().getResource("res/User.png")).getImage();
	Image image_standart = new ImageIcon("res/User.png").getImage();
	Image image_left = new ImageIcon("res/User_left.png").getImage();
	Image image_right = new ImageIcon("res/User_right.png").getImage();
	
	Image boom = new ImageIcon("res/destroy.png").getImage();
	
	Image image = image_standart;
	
	int sp = 0;			// jet speed
	int dSp = 0;		// speed delta
	int dist = 0;   	// full distance
	
	static final int VERT = 10;
	static final int INCR_SPEED = 3;
	
	
	int x = 0;			// user basic x
	int y = 130;		// user basic y
	int dY = 0;
	
	int layer1 = 0;		
	int layer2 = 940;
	
	public Rectangle getRect(){
		return new Rectangle(x+40, y+40, 160, 50);
	}
	
	public void move(){
		dist += sp;
		sp += dSp;
		if(sp <= MIN_SP) 
			sp = MIN_SP;
		if(sp >= MAX_SP) 
			sp = MAX_SP;
		y -= dY;
		if(y <= MAX_TOP) 
			y = MAX_TOP;
		if(y >= MAX_BOT) 
			y = MAX_BOT;
		
		if (layer2 - sp <= 0){ //moving sky
		    layer1 = 0;
		    layer2 = 940;
		} else {
			layer1 -= sp;
		    layer2 -= sp;
		}
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_RIGHT){
			dSp = INCR_SPEED;
		} 
		if(key == KeyEvent.VK_LEFT){
			if(sp - dSp > 0){
				dSp = -INCR_SPEED;
			} else{
				sp = 2;
				dSp = 0;
			}
		}
		if(key == KeyEvent.VK_UP){		//fly up
			dY = VERT;
			image = image_left;
		} 
		if(key == KeyEvent.VK_DOWN){ 	//fly down
			dY = -VERT;
			image = image_right;
		} 
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_LEFT ||key == KeyEvent.VK_RIGHT){
			dSp = 0;
			image = image_standart;
		} 
		if(key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN){
			dY = 0;
			image = image_standart;
		} 
	}
}
