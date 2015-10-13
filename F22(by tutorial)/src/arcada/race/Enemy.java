package arcada.race;

import java.awt.*;

import javax.swing.*;

public class Enemy {

	int x;
	int y;
	int sp;
	Image image = new ImageIcon("res/Enemy.png").getImage();
	Sky sky;
	
	public Rectangle getRect(){
		return new Rectangle(x+20, y+20, 200, 70);
	}
	
	public Enemy(int x, int y, int sp, Sky sky){
		this.x = x;
		this.y = y;
		this.sp = sp;
		this.sky = sky;
	}
	
	public void move(){
		x = x - sky.player.sp + sp;
	}

}
