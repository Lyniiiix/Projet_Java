package objets;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class Bulles {
	private float x, y;
	private int diametre;
	private float vy;
	
	
	
	public float getY() {
		return y;
	}


	public Bulles(float x) {
		diametre = 40;
		vy = 10;
		y = 0;
		if(x>=0 && x<=1024) {
			this.x = x;
		}
	}
	
	public void dessiner(Graphics g) {
		g.drawOval(x, y, diametre, diametre);
	}
	
	public void deplacer(int delta) {
		y+=vy*delta/100f;
	}
	
	public boolean testCollision(GameContainer gc) {
		if(this.y >= 580 && this.y <= 640) {
			return true;
		}
		else {
			return false;
		}
	}
	
}

